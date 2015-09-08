package SE1ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;

import SE1.EmployeeNotAvailableException;
import SE1.HasAdminException;
import SE1.IllegalWeeksException;
import SE1.InvalidTimeInputException;
import SE1.ManApp;
import SE1.NotFoundException;
import SE1.IllegalActionException;

//Nina og Hubert ;)
public class ManAppUI extends SampleData{
	private Screen screen;
	
	public ManAppUI() throws NotFoundException, EmployeeNotAvailableException, IllegalActionException, InvalidTimeInputException, HasAdminException,IllegalWeeksException  {
		setScreen(new LoginScreen(null));
	
	}
	public void printMenu(PrintWriter out) throws IOException {
		getScreen().printMenu(out);
	}
	private Screen getScreen() {
		return screen;
	}
	public boolean processInput(String input, PrintWriter out) throws IOException, ParseException, IllegalActionException {
		boolean exit = getScreen().processInput(input, out);
		out.println();
		return exit;
	}
	
	public static void main(String[] args) throws IOException, EmployeeNotAvailableException, NotFoundException, IllegalActionException, InvalidTimeInputException, HasAdminException, ParseException, IllegalWeeksException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out, true);
		ManAppUI ui = new ManAppUI();
		ui.basicLoop(in, out);
		
	}
	
	public void basicLoop(BufferedReader in, PrintWriter out) throws IOException, ParseException, IllegalActionException{
		String selection;
		do {
		printMenu(out);
		selection = readInput(in);
		} while (!processInput(selection, out));
	}
	
	private String readInput(BufferedReader in) throws IOException {

		return in.readLine();
	}
	
	void setScreen(Screen screen) {
		this.screen = screen;
		this.screen.setManAppUI(this);
		this.screen.setManApp(ma);
	}
	
	public ManApp getManApp() {
		return ma;
	}
	

}
