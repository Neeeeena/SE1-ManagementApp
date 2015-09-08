package SE1ui;

import java.io.IOException;
import java.io.PrintWriter;

import SE1.Employee;

public class UserScreen extends Screen {
	
	Employee employee;
	
	public UserScreen(Screen previousScreen) {
		super(previousScreen);
	}
	//Nina
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("0) Exit.");
		out.println("1) Return.");
		out.println("2) Manage projects.");
		out.println("3) Manage special activities.");
		out.println("4) Manage time registration.");
		out.println("5) Become a project administrator.");
	}
	//Nina
	@Override
	public boolean processInput(String selection, PrintWriter out) {
		if ("0".equals(selection)) {
			out.print("Exited.");
			return true;
		}else if("1".equals(selection)){
			maUI.setScreen(previousScreen);
		}
		else if("2".equals(selection)) {
			SelectProjectScreen nextScreen = new SelectProjectScreen(this);
			nextScreen.setProjectList(employee.getProjects());
			maUI.setScreen(nextScreen);
		}else if("3".equals(selection)) {
			MyCalendarScreen nextScreen = new MyCalendarScreen(this);
			nextScreen.setMyC(employee.getMyCalendar());
			maUI.setScreen(nextScreen);
		}else if("4".equals(selection)) {
			WorkRegistrationScreen nextScreen = new WorkRegistrationScreen(this);
			nextScreen.setWR(employee.getWorkRegistrator());
			nextScreen.setActiList(employee.getActivities());
			maUI.setScreen(nextScreen);
		}else if("5".equals(selection)){
			ProjectAdminScreen nextScreen = new ProjectAdminScreen(this);
			nextScreen.setEmployee(employee);
			maUI.setScreen(nextScreen);
		}
	return false;
	}

	public void setEmployee(Employee e) {
		employee = e;
		
	}
}
