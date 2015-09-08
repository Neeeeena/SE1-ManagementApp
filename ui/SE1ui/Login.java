package SE1ui;

import java.util.Calendar;

import SE1.Employee;
import SE1.ManApp;
import SE1.NotFoundException;

public class Login {
	
	//MATHIAS
	public Employee checkInitials(String ID, ManApp manApp){
		for(Employee e: manApp.getEmployees()){
			if(e.getInitials().equals(ID.toUpperCase())){
				return e;
			}
		}
		return null;

	}
	//Nina
	public boolean isAdmin(String ID){
		return ID.equals("admin");
	}
	
	
}
