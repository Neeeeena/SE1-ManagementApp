package SE1;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class WorkRegistrator {
	
	private ArrayList<HashMap<Activity,Double>> work;
	private ArrayList<HashMap<Activity,Double>> workDecemberLastYear;
	private ManCal calendar;
	
	//Nina
	public WorkRegistrator(){
		work = new ArrayList<HashMap<Activity,Double>>();
		for(int i = 0; i<366;i++){
			work.add(new HashMap<Activity,Double>());
		}
		workDecemberLastYear = new ArrayList<HashMap<Activity,Double>>();
		for(int i = 0; i<31;i++){
			workDecemberLastYear.add(new HashMap<Activity,Double>());
		}
	}
	//Nina
	public void setManApp(ManApp ma){
		calendar = ma.getCalendar();
	}
	//Nina
	public void registerWork(Activity a1, double d, String date) throws ParseException, InvalidTimeInputException{
		if(d<=24 && d>0){
			if(date=="today"){
				work.get(calendar.get(ManCal.DAY_OF_YEAR)).put(a1,d);
			}
			else{
				work.get(calendar.getDay(date)).put(a1,d);
			}
			a1.addToWorkedHours(d);
		}else{throw new InvalidTimeInputException("Input must be a number between 0 and 24");}
	}
	//Nina
	public void editRegisteredWork(Activity a1, double d, String date) throws ParseException, NotFoundException{
		HashMap<Activity,Double> day = work.get(calendar.getDay(date));
		if(day.containsKey(a1)){
			double old = day.get(a1);
			day.put(a1, d);
			a1.addToWorkedHours(-old);
			a1.addToWorkedHours(d);
		}else{throw new NotFoundException("The registered time can not be found");}	
	}
	//Nina
	public void deleteRegisteredWork(Activity a1, String date) throws ParseException, NotFoundException{
		HashMap<Activity,Double> day = work.get(calendar.getDay(date));
		if(day.containsKey(a1)){
			double old = day.get(a1);
			day.remove(a1);
			a1.addToWorkedHours(-old);
		}else{throw new NotFoundException("The registered time can not be found");}	
	}
	//Nina
	public double getMonthlyWorked(){
		double sum = 0;
		int curDate = calendar.get(ManCal.DAY_OF_YEAR);
		for(int i = 0 ; i < (calendar.get(ManCal.DAY_OF_MONTH)); i++){
			HashMap<Activity,Double> temp = work.get(curDate-i);
			for(Activity a : temp.keySet()){
				sum += temp.get(a);
			}
		}
		return sum;
	}
	
	//ALEXANDER
	public String printSpecificDaysWork(String date) throws ParseException {
		String output = "";
		double sum = 0;
		HashMap<Activity,Double> day = work.get(calendar.getDay(date));
		if(day.isEmpty()){
			return "No time is registered that day";
		}else{
			for(Activity a: day.keySet()){
				output += "" + a.getID()+ ", " + a.getName() + ": worked " + day.get(a) + " hours, ";
				sum += day.get(a);
			}
			output += "sum of registered work for today is " + sum + " hours";
			return output;
		}
	}
	
	//ALEXANDER
	public String printSpecificDaysWorkDecemberLastYear(int date) throws ParseException {
		String output = "";
		double sum = 0;
		HashMap<Activity,Double> day = workDecemberLastYear.get(date-1);
		if(day.isEmpty()){
			return "No time is registered that day";
		}else{
			for(Activity a: day.keySet()){
				output += "" + a.getID()+ ", " + a.getName() + ": worked " + day.get(a) + " hours, ";
				sum += day.get(a);
			}
			output += "sum of registered work for today is " + sum + " hours";
			return output;
		}
	}
	
	//ALEXANDER
	public double getMonthlyWorkedDecemberLastYear(){
		double sum = 0;
		for(int i = 0 ; i < 31; i++){
			HashMap<Activity,Double> temp = workDecemberLastYear.get(i);
			for(Activity a : temp.keySet()){
				sum += temp.get(a);
			}
		}
		return sum;
	}
	//Nina
	public String printTodaysWork() {
		String output = "";
		double sum = 0;
		HashMap<Activity,Double> today = work.get(calendar.get(ManCal.DAY_OF_YEAR));
		if(today.isEmpty()){
			return "No time is registered today";
		}else{
			for(Activity a: today.keySet()){
				output +=  ""+ a.getID()+ ", " + a.getName() + ": worked " + today.get(a) + " hours, ";
				sum += today.get(a);
			}
			output += "sum of registered work for today is " + sum + " hours";
			return output;
		}
		
	}
	//Nina
	public ArrayList<HashMap<Activity,Double>> getWork() {
		return work;
	}
	
	//ALEXANDER
	public void resetWork() {
		work = new ArrayList<HashMap<Activity,Double>>();
		for(int i = 0; i<366;i++){
			work.add(new HashMap<Activity,Double>());
		}
	}
	
	//ALEXANDER
	public ArrayList<HashMap<Activity,Double>> getWorkDecemberLastYear() {
		return workDecemberLastYear;
	}
	
	//ALEXANDER
	public void saveRegisteredWork() {
		workDecemberLastYear = new ArrayList<HashMap<Activity,Double>>();
		for(int i = 0; i<31;i++){
			workDecemberLastYear.add(new HashMap<Activity,Double>());
			workDecemberLastYear.get(i).putAll(work.get(365-30+i));
		}
	}

}
