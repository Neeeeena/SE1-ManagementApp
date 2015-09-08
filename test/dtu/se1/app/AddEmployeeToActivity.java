package dtu.se1.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import SE1.Activity;
import SE1.Employee;
import SE1.EmployeeNotAvailableException;
import SE1.IllegalActionException;
import SE1.IllegalWeeksException;
import SE1.IllegalWorkloadException;
import SE1.InvalidTimeInputException;
import SE1.ManCal;
import SE1.MyCalendar;
import SE1.NotFoundException;
import SE1.Project;
import SE1.SpecialActivity;
//Nina
public class AddEmployeeToActivity extends SampleDataSetup{
	
	@Test
	public void activityNotFound() throws NotFoundException {
		Employee e1 = ma.getEmployee("JP");
		ArrayList<Project> ps = e1.getProjects();
		Project cp = null;
		for(Project p : ps){
			if( p.getName() == "MobilePay App"){
				cp = p;
			}
		}
		try {
			Activity activity1 = cp.getActivity("123");
			fail("An activityNotFoundException should have been thrown");
		} catch (NotFoundException e) {
			assertEquals("Activity was not found", e.getMessage());
		}
	}
	
	@Test
	public void testAddEmployeeActivity() throws NotFoundException, IllegalWorkloadException, EmployeeNotAvailableException, IllegalWeeksException, InvalidTimeInputException{
		Employee e1 = ma.getEmployee("JP");
		ArrayList<Project> ps = e1.getProjects();
		Project cp = null;
		for(Project p : ps){
			if( p.getName() == "MobilePay App"){
				cp = p;
			}
		}
			
		Activity a = cp.getActivity("Design");
		a.setExpectedWorkload(20);
		a.setStartAndEndWeek(50, 6);
		assertTrue(a.getAvailableWork()==20);
		a.addEmployeeToActivity(ma.getEmployee("MT"),10.0);
		assertTrue(a.getAvailableWork()==10);
		assertTrue(a.getEmployees().size()>0);
	}
	
	@Test
	public void testEmployeeHasMoreThan37HoursNewYear() throws IllegalWorkloadException, NotFoundException, EmployeeNotAvailableException, IllegalWeeksException, InvalidTimeInputException{
		Employee mt = ma.getEmployee("MT");
		ArrayList<Project> projects = ma.getProjects();
		ArrayList<Activity> activities = projects.get(1).getActivities();
		Activity a1 = activities.get(0);
		a1.setExpectedWorkload(37);
		a1.setStartAndEndWeek(1,2);
		a1.addEmployeeToActivity(mt,37.0);
		
		
		Employee e1 = ma.getEmployee("JP");
		
		ArrayList<Project> ps = e1.getProjects();
		Project cp=null;
		for(Project p : ps){
			if( p.getName() == "MobilePay App"){
				cp = p;
			}
		}
			
		Activity a = cp.getActivity("Design");
		a.setStartAndEndWeek(50,2);
		a.setExpectedWorkload(37);
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==37);
		try{
			a.addEmployeeToActivity(mt,2);
			fail("An EmployeeNotAvailableException should have been thrown");
		}catch (EmployeeNotAvailableException e){
			assertEquals("Employee has more than 37 hours of work in the time interval",e.getMessage());	
		}
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==37);
	}
	
	@Test
	public void testEmployeeHasMoreThan37Hours() throws IllegalWorkloadException, NotFoundException, EmployeeNotAvailableException
														, IllegalWeeksException, InvalidTimeInputException{
		Employee mt = ma.getEmployee("MT");
		ArrayList<Project> projects = ma.getProjects();
		ArrayList<Activity> activities = projects.get(1).getActivities();
		Activity a1 = activities.get(0);
		a1.setExpectedWorkload(37);
		a1.setStartAndEndWeek(1,2);
		a1.addEmployeeToActivity(mt,37.0);
		
		
		Employee e1 = ma.getEmployee("JP");
		
		ArrayList<Project> ps = e1.getProjects();
		Project cp=null;
		for(Project p : ps){
			if( p.getName() == "MobilePay App"){
				cp = p;
			}
		}
			
		Activity a = cp.getActivity("Design");
		a.setStartAndEndWeek(1,2);
		a.setExpectedWorkload(37);
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==37);
		try{
			a.addEmployeeToActivity(mt,2);
			fail("An EmployeeNotAvailableException should have been thrown");
		}catch (EmployeeNotAvailableException e){
			assertEquals("Employee has more than 37 hours of work in the time interval",e.getMessage());	
		}
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==37);
	}
	
	@Test
	public void testEmployeeIsOnHoliday() throws NotFoundException, IllegalActionException, IllegalWeeksException, InvalidTimeInputException, EmployeeNotAvailableException{
		Employee hu = ma.getEmployee("HU");
		MyCalendar mch = hu.getMyCalendar();
		mch.addSpecialActivity(2015,1,1,16,SpecialActivity.HOLIDAY);
		
		Employee e1 = ma.getEmployee("JP");
		
		ArrayList<Project> ps = e1.getProjects();
		Project cp = null;
		for(Project p : ps){
			if( p.getName() == "MobilePay App"){
				cp = p;
			}
		}
			
		Activity a = cp.getActivity("Design");
		a.setStartAndEndWeek(1,1);
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==1);
		try{
			a.addEmployeeToActivity(ma.getEmployee("HU"),5.0);
			fail("An EmployeeNotAvailableException should have been thrown");
		}catch (EmployeeNotAvailableException e){
			assertEquals("Employee has a special activity in the time interval",e.getMessage());	
		}
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==1);
	}
	//Nina
	@Test
	public void testEmployeeIsOnHolidayNewYear() throws NotFoundException, IllegalActionException, IllegalWeeksException, InvalidTimeInputException, EmployeeNotAvailableException{
		Employee hu = ma.getEmployee("HU");
		MyCalendar mch = hu.getMyCalendar();
		mch.addSpecialActivity(2015,12,15,30,SpecialActivity.HOLIDAY);
		
		Employee e1 = ma.getEmployee("JP");
		
		ArrayList<Project> ps = e1.getProjects();
		Project cp = null;
		for(Project p : ps){
			if( p.getName() == "MobilePay App"){
				cp = p;
			}
		}
			
		Activity a = cp.getActivity("Design");
		a.setStartAndEndWeek(52,2);
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==1);
		try{
			a.addEmployeeToActivity(ma.getEmployee("HU"),5.0);
			fail("An EmployeeNotAvailableException should have been thrown");
		}catch (EmployeeNotAvailableException e){
			assertEquals("Employee has a special activity in the time interval",e.getMessage());	
		}
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==1);
	}
	
	@Test
	public void testEmployeeHasMoreThan20Activites() throws EmployeeNotAvailableException, NotFoundException, InvalidTimeInputException{
		Employee rk = ma.getEmployee("RK");
		
		Employee e1 = ma.getEmployee("JP");
		
		ArrayList<Project> ps = e1.getProjects();
		Project cp=null;
		for(Project p : ps){
			if( p.getName() == "MobilePay App"){
				cp = p;
			}
		}
		for(int i=0;i<20;i++){
			String nameTemp = "Tests"+i;
			cp.addActivity(nameTemp,1,2,4);
			cp.getActivity(nameTemp).addEmployeeToActivity(rk,1.0);
			
		}
		assertTrue(rk.getActivities().size()==20);
			
		Activity a = cp.getActivity("Design");
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==1);
		try{
			a.addEmployeeToActivity(rk,2.0);
			fail("An EmployeeNotAvailableException should have been thrown");
		}catch (EmployeeNotAvailableException e){
			assertEquals("Employee has more than 20 activities",e.getMessage());	
		}
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==1);
	}
	
	@Test
	public void testAddEmployeeDoesntExist() throws EmployeeNotAvailableException, NotFoundException, InvalidTimeInputException{
		Employee e1 = ma.getEmployee("JP");
		ArrayList<Project> ps = e1.getProjects();
		Project cp = null;
		for(Project p : ps){
			if( p.getName() == "MobilePay App"){
				cp = p;
			}
		}
			
		Activity a = cp.getActivity("Design");
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==1);
		try{
		a.addEmployeeToActivity(ma.getEmployee("BUH"),3.0);
		fail("An NotFoundException should have been thrown");
		}catch (NotFoundException e){
			assertEquals("Employee was not found", e.getMessage());
		}
		assertTrue(a.getEmployees().size()==0);
		assertTrue(a.getAvailableWork()==1);
	}
	
	@Test
	public void testInvalidHours() throws NotFoundException, IllegalWorkloadException, IllegalWeeksException, EmployeeNotAvailableException{
		Employee e1 = ma.getEmployee("JP");
		ArrayList<Project> ps = e1.getProjects();
		Project cp = null;
		for(Project p : ps){
			if( p.getName() == "MobilePay App"){
				cp = p;
			}
		}
			
		Activity a = cp.getActivity("Design");
		a.setExpectedWorkload(20);
		a.setStartAndEndWeek(50, 6);
		assertTrue(a.getAvailableWork()==20);
		try{
			a.addEmployeeToActivity(ma.getEmployee("MT"),-10.0);
			fail("An InvalidTimeInputException should have been thrown");
		}catch(InvalidTimeInputException e){
			assertEquals(e.getMessage(),"Number must be positive");
		}
		assertTrue(a.getAvailableWork()==20);
		assertTrue(a.getEmployees().size()==0);
	}
	
}	

