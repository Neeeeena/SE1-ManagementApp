package dtu.se1.app;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import SE1.Activity;
import SE1.Employee;
import SE1.InvalidTimeInputException;
import SE1.ManCal;
import SE1.NotFoundException;

public class PrintRegisteredTimeForDecemberLastYear extends SampleDataSetup {
	
	//ALEXANDER
	@Test
	public void getMonthlyWorkedDecember() throws NotFoundException, ParseException, InvalidTimeInputException {
		assertTrue(ma.getHasBeenResat());
		ma.setCalendar(2015, ManCal.DECEMBER, 03);
		ma.decemberResetCheck();
		assertFalse(ma.getHasBeenResat());
		
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500010);;
		Activity a0 = hu.getActivity(1500011);;
		hu.registerWork(a0, 3, "20151209");
		hu.registerWork(a1,5.5,"20151209");
		hu.registerWork(a1,4.5,"20151212");
		
		ma.setCalendar(2016, ManCal.JANUARY, 11);
		ma.checkNeedToClearRegisteredWork();
		assertTrue(ma.getHasBeenResat());
		
		System.out.println(hu.seeSpecificDaysWork("20151209"));
		System.out.println(hu.seeSpecificDaysWorkDecemberLastYear(9));
	}
	
	//ALEXANDER
	@Test
	public void getWorkSpecificDayDecember() throws NotFoundException, ParseException, InvalidTimeInputException {
		assertTrue(ma.getHasBeenResat());
		ma.setCalendar(2015, ManCal.DECEMBER, 03);
		ma.decemberResetCheck();
		assertFalse(ma.getHasBeenResat());
		
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500010);;
		Activity a0 = hu.getActivity(1500011);;
		hu.registerWork(a0, 3, "20151209");
		hu.registerWork(a1,5.5,"20151209");
		hu.registerWork(a1,4.5,"20151212");
		hu.registerWork(a1,4.5,"20151112");
		
		ma.setCalendar(2016, ManCal.JANUARY, 11);
		ma.checkNeedToClearRegisteredWork();
		assertTrue(ma.getHasBeenResat());
		
		assertTrue(hu.getMonthlyWorked() == 0);
		assertTrue(hu.getMonthlyWorkedDecemberLastYear() == 13);
		System.out.println(hu.seeSpecificDaysWorkDecemberLastYear(12));
	}
	
	//ALEXANDER
	@Test
	public void getWorkSpecificDayDecemberNoWork() throws NotFoundException, ParseException, InvalidTimeInputException {
		assertTrue(ma.getHasBeenResat());
		ma.setCalendar(2015, ManCal.DECEMBER, 03);
		ma.decemberResetCheck();
		assertFalse(ma.getHasBeenResat());
		
		Employee hu = ma.getEmployee("HU");
		assertTrue(hu.getActivities().size()>0);
		Activity a1 = hu.getActivity(1500010);;
		Activity a0 = hu.getActivity(1500011);;
		hu.registerWork(a0, 3, "20151209");
		hu.registerWork(a1,5.5,"20151209");
		
		ma.setCalendar(2016, ManCal.JANUARY, 11);
		ma.checkNeedToClearRegisteredWork();
		assertTrue(ma.getHasBeenResat());
		System.out.println(hu.seeSpecificDaysWorkDecemberLastYear(10));
	}

}
