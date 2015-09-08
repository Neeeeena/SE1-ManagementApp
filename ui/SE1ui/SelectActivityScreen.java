package SE1ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import SE1.Activity;
import SE1.Project;

public class SelectActivityScreen extends Screen{
	
	public SelectActivityScreen(Screen previousScreen) {
		super(previousScreen);
	}

	int count = 0;
	ArrayList<Activity> activities = new ArrayList<Activity>();
	//Nina
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		count = 0;
		out.println("0) Exit.");
		out.println("1) Return.");
		out.println("Choose an activity: " );
		for(Activity a : activities) {
			out.println(count+2 + ") " + a.getName()+".");
			count ++;
		}
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
				Activity selectedActivity = activities.get(Integer.parseInt(selection)-2);
				ActivityScreen nextScreen = new ActivityScreen(this);
				nextScreen.setActivty(selectedActivity);
				maUI.setScreen(nextScreen);
				}
			
	return false;
	}
	
	
	//Nina
	public void setActivityList(ArrayList<Activity> activities) {
		this.activities = activities;
		
	}
}
