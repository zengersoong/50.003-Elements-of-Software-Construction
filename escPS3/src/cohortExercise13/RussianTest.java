package cohortExercise13;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RussianTest {
@Test
public void blackBoxTest() {
	Russian rushA= new Russian();
	int result = rushA.multiply(5, 5);
	assertEquals(result,25);
}
@Test
public void whiteBoxTest() {
	Russian rushB = new Russian();
	int result2 = rushB.multiply(30, 10);
	assertEquals(result2,300); 
	
}

@Test
public void faultBasedTest() {
	Russian rushA= new Russian();
	int result = rushA.multiply(10,-1);
	assertEquals(result,-10);// failure!
}
}
