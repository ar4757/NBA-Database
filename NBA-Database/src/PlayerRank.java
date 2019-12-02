
public class PlayerRank {
	private String rank;
	private String name;
	private String position;
	private String rankScore;
	
	public PlayerRank(String rank, String name, String position, String rankScore) {
		this.rank = rank;
		this.name = name;
		this.position = position;
		this.rankScore = rankScore;
	}
	
	public String getRank() {
		return rank;
	}
	
	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}
	
	public String getRankScore() {
		return rankScore;
	}
}
