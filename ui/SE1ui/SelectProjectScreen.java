package SE1ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import SE1.Project;
import SE1.Employee;

public class SelectProjectScreen extends Screen {
	

	ArrayList<Project> projects;
	int count = 0;
	
	public SelectProjectScreen(Screen previousScreen) {
		super(previousScreen);
	}

	//Nina
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		count = 0;
		out.println("0) Exit.");
		out.println("1) Return.");
		if(!projects.isEmpty()) {
			out.println(" Projects that you lead:");
			for(Project p : projects) {
				out.println(count+2 + ") " + p.getName()+".");
				count ++;
			}
		}else{out.println("You lead no projects");}

	}
	
	//MATHIAS
	@Override
	public boolean processInput(String selection, PrintWriter out) {
		if ("0".equals(selection)) {
			out.print("Exited.");
			return true;
		}else if("1".equals(selection)){
			maUI.setScreen(previousScreen);
		}else if(Integer.parseInt(selection) <= count+1){
					Project selectedProject = projects.get(Integer.parseInt(selection)-2);
					ProjectScreen nextScreen = new ProjectScreen(this);
					nextScreen.setProject(selectedProject);
					maUI.setScreen(nextScreen);
			
			}
	return false;
	}
	//Nina
	public void setProjectList(ArrayList<Project> projects) {
		this.projects = projects;
		
	}

	
}
