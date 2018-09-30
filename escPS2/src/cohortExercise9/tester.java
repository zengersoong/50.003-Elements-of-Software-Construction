package cohortExercise9;

public class tester {
	public static void main(String[] args) {
		SUTD oneSUTD = new SUTD();
		for(Employee e : oneSUTD.getEmployee()) {
			e.accept(oneSUTD);
			
		}
	}

}
