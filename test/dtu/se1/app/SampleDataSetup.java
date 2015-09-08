package dtu.se1.app;


import org.junit.Before;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import SE1.EmployeeNotAvailableException;
import SE1.HasAdminException;
import SE1.InvalidTimeInputException;
import SE1.NotFoundException;
import SE1.Project;
import SE1.Employee;
import SE1.ManApp;
import SE1.Activity;
import SE1.ManCal;
public class SampleDataSetup {
	
	ManApp ma = new ManApp();
	
	//Nina
	@Before
	public void setUp() throws NotFoundException, EmployeeNotAvailableException, InvalidTimeInputException, HasAdminException{
		ma.createProject("MobilePay App");
		ma.createProject("OAST Program");
		ma.createProject("Tracker App");

		ma.addEmployee("JP","Jens Poulsen");
		ma.addEmployee("MT","Maria Thomsen");
		ma.addEmployee("HU","Hans Undergaard");
		ma.addEmployee("RK","Rasmus Kilested");
		
		ArrayList<Project> projects = ma.getProjects();
		for(Project p : projects){
			p.addActivity("Design",1,2,4);
			p.addActivity("Implementation",1,2,4);
			p.addActivity("Testing",1,2,4);
		}

		Employee e1 = ma.getEmployee("JP");
		(projects.get(0)).addProjectAdmin(e1);
		
		Employee hu = ma.getEmployee("HU");
		Project pt = ma.getProject(150001);
		ArrayList<Activity> as = pt.getActivities();
		as.get(0).addEmployeeToActivity(hu,1);
		as.get(1).addEmployeeToActivity(hu,1);
		
		ma.setCalendar(2015, Calendar.APRIL, 02);
	}

}
