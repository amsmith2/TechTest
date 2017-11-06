package com.techtest.abbysmith;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class VehicleTest {
	private Vehicle vehicle;

	@Before
	public void generateVehicles() {
		Gson gson = new Gson();
		String vehicleStr = "{ \"sipp\": \"CDMR\", \"name\": \"Ford Focus\", \"price\": 157.85, \"supplier\": \"Hertz\", \"rating\": 8.9 }";
		vehicle = gson.fromJson(vehicleStr, Vehicle.class);
		vehicle.setUp();

	}

	@Test
	public void testSippToString() {
		String expectedResult = "Ford Focus - CDMR - Compact - 5 doors - Manual - Petrol - AC";
		String actualResult = vehicle.sippToString();
		assertEquals(expectedResult,actualResult);
	}

	@Test
	public void testCalculateSum() {
		double expectedResult =  8.9 + 1 + 2;
		double actualResult = vehicle.sumScore;
		assertEquals(expectedResult,actualResult,0.01);
	}

	@Test
	public void testCalculateScore() {
		int expectedResult = 1 + 2;
		int actualResult = vehicle.vehicleScore;
		assertEquals(expectedResult,actualResult);
	}

	@Test
	public void testGenerateSpecChar1() {
		assertEquals("Compact",vehicle.carType);
	}

	@Test
	public void testGenerateSpecChar2() {
		assertEquals("5 doors",vehicle.doors);
	}

	@Test
	public void testGenerateSpecChar3() {
		assertEquals("Manual",vehicle.transmission);
	}

	@Test
	public void testGenerateSpecChar4() {
		assertEquals("Petrol",vehicle.fuel);
		assertTrue(vehicle.aircon);
	}

}
