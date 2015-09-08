package dtu.se1.app;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import SE1.Activity;
import SE1.EmployeeNotAvailableException;
import SE1.IllegalWeeksException;
import SE1.InvalidTimeInputException;
import SE1.NotFoundException;
import SE1.Employee;
import SE1.Project;

public class SetStartAndEndDateForActivity extends SampleDataSetup {
	
	//ALEXANDER
	@Test
	public void ProjectFoundActivityFoundValidWeeks() throws NotFoundException, IllegalWeeksException, EmployeeNotAvailableException {
		Employee e1 = ma.getEmployee("JP");
		assertFalse(e1.getProject(150000) == null);
		Activity a1 = e1.getProject(150000).getActivity("Design");
		a1.setStartAndEndWeek(5,25);
		assertTrue(a1.getStartWeek() == 5);
		assertTrue(a1.getEndWeek() == 25);
	}
	
	//ALEXANDER
	@Test
	public void projectNotFound1() throws NotFoundException {
		Employee e1 = ma.getEmployee("JP");
		ArrayList<Project> projects = e1.getProjects();
		assertTrue(projects.size() == 1);
		try {
			Project project4 = e1.getProject(4);
			fail("A NotFoundException should have been thrown");
		} catch (NotFoundException e) {
			assertEquals("Project was not found", e.getMessage());
		}
	}	
	
	//ALEXANDER
	@Test
	public void activityNotFound1() throws NotFoundException {
		Employee e1 = ma.getEmployee("JP");
		Project project1 = e1.getProjects().get(0);
		assertTrue(project1.getActivities().size() == 3);		
		try {
			Activity activity1 = project1.getActivity("I�m nonexisting");
			fail("An activityNotFoundException should have been thrown");
		} catch (NotFoundException e) {
			assertEquals("Activity was not found", e.getMessage());
		}
	}
	
	//ALEXANDER
	@Test
	public void ProjectFoundActivityFoundNonValidWeeks() throws NotFoundException, EmployeeNotAvailableException {
		Employee e1 = ma.getEmployee("JP");
		assertFalse(e1.getProject(150000) == null);
		Activity a1 = e1.getProject(150000).getActivity("Design");
		
		try {
			a1.setStartAndEndWeek(5,55);
			fail("An IllegalWeeksException should have been thrown");
		} catch (IllegalWeeksException e) {
			assertEquals("Not a valid week number (1 - 52)", e.getMessage());
		}
		
		try {
			a1.setStartAndEndWeek(53,45);
			fail("An IllegalWeeksException should have been thrown");
		} catch (IllegalWeeksException e) {
			assertEquals("Not a valid week number (1 - 52)", e.getMessage());
		}		
		
		try {
			a1.setStartAndEndWeek(64,-3);
			fail("An IllegalWeeksException should have been thrown");
		} catch (IllegalWeeksException e) {
			assertEquals("Not a valid week number (1 - 52)", e.getMessage());
		}
	}
	//Nina
	@Test
	public void ProjectFoundActivityFoundEmployeeNotAvailable() throws NotFoundException, IllegalWeeksException, EmployeeNotAvailableException, InvalidTimeInputException {
		Employee e1 = ma.getEmployee("JP");
		assertFalse(e1.getProject(150000) == null);
		Activity a1 = e1.getProject(150000).getActivity("Design");
		Activity a2 = e1.getProject(150000).getActivity("Implementation");
		
		Employee e2 = ma.getEmployee("MT");
		
		a1.addEmployeeToActivity(e2,20);
		
		//Change time of activity, add MT to activity with maximum hours
		a2.setStartAndEndWeek(5, 6);
		a2.addEmployeeToActivity(e2, 37);
		
		assertTrue(a1.getStartWeek()==2);
		assertTrue(a1.getEndWeek()==4);
		//Try to move Design activity to time where, MT has no free time
		try{
			a1.setStartAndEndWeek(5,6);
			fail("An EmployeeNotAvailableException should have been thrown");
		}catch(EmployeeNotAvailableException e){
			assertEquals(e.getMessage(),"Maria Thomsen is not available in the time interval");
		}
		assertTrue(a1.getStartWeek()==2);
		assertTrue(a1.getEndWeek()==4);

	}
	

}
