package dtu.se1.app;
import java.text.ParseException;
import java.util.ArrayList;

import SE1.Activity;
import SE1.ManCal;
import SE1.NotFoundException;
import SE1.Project;
import SE1.Employee;
import SE1.ManApp;
import SE1.InvalidTimeInputException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
//Nina
public class AdjustRegisteredTime extends SampleDataSetup {
	@Test
	public void testRegisterOldWork() throws NotFoundException, ParseException, InvalidTimeInputException{
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500010);
		
		ma.getCalendar().set(ManCal.DAY_OF_MONTH,12);

		assertEquals(a1.getWorkedHours(), 0,0);
		assertEquals(hu.getMonthlyWorked(),0,0);
		hu.registerWork(a1,5.0,"20150101");
		assertEquals(a1.getWorkedHours(), 5,0);
		assertEquals(hu.getMonthlyWorked(),5,0);

	}
	
	@Test
	public void testRegisteredTimeDoesntExist() throws NotFoundException, ParseException{
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500011);
		ma.getCalendar().set(ManCal.DAY_OF_MONTH,5);
		
		assertEquals(a1.getWorkedHours(), 0,0);
		assertEquals(hu.getMonthlyWorked(),0,0);
		try{
			hu.editRegisteredWork(a1,5.0,"20150101");
			fail("An NotFoundException should have been thrown");
		}catch(NotFoundException e){
			assertEquals("The registered time can not be found", e.getMessage());
		}
		assertEquals(a1.getWorkedHours(), 0,0);
		assertEquals(hu.getMonthlyWorked(),0,0);
	}
	
	@Test
	public void testEditRegisteredTime() throws ParseException, NotFoundException, InvalidTimeInputException{
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500011);
		
		ma.getCalendar().set(ManCal.DAY_OF_MONTH,5);
		hu.registerWork(a1,5.0,"20150101");
		assertEquals(a1.getWorkedHours(), 5,0);
		assertEquals(hu.getMonthlyWorked(),5,0);
		try{
			hu.editRegisteredWork(a1,3.0,"20150101");
		}catch(NotFoundException e){
			assertEquals("The registered time can not be found", e.getMessage());
		}
		assertEquals(a1.getWorkedHours(), 3,0);
		assertEquals(hu.getMonthlyWorked(),3,0);
	}
	
	@Test
	public void deleteRegisteredTime() throws NotFoundException, ParseException, InvalidTimeInputException{
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500011);
		
		ma.getCalendar().set(ManCal.DAY_OF_MONTH,5);
		hu.registerWork(a1,5.0,"20150101");
		assertEquals(a1.getWorkedHours(), 5,0);
		assertEquals(hu.getMonthlyWorked(),5,0);
		try{
			hu.deleteRegisteredWork(a1,"20150101");
		}catch(NotFoundException e){
			assertEquals("The registered time can not be found", e.getMessage());
		}
		assertEquals(a1.getWorkedHours(), 0,0);
		assertEquals(hu.getMonthlyWorked(),0,0);
	}
	
	@Test
	public void testDeleteRegisteredTimeDoesntExist() throws NotFoundException, ParseException{
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500011);
		ma.getCalendar().set(ManCal.DAY_OF_MONTH,5);
		
		assertEquals(a1.getWorkedHours(), 0,0);
		assertEquals(hu.getMonthlyWorked(),0,0);
		try{
			hu.deleteRegisteredWork(a1,"20150101");
			fail("An NotFoundException should have been thrown");
		}catch(NotFoundException e){
			assertEquals("The registered time can not be found", e.getMessage());
		}
		assertEquals(a1.getWorkedHours(), 0,0);
		assertEquals(hu.getMonthlyWorked(),0,0);
	}
}
