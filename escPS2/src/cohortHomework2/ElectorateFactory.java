package cohortHomework2;



public class ElectorateFactory{
	static int counta=1;
	 static int countb=1;
	public static Electorate getVote(String vote, String name){
		if(vote.equals("A") || vote.equals("a") ) {
			counta +=1 ;
			return new AVoters(name);
		}
		else if(vote.equals("B")|| vote.equals("b")) {
			countb +=1;
			return new BVoters(name);
		}else System.out.println("Vote is wasted");

		
			
		
	
		return null;
		
		
	}
	public void result(){
		if(this.counta>this.countb) {
			System.out.println("Candidate A win by majority votes");
		}else {
			System.out.println("Candidate B wins by majority votes!");
		}
		
	}
	      
}
