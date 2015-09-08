package dtu.se1.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import SE1.Employee;
import SE1.EmployeeNotAvailableException;
import SE1.InvalidTimeInputException;
import SE1.ManApp;
import SE1.NotFoundException;
import SE1.Project;
//Nina
public class SeeAvailableEmployees {
	ManApp ma = new ManApp();
	
	@Test
	public void testNoAvailableEmployees() throws NotFoundException, EmployeeNotAvailableException, InvalidTimeInputException{
		ma.createProject("OAST Program");
		ma.addEmployee("JP","Jens Poulsen");
		ma.addEmployee("MT","Maria Thomsen");
		
		Employee e1 = ma.getEmployee("JP");
		Project p = ma.getProjects().get(0);
		e1.addProject(p);
		p.addActivity("Design",1,2,4);
		p.addActivity("Implementation",37,2,4);
		p.addActivity("Test",37,1,6);
		p.getActivity("Implementation").addEmployeeToActivity(ma.getEmployee("MT"),37);
		p.getActivity("Test").addEmployeeToActivity(ma.getEmployee("JP"),37);
		assertTrue(p.getActivity("Design").seeAvailableEmployees(20).size()==0);
	}
	
	@Test
	public void testOneAvailableEmployee() throws NotFoundException, EmployeeNotAvailableException, InvalidTimeInputException{
		ma.createProject("OAST Program");
		ma.addEmployee("JP","Jens Poulsen");
		ma.addEmployee("MT","Maria Thomsen");
		
		Employee e1 = ma.getEmployee("JP");
		Project p = ma.getProjects().get(0);
		e1.addProject(p);
		p.addActivity("Design",1,2,4);
		p.addActivity("Test",37,1,6);
		p.getActivity("Test").addEmployeeToActivity(ma.getEmployee("JP"),37);
		assertTrue(p.getActivity("Design").seeAvailableEmployees(2).size()==1);
	}
	
	@Test
	public void testTwoAvailableEmployees() throws NotFoundException, EmployeeNotAvailableException{
		ma.createProject("OAST Program");
		ma.addEmployee("JP","Jens Poulsen");
		ma.addEmployee("MT","Maria Thomsen");
		
		Employee e1 = ma.getEmployee("JP");
		Project p = ma.getProjects().get(0);
		e1.addProject(p);
		p.addActivity("Design",1,2,4);
		assertTrue(p.getActivity("Design").seeAvailableEmployees(2).size()==2);
	}
	
	@Test
	public void testOverYear() throws NotFoundException, EmployeeNotAvailableException, InvalidTimeInputException{
		ma.createProject("OAST Program");
		ma.addEmployee("JP","Jens Poulsen");
		ma.addEmployee("MT","Maria Thomsen");
		
		Employee e1 = ma.getEmployee("JP");
		Project p = ma.getProjects().get(0);
		e1.addProject(p);
		p.addActivity("Design",37,50,4);
		p.getActivity("Design").addEmployeeToActivity(ma.getEmployee("MT"),37);
		assertTrue(p.getActivity("Design").seeAvailableEmployees(2).size()==1);
	}
		
}
