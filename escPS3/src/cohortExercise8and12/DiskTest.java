package cohortExercise8and12;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

// there is a minimum number of test required. 2 test is required to cover all the branches
class DiskTest {

	@Test
	public void max() {
		//Disk testDisk1 = new Disk(6, 1);
		Disk testDisk1 = new Disk(4,5); // Branches 2 &3
		Disk testDisk2 = new Disk(1001,-5);// Branches 1 & 4
		//testDisk1.manipulate();
		testDisk1.manipulate();
		testDisk2.manipulate();
	//*********************Exercise 11******************************************8
	//for every if statement there is only exactly 1 atomic condition. Not true if there is more
	//therefore its possible 
		
	}
	//-----------------------------------CohortExercise 12----------------------------------------------//
	@Test
	public void bugReveal() {
		Disk testDisk3 = new Disk(1001,-5);// Infinite Loop bug
		testDisk3.manipulate();
	}

}
