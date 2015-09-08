package SE1;
import SE1.Activity;

import java.util.ArrayList;
import java.util.Calendar;

public class Project {
	private int latestActivity = 0;
	private String name;
	private int ID;
	private boolean hasAdmin = false;
	private ManApp ma;
	
	private ArrayList<Activity> activities = new ArrayList<Activity>();	
	//ALEXANDER
	public Project(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}
	//Nina
	public void setManApp(ManApp manApp){
		ma = manApp;
	}
	//ALEXANDER
	public String getName(){
		return name;
	}
	
	//MATHIAS
	public void addActivity(String name, int expectedWorkload, int start, int end) {
		int aID = Integer.parseInt(""+ID+latestActivity);
		Activity a = new Activity(aID, name, expectedWorkload, start, end);
		a.setManApp(ma);
		activities.add(a);
		latestActivity++;		
	}
	//ALEXANDER
	public ArrayList<Activity> getActivities() {
		return activities;
	}
	//Nina
	public Activity getActivity(String string) throws NotFoundException {
		for(Activity activity : activities) {
			if(activity.getName().equals(string)) {
				return activity;
			}
		}
		throw new NotFoundException("Activity was not found");
	}
	//MATHIAS
	public int getID() {
		return ID;
	}
	
	//ALEXANDER
	public boolean hasAdmin() {
		return hasAdmin;
	}
	
	//ALEXANDER
	public void addProjectAdmin(Employee e1) throws HasAdminException{
		if(!hasAdmin) {
			hasAdmin = true;
			e1.addProject(this);
		}
		else {
			throw new HasAdminException("Project already has admin");
		}
	}
	
	//ALEXANDER
	public void removeProjectAdmin(Employee e1) throws HasAdminException, NotFoundException{
		if(hasAdmin) {
			if(e1.getProjects().contains(this)) {
				hasAdmin = false;
				e1.removeProject(this);
			}
			else {
				throw new NotFoundException("Employee not admin for this project");
			}
		}
		else {
			throw new HasAdminException("Project has no admin");
		}
	}
	
	//ALEXANDER
	public String generateStatusReport() {
		String output = printProject() + printActivities();
		return output;
	}
	
	//ALEXANDER
	private String printActivities() {
		String output = "";
		for(Activity a : activities) {
			output += (a.getName() + "  Startweek: " + a.getStartWeek() + "  Endweek: " + a.getEndWeek() + "  Expected workload: "
							+ a.getTotalExpectedWorkload() + "  Worked hours: " + a.getWorkedHours() + " Remaining work: " + (a.getTotalExpectedWorkload()-a.getWorkedHours()) +"\n");
		}
		return output;
	}
	
	//ALEXANDER
	private String printProject() {
		String output = "";
		output += ("Statusreport for project: " + ID + ", " + name + "\n");	
		return output;
	}
	//Nina
	public void clearEndedActivities() throws NotFoundException{
		for(Activity a: activities){
			if(a.getEndWeek()<ma.getCalendar().get(Calendar.WEEK_OF_YEAR)){
				a.endActivity();
			}
		}
	}
	//Nina
	public void clearAllActivities() throws NotFoundException{
		for(Activity a: activities){
			a.endActivity();
		}
	}
	
}
