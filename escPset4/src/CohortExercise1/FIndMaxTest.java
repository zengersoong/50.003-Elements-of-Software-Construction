package CohortExercise1;

import static org.junit.jupiter.api.Assertions.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.jupiter.api.Test;

class FIndMaxTest {

	@Test
	public void findmax() {
		Mockery context = new JUnit4Mockery();
		final FindMaxUsingSorting findmaxusingsorting = context.mock(FindMaxUsingSorting.class);
		final Sorter sorter = context.mock(Sorter.class);
		int[] arr1 = {5,4,7,8,2,4,8,9};
		
		context.checking(new Expectations() {{
            oneOf  (findmaxusingsorting).findmax(arr1,sorter);
      
        }});
		findmaxusingsorting.findmax(arr1, sorter);
		context.assertIsSatisfied();
		
	}
	public interface FindMaxUsingSorting {
		public int findmax(int[] inputArr,Sorter sorter);
		
			
	}
	public interface Sorter{
		 public int[] sort(int[] inputArr);
	}
	
}
