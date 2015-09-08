package dtu.se1.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import SE1.Activity;
import SE1.Employee;
import SE1.EmployeeNotAvailableException;
import SE1.HasAdminException;
import SE1.ManCal;
import SE1.NotFoundException;
import SE1.Project;

public class BecomeOrRemoveProjectAdministrator extends SampleDataSetup {
	
	//ALEXANDER
	@Test
	public void ProjectFoundAndGotNoAdmin() throws NotFoundException, HasAdminException{
		Employee e1 = ma.getEmployee("HU");
		Project p1 = ma.getProject(150001);
		assertTrue(p1.getName() == "OAST Program");
		assertTrue(e1.getProjects().size() == 0);
		assertFalse(p1.hasAdmin());
		p1.addProjectAdmin(e1);
		assertTrue(p1.hasAdmin());
		assertTrue(e1.getProject(150001).getName() == "OAST Program");
	}
	
	//ALEXANDER
	@Test
	public void ProjectNotFound() {
		try {
			Project p1 = ma.getProject(150007);
			fail("NotFoundException should have been thrown");
		} catch(NotFoundException e) {
			assertEquals("Project was not found", e.getMessage());
		}		
	}
	
	//ALEXANDER
	@Test
	public void ProjectHasAdmin() throws NotFoundException, HasAdminException {
		Employee e1 = ma.getEmployee("HU");
		Employee e2 = ma.getEmployee("JP");
		Project p1 = ma.getProject(150001);
		assertTrue(p1.getName() == "OAST Program");
		p1.addProjectAdmin(e1);
		try {
			p1.addProjectAdmin(e2);
			fail("Exception should have been thrown");
		} catch(HasAdminException e) {
			assertEquals("Project already has admin", e.getMessage());
		}
	}
	
	//ALEXANDER
	@Test
	public void RemoveProjectAdmin() throws NotFoundException, HasAdminException {
		Employee e1 = ma.getEmployee("HU");
		Employee e2 = ma.getEmployee("JP");
		Project p1 = ma.getProject(150001);
		assertTrue(p1.getName() == "OAST Program");
		p1.addProjectAdmin(e1);
		assertFalse(e1.getProjects().isEmpty());
		p1.removeProjectAdmin(e1);
		assertTrue(e1.getProjects().isEmpty());
	}
	
	//ALEXANDER
	@Test
	public void RemoveProjectAdminNoAdminFound() throws NotFoundException, HasAdminException {
		Employee e1 = ma.getEmployee("HU");
		Employee e2 = ma.getEmployee("JP");
		Project p1 = ma.getProject(150001);
		assertTrue(p1.getName() == "OAST Program");
		assertFalse(p1.hasAdmin());
		try {
			p1.removeProjectAdmin(e2);
			fail("Exception should have been thrown");
		} catch(HasAdminException e) {
			assertEquals("Project has no admin", e.getMessage());
		}
	}
	
	//ALEXANDER
	@Test
	public void RemoveProjectAdminWrongEmployee() throws NotFoundException, HasAdminException {
		Employee e1 = ma.getEmployee("HU");
		Employee e2 = ma.getEmployee("MT");
		Project p1 = ma.getProject(150001);
		assertTrue(p1.getName() == "OAST Program");
		p1.addProjectAdmin(e1);
		assertFalse(e1.getProjects().isEmpty());
		assertTrue(e2.getProjects().isEmpty());
		try {
			p1.removeProjectAdmin(e2);
			fail("Exception should have been thrown");
		} catch(NotFoundException e) {
			assertEquals("Employee not admin for this project", e.getMessage());
		}
	}
}
