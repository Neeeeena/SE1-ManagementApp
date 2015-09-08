package SE1ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import SE1.IllegalActionException;
import SE1.ManApp;
import SE1.Employee;

public abstract class Screen {
	ManAppUI maUI;
	ManApp ma;
	Employee employee;
	Screen previousScreen;
	//Nina
	public Screen(Screen previousScreen){
		this.previousScreen = previousScreen;
	}
	public abstract void printMenu(PrintWriter out) throws IOException;
	
	public abstract boolean processInput(String selection, PrintWriter out) throws IOException, ParseException, IllegalActionException;
	
	//Nina
	public void setManAppUI(ManAppUI manAppUI) {
		maUI = manAppUI;
		
	}
	//Nina
	public void setManApp(ManApp ma) {
		this.ma = ma;
	}
	//Nina
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	//MATHIAS
	public int readInsideInterval(BufferedReader in, PrintWriter out,String errorMsg, int min, int max) throws IOException {
		String recentInput;
		int recentInt = 0;
		for(;;) {
	        recentInput = in.readLine();
	
	        recentInt = Integer.parseInt(recentInput);
	        if(!(recentInt >= min && recentInt <= max)) {
	        out.println(errorMsg);
	        }else break;
	        	
	    }
		return recentInt;
	}
	
	//MATHIAS
	public int readInt(BufferedReader in, PrintWriter out, String errorMsg) throws IOException {
		int ID = 0;
		boolean okay = false;
		while(!okay){
			String sID = in.readLine();
			try{
				ID = Integer.parseInt(sID);
				okay = true;
			}catch(NumberFormatException e){
				out.println(errorMsg);
			}
		}return ID;
	}
	
	//ALEXANDER
	public double readDouble(BufferedReader in, PrintWriter out, String errorMsg) throws IOException {
		double ID = 0;
		boolean okay = false;
		while(!okay){
			String sID = in.readLine();
			try{
				ID = Double.parseDouble(sID);
				ID = ((double)Math.round((ID*2)))/2;
				okay = true;
			}catch(NumberFormatException e){
				out.println(errorMsg);
			}
		}return ID;
	}
	
	//MATHIAS
	public boolean isNumber(String s) {
		for(int i=0; i<s.length(); i++) {
			if(!(s.charAt(i) <= '9' && s.charAt(i) >= '0')) return false;
		}
		return true;
	}
	
	//MATHIAS
	public int readDoubleCheck(BufferedReader in, PrintWriter out, String errorMsg1, String errorMsg2, int min, int max) throws IOException {
		String recentInput;
		int recentInt = 0;
		for(;;) {
	        recentInput = in.readLine();
	        if(!isNumber(recentInput)) {
	        	out.println(errorMsg1);
	        }else{
	        	recentInt = Integer.parseInt(recentInput);
	        	if(!(recentInt >= min && recentInt <= max)) {
	        		out.println(errorMsg2);
	        	}else break;
	        	
	        }
	        
	    }
		return recentInt;
	}
	
	
}
