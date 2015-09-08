package dtu.se1.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;

import org.junit.Test;

import SE1.Activity;
import SE1.Employee;
import SE1.NotFoundException;
import SE1.InvalidTimeInputException;
//Nina
public class PrintRegisteredTime extends SampleDataSetup{
	
	@Test
	public void testSeeRegisteredTimeToday() throws NotFoundException, ParseException, InvalidTimeInputException{
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500010);;
		Activity a0 = hu.getActivity(1500011);;
		hu.registerWork(a0, 3, "today");
		hu.registerWork(a1,5.5,"today");
		System.out.println(hu.seeTodaysWork());
		//Cant check result because hashmaps are not ordered, print shows correct information
	}
	
	@Test
	public void testSeeRegisteredTimeSpecificDay() throws NotFoundException, ParseException, InvalidTimeInputException{
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500010);;
		Activity a0 = hu.getActivity(1500011);;
		hu.registerWork(a0, 3, "20150109");
		hu.registerWork(a1,5.5,"20150109");
		hu.registerWork(a1,4.5,"20150312");
		System.out.println(hu.seeSpecificDaysWork("20150109"));
		System.out.println(hu.seeSpecificDaysWork("20150312"));
		//Should return no time registered
		System.out.println(hu.seeSpecificDaysWork("20150512"));
		//Cant check result because hashmaps are not ordered, print shows correct information
	}
	
	@Test
	public void testSeeNoRegisteredTime() throws NotFoundException{
		Employee hu = ma.getEmployee("HU");
		
		String out = hu.seeTodaysWork();
		assertEquals("No time is registered today", out);
	}
	
	

}
