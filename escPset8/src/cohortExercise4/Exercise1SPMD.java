package cohortExercise4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise1SPMD {
	public static void main(String[] args) throws Exception {
		int NTHREADS = 5;
		ExecutorService exec = Executors.newFixedThreadPool(NTHREADS - 1);
		// todo: complete the program by writing your code below.
		Runnable t = new Runnable() {

			@Override
			public void run() {
				double ans=integrate(0,1);
				System.out.println(ans);
			}
			
			
		};
		exec.execute(t);
		exec.shutdown();
	}

	public static double f(double x) {
		return 4.0 / (1 + x * x);
	}

	// the following does numerical integration using Trapezoidal rule.
	public static double integrate(double a, double b) {
		int N = 10000; // preciseness parameter
		
		double h = (b - a) / (N - 1); // step size
		
		double sum = 1.0 / 2.0 * (f(a) + f(b)); // 1/2 terms

		for (int i = 1; i < N - 1; i++) {
			double x = a + h * i;
			sum += f(x);
		}

		return sum * h;
	}
	
	
	
	
}
