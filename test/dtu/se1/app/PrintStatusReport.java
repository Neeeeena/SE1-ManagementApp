package dtu.se1.app;

import static org.junit.Assert.*;

import org.junit.Test;

import SE1.ManCal;
import SE1.NotFoundException;
import SE1.Project;

public class PrintStatusReport extends SampleDataSetup{
	
	//ALEXANDER
	@Test
	public void ProjectFoundAndStatusReportPrinted() throws NotFoundException {
		Project p1 = ma.getProject((ma.getCalendar().get(ManCal.YEAR)-2000)*10000);
		p1.generateStatusReport();
	}
	
	//ALEXANDER
	@Test
	public void ProjectNotFound3() {
		try {
			Project p1 = ma.getProject(150007);
			fail("NotFoundException should have been thrown");
		} catch(NotFoundException e) {
			assertEquals("Project was not found", e.getMessage());
		}		
	}
	//Nina
	@Test
	public void ReportOverNewYear() throws NotFoundException{
		Project p1 = ma.getProject((ma.getCalendar().get(ManCal.YEAR)-2000)*10000);
		p1.addActivity("NY", 20, 50, 4);
		p1.generateStatusReport();
			
	}

}
