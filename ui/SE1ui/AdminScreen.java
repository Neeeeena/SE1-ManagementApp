package SE1ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import SE1.ManApp;
import SE1.NotFoundException;

//Nina
public class AdminScreen extends Screen {

	public AdminScreen(Screen previousScreen) {
		super(previousScreen);
	}

	ManApp ma;
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("0) Exit");
		out.println("1) Return");
		out.println("2) Add Project");
		out.println("3) Add Employee");
		out.println("4) Remove Project");
		out.println("5) Remove Employee");

	}
	

	@Override
	public boolean processInput(String selection, PrintWriter out) {
		if ("0".equals(selection)) {
			out.print("Exited.");
			return true;
		}else if("1".equals(selection)){
			maUI.setScreen(previousScreen);
		}else if("2".equals(selection)){
			try {
				addProject(out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("3".equals(selection)){
			try {
				addEmployee(out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("4".equals(selection)){
			try {
				removeProject(out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("5".equals(selection)){
			try {
				removeEmployee(out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	return false;
	}
	
	public void setManApp(ManApp ma){
		this.ma = ma;
	}
	
	public void addProject(PrintWriter out) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Enter a project name:");
		String name = in.readLine();
		ma.createProject(name);
		out.println("Project added");
	}
	
	public void removeProject(PrintWriter out) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Please enter the ID of the project to be deleted:");
		int ID = readInt(in, out, "This is not a valid project ID, try again:");
		try {
			ma.removeProject(ID);
			out.println("Project removed.");
		} catch (NotFoundException e) {
			out.println("Project not found");		
		}
	}
	
	public void addEmployee(PrintWriter out) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Enter the name of the employee:");
		String name = in.readLine();
		out.println("Enter the initials of the employee:");
		boolean okay = false;
		String ini = null;
		while(!okay){
			ini = in.readLine();
			if(!ma.employeeInSystem(ini)){
				okay = true;
			}else{
				out.println("Initials are already in the system, please enter another");
			}
		}
		ma.addEmployee(ini,name);
		
	}
	
	public void removeEmployee(PrintWriter out) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Enter the initials of the employee to be deleted:");
		String ini = in.readLine();
		try{
			ma.removeEmployee(ini);
			out.println("Employee removed");
		}catch(NotFoundException e){
			out.println("Initials are not in the system");
		}
	}

}
