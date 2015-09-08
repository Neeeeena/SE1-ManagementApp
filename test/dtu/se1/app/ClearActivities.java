package dtu.se1.app;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;

import org.junit.Test;

import SE1.Employee;
import SE1.EmployeeNotAvailableException;
import SE1.InvalidTimeInputException;
import SE1.ManCal;
import SE1.NotFoundException;
import SE1.Project;

public class ClearActivities extends SampleDataSetup{
	//Nina
	@Test
	public void testClearEndedActivities() throws NotFoundException, EmployeeNotAvailableException, InvalidTimeInputException{
		Employee e2 = ma.getEmployee("JP");
		Project p = e2.getProjects().get(0);
		p.addActivity("test1", 10, 1, 3);
		p.addActivity("test2", 15, 2, 4);
		p.getActivity("test1").addEmployeeToActivity(ma.getEmployee("RK"),5);
		p.getActivity("test2").addEmployeeToActivity(ma.getEmployee("RK"),5);

		assertTrue(ma.getEmployee("RK").getWorkload(2)==10);
		
		ManCal manCal = mock(ManCal.class);
		ma.setCalendar(manCal);
		when(manCal.get(Calendar.WEEK_OF_YEAR)).thenReturn(5);
		p.clearEndedActivities();
		assertTrue(ma.getEmployee("RK").getWorkload(2)==0);
	}
	//Nina
	@Test
	public void testClearNoEndedActivities() throws NotFoundException, EmployeeNotAvailableException, InvalidTimeInputException{
		Employee e2 = ma.getEmployee("JP");
		Project p = e2.getProjects().get(0);
		p.addActivity("test1", 10, 3, 6);
		p.addActivity("test2", 15, 2, 7);
		p.getActivity("test1").addEmployeeToActivity(ma.getEmployee("RK"),5);
		p.getActivity("test2").addEmployeeToActivity(ma.getEmployee("RK"),5);

		
		ManCal manCal = mock(ManCal.class);
		ma.setCalendar(manCal);
		when(manCal.get(Calendar.WEEK_OF_YEAR)).thenReturn(5);
		p.clearEndedActivities();
		assertTrue(ma.getEmployee("RK").getWorkload(4)==10);
	}
	
	//ALEXANDER
	@Test
	public void testClearAllActivities() throws NotFoundException, EmployeeNotAvailableException, InvalidTimeInputException{
		Employee e2 = ma.getEmployee("JP");
		Project p = e2.getProjects().get(0);
		p.addActivity("test1", 10, 3, 6);
		p.addActivity("test2", 15, 2, 7);
		p.getActivity("test1").addEmployeeToActivity(ma.getEmployee("RK"),5);
		p.getActivity("test2").addEmployeeToActivity(ma.getEmployee("RK"),5);
		p.getActivity("test2").addEmployeeToActivity(ma.getEmployee("MT"),5);

		assertFalse(ma.getEmployee("RK").getActivities().isEmpty());
		assertFalse(ma.getEmployee("MT").getActivities().isEmpty());
		
		p.clearAllActivities();
		assertTrue(ma.getEmployee("RK").getActivities().isEmpty());
		assertTrue(ma.getEmployee("MT").getActivities().isEmpty());
	}
}
