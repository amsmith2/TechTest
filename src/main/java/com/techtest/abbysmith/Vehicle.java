package com.techtest.abbysmith;

/**
* Vehicle Object.
*
* <P>Various attributes of Vehicle specifications.
*
* @author Abigail Smith
*/

public class Vehicle {
	public String sipp;
	public String name;
	public Double price;
	public String supplier;
	public Double rating;
	public String carType;
	public String doors;
	public String transmission;
	public String fuel;
	public Boolean aircon;
	public Integer vehicleScore = 0;
	public Double sumScore;

	/**
	 * Sets up the vehicle object. Generating the SIPP expansion, calculating the vehicle
	 * score and the total score.
	 */
	public void setUp() {
		generateSpec();
		calculateScore();
		calculateSum();
	}

	/**
	 * Expands the SIPP specification. Setting the carType, doors, transmission, fuel
	 * and aircon attributes of the Vehicle object.
	 */
	private void generateSpec() {
		String sipp = this.sipp;
		switch(sipp.charAt(0)) {
			case 'M':
				this.carType = "Mini";
				break;
			case 'E':
				this.carType = "Economy";
				break;
			case 'C':
				this.carType = "Compact";
				break;
			case 'I':
				this.carType = "Intermediate";
				break;
			case 'S':
				this.carType = "Standard";
				break;
			case 'F':
				this.carType = "Full size";
				break;
			case 'P':
				this.carType = "Premium";
				break;
			case 'L':
				this.carType = "Luxury";
				break;
			case 'X':
				this.carType = "Special";
				break;
			default:
				break;
		}
		switch(sipp.charAt(1)) {
			case 'B':
				this.doors = "2 doors";
				break;
			case 'C':
				this.doors = "4 doors";
				break;
			case 'D':
				this.doors = "5 doors";
				break;
			case 'W':
				this.doors = "Estate";
			case 'T':
				this.doors = "Convertible";
			case 'F':
				this.doors = "SUV";
			case 'P':
				this.doors = "Pick up";
			case 'V':
				this.doors = "Passenger Van";
			default:
				break;
		}
		switch(sipp.charAt(2)) {
			case 'M':
				this.transmission = "Manual";
				break;
			case 'A':
				this.transmission = "Automatic";
				break;
			default:
				break;
		}
		switch(sipp.charAt(3)) {
			case 'N':
				this.fuel = "Petrol";
				this.aircon = false;
				break;
			case 'R':
				this.fuel = "Petrol";
				this.aircon = true;
				break;
			default:
				break;
		}
	}

	/**
	 * Calculates the vehicle score. If there is aircon, 2 points are added. If there
	 * is manual transmission 1 point is added. If there is automatic transmission,
	 * 5 points are added. The vehicleScore attribute is updated with these additions.
	 */
	private void calculateScore() {
		vehicleScore += (this.aircon ? 2 : 0);
		vehicleScore += (this.transmission == "Manual" ? 1 : 5);
	}

	/**
	 * Calculates the total score by adding the rating to the vehicle score. The sumScore
	 * attribute is set to the result of this addition.
	 */
	private void calculateSum() {
		sumScore = this.rating + this.vehicleScore;
	}

	/**
	 * Returns a String of the expanded SIPP specification.
	 * @return		the expanded SIPP specification as a String in the specified
	 * 				format.
	 */
	public String sippToString() {
		return this.name + " - " + this.sipp + " - " + this.carType + " - " + this.doors + " - " + this.transmission + " - " + this.fuel + " - " + (this.aircon ? "AC" : "No AC");
	}

}
