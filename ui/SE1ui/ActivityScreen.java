package SE1ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;

import SE1.Activity;
import SE1.Employee;
import SE1.EmployeeNotAvailableException;
import SE1.IllegalWeeksException;
import SE1.IllegalWorkloadException;
import SE1.InvalidTimeInputException;
import SE1.NotFoundException;

public class ActivityScreen extends Screen{
	
	Activity activity;
	
	public ActivityScreen(Screen previousScreen) {
		super(previousScreen);
	}

	//ALEXANDER
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("0) Exit.");
		out.println("1) Return.");
		out.println("2) Add employee to the activity.");
		out.println("3) Set start and end week.");
		out.println("4) Set expected workload.");
		out.println("5) Remove an employee.");
		//return er vel searchActivityScreen?
	}
	
	//ALEXANDER
	@Override
	public boolean processInput(String selection, PrintWriter out) {
		if ("0".equals(selection)) {
			out.print("Exited.");
			return true;
		}else if("1".equals(selection)) {
			maUI.setScreen(previousScreen);
		}else if("2".equals(selection)){
			try {
				addEmployee(out);
			} catch (EmployeeNotAvailableException | IOException | InvalidTimeInputException e) {
				out.println("Something bad happened");
			}
		}else if("3".equals(selection)){
			try {
				setStartAndEndWeek(out);
			} catch(IOException | IllegalWeeksException e) {
				out.println("Something bad happened");
			}
		}else if("4".equals(selection)){
			try {
				setExpectedWorkload(out);
			} catch (IOException | IllegalWorkloadException e) {
				out.println("Something bad happened");
			}
		}else if("5".equals(selection)){
			try {
				removeEmployee(out);
			} catch (IOException | NotFoundException e) {
				out.println("Something bad happened");
			}
		}
	return false;
	}
	
	//ALEXANDER
	private void addEmployee(PrintWriter out) throws EmployeeNotAvailableException, IOException, InvalidTimeInputException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Choose amount of work the employee should work per week");
		boolean okay = false;
		double t = 0;
		while(!okay) {
			t = readDouble(in, out, "Not a valid number");
			if(!(t > 37 || t <= 0)) {
				okay = true;
			}
			else {
				out.println("Work needs to be between 1 and 37 hours");
			}
		}
		
		out.println("Here's a list of avaible employees");
		for(Employee e : activity.seeAvailableEmployees(t)) {
			out.println(e.getInitials() + "  " + e.getName());
		}
		out.println("Choose an employee from the list by using his initials (0 to return)");
		okay = false;
		Employee emp = null;
		while(!okay) {
			String initials = in.readLine();
			if(initials.equals("0")) return;
			for(Employee e : activity.seeAvailableEmployees(t)) {
				if(initials.equals(e.getInitials())) {
					okay = true;
					emp = e;
				}
			}
			if(!okay) {
				out.println("Employee not found");
			}			
		}
		activity.addEmployeeToActivity(emp, t);		
		out.println("Employee ID: "+ emp.getInitials() + " added to activity with weekly workload: " + t);
	}
	
	//ALEXANDER
	private void setStartAndEndWeek(PrintWriter out) throws IOException, IllegalWeeksException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Choose the start week");
		int startWeek = -1;
		int endWeek = -1;
		while(startWeek == -1) {
			startWeek = readInt(in, out, "Not a number");
			if(startWeek < 1 || startWeek > 52) {
				startWeek = -1;
				out.println("Number needs to bee a weeknumber (1-52)");
			}
		}
		out.println("Choose the end week");
		while(endWeek == -1) {
			endWeek = readInt(in, out, "Not a number");
			if(endWeek < 1 || endWeek > 52) {
				endWeek = -1;
				out.println("Number needs to bee a weeknumber (1-52)");
			}
		}
		try {
			activity.setStartAndEndWeek(startWeek, endWeek);
			out.println("Startweek and endweek (" + startWeek + "," + endWeek + ") chosen for activity ID: " + activity.getID());
		} catch (EmployeeNotAvailableException e) {
			out.println(e.getMessage());
		}
		
	}
	
	//ALEXANDER
	private void setExpectedWorkload(PrintWriter out) throws IOException, IllegalWorkloadException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Choose the expected weekly workload");
		int eW = -1;
		while(eW == -1) {
			eW = readInt(in,out,"Not a number");
			if(eW <= 0) {
				eW = -1;
				out.println("Workload should be bigger than zero");
			}
		}
		activity.setExpectedWorkload(eW);
		out.println("Weekly workload for activity ID: "+ activity.getID() + " updated to: " + eW);
	}
	
	//ALEXANDER
	private void removeEmployee(PrintWriter out) throws IOException, NotFoundException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Here's a list of employees added working on the activity");
		for(Employee e : activity.getEmployees()) {
			out.println(e.getInitials() + " name: " + e.getName());
		}
		out.println("Choose an employee from the list by using his initials (0 to return)");
		boolean okay = false;
		Employee emp = null;
		while(!okay) {
			String initials = in.readLine();
			if(initials.equals("0")) return;
			for(Employee e : activity.getEmployees()) {
				if(initials.equals(e.getInitials())) {
					okay = true;
					emp = e;
				}
			}
			if(!okay) {
				out.println("Employee not found");
			}			
		}
		activity.deleteEmployee(emp);	
		out.println("Employee ID: "+ emp.getInitials() + " removed from activity");
	}

	//Nina, yes jeg fik den!
	public void setActivty(Activity selectedActivity) {
		activity = selectedActivity;
		
	}

}
