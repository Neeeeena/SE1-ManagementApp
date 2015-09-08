package dtu.se1.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import SE1.Activity;
import SE1.Employee;
import SE1.EmployeeNotAvailableException;
import SE1.IllegalWorkloadException;
import SE1.InvalidTimeInputException;
import SE1.NotFoundException;
import SE1.Project;
//Nina
public class DeleteEmployeeFromActivity extends SampleDataSetup{
	

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
	public void testDeleteEmployeeFromActivity() throws EmployeeNotAvailableException, NotFoundException, IllegalWorkloadException, InvalidTimeInputException{
		Employee e1 = ma.getEmployee("JP");
		ArrayList<Project> ps = e1.getProjects();
		Project cp = null;
		for(Project p : ps){
			if( p.getName() == "MobilePay App"){
				cp = p;
			}
		}
			
		Activity a = cp.getActivity("Design");
		a.setExpectedWorkload(10);
		Employee mt = ma.getEmployee("MT");
		a.addEmployeeToActivity(mt,5);
		assertTrue(mt.getWorkload(3)==5);
		assertTrue(a.getEmployees().size()>0);
		a.deleteEmployee(ma.getEmployee("MT"));
		assertTrue(mt.getWorkload(3)==0);
		assertTrue(a.getEmployees().size()==0);
	}

	@Test
	public void testEmployeeNotOnActivity() throws NotFoundException, EmployeeNotAvailableException, InvalidTimeInputException{
		Employee e1 = ma.getEmployee("JP");
		ArrayList<Project> ps = e1.getProjects();
		Project cp = null;
		for(Project p : ps){
			if( p.getName() == "MobilePay App"){
				cp = p;
			}
		}
		Activity a = cp.getActivity("Implementation");
		a.addEmployeeToActivity(ma.getEmployee("MT"),1);
		assertTrue(a.getEmployees().size()==1);
		try{
			a.deleteEmployee(ma.getEmployee("HU"));
			fail("A NotFoundException should have been thrown");
		}catch(NotFoundException e){
			assertEquals("Employee not found", e.getMessage());
		}
		assertTrue(a.getEmployees().size()==1);
	}



		


}
