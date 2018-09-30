package cohortExercise1;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class FindMaxTest {

	@Test
	 public void max() {
		FindMax fm =  new FindMax();
		FindMax fm1 = new FindMax();
		FindMax fm2 = new FindMax();
		int ans = fm.max(new int [] {3,4,6,2,300});
		int ans1 = fm1.max(new int [] {3,1,6,4,2,5,2,2});
		int ans2 = fm2.max(new int[] {});
		System.out.println(ans2);
		assertTrue(ans1==6);//this pass
		assertTrue(ans==300);//this fails
		assertTrue(ans2==0); // array out of bounds errors
		
	}
	
	
	

}
