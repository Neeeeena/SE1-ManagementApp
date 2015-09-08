package dtu.se1.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import SE1.Employee;
import SE1.ManApp;
import SE1.NotFoundException;

import java.util.ArrayList;

public class TestManAppFunctionality {
	//MATHIAS
	@Test
	public void testAddProject() {
		ManApp ma = new ManApp();
		assertTrue(ma.getProjects().isEmpty());
		ma.createProject("PROJECT1");
		assertFalse(ma.getProjects().isEmpty());
	}
	//MATHIAS
	@Test
	public void testAddEmployee() throws NotFoundException {
		ManApp ma = new ManApp();
		assertTrue(ma.getEmployees().isEmpty());
		ma.addEmployee("DJ", "Disc Jockey");
		ma.addEmployee("IJ", "Indiana Jones");
		ma.addEmployee("MKM", "MATHIAS KIRKESKOV MADSEN");
		assertFalse(ma.getEmployees().isEmpty());
		System.out.println(ma.getEmployee("IJ").getName());
		assertFalse(ma.getEmployee("DJ").getName().equals("SUPERDANK"));
		assertTrue(ma.getEmployee("IJ").getName().equals("Indiana Jones"));
	}
	//MATHIAS
	@Test
	public void testAddProjectToEmployee() throws NotFoundException {
		ManApp ma = new ManApp();
		ma.addEmployee("DJ", "Disc Jockey");
		ma.addEmployee("IJ", "Indiana Jones");
		ma.createProject("PROJECT1");
		assertTrue(ma.getEmployee("IJ").getProjects().isEmpty());
		ma.getEmployee("IJ").addProject(ma.getProject(150000));
		assertFalse(ma.getEmployee("IJ").getProjects().isEmpty());
		
	}
	//MATHIAS
	@Test
	public void testAddActivityToProject() throws NotFoundException {
		ManApp ma = new ManApp();
		ma.addEmployee("DJ", "Disc Jockey");
		ma.addEmployee("IJ", "Indiana Jones");
		ma.addEmployee("CTF", "Conan the Fartbarian");
		ma.createProject("PROJECT1");
		ma.createProject("PROJECT2");
		ma.getEmployee("IJ").addProject(ma.getProject(150000));
		assertTrue(ma.getProject(150000).getActivities().isEmpty());
		ma.getProject(150000).addActivity("Arnold Swartznegger's Big Great Manly Manhunt",1,1,1);
		ma.getProject(150000).addActivity("Get to da choppa",1,1,1);
		assertFalse(ma.getProject(150000).getActivities().isEmpty());
		assertFalse(ma.getEmployee("IJ").getProject(150000).getActivities().isEmpty());
		}	
	
	//ALEXANDER
	@Test
	public void testRemoveProject() throws NotFoundException {
		ManApp ma = new ManApp();
		assertTrue(ma.getProjects().isEmpty());
		for(int i = 0; i < 10; i++) {
			ma.createProject("PROJECT" + i);
		}
		assertTrue(ma.getProjects().size() == 10);
		
		ma.addEmployee("AL", "Alex Ander");
		ma.addEmployee("GO", "Geo Olsen");
		Employee go = ma.getEmployee("GO");
		go.addProject(ma.getProject(150004));
		assertTrue(go.getProjects().size()==1);
		ma.removeProject(150004);
		assertTrue(go.getProjects().size()==0);
		assertTrue(ma.getProjects().size() == 9);
	}
	
	//ALEXANDER
	@Test
	public void testRemoveProjectNotFound() throws NotFoundException {
		ManApp ma = new ManApp();
		assertTrue(ma.getProjects().isEmpty());
		for(int i = 0; i < 10; i++) {
			ma.createProject("PROJECT" + i);
		}
		assertTrue(ma.getProjects().size() == 10);
		
		ma.addEmployee("GO", "Geo Olsen");
		Employee go = ma.getEmployee("GO");
		go.addProject(ma.getProject(150004));
		assertTrue(go.getProjects().size()==1);
		try {
			ma.removeProject(150014);
			fail("Project not found should have been thrown");
		} catch (NotFoundException e) {
			assertEquals("Project not found", e.getMessage());
		}
		assertTrue(go.getProjects().size()==1);
		assertTrue(ma.getProjects().size() == 10);
	}
	
	//ALEXANDER
	@Test
	public void testRemoveProjectEmployeeFunction() throws NotFoundException {
		ManApp ma = new ManApp();
		assertTrue(ma.getProjects().isEmpty());
		for(int i = 0; i < 10; i++) {
			ma.createProject("PROJECT" + i);
		}
		assertTrue(ma.getProjects().size() == 10);
		
		ma.addEmployee("GO", "Geo Olsen");
		Employee go = ma.getEmployee("GO");
		go.addProject(ma.getProject(150004));
		assertTrue(go.getProjects().size()==1);
		try {
			go.removeProject(ma.getProject(150006));
			fail("Not found exception should have been found");
		} catch(NotFoundException e) {
			assertEquals("Project not found",e.getMessage());
		}
	}
	
	//ALEXANDER
	@Test
	public void testRemoveEmployee() throws NotFoundException {
		ManApp ma = new ManApp();
		assertTrue(ma.getEmployees().isEmpty());
		ma.addEmployee("KA", "Meeh");
		ma.addEmployee("BO", "bob");
		assertTrue(ma.getEmployees().size() == 2);
		ma.removeEmployee("KA");
		assertTrue(ma.getEmployees().size() == 1);
	}
	
	//ALEXANDER
	@Test
	public void testRemoveEmployeeNotFound() throws NotFoundException {
		ManApp ma = new ManApp();
		assertTrue(ma.getEmployees().isEmpty());
		ma.addEmployee("KA", "Meeh");
		assertTrue(ma.getEmployees().size() == 1);
		try {
			ma.removeEmployee("TH");
			fail("Employee not found should have been thrown");
		} catch (NotFoundException e) {
			assertEquals("Employee not found", e.getMessage());
		}
		assertTrue(ma.getEmployees().size() == 1);
	}
	
	//ALEXANDER
	@Test
	public void testEmployeeInSystem() throws NotFoundException {
		ManApp ma = new ManApp();
		assertTrue(ma.getEmployees().isEmpty());
		ma.addEmployee("MI", "Mikael Iler");
		ma.addEmployee("KA", "Meeh");
		ma.addEmployee("OP", "Ole Pedersen");
		ma.addEmployee("SG", "Signe Grill");
		assertTrue(ma.getEmployees().size() == 4);

		assertTrue(ma.employeeInSystem("OP"));
		assertFalse(ma.employeeInSystem("TK"));
	}	
}
