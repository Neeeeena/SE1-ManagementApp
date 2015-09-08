package SE1;
import java.util.ArrayList;

import SE1.Employee;
import SE1.Database;

public class Activity {
	
	private String name;
	private int ID; 
	private ArrayList<Employee> employees;
	private int expectedWeeklyWorkload = 0;
	private int startWeek;
	private int endWeek;
	private double actualWorkedHours;
	private double plannedWorkedHours;
	private ManApp ma;
	
	public Activity(int ID, String name, int expectedWeeklyWorkload, int startWeek, int endWeek) {
		this.ID = ID;
		this.name = name;
		employees = new ArrayList<Employee>();
		this.expectedWeeklyWorkload = expectedWeeklyWorkload;
		this.startWeek = startWeek;
		this.endWeek = endWeek;

	}
	//Nina
	public void setManApp(ManApp ma){
		this.ma = ma;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getID(){
		return ID;
	}
	
	//ALEXANDER
	public void setExpectedWorkload(int i) throws IllegalWorkloadException {
		if(i > 0) expectedWeeklyWorkload = i;
		else throw new IllegalWorkloadException("Unknown amount of hours entered");
	}
	
	//Nina
	public int getExpectedWorkload() {
		return expectedWeeklyWorkload;
	}
	
	//Nina
	public int getTotalExpectedWorkload(){
		int noOfWeeks = 0;
		if(startWeek<endWeek){
			noOfWeeks = endWeek-startWeek;
		}else{
			noOfWeeks = 52-startWeek + endWeek;
		}
		return noOfWeeks*expectedWeeklyWorkload;
	}
	//Nina
	public double getAvailableWork(){
		return expectedWeeklyWorkload-plannedWorkedHours;
	}
	
	//Nina
	public void addEmployeeToActivity(Employee employee, double hours) throws EmployeeNotAvailableException, InvalidTimeInputException {
		if(hours>0){
			if(!employee.saOnActivity(startWeek, endWeek)){
				if(employee.getActivities().size()<20){
					if(!employee.checkTooManyHours(startWeek,endWeek,hours)){
						employees.add(employee);
						employee.addActivity(this,hours);
						plannedWorkedHours += hours;
					}else{throw new EmployeeNotAvailableException("Employee has more than 37 hours of work in the time interval");}
				}else{throw new EmployeeNotAvailableException("Employee has more than 20 activities");}
			}else{throw new EmployeeNotAvailableException("Employee has a special activity in the time interval");}
		}else{throw new InvalidTimeInputException("Number must be positive");}
	}
	
	private boolean checkEmployeeAvailability(Employee employee, double hours) throws EmployeeNotAvailableException{
		if(!employee.saOnActivity(startWeek, endWeek)){
			if(employee.getActivities().size()<20){
				if(!employee.checkTooManyHours(startWeek, endWeek, hours)){
					return true;
				}
			}
		}
		return false;
				
		
	}
	
	//Nina
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	//ALEXANDER
	public void setStartAndEndWeek(int s, int e) throws IllegalWeeksException, EmployeeNotAvailableException {
		if(s > 0 && s < 53 && e < 53 && e > 0) {
			for(Employee em: employees){
				double hours = em.getActivities().get(this);
				if(em.checkTooManyHours(s, e, hours)){
					throw new EmployeeNotAvailableException(""+em.getName() +" is not available in the time interval");
				}
			}
			startWeek = s;
			endWeek = e;
		}
		else {
			throw new IllegalWeeksException("Not a valid week number (1 - 52)");
		}
		
	}
	
	//ALEXANDER
	public int getStartWeek() {
		return startWeek;
	}

	//ALEXANDER
	public int getEndWeek() {
		return endWeek;
	}
	
	//Nina
	public boolean containsWeek(int week){
		if(startWeek<endWeek && startWeek <= week && week<= endWeek){
			return true;
		}else if(startWeek>endWeek && (week>=startWeek || week <= endWeek)){
			return true;
		}return false;
	}
	
	//Nina
	public void deleteEmployee(Employee employee) throws NotFoundException {
		if(employees.contains(employee)){
			employees.remove(employee);
			employee.removeActivity(this);

		}else{throw new NotFoundException("Employee not found");}
		
	}
	
	//Nina
	public void addToWorkedHours(double hours){
		actualWorkedHours += hours;
	}
	
	//Nina
	public double getWorkedHours() {
		return actualWorkedHours;
	}
	//Nina
	public void endActivity() throws NotFoundException{
		while(employees.size()>0){
			deleteEmployee(employees.get(0));
		}
		expectedWeeklyWorkload = 0; 
	}
	//Nina
	public ArrayList<Employee> seeAvailableEmployees(double hours) throws EmployeeNotAvailableException {
		ArrayList<Employee> avEmplo = new ArrayList<Employee>();
		for(Employee e: ma.getEmployees()){
			if(checkEmployeeAvailability(e,hours) && !e.getActivities().containsKey(this)){
				avEmplo.add(e);
			}
		}
		return avEmplo;
	}
	
}
