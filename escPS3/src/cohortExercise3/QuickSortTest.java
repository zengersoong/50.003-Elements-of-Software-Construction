package cohortExercise3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(Parameterized.class)
class QuickSortTest {
	public int[] sorted , unsorted;
	//TODO :MUST construct this parameterized class
	public QuickSortTest(int[] sorted , int[] unsorted) {
		this.sorted = sorted;
		this.unsorted = unsorted;
	}
	
@Parameters public static Collection<Object> parameters(){
	return Arrays.asList (new Object[][] {{new int [] {1,1,2}, new int[] {2,1,1}},
		{new int[] {4,5,6}, new int[] {5,6,4}},
	});
}
	@Test public void quickSortTest() {
		QuickSort test = new QuickSort();
		test.sort(unsorted);
		for (int i =0; i<sorted.length;i++) {
			assertEquals(sorted[i],test.getArray()[i]);
		}
	}
}
