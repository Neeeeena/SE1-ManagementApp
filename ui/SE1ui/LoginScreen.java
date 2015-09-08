package SE1ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import SE1.Employee;
import SE1.NotFoundException;

public class LoginScreen extends Screen {
	
    public LoginScreen(Screen previousScreen) {
		super(previousScreen);
	}

	Login login = new Login();
	

	//Nina
	@Override
	public void printMenu(PrintWriter out) throws IOException {
		out.println("Please enter you initials or admin login");
		out.println("0) to exit");

	}
	//MATHIAS
	@Override
	public boolean processInput(String selection, PrintWriter out) {
		if ("0".equals(selection)) {
			out.print("Exited.");
			return true;
		}else{
			if(login.isAdmin(selection)){
				maUI.setScreen(new AdminScreen(this));
			}else{
				Employee e = login.checkInitials(selection, maUI.getManApp());
				if(e != null){
					UserScreen us = new UserScreen(this);
					us.setEmployee(e);
					maUI.setScreen(us);
					
				}else{
					out.print("Initials not found");
					maUI.setScreen(new LoginScreen(null));
				}
			}
		}
	return false;
	}

}
