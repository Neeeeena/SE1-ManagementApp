package dtu.se1.app;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import SE1.Activity;
import SE1.Employee;
import SE1.IllegalWorkloadException;
import SE1.NotFoundException;
import SE1.Project;
import SE1.NotFoundException;

public class SetExpectedWorkloadForActivity extends SampleDataSetup {
	
	//ALEXANDER
	@Test
	public void projectFoundActivityFoundAndTimeAdded() throws NotFoundException, IllegalWorkloadException {
		Employee e1 = ma.getEmployee("JP");
		assertFalse(e1.getProjects().isEmpty());
		Project project1 = e1.getProject(150000);
		assertFalse(project1.getActivities().isEmpty());
		Activity activity1 = project1.getActivity("Design");
		activity1.setExpectedWorkload(56);
		assertEquals(activity1.getExpectedWorkload(),56);
	}	
	
	//ALEXANDER
	@Test
	public void projectNotFound() throws NotFoundException {
		Employee e1 = ma.getEmployee("JP");
		ArrayList<Project> projects = e1.getProjects();
		assertTrue(projects.size() == 1);
		try {
			Project project4 = e1.getProject(150004);
			fail("A NotFoundException should have been thrown");
		} catch (NotFoundException e) {
			assertEquals("Project was not found", e.getMessage());
		}
	}	
	
	//ALEXANDER
	@Test
	public void activityNotFound() {
		Project project1 = ma.getProjects().get(0);
		project1.getActivities().remove(0);
		try {
			Activity activity1 = project1.getActivity("Design");
			fail("An activityNotFoundException should have been thrown");
		} catch (NotFoundException e) {
			assertEquals("Activity was not found", e.getMessage());
		}
	}
	
	//ALEXANDER
	@Test
	public void activityFoundButIlligealTimeAdded() throws IllegalWorkloadException, NotFoundException {
		Project project1 = ma.getProjects().get(0);
		assertFalse(project1.getActivities().isEmpty());
		Activity activity1 = project1.getActivity("Design");
		try {
			activity1.setExpectedWorkload(-5);
			fail("An IllegalWorkloadException should have been thrown");
		} catch (IllegalWorkloadException e) {
			assertEquals("Unknown amount of hours entered", e.getMessage());
		}
	}
	
}
