
public class TeamRank {
	private String rank;
	private String abbreviation;
	private String name;
	private String winscore;
	
	public TeamRank(String rank, String abbreviation, String name, String winscore) {
		this.rank = rank;
		this.abbreviation = abbreviation;
		this.name = name;
		this.winscore = winscore;
	}
	
	public String getRank() {
		return rank;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	
	public String getName() {
		return name;
	}

	public String getWinscore() {
		return winscore;
	}
	
	public String getLogo() {
		if (abbreviation.equals("GS")) {
			return "https://www.nba.com/assets/logos/teams/secondary/web/" + "GSW" + ".svg";
		}
		else if (abbreviation.equals("NO")) {
			return "https://www.nba.com/assets/logos/teams/secondary/web/" + "NOP" + ".svg";
		}
		else if (abbreviation.equals("PHO")) {
			return "https://www.nba.com/assets/logos/teams/secondary/web/" + "PHX" + ".svg";
		}
		else if (abbreviation.equals("SA")) {
			return "https://www.nba.com/assets/logos/teams/secondary/web/" + "SAS" + ".svg";
		}
		else if (abbreviation.equals("NY")) {
			return "https://www.nba.com/assets/logos/teams/secondary/web/" + "NYK" + ".svg";
		}
		else {
			return "https://www.nba.com/assets/logos/teams/secondary/web/" + abbreviation + ".svg";
		}
	}
}
