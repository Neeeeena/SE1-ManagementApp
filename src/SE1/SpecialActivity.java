package SE1;

//MATHIAS
public class SpecialActivity {
	int day;
	int year;
	int type;
	public static final int HOLIDAY = 1;
	public static final int COURSE = 2;
	public static final int ILLNESS = 3;
	
	
	//MATHIAS
	SpecialActivity(int day, int year, int type) throws IllegalActionException {
		if(type <= 0 || type >= 4) throw new IllegalActionException("No such type");
		this.day = day;
		this.year = year;
		this.type = type;
	}
	//Nina
	public boolean betweenDays(int d1, int d2){
		if(d1<=day && day<=d2){
			return true;
		}else if(d1>d2 && (d1<= day || d2>= day)){
			return true;
		}
		return false;
	}
	//MATHIAS
	public int getDay() {
		return day;
	}
	//MATHIAS
	public int getYear() {
		return year;
	}
	//MATHIAS
	public int getType() {
		return type;
	}
}
