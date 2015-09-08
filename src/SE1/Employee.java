package SE1;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import SE1.MyCalendar;
import SE1.Project;


public class Employee {
	private String initials;
	private String name;
	private MyCalendar myCalendar;
	private ArrayList<Project> projects;
	private HashMap<Activity,Double> activities;
	private WorkRegistrator workRegistrator;
	private ManApp manApp;
	
	
	public Employee(String initials, String name) {
		this.name = name;
		this.initials = initials;
		projects = new ArrayList<Project>();
		activities = new HashMap<Activity,Double>();
		workRegistrator = new WorkRegistrator();
		myCalendar = new MyCalendar(this);
	}
	//Nina
	public void setManApp(ManApp ma){
		manApp = ma;
		workRegistrator.setManApp(ma);
	}
	//Check if an employee already has 37 hours planned for a week during the activity.
	//Nina
	public boolean checkTooManyHours(int week1, int week2, double hours){
		if(week1<week2){
			for(int i = week2-week1;i>=0;i--){
				if(getWorkload(week1+i)+hours>37){
					return true;
				}
			}
		}else if(week1>week2){			
			for(int i = week1; i<52;i++){
				if(getWorkload(i)+hours>37){
					return true;
				}
			}
			for(int i = 0; i<=week2;i++){
				if(getWorkload(i)+hours>37){
					return true;
				}
			}
		}
		return false;
	}
	//Nina
	public double getWorkload(int week){
		int weeklyWorkload = 0;
		for(Activity a: activities.keySet()){
			if(a.containsWeek(week)){
				weeklyWorkload += activities.get(a);
			}
		}
		return weeklyWorkload;
	}
	//MATHIAS
	public void addProject(Project project) {
		projects.add(project);
	}
	//MATHIAS
	public String getInitials() {
		return initials;
	}
	//MATHIAS
	public String getName() {
		return name;
	}
	
	//MATHIAS
	public Project getProject(int ID) throws NotFoundException {
		for(Project project : projects) {
			if(project.getID() == ID) {
				return project;
			}
		}
		throw new NotFoundException("Project was not found");
	}
	
	//MATHIAS
	public ArrayList<Project> getProjects() {
		return projects;
	}
	//Nina
	public void addActivity(Activity activity,Double hours){
		activities.put(activity,hours);
	}
	//Nina
	public void removeActivity(Activity activity){
		activities.remove(activity);
	}
	//Nina
	public HashMap<Activity,Double> getActivities() {
		return activities;
	}
	public Activity getActivity(int ID) throws NotFoundException{
		for(Activity a: activities.keySet()){
			if(a.getID()==ID){
				return a;
			}
		}throw(new NotFoundException("Activity not found"));
	}
	//Nina
	public double getMonthlyWorked() {
		return workRegistrator.getMonthlyWorked();
	}
	//Nina
	public void registerWork(Activity a1, double d, String date) throws ParseException, InvalidTimeInputException {
		workRegistrator.registerWork(a1, d, date);
	}
	//Nina
	public void editRegisteredWork(Activity a1, double d, String date) throws ParseException, NotFoundException{
		workRegistrator.editRegisteredWork(a1, d, date);
	}
	//Nina
	public void deleteRegisteredWork(Activity a1, String date) throws ParseException, NotFoundException{
		workRegistrator.deleteRegisteredWork(a1, date);
	}
	
	public void removeProject(Project p) throws NotFoundException{
		if(projects.contains(p)){
			projects.remove(p);
		}else{
			throw new NotFoundException("Project not found");
		}
	}	
	
	public MyCalendar getMyCalendar() {
		return myCalendar;
	}	
	//Nina
	public String seeTodaysWork() {
		String output = workRegistrator.printTodaysWork();
		return output;
	}
	
	//ALEXANDER
	public String seeSpecificDaysWork(String date) throws ParseException {
		String output = workRegistrator.printSpecificDaysWork(date);
		return output;
	}
	
	//ALEXANDER
	public String seeSpecificDaysWorkDecemberLastYear(int date) throws ParseException {
		String output = workRegistrator.printSpecificDaysWorkDecemberLastYear(date);
		return output;		
	}
	
	//ALEXANDER
	public double getMonthlyWorkedDecemberLastYear() {
		return workRegistrator.getMonthlyWorkedDecemberLastYear();
	}
	
	//Special activity colliding with a activity
	//Nina
	public boolean saOnActivity(int week1, int week2) {
		return myCalendar.onActivity(week1,week2);
	}
	
	//MATHIAS
	public ManApp getManApp() {
		return manApp;
	}
	//Nina
	public WorkRegistrator getWorkRegistrator() {
		return workRegistrator;
	}
	
}
