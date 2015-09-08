package SE1ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import SE1.InvalidTimeInputException;
import SE1.NotFoundException;
import SE1.WorkRegistrator;
import SE1.Activity;

public class WorkRegistrationScreen extends Screen{
	
	
	WorkRegistrator wr;
	HashMap<Activity,Double> activities;
	
	public WorkRegistrationScreen(Screen previousScreen) {
		super(previousScreen);
	}

	
	//Nina
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("0) Exit");
		out.println("1) Return");
		out.println("2) Register time today");
		out.println("3) See registered work today");
		out.println("4) Edit registered time");
		out.println("5) Delete registered time");
		out.println("6) Register old time");
		out.println("7) See earlier registered work");
		out.println("8) See registered work for december last year");
	}
	//Nina
	@Override
	public boolean processInput(String selection, PrintWriter out) {
		if ("0".equals(selection)) {
			out.print("Exited.");
			return true;
		}else if("1".equals(selection)) {
			maUI.setScreen(previousScreen);
		}else if("2".equals(selection)){
			try {
				registerTimeToday(out);
			} catch (IOException | ParseException e) {
				out.println("Something bad happened");
			}
		}else if("3".equals(selection)){
			seeRegisteredTimeToday(out);
		}else if("4".equals(selection)){
			try {
				editRegisteredTime(out);
			} catch (IOException e) {
				out.println("Something bad happened");
			}
		}else if("5".equals(selection)){
			try {
				deleteRegisteredTime(out);
			} catch (IOException e) {
				out.println("Something bad happened");
			}
		}
		else if("6".equals(selection)){
			try {
				registerOldTime(out);
			} catch (IOException e) {
				out.println("Something bad happened");
			}
		}
		else if("7".equals(selection)){
			try {
				seeRegisteredTime(out);
			} catch (IOException | ParseException e) {
				out.println("Something bad happened");
			}
		}
		else if("8".equals(selection)){
			try {
				seeRegisteredTimeFromDecemberLastYear(out);
			} catch (IOException | ParseException e) {
				out.println("Something bad happened");
			}
		}
	return false;
	}
	
	//ALEXANDER
	private void seeRegisteredTimeFromDecemberLastYear(PrintWriter out) throws IOException, ParseException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Please enter the day in december (1st - 31st) you wish to see the registered work");
		int date = 0;
		while(date < 1 || date > 31) {
			date = readInt(in,out,"Not a number");
			if(date < 1 || date > 31) {
				out.println("Not a valid day in december");
			}
		}
		out.println(wr.printSpecificDaysWorkDecemberLastYear(date));
	}


	//Nina
	public void setWR(WorkRegistrator workRegistrator) {
		wr = workRegistrator;
		
	}
	//Nina
	public void registerTimeToday(PrintWriter out) throws IOException, ParseException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("These are your activities: ");
		for(Activity a: activities.keySet()){
			out.println("ID: "+a.getID() + ", Name: " + a.getName() + ", Expected work: " + activities.get(a));
		}
		out.println("Please enter the activity ID either from the above list or for an activity you helped a colleague with");
		int aID = readInt(in, out, "This is not a valid activity ID, try again:");
		Activity a= null;
		try{
			a=checkActivityID(aID);
		}catch(NotFoundException e){
			out.println("The ID entered is not in the system");
			return;
		}
		
		out.println("Please enter the number of hours you worked on the activity");
		double t = readDouble(in, out, "Not a valid number");
		
		try {
			wr.registerWork(a, t, "today");
		} catch (InvalidTimeInputException e) {
			out.println("Not a valid time input");
		}
		out.println("Time registered");
	}
	//Nina
	public void seeRegisteredTimeToday(PrintWriter out){
		out.println(wr.printTodaysWork());
	}
	//Nina
	public void seeRegisteredTime(PrintWriter out) throws IOException, ParseException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Please enter the date where you wish to see your work registration in the format YYYYMMDD");
		String date = in.readLine();
		if(!checkValidDate(date)){
			out.println("Not a valid date!");
			return;
		}
		out.println(wr.printSpecificDaysWork(date));
	}
	//Nina
	public void editRegisteredTime(PrintWriter out) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Please enter the date where you wish to edit your work registration in the format YYYYMMDD");
		String date = in.readLine();
		if(!checkValidDate(date)){
			out.println("Not a valid date!");
			return;
		}
		out.println("These are your activities: ");
		for(Activity a: activities.keySet()){
			out.println("ID: "+a.getID() + ", Name: " + a.getName() + ", Expected work: " + activities.get(a));
		}
		out.println("Please enter the activity ID either from the above list or for an activity you helped a colleague with");

		int aID = readInt(in, out, "This is not a valid activity ID, try again:");
		Activity a= null;
		try{
			a=checkActivityID(aID);
		}catch(NotFoundException e){
			out.println("The ID entered is not in the system");
			return;
		}
		out.println("Please enter the number of hours you worked on the activity");
		double t = readDouble(in, out, "Not a valid number");
		
		try{
			wr.editRegisteredWork(a, t, date);
			out.println("Registered work edited.");
		}catch(NotFoundException e){
			out.println("Registered work not found");
		} catch (ParseException e) {
			out.println("Not a valid date");
		}
	}
	//Nina
	public void deleteRegisteredTime(PrintWriter out) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Please enter the date where you wish to delete your work registration in the format YYYYMMDD");
		String date = in.readLine();
		if(!checkValidDate(date)){
			out.println("Not a valid date!");
			return;
		}
		out.println("These are your activities: ");
		for(Activity a: activities.keySet()){
			out.println("ID: "+a.getID() + ", Name: " + a.getName() + ", Expected work: " + activities.get(a));
		}
		out.println("Please enter the activity ID either from the above list or for an activity you helped a colleague with");

		int aID = readInt(in, out, "This is not a valid activity ID, try again:");
		Activity a= null;
		try{
			a=checkActivityID(aID);
		}catch(NotFoundException e){
			out.println("The ID entered is not in the system");
			return;
		}
		
		try{
			wr.deleteRegisteredWork(a, date);
			out.println("Registered work edited.");
		}catch(NotFoundException e){
			out.println("Registered work not found");
		} catch (ParseException e) {
			out.println("Not a valid date");
		}
	}
	//Nina
	public void registerOldTime(PrintWriter out) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Please enter the date where you wish to edit your work registration in the format YYYYMMDD");
		String date = in.readLine();
		if(!checkValidDate(date)){
			out.println("Not a valid date!");
			return;
		}
		out.println("These are your activities: ");
		for(Activity a: activities.keySet()){
			out.println("ID: "+a.getID() + ", Name: " + a.getName() + ", Expected work: " + activities.get(a));
		}
		out.println("Please enter the activity ID");
		int aID = readInt(in, out, "This is not a valid activity ID, try again:");
		Activity a= null;
		try{
			a=checkActivityID(aID);
		}catch(NotFoundException e){
			out.println("The ID entered is not in the system");
			return;
		}
		out.println("Please enter the number of hours you worked on the activity");
		double t = readDouble(in, out, "Not a valid number");
		
		try{
			wr.registerWork(a, t, date);
			out.println("Time registered");
		}catch (ParseException e) {
			out.println("Not a valid date");
		} catch (InvalidTimeInputException e) {
			out.println("Not a valid Time");
		}
	
	}
	//Nina
	public void setActiList(HashMap<Activity, Double> activities) {
		this.activities = activities;
		
	}
	//Nina
	private Activity checkActivityID(int ID) throws NotFoundException{
		for(Activity a: activities.keySet()){
			if(a.getID()== ID){
				return a;
			}
		}
		throw new NotFoundException("Activity not found");
	}
	//Nina
	private boolean checkValidDate(String date){ //Udvid!
		if(isNumber(date)&&date.length()==8){
			int temp = Integer.parseInt(date);
			if(20100101 <= temp && temp <= 20200101){
				return true;
			}
		}return false;
	}

}
