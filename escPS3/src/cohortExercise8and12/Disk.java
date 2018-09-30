package cohortExercise8and12;
/*
 * 
 */
public class Disk {
	private int x;
	private int y;

	Disk(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void manipulate () {
		int threshold = 1000;
		while ((threshold - (x + y)) > 0) {
			if (x > 5) {
				threshold = threshold - 1;
				System.out.println("covered 1");
			}
			else if (y <= 12) {
				threshold = threshold - 2;
				System.out.println("covered 2");
			}
			if (x <= 1000) {
				System.out.println("covered 3");
				threshold = threshold - 3;
			}
			else if (y < 1) {
				System.out.println("covered 4");
				threshold = threshold + 1;
			}
		}
	}
}
