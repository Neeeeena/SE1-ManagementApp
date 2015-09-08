package SE1ui;

import SE1.HasAdminException;
import SE1.IllegalWeeksException;
import SE1.InvalidTimeInputException;
import SE1.ManApp;
import SE1.Employee;
import SE1.Project;
import SE1.Activity;
import SE1.NotFoundException;
import SE1.EmployeeNotAvailableException;
import SE1.IllegalActionException;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.ArrayList;

import SE1.MyCalendar;


public class SampleData {
	ManApp ma;
	
	//MATHIAS
	public SampleData() throws NotFoundException, EmployeeNotAvailableException, IllegalActionException, InvalidTimeInputException, HasAdminException, IllegalWeeksException {
		ma = new ManApp();
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
		(projects.get(0)).addProjectAdmin(e1);;
		
		Employee hu = ma.getEmployee("HU");
		Project pt = ma.getProject(150001);
		ArrayList<Activity> as = pt.getActivities();
		as.get(0).addEmployeeToActivity(hu,1);
		as.get(1).addEmployeeToActivity(hu,1);
		
		for(Employee emp : ma.getEmployees()) {
			emp.getMyCalendar().addSpecialActivity(2014,5,2,10,1);
			emp.getMyCalendar().addSpecialActivity(2014,7,1,2,3);
		}
		
		Employee e2 = ma.getEmployee("MT");
		Activity a2 = e1.getProject(150000).getActivity("Implementation");
		a2.setStartAndEndWeek(5, 6);
		a2.addEmployeeToActivity(e2, 37);
		
		
		ma.setCalendar(2015, Calendar.JANUARY, 01);
	}
}
