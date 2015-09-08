package SE1ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.ParseException;

import SE1.MyCalendar;
import SE1.SpecialActivity;
import SE1.IllegalActionException;

public class MyCalendarScreen extends Screen{
	private int count;
	private MyCalendar mc;
	private ArrayList<SpecialActivity> specialActivities;
	private int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private int[] daysPerMonthLeap = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	//MATHIAS
	public MyCalendarScreen(Screen previousScreen) {
		super(previousScreen);
	}
	
	//MATHIAS
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("0) Exit.");
		out.println("1) Return.");
		out.println("2) Register holiday.");
		out.println("3) Register course.");
		out.println("4) Register illness.");
		out.println("5) Clear special activities in interval.");
		out.println("6) See all special activities");
	}
	
	//MATHIAS
	@Override
	public boolean processInput(String selection, PrintWriter out) throws IOException, ParseException, IllegalActionException {
		if ("0".equals(selection)) {
			out.print("Exited.");
			return true;
		}else if("1".equals(selection)) {
			//TODO
			maUI.setScreen(previousScreen);
		}else if("2".equals(selection)) {
			if(createNewSpecialActivity(out, 1))
				out.println("Holiday added!");
			//STARTMENU
		}else if("3".equals(selection)) {
			if(createNewSpecialActivity(out, 2))
				out.println("Course added!");
			//STARTMENU
		}else if("4".equals(selection)) {
			if(createNewSpecialActivity(out, 3))
				out.println("Illness added!");
			//STARTMENU
		}else if("5".equals(selection)) {
			deleteSpecialActivity(out);
		}else if("6".equals(selection)) {
			printSpecialActivityInfo(out);
			
		}
	return false;
	}

	//MATHIAS
	public void setMyC(MyCalendar myCalendar) {
		mc = myCalendar;
		
	}
	
	//MATHIAS
	private void printSpecialActivityInfo(PrintWriter out) {
		specialActivities = new ArrayList<SpecialActivity>();
		count = 2;
		
		for(SpecialActivity sa : mc.getSpecialActivities()) {
			specialActivities.add(sa);
			out.println(saToString(sa));
			
		}
	}


	//MATHIAS
	private String saToString(SpecialActivity sa) {
		String saOut =  sa.getYear() + "-" +
						mc.dayOfYearToMonth(sa.getDay(), sa.getYear()) + "-" +
						mc.dayOfYearToDayOfMonth(sa.getDay(), sa.getYear()) + " \t";

		if(sa.getType()==1) {
			saOut += "HOLIDAY";
		}else if(sa.getType()==2) {
			saOut += "COURSE";
		}else if(sa.getType()==3) {
			saOut += "ILLNESS";
		}
		return saOut;
	}
	//MATHIAS
	private void addSpecialActivity(String dateString , int length,  int type) throws IllegalActionException, ParseException {
		String format = "yyyyMMdd";
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = df.parse(dateString);

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		mc.addSpecialActivity(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DAY_OF_MONTH), length, type);
	}
	//MATHIAS
	public boolean createNewSpecialActivity(PrintWriter out, int type) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Please enter the day you want to start you special activity, in the format \"yyyyMMdd\"");
		String date = in.readLine();
		for(;;) {
			if(!checkValidDate(date)){
				out.println("Not a valid date!");
				date = in.readLine();
			}else break;
		}
		out.println("Please enter the duration of your special activity:");
		int duration = readInsideInterval(in, out, "Please enter a number between 1 and 150:",1,150);
		try{
			addSpecialActivity(date, duration, type);
			//return true;
		}catch(IllegalActionException | ParseException e) {
			out.print(e.getMessage());
			out.println(". Action aborted.");
			return false;
		}
		return true;
		
		
	}
	//MATHIAS
	private boolean checkValidDate(String date){
		if(isNumber(date)&&date.length()==8){
			int year = Integer.parseInt(date.substring(0,4));
			int month = Integer.parseInt(date.substring(4, 6));
			int day = Integer.parseInt(date.substring(6, 8));
			if(year >= 2010 && month<= 12 && month >= 0 && day <= ((new GregorianCalendar()).isLeapYear(year) ? daysPerMonth[month] : daysPerMonthLeap[month])){
				return true;
			}
		}return false;
	}
	//MATHIAS
	private void deleteSpecialActivity(PrintWriter out) throws IOException, ParseException, IllegalActionException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		out.println("Please enter the day you want to start deleting special activities, in the format \"yyyyMMdd\"");
		String date1 = in.readLine();
		for(;;) {
			if(!checkValidDate(date1)){
				out.println("Not a valid date!");
				date1 = in.readLine();
			}else break;
		}
		out.println("Please enter the day you want to end deleting special activities, in the format \"yyyyMMdd\" (The activity this day, will also be removed)");
		String date2 = in.readLine();
		for(;;) {
			if(!checkValidDate(date2)){
				out.println("Not a valid date!");
				date2 = in.readLine();
			}else break;
		}
		int deleted = deleteSpecialActivity(date1,date2);
		out.println(deleted+" special activities removed");
	}
	//MATHIAS
	private int deleteSpecialActivity(String dateString1 , String dateString2) throws IllegalActionException, ParseException {
		String format = "yyyyMMdd";
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date1 = df.parse(dateString1);
		Date date2 = df.parse(dateString2);
		GregorianCalendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);
		GregorianCalendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);
		return mc.deleteSpecialActivities(cal1.get(Calendar.DAY_OF_MONTH), cal1.get(Calendar.MONTH)+1, cal1.get(Calendar.YEAR), 
									cal2.get(Calendar.DAY_OF_MONTH), cal2.get(Calendar.MONTH)+1, cal2.get(Calendar.YEAR));
		
	}
	
	
}
