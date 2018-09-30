package cohortHomework2;

import java.util.Scanner;

public class PollingMachine {
	public static int countA;
	public static int countB;
	
	public static void main(String[] args) {
		ElectorateFactory authenticatedMachine = new ElectorateFactory();
		
		for (int c = 1;c<=5;c++) {
			System.out.println("Enter vote one by one"+"\n");
			System.out.println("Please enter your name");
			Scanner nm = new Scanner(System.in);
			String name = nm.next();
			System.out.println("Please enter your vote");
			Scanner vt = new Scanner(System.in);
			String vote = vt.next();
			authenticatedMachine.getVote(vote,name);
		}
		authenticatedMachine.result();
	}
	

}
