
public class GamePrediction {
	private String winner;
	private String loser;
	private Double certainty;
	
	public GamePrediction(String winner, String loser, Double certainty) {
		this.winner = winner;
		this.loser = loser;
		this.certainty = certainty;
	}
	
	public String getWinner() {
		return winner;
	}
	
	public String getLoser() {
		return loser;
	}
	
	public Double getCertainty() {
		return certainty;
	}
}
