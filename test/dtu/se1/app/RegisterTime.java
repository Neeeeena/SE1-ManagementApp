package dtu.se1.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Test;

import SE1.Activity;
import SE1.Employee;
import SE1.NotFoundException;
import SE1.Project;
import SE1.InvalidTimeInputException;

public class RegisterTime extends SampleDataSetup{

	//Nina
	@Test
	public void testRegisterWork() throws NotFoundException, ParseException, InvalidTimeInputException{
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500011);;
		assertEquals(a1.getWorkedHours(), 0,0.001);
		assertEquals(hu.getMonthlyWorked(),0,0.001);
		hu.registerWork(a1,5.0,"today");
		assertEquals(a1.getWorkedHours(), 5,0.001);
		assertEquals(hu.getMonthlyWorked(),5,0.001);

	}
	
	//Alexander
	@Test
	public void testRegisterWorkActivityNotFound() throws NotFoundException, ParseException, InvalidTimeInputException{
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		try {
			Activity a1 = hu.getActivity(1500022);
			fail("Activity not found shoudl have been thrown");
		} catch (NotFoundException e) {
			assertEquals("Activity not found",e.getMessage());
		}
	}
	//Nina
	@Test
	public void testTooLargeInput() throws NotFoundException, ParseException{
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500010);;
		
		assertEquals(a1.getWorkedHours(), 0,0.001);
		assertEquals(hu.getMonthlyWorked(),0,0.001);
		try{
			hu.registerWork(a1,24.5,"today");
			fail("An UnvalidTimeInputException should have been thrown");
		}catch(InvalidTimeInputException e){
			assertEquals("Input must be a number between 0 and 24", e.getMessage());
		}
		assertEquals(a1.getWorkedHours(), 0,0.001);
		assertEquals(hu.getMonthlyWorked(),0,0.001);
	}
	//Nina
	@Test
	public void testNegativeInput() throws NotFoundException, ParseException{
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500010);;
		assertEquals(a1.getWorkedHours(), 0,0);
		assertEquals(hu.getMonthlyWorked(),0,0);
		try{
			hu.registerWork(a1,-2,"today");
			fail("An UnvalidTimeInputException should have been thrown");
		}catch(InvalidTimeInputException e){
			assertEquals("Input must be a number between 0 and 24", e.getMessage());
		}
		assertEquals(a1.getWorkedHours(), 0,0.001);
		assertEquals(hu.getMonthlyWorked(),0,0.001);
	}
	
	

}
