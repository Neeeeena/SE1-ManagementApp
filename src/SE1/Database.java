package SE1;
import java.util.ArrayList;




import SE1.Employee;
//import SE1.Activity; //NO
import SE1.Project;

//MATHIAS
public class Database {
	
	ArrayList<Employee> employees;
	ArrayList<Project> projects;
	
	public Database() {
		employees = new ArrayList<Employee>();
		projects = new ArrayList<Project>();
	}
	
	public ArrayList<Project> getProjects() {
		return projects;
	}
	
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	public Employee getEmployee(String initials) throws NotFoundException {
		for(Employee e : employees) {
			if(e.getInitials().equals(initials)) {
				return e;
			}
		}
		throw new NotFoundException("Employee was not found");
	}
	
	public Project getProject(int ID) throws NotFoundException {
		for(Project project : projects) {
			if(project.getID() == ID) {
				return project;
			}
		}
		throw new NotFoundException("Project was not found");
	}
	
	public void saveProject(Project p){
		projects.add(p);
	}
	
	public void saveEmployee(Employee e){
		employees.add(e);
	}
	
		
	
	
}
