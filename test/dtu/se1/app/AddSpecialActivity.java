package dtu.se1.app;



import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import SE1.Employee;
import SE1.SpecialActivity;
import SE1.IllegalActionException;

import SE1.NotFoundException;
import SE1.ManCal;
import SE1.MyCalendar;

//MATHIAS

public class AddSpecialActivity extends SampleDataSetup{
	//MATHIAS
	@Test
	public void testAddHoliday() throws IllegalActionException, NotFoundException{
		Employee mt = ma.getEmployee("MT");
		MyCalendar cmt = mt.getMyCalendar();
		assertTrue(cmt.getSpecialActivities().isEmpty());
		try{
			cmt.addSpecialActivity(2015,1,1,1,5);
			fail("An IllegalActionException should have been thrown");
		}catch(IllegalActionException e) {
			assertEquals("No such type", e.getMessage());
		}
		try{
			cmt.addSpecialActivity(2015,1,1,1,0);
			fail("An IllegalActionException should have been thrown");
		}catch(IllegalActionException e) {
			assertEquals("No such type", e.getMessage());
		}
		
		
		cmt.addSpecialActivity(2015,1,1,1,SpecialActivity.HOLIDAY); //Adding holiday on the first day of 2015, with a duration of 1 day, and of type 1(holiday).
		assertTrue(!cmt.getSpecialActivities().isEmpty());
		for(SpecialActivity sa : cmt.getSpecialActivities()) {
			if(sa.getDay()==1 && sa.getYear() == 2015){
				assertTrue(sa.getType() == SpecialActivity.HOLIDAY);
			}
		}
		
	}
	

	
	//MATHIAS
	@Test
	public void testAddIllness() throws IllegalActionException, NotFoundException{
		
		ManCal calendar = new ManCal(2015, Calendar.FEBRUARY,10);
		
		Employee rk = ma.getEmployee("RK");
		MyCalendar crk = rk.getMyCalendar();
		
		assertTrue(crk.getSpecialActivities().size() == 0);
		crk.addSpecialActivity(2015, 2, 9, 2, SpecialActivity.ILLNESS);
		assertTrue(crk.getSpecialActivities().size() == 2);
		
		crk.addSpecialActivity(2014, 2, 15, 2, SpecialActivity.ILLNESS);
		assertTrue(crk.getSpecialActivities().size() == 4);
		
	
		
	}
	//MATHIAS
	@Test
	public void testAddFutureIllness() throws IllegalActionException, NotFoundException{
		Employee rk = ma.getEmployee("RK");
		MyCalendar crk = rk.getMyCalendar();
		
		ManCal manCal = mock(ManCal.class);
		ma.setCalendar(manCal);
		Calendar calendar = new ManCal(2015, Calendar.FEBRUARY,10);
		when(manCal.getDate()).thenReturn(calendar);
		
		assertTrue(crk.getSpecialActivities().size() == 0);
		try{
			crk.addSpecialActivity(2015, 2, 15, 2, SpecialActivity.ILLNESS);
			fail("An IllegalActionException should have been thrown");
		}catch(IllegalActionException e){
			assertEquals("You cannot register future illness",e.getMessage());
		}
		
		try{
			crk.addSpecialActivity(2016, 2, 15, 2, SpecialActivity.ILLNESS);
			fail("An IllegalActionException should have been thrown");
		}catch(IllegalActionException e){
			assertEquals("You cannot register future illness",e.getMessage());
		}
		
		assertTrue(crk.getSpecialActivities().size() == 0);

	}
	//MATHIAS
	@Test
	public void testAddDouble() throws IllegalActionException, NotFoundException {
		Employee jp = ma.getEmployee("JP");
		MyCalendar cjp = jp.getMyCalendar();
		assertFalse(cjp.getSpecialActivities().size()==5);
		cjp.addSpecialActivity(2015, 1, 1, 5, SpecialActivity.HOLIDAY); //Adding holiday between 0101 and 0301
		assertTrue(cjp.getSpecialActivities().size()==5);
		//Trying to make two special activities the same day
		try {
			cjp.addSpecialActivity(2015,1,3,1, SpecialActivity.COURSE);
			fail("An IllegalActionException should have been thrown");
		}catch(IllegalActionException e) {
			assertEquals("Can only have one special activity each day",e.getMessage());
		}
		
	}
	//MATHIAS
	@Test
	public void testAddAcrossYear() throws IllegalActionException, NotFoundException {
		Employee jp = ma.getEmployee("JP");
		MyCalendar cjp = jp.getMyCalendar();
		cjp.addSpecialActivity(2012,1,10, 5, SpecialActivity.HOLIDAY);
		assertFalse(cjp.getSpecialActivities().size()==13);
		cjp.addSpecialActivity(2013, 12, 26, 8 , SpecialActivity.HOLIDAY);
		assertTrue(cjp.getSpecialActivities().size()==13);
		
		//Trying to make two special activities the same day, while shifting years.
		
		try{
			cjp.addSpecialActivity(2014,01,01,1, SpecialActivity.HOLIDAY);
			fail("An IllegalActionException should have been thrown");
		}catch(IllegalActionException e) {
			assertEquals("Can only have one special activity each day",e.getMessage());
		}
		
		//Trying for leap year
		cjp.addSpecialActivity(2012, 12, 25, 8 , SpecialActivity.HOLIDAY);
		assertTrue(cjp.getSpecialActivities().size()==21);
		cjp.addSpecialActivity(2013, 1, 2, 100, SpecialActivity.HOLIDAY);
		assertTrue(cjp.getSpecialActivities().size()==121);
	}
	//MATHIAS
	@Test
	public void testConverters() throws NotFoundException {
		Employee jp = ma.getEmployee("JP");
		MyCalendar cjp = jp.getMyCalendar();
		int year = 2002;
		int month = 1;
		int day = 1;
		int dayOfYear = cjp.dateToDay(year, month, day);

		assertTrue(cjp.dayOfYearToDayOfMonth(dayOfYear, year) == day);
		assertTrue(cjp.dayOfYearToMonth(dayOfYear, year) == month);
		
		year = 2001;
		month = 2;
		day = 1;
		dayOfYear = cjp.dateToDay(year, month, day);

		assertTrue(cjp.dayOfYearToDayOfMonth(dayOfYear, year) == day);
		assertTrue(cjp.dayOfYearToMonth(dayOfYear, year) == month);
		
		year = 2004;
		month = 3;
		day = 1;
		dayOfYear = cjp.dateToDay(year, month, day);
		
		assertTrue(cjp.dayOfYearToDayOfMonth(dayOfYear, year) == day);
		assertTrue(cjp.dayOfYearToMonth(dayOfYear, year) == month);
		
		
	}
	//MATHIAS
	@Test
	public void deleteSpecialActivity() throws NotFoundException, IllegalActionException {
		Employee jp = ma.getEmployee("JP");
		MyCalendar cjp = jp.getMyCalendar();
		assertTrue(cjp.getSpecialActivities().isEmpty());
		cjp.addSpecialActivity(2014,02,01,05,SpecialActivity.HOLIDAY);
		assertTrue(cjp.getSpecialActivities().size()==5);
		

		cjp.deleteSpecialActivities(01,02,2014, 4,02,2014);
		assertTrue(cjp.getSpecialActivities().size()==1);
		assertTrue(cjp.deleteSpecialActivities(01,02,2012, 4,02,2018) == 1);
		assertTrue(cjp.getSpecialActivities().isEmpty());
		
		cjp.addSpecialActivity(2014,02,01,10,SpecialActivity.HOLIDAY);
		assertTrue(cjp.deleteSpecialActivities(03,02,2014, 5,02,2014) == 3);
		assertTrue(cjp.deleteSpecialActivities(03,02,2014, 5,02,2014) == 0);
		assertTrue(cjp.deleteSpecialActivities(01,02,2014, 8,02,2012) == 0);
		assertTrue(cjp.getSpecialActivities().size()==7);
		
		
		
		
	}

	
	
}
