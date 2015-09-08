package SE1ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import SE1.HasAdminException;
import SE1.Project;
//Nina
public class ProjectAdminScreen extends Screen{
	int count = 0;
	ArrayList<Project> projects;
	
	public ProjectAdminScreen(Screen previousScreen) {
		super(previousScreen);
	}

	
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		projects = getAvailableProjects();
		count = 0;
		out.println("0) Exit.");
		out.println("1) Return.");
		if(!projects.isEmpty()) {
			out.println(" Projects with no administrator:");
			for(Project p : projects) {
				out.println(count+2 + ") " + p.getName()+".");
				count ++;
			}
		}else{
			out.println("All projects have administrators");
		}

	}
	
	@Override
	public boolean processInput(String selection, PrintWriter out) {
		if ("0".equals(selection)) {
			out.print("Exited.");
			return true;
		}else if("1".equals(selection)){
			maUI.setScreen(previousScreen);
		}else if(Integer.parseInt(selection) <= count+1){
					Project selectedProject = projects.get(Integer.parseInt(selection)-2);
					try {
						selectedProject.addProjectAdmin(employee);
						out.println(""+employee.getName()+" added as admin for "+selectedProject.getName());
					} catch (HasAdminException e) {
						out.println("Oops project already has admin");
					}

			}
	return false;
	}
	private ArrayList<Project> getAvailableProjects(){
		ArrayList<Project> output = new ArrayList<Project>();
		for(Project p:ma.getProjects()){
			if(!p.hasAdmin()){
				output.add(p);
			}
		}
		return output;
	}
	
}
