package dtu.se1.app;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import SE1.Activity;
import SE1.Employee;
import SE1.InvalidTimeInputException;
import SE1.ManCal;
import SE1.NotFoundException;

public class NewYearTimeClear extends SampleDataSetup {
	
	//ALEXANDER
	@Before
		public void init() throws NotFoundException, ParseException, InvalidTimeInputException {
		Employee hu = ma.getEmployee("HU");
		Employee mt = ma.getEmployee("MT");		
		Employee jp = ma.getEmployee("JP");
		Activity hue = new Activity(12,"Tihi", 50, 1, 52);
		
		assertTrue(hu.getActivities().size()>0);
		Activity huA1 = hu.getActivity(1500011);
				
		hu.registerWork(huA1,5.0,"20150101");
		hu.registerWork(huA1,7.0,"20150511");
		hu.registerWork(huA1,3.0,"20151201");
		
		mt.registerWork(hue,5.0,"20150101");
		mt.registerWork(hue,6.0,"20150909");
		mt.registerWork(hue,8.0,"20151205");
		
		jp.registerWork(hue,2.0,"20150101");
		jp.registerWork(hue,11.0,"20150917");
		jp.registerWork(hue,3.0,"20151202");
	}
	
	//ALEXANDER
	@Test
	public void testResetBooleanNotInDecember() {
		assertTrue(ma.getHasBeenResat());
		ma.decemberResetCheck();
		assertTrue(ma.getHasBeenResat());
	}
	
	//ALEXANDER
	@Test
	public void testResetBooleanInDecember() {
		assertTrue(ma.getHasBeenResat());
		ma.setCalendar(2015, ManCal.DECEMBER, 03);
		ma.decemberResetCheck();
		assertFalse(ma.getHasBeenResat());
	}
	
	//ALEXANDER
	@Test
	public void testAlreadyResatBooleanInDecember() {
		assertTrue(ma.getHasBeenResat());
		ma.setCalendar(2015, ManCal.DECEMBER, 03);
		ma.decemberResetCheck();
		assertFalse(ma.getHasBeenResat());
		ma.decemberResetCheck();
		assertFalse(ma.getHasBeenResat());	
	}
	
	//ALEXANDER
	@Test
	public void testNewYearWorkClearResatFalse() throws ParseException {
		ma.setCalendar(2015, ManCal.DECEMBER, 06);
		ma.decemberResetCheck();
		assertFalse(ma.getHasBeenResat());
		
		ma.setCalendar(2015, ManCal.JANUARY, 06);
		ma.checkNeedToClearRegisteredWork();		
		assertTrue(ma.getHasBeenResat());
		
		for(Employee e : ma.getEmployees()) {
			for(int i = 0; i < 366; i++) {
				assertTrue(e.getWorkRegistrator().getWork().get(i).isEmpty());
			}
			assertTrue(e.getWorkRegistrator().getMonthlyWorked() == 0.0);
		}
	}
	
	//ALEXANDER
	@Test
	public void testNewYearWorkClearResatTrue() throws NotFoundException, ParseException {
		int n = 0;
		Employee empList[] = new Employee[3];
		empList[0] = ma.getEmployee("HU");
		empList[1] = ma.getEmployee("MT");
		empList[2] = ma.getEmployee("JP");
				
		assertTrue(ma.getHasBeenResat());
		ma.setCalendar(2015, ManCal.JANUARY, 06);
		ma.checkNeedToClearRegisteredWork();		
		
		for(Employee e : empList) {
			for(int i = 0; i < 366; i++) {
				if(!(e.getWorkRegistrator().getWork().get(i).isEmpty())) n++;
			}
			assertTrue(n > 0);
			n = 0;
		}
	}
	
	//ALEXANDER
	@Test
	public void testNewYearWorkClearNotJanuary() throws NotFoundException, ParseException, InvalidTimeInputException {
		int n = 0;
		Employee empList[] = new Employee[3];
		empList[0] = ma.getEmployee("HU");
		empList[1] = ma.getEmployee("MT");
		empList[2] = ma.getEmployee("JP");
//		Employee jp = ma.getEmployee("JP");
//		jp.registerWork(new Activity(5,"mmm",40,2,50),11.0,"20150001");
		
		ma.setCalendar(2015, ManCal.DECEMBER, 06);
		ma.decemberResetCheck();
		assertFalse(ma.getHasBeenResat());
		ma.checkNeedToClearRegisteredWork();
		
		for(Employee e : empList) {
			for(int i = 0; i < 366; i++) {
				if(!(e.getWorkRegistrator().getWork().get(i).isEmpty())) n++;
			}
			assertTrue(n > 0);
			n = 0;
		}
	}
	
	//ALEXANDER
	@Test
	public void testNewYearWorkClearDecemberWorkSaved() throws NotFoundException, ParseException {
		int n = 0;
		Employee empList[] = new Employee[3];
		empList[0] = ma.getEmployee("HU");
		empList[1] = ma.getEmployee("MT");
		empList[2] = ma.getEmployee("JP");
		
		ma.setCalendar(2015, ManCal.DECEMBER, 06);
		ma.decemberResetCheck();
		assertFalse(ma.getHasBeenResat());
		
		for(Employee e : empList) {
			e.getWorkRegistrator().saveRegisteredWork();
		}
		
		//Checks that map has saved values
		for(Employee e : empList) {
			for(int i = 0; i < 31; i++) {
				if(!(e.getWorkRegistrator().getWorkDecemberLastYear().get(i).isEmpty())) n++;
			}
			assertTrue(n > 0);
			n = 0;
		}
		
		//Checks that both maps has the same values
		for(Employee e : empList) {
			for(int i = 0; i<31;i++){
				assertTrue(e.getWorkRegistrator().getWorkDecemberLastYear().get(i).equals(e.getWorkRegistrator().getWork().get(365-30+i)));
			}
		}
		
		//Clears the December map again
		for(Employee e : empList) {
			e.getWorkRegistrator().getWorkDecemberLastYear().clear();
			assertTrue(e.getWorkRegistrator().getWorkDecemberLastYear().isEmpty());
		}
		
				
		ma.setCalendar(2015, ManCal.JANUARY, 06);
		ma.checkNeedToClearRegisteredWork();		
		
		//Checks that December map got values and still has values after a work registration clear
		for(Employee e : empList) {
			for(int i = 0; i < 31; i++) {
				if(!(e.getWorkRegistrator().getWorkDecemberLastYear().get(i).isEmpty())) n++;
			}
			assertTrue(n > 0);
			n = 0;
		}
	}

}
