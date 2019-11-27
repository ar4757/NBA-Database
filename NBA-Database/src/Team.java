
public class Team {
	private String name;
	private String abbreviation;
	
	public Team(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAbbreviation() {
		return abbreviation;
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
