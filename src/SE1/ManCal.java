package SE1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ManCal extends GregorianCalendar{
	//Nina
	public ManCal(int year, int month, int dayOfMonth){
		super(year,month,dayOfMonth);
	}
	
	//Nina
	public Calendar getDate(){
		return(this);
	}

	//Nina
	public int getDay(String dateString) throws ParseException{
		String format = "yyyyMMdd";
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date date = df.parse(dateString);

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		return cal.get(Calendar.DAY_OF_YEAR);
	}


	
	
}
