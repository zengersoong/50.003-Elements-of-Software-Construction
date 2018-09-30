package cohortHomework2;

abstract class Electorate {
	private String name;
	private String vote;
	public void setVote(String vote) {
		this.vote=vote;
	}
	public void setName(String nm) {
		this.name=nm;
	}
	public String getVote() {
		return vote;
	}
	public void displayVoteChoice() {
		System.out.println(name + " voted for candidate "+ vote + ".");
	}
	
}
