package com.techtest.abbysmith;

import java.util.List;
import java.util.Scanner;

/**
* App class.
*
* <P>Allows the user to select different options of what list of vehicles to view.
*
* @author Abigail Smith
*/

public class App {

	public static void main(String[] args) throws Exception {
		CommandInterpreter ci = new CommandInterpreter();

		//Gets the vehicles from the JSON
		List<Vehicle> vehicles = ci.parseJsonVehicles("http://www.rentalcars.com/js/vehicles.json");

		//Get user to input choice of what list to view
		Scanner input = new Scanner(System.in);
		boolean validInput = false;
		boolean quit = false;

		do {
		  System.out.println("Select an option to display: ");
		  System.out.println("1. Sort by price");
		  System.out.println("2. Show SIPP specifications");
		  System.out.println("3. Show highest rated suppliers per car type");
		  System.out.println("4. Sort by combined scores");
		  System.out.println("5. Exit");
		  System.out.print("> ");
		  int option = input.nextInt();
		  if(option > 0 && option < 6) {
			  validInput = true;
			  switch(option) {
			  	case 1:
			  		System.out.println(ci.sortByPrice(vehicles));
			  		break;
			  	case 2:
			  		System.out.println(ci.getSpecs(vehicles));
			  		break;
			  	case 3:
			  		System.out.println(ci.sortHighestSupplier(vehicles));
			  		break;
			  	case 4:
			  		System.out.println(ci.sortBySum(vehicles));
			  		break;
			  	case 5:
			  		quit = true;
			  		break;
			  	default:
			  		break;
			  }
			  if (!quit) {
				  // Allow user to view more options after reading the output
				  System.out.println("Press Enter key to return to options.");
				  try { System.in.read(); } catch(Exception e) {}
			  }
		  }
		  else
			  // If the input is not a specified option
			  System.out.println("That is not a valid option. Please input a number between 1 and 5.");
		} while (!quit || !validInput); //Keep looping until the user quits

		input.close();
	}

}
