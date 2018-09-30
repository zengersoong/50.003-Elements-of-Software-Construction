package CohortExercise6;
import java.util.Random;

public class FuzzerCal {
    Random random = new Random();
    int repeatCounter = 0;
    int maxExpansion = 3;
    public static void main(String[] args) {
        FuzzerCal fuzzer = new FuzzerCal();
     // Domino effect here onwards
        String result = fuzzer.Expression();
        try {
            System.out.println("Fuzzer Generated: "+ result);
        } catch(Exception e){
            System.out.println(e);
        }
    }
    public String Expression(){
    	Random random1 = new Random();
        int randExpr = random1.nextInt(3);
        if(repeatCounter>=maxExpansion) 
        	randExpr = repeatCounter;
        repeatCounter++;
        switch (randExpr){
            case 0:
                return Expression()+"+"+Term();
            case 1:
                return Expression()+"-"+Term();
            default:
                return Term();
        }
    }
    public String Term(){
    	Random random2 = new Random();
        int randTerm = random2.nextInt(3);
        if(repeatCounter>=maxExpansion) 
        	randTerm = repeatCounter;
        repeatCounter++;
        switch (randTerm){
            case 0:
                return Term()+"*"+Factor();
            case 1:
                return Term()+"/"+Factor();
            default:
                return Factor();
        }
    }
    public String Factor(){
    	Random random3 = new Random();
        int randFact = random3.nextInt(4);
        if(repeatCounter>=maxExpansion) 
        	randFact = repeatCounter;
        repeatCounter++;
        switch (randFact){
            case 0:
                return "-"+Factor();
            case 1:
                return "("+Expression()+")";
            case 2:
                return Factor()+"."+Integer();
            default:
                return Integer();
        }
    }
    public String Integer(){
    	Random random3 = new Random();
        int randInt = random3.nextInt(2);
        switch (randInt){
            case 0:
                return Digit();
            default:
                return Integer()+Digit();
        }
    }
    public String Digit(){
    	Random random4 = new Random();
        int randDigit = random4.nextInt(10);
        return Integer.toString(randDigit);
    }
}
