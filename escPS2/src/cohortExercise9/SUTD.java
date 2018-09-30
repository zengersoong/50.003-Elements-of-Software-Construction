package cohortExercise9;

import java.util.ArrayList;



public class SUTD implements SUTDinterface{
	private ArrayList<Employee> employee; 
	
	public SUTD () {
		employee = new ArrayList<Employee>(); 
		employee.add(new Professor("Sun Jun", 0));
		employee.add(new AdminStuff("Stacey", 5));
		employee.add(new Student("Allan", 3));		
		
	}
	
	public ArrayList<Employee> getEmployee () {
		System.out.println(employee);
		return employee;
	}

	@Override
	public void visit(Professor prof) {
		String name=prof.getName();
		Integer number = prof.getNo_of_publications();
		System.out.println(name+" has " + number + " of publications");		
	}

	@Override
	public void visit(Student stud) {
		String name=stud.getName();
		float gpa = stud.getGPA();
		System.out.println(name+ " has GPA of "+ gpa +".");
		
	}

	@Override
	public void visit(AdminStuff stuf) {
		String name = stuf.getName();
		float eff = stuf.getEfficiency();
		System.out.println(name + " has an efficiency rating of " + eff + "%.");
	}
	
}