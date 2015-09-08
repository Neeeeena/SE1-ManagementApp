package SE1;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

//import SE1.Login;
import SE1.Database;

public class ManApp {
	private int latestProject = 0;
//	private Login login;
	private Database db;
	private ManCal calendar;
	private boolean hasBeenResat = true;	
	
	//Nina
	public ManApp(){
		db = new Database();
		calendar = new ManCal(2015, ManCal.JANUARY,1);
	}
	//Nina
	public void createProject(String name) {
		int proNo = (calendar.get(ManCal.YEAR)-2000)*10000 + latestProject;
		Project pTemp = new Project(proNo, name);
		pTemp.setManApp(this);
		db.saveProject(pTemp);
		latestProject++;
	}
	//Nina
	public void addEmployee(String initials, String name) {
		Employee eTemp = new Employee(initials, name);
		eTemp.setManApp(this);
		db.saveEmployee(eTemp);
	}
	//Nina
	public ArrayList<Project> getProjects() {
		return db.getProjects();
	}
	//Nina
	public Employee getEmployee(String initials) throws NotFoundException {
		return db.getEmployee(initials);
	}
	//Nina
	public Project getProject(int ID) throws NotFoundException {
		return db.getProject(ID);
	}
	
	//Nina
	public ArrayList<Employee> getEmployees() {
		return db.getEmployees();
	}
	
	//Nina
	public ManCal getCalendar(){
		return calendar;
	}
	//Nina	
	public void setCalendar(int year, int month, int dayOfMonth) {
		calendar = new ManCal(year, month, dayOfMonth);
		
	}
	//MATHIAS
	public void setCalendar(ManCal calendar) { 
		this.calendar = calendar;
	}
	
	//ALEXANDER
	public void removeProject(int ID) throws NotFoundException {
		Project pRemove = null;
		for(Project p : db.getProjects()) {
			if(p.getID() == ID) {
				pRemove = p;
				break;
			}
		}
		if(pRemove != null){
			pRemove.clearAllActivities();
			db.getProjects().remove(pRemove);
			for(Employee e: getEmployees()){
				if(e.getProjects().contains(pRemove)){
					e.removeProject(pRemove);
				}
			}
		}else{throw new NotFoundException("Project not found");}
	}
	
	//ALEXANDER
	public void removeEmployee(String initials) throws NotFoundException {
		Employee eRemove = null;
		for(Employee e : getEmployees()) {
			if(e.getInitials().equals(initials)) {
				eRemove = e;
				break;
			}
		}
		if(eRemove != null){db.getEmployees().remove(eRemove);}
		else{throw new NotFoundException("Employee not found");}
	}
	
	//ALEXANDER
	public boolean getHasBeenResat() {
		return hasBeenResat; 
	}
	
	//ALEXANDER
	public void decemberResetCheck() {
		if(calendar.get(ManCal.MONTH) == ManCal.DECEMBER && hasBeenResat) {
			hasBeenResat = false;
		}
				
	}
	
	//ALEXANDER
	public void checkNeedToClearRegisteredWork() throws ParseException {
		if(!hasBeenResat && calendar.get(ManCal.MONTH) == ManCal.JANUARY) {
			System.out.println("Resetting Registered Work");
			hasBeenResat = true;
			saveRegisteredWorkFromDecember();
			clearRegisteredWork();			
			System.out.println("Resetting complete");
		}
	}
	
	//ALEXANDER
	private void saveRegisteredWorkFromDecember() {
		for(Employee e : getEmployees()) {
			for(int i = 0; i < 366; i++) {
				e.getWorkRegistrator().saveRegisteredWork();
			}
		}
	}
	
	//ALEXANDER
	private void clearRegisteredWork() {
		for(Employee e : getEmployees()) {
			for(int i = 0; i < 366; i++) {
				e.getWorkRegistrator().resetWork();
			}
		}
	}
	//Nina
	public boolean employeeInSystem(String initials){
		for(Employee e: getEmployees()){
			if(e.getInitials().equals(initials)){
				return true;
			}
		}return false;
	}

	
}
