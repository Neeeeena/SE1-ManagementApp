package SE1ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import SE1.Project;
//Nina
public class ProjectScreen extends Screen{
	
	public ProjectScreen(Screen previousScreen) {
		super(previousScreen);
	}
	Project project;
	
	
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("0) Exit.");
		out.println("1) Return to projects.");
		out.println("2) Manage Activity."); //selectActivityScreen
		out.println("3) Create new Activity.");
		out.println("4) Print status report.");
		
		//out.println("3) Go home."); hvad fanden er go home?
	}
	
	@Override
	public boolean processInput(String selection, PrintWriter out) {
		if ("0".equals(selection)) {
			out.print("Exited.");
			return true;
		}else if("1".equals(selection)){
			maUI.setScreen(previousScreen);
		}else if("2".equals(selection)) {
			SelectActivityScreen nextScreen = new SelectActivityScreen(this);
			nextScreen.setActivityList(project.getActivities());
			maUI.setScreen(nextScreen);
		}else if("3".equals(selection)){
			try {
				createNewActivity(out);
			} catch (IOException e) {
				out.println("Something bad happened");
			}
		}else if("4".equals(selection)) {
				out.println(project.generateStatusReport());
			}
		
	return false;
	}
	public void setProject(Project p) {
		project = p;
		
	}
	public void createNewActivity(PrintWriter out) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Please enter the name of the activity:");
		String name = in.readLine();
		out.println("Please enter the expected weekly workload of the activity:");
		int ew = readDoubleCheck(in, out, "Not a valid number", "Not a valid number", 0,10000);
		out.println("Please enter the start week:");
		int startWeek = readDoubleCheck(in, out, "Not a valid week number", "Not a valid week number", 0,53);
		out.println("Please enter the end week:");
		int endWeek = readDoubleCheck(in, out, "Not a valid week number", "Not a valid week number", 0,53);
		project.addActivity(name,ew,startWeek,endWeek);
		out.println("Activity created.");
	}
	

}
