package SE1;
import java.util.ArrayList;
import java.util.Stack;


import java.util.Calendar;

import SE1.SpecialActivity;
//import SE1.ManApp;
//import SE1.ManCal;
import SE1.Employee;

import java.util.GregorianCalendar;
//MATHIAS
public class MyCalendar {
	//MATHIAS
	public MyCalendar(Employee employee) {
		specialActivities = new ArrayList<SpecialActivity>();
		this.employee = employee;
	}
	
	
	private ArrayList<SpecialActivity> specialActivities;
	private Employee employee;
	private int[] dayMonth = {0,0,31,31,62,92,123,153,184,215,245,276,306,337};
	
	//MATHIAS
	private boolean hasSpecialActivity(int day, int year) {
		for(SpecialActivity sa : specialActivities) {
			if(sa.getDay() == day && sa.getYear() == year) return true;
		}
		return false;
	}
	//MATHIAS
	public int dateToDay(int year, int month, int day) {
		int newDate = day;
		if(month != 1) {
			newDate += dayMonth[month];
			if(month != 2) newDate += getCalendar().isLeapYear(year) ? 29 : 28;
		}
		return newDate;
		
	}
	//MATHIAS
	public int dayOfYearToDayOfMonth(int dayOfYear, int year) {
		int i = 1;
		int[] tempDayMonth = new int[14];
		for(int j = 0; j <= 13; j++) {
			if(j < 3) tempDayMonth[j] = dayMonth[j];
			else tempDayMonth[j] = dayMonth[j] + (getCalendar().isLeapYear(year) ? 29 : 28);
		}
		for(;;) {
			if(dayOfYear - tempDayMonth[i+1] < 0) break;
			i++;
			
		}
		
		return dayOfYear - tempDayMonth[i];
	}
	//MATHIAS
	public int dayOfYearToMonth(int dayOfYear, int year) {
		int i = 1;
		int[] tempDayMonth = new int[14];
		for(int j = 0; j <= 13; j++) {
			if(j < 3) tempDayMonth[j] = dayMonth[j];
			else tempDayMonth[j] = dayMonth[j] + (getCalendar().isLeapYear(year) ? 29 : 28);
		}
		for(;;) {
			if(dayOfYear - tempDayMonth[i+1] <= 0) break;
			i++;
			
		}
		return i;
	}
	
	
	//MATHIAS
	public void addSpecialActivity(int startYear, int startMonth, int startDay, int duration, int type) throws IllegalActionException {
		int sDay = dateToDay(startYear,startMonth,startDay);
		int day = sDay;
		int year = startYear;
		Stack<SpecialActivity> addThese = new Stack<SpecialActivity>();
		if(type == SpecialActivity.ILLNESS) {
			if( ((day > getCalendar().getDate().get(Calendar.DAY_OF_YEAR)) && (year == getCalendar().getDate().get(Calendar.YEAR)))
					|| year > getCalendar().getDate().get(Calendar.YEAR)) {
				throw new IllegalActionException("You cannot register future illness");
			}
		}

		for(int i = 0; i < duration; i++) {	
			//In case of year break;
			if(day > (((new GregorianCalendar()).isLeapYear(year)) ? 366 : 365)) {
				year++;
				day = 1;
			}
			if(hasSpecialActivity(day, year)) {
				throw new IllegalActionException("Can only have one special activity each day");
			}
			addThese.push(new SpecialActivity(day, year, type));
			day++;
		}
		while(!addThese.isEmpty()) {
			specialActivities.add(addThese.pop());
		}
		
	}
	//MATHIAS
	public int deleteSpecialActivities(int fromDayOfMonth, int fromMonth, int fromYear, int toDayOfMonth, int toMonth, int toYear) {
		int fromDay = dateToDay(fromYear, fromMonth, fromDayOfMonth);
		int toDay = dateToDay(toYear, toMonth, toDayOfMonth);
		Stack<SpecialActivity> deletion = new Stack<SpecialActivity>();
		int activitiesRemoved = 0;
		for(SpecialActivity sa : specialActivities) {
			if( (((sa.getDay() >= fromDay && sa.getYear() == fromYear))
					|| sa.getYear() > fromYear) &&
				(((sa.getDay() <= toDay && sa.getYear() == toYear))
					|| sa.getYear() < toYear)) {
				
				deletion.push(sa);
				activitiesRemoved++;
			}
		}
		while(!deletion.empty()) {
			specialActivities.remove(deletion.pop());
		}
		return activitiesRemoved;
	}
	
	//MATHIAS
	public ArrayList<SpecialActivity> getSpecialActivities() {
		return specialActivities;
	}


	
	//MATHIAS
	public ManCal getCalendar() {
		return employee.getManApp().getCalendar();
	}
	//Nina
	//If the specialactivity is during half of the time of the activity
	public boolean onActivity(int startWeek, int endWeek){
		int startDay = (startWeek-1)*7;
		int endDay = endWeek*7;
		int okRatio = 0;
		int counter=0;
		if(endDay-startDay>0){
			okRatio = (endDay-startDay)/2;
		}else{okRatio = (365-startDay+endDay)/2;}
		for(SpecialActivity sa : specialActivities) {
			if(sa.betweenDays(startDay, endDay)){
				counter++;
			}
		}
		
		if(counter<okRatio){
			return false;
		}

		return true;
	}
	

}
