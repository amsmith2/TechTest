package com.techtest.abbysmith;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;

/**
* CommandInterpreter class.
*
* <P>Various methods to return different ordered lists of Vehicle objects as strings.
*
* @author Abigail Smith
*/
public class CommandInterpreter {

	/**
	 * Returns a String from the URL which can be parsed as a JSON later.
	 *
	 * @param	urlStr	the string of the URL to be read
	 * @return		the data at the URL location as a String
	 */
	private static String readUrl(String urlStr) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlStr);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read);

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}

	/**
	 * Returns a list of vehicles parsed from a JSON available at a URL.
	 *
	 * @param	urlStr	the string of the URL to be read
	 * @return	vehicles		the list of Vehicle objects defined in the JSON
	 */
	public List<Vehicle> parseJsonVehicles(String urlStr) throws Exception{
		Gson gson = new Gson();
		List<Vehicle> vehicles = new ArrayList<Vehicle>();

		String jsonStr = readUrl(urlStr);

		JSONParser parser = new JSONParser();
		JSONObject jsonObj = (JSONObject) parser.parse(jsonStr);
		JSONObject searchObj = (JSONObject) jsonObj.get("Search");
		JSONArray vehicleArr = (JSONArray) searchObj.get("VehicleList");

		for (Object v : vehicleArr) {
			Vehicle vehicle = gson.fromJson(v.toString(), Vehicle.class);
			vehicle.setUp();
			vehicles.add(vehicle);
		}
		return vehicles;
	}

	/**
	 * Returns a String which lists vehicles sorted by price in descending order.
	 *
	 * @param	vehicles		the list of Vehicle objects to be sorted
	 * @return	result		the String output of ordered Vehicle objects
	 */
	public String sortByPrice(List<Vehicle> vehicles) {
		String result = "";
		vehicles.sort((v1, v2) -> v1.price.compareTo(v2.price));
		int i = 1;
		for (Vehicle v : vehicles) {
			result += (i+".\t"+v.name+" - "+v.price+"\n");
			i++;
		}
		return result;
	}

	/**
	 * Returns a String which lists the SIPP specification of vehicles.
	 *
	 * @param	vehicles		the list of Vehicle objects to be displayed
	 * @return	result		the String output of Vehicle objects with expanded SIPP specifications
	 */
	public String getSpecs(List<Vehicle> vehicles) {
		String result = "";
		int i = 1;
		for (Vehicle v : vehicles) {
			result += (i+".\t"+v.sippToString()+"\n");
			i++;
		}
		return result;
	}

	/**
	 * Returns a String which lists the vehicles with the highest rated supplier per car type.
	 *
	 * @param	vehicles		the list of Vehicle objects to be sorted
	 * @return	result		the String output of the ordered Vehicle objects
	 */
	public String sortHighestSupplier(List<Vehicle> vehicles) {
		String result = "";

		//Sort into car types (descending alphabetically) and then sort by ratings (ascending) within the car type
		Collections.sort(vehicles, new Comparator<Vehicle>() {
		    @Override
		    public int compare(Vehicle v1, Vehicle v2) {
		    		//Sort into car types
		    		int value1 = v1.carType.compareTo(v2.carType);
		    		if (value1 == 0) {
		    			//Sort by rating within car type
		    			int value2 = v2.rating.compareTo(v1.rating);
		    			return value2;
		    		}
		    		else {
		    			return value1;
		    		}
		    }
		});

		//Only use the highest rating of each car type
		String previousType = "";
		int i = 1;
		for (Vehicle v : vehicles) {
			 String currentType = v.carType;
			 if (currentType != previousType) {
				 result += (i+".\t"+v.name+" - "+v.carType+" - "+v.supplier+" - "+v.rating+"\n");
				 i++;
			 }
			 previousType = v.carType;
		}

		return result;
	}

	/**
	 * Returns a String which lists the vehicles with the highest total score.
	 *
	 * @param	vehicles		the list of Vehicle objects to be sorted
	 * @return	result		the String output of the ordered Vehicle objects
	 */
	public String sortBySum(List<Vehicle> vehicles) {
		String result = "";
		vehicles.sort((v1, v2) -> v2.sumScore.compareTo(v1.sumScore));
		int i = 1;
		for (Vehicle v : vehicles) {
			result += (i+".\t"+v.name+" - "+v.vehicleScore+" - "+v.rating+" - "+v.sumScore+"\n");
			i++;
		}
		return result;
	}

}
