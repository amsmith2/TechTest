package com.techtest.abbysmith;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class CommandInterpreterTest {

	private CommandInterpreter ci = new CommandInterpreter();

	@Test
	public void testParseJsonVehicles() throws Exception {
		List<Vehicle> vehicles = ci.parseJsonVehicles("http://www.rentalcars.com/js/vehicles.json");
		assertEquals(vehicles.size(),31);
	}

	@Test
	public void testSortByPrice() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for (int i =1;i<5;i++) {
			Vehicle v = new Vehicle();
			v.name = "Ford "+i;
			v.price = i*100.0;
			vehicles.add(v);
		}

		Collections.shuffle(vehicles);
		String actualResult = ci.sortByPrice(vehicles);

		assertEquals("1.\tFord 1 - 100.0",actualResult.split("\\r?\\n")[0]);
		assertEquals("2.\tFord 2 - 200.0",actualResult.split("\\r?\\n")[1]);
		assertEquals("3.\tFord 3 - 300.0",actualResult.split("\\r?\\n")[2]);
		assertEquals("4.\tFord 4 - 400.0",actualResult.split("\\r?\\n")[3]);
	}

	@Test
	public void testGetSpecs() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle v = new Vehicle();
		v.name = "Vauxhall Corsa";
		v.sipp = "ECMR";
		v.rating = 7.8;
		v.setUp();
		vehicles.add(v);

		String actualResult = ci.getSpecs(vehicles);

		String expectedResult = "1.\tVauxhall Corsa - ECMR - Economy - 4 doors - Manual - Petrol - AC\n";
		assertEquals(expectedResult,actualResult);
	}

	@Test
	public void testSortHighestSupplier() {
		//Set up vehicles to test
		String[] suppliers = {"Hertz", "Sixt", "Europcar", "Alamo"};
		String[] cartypes = {"Mini", "Economy", "Compact"};
		double[] ratings = {7.3,7.0,8.0,7.8};
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		int i = 1;
		for (String ct : cartypes) {
			int r = 0;
			for (String sup : suppliers) {
				Vehicle v = new Vehicle();
				v.name = "Vehicle "+i;
				v.carType = ct;
				v.supplier = sup;
			    v.rating = ratings[r];
				vehicles.add(v);
				i++;
				r++;
			}
		}
		//Check for each cartype, the highest rated supplier is chosen
		String actualResult = ci.sortHighestSupplier(vehicles);
		String expectedResult1 = "1.\tVehicle 11 - Compact - Europcar - 8.0";
		String expectedResult2 = "2.\tVehicle 7 - Economy - Europcar - 8.0";
		String expectedResult3 = "3.\tVehicle 3 - Mini - Europcar - 8.0";
		assertEquals(expectedResult1,actualResult.split("\\r?\\n")[0]);
		assertEquals(expectedResult2,actualResult.split("\\r?\\n")[1]);
		assertEquals(expectedResult3,actualResult.split("\\r?\\n")[2]);
	}

	@Test
	public void sortBySum() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle v1 = new Vehicle();
		v1.name = "Ford Focus";
		v1.sipp = "ECMR"; //vehicle score is 3
		v1.rating = 7.8;
		v1.setUp();
		vehicles.add(v1);
		Vehicle v2 = new Vehicle();
		v2.name = "Vauxhall Corsa";
		v2.sipp = "ECMR"; //vehicle score is 3
		v2.rating = 8.0;
		v2.setUp();
		vehicles.add(v2);
		String actualResult = ci.sortBySum(vehicles);
		String expectedResult1 = "1.\tVauxhall Corsa - 3 - 8.0 - 11.0";
		String expectedResult2 = "2.\tFord Focus - 3 - 7.8 - 10.8";
		assertEquals(expectedResult1,actualResult.split("\\r?\\n")[0]);
		assertEquals(expectedResult2,actualResult.split("\\r?\\n")[1]);
	}
}
