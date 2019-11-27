
public class PlayerImprovement {
	private String name;
	private String beforeAveragePoint;
	private String beforeAverageAssist;
	private String beforeAverage3PT;
	private String afterAveragePoint;
	private String afterAverageAssist;
	private String afterAverage3PT;
	
	public PlayerImprovement(String name, String beforeAveragePoint, String beforeAverageAssist, String beforeAverage3PT, String afterAveragePoint, String afterAverageAssist, String afterAverage3PT) {
		this.name = name;
		this.beforeAveragePoint = beforeAveragePoint;
		this.beforeAverageAssist = beforeAverageAssist;
		this.beforeAverage3PT = beforeAverage3PT;
		this.afterAveragePoint = afterAveragePoint;
		this.afterAverageAssist = afterAverageAssist;
		this.afterAverage3PT = afterAverage3PT;
	}
	
	public String getName() {
		return name;
	}

	
	public String getBeforeAveragePoint() {
		return beforeAveragePoint;
	}
	
	public String getBeforeAverageAssist() {
		return beforeAverageAssist;
	}
	
	public String getBeforeAverage3PT() {
		return beforeAverage3PT;
	}
	
	public String getAfterAveragePoint() {
		return afterAveragePoint;
	}
	
	public String getAfterAverageAssist() {
		return afterAverageAssist;
	}
	
	public String getAfterAverage3PT() {
		return afterAverage3PT;
	}
	
	public String getAveragePoint() {
		double diff = Double.parseDouble(afterAveragePoint) - Double.parseDouble(beforeAveragePoint);
		diff = Math.round(diff * 100) / 100.0;
		if (diff >= 0) {
			return "+" + Double.toString(diff);
		}
		else {
			return Double.toString(diff);
		}
	}
	
	public String getAverageAssist() {
		double diff = Double.parseDouble(afterAverageAssist) - Double.parseDouble(beforeAverageAssist);
		diff = Math.round(diff * 100) / 100.0;
		if (diff >= 0) {
			return "+" + Double.toString(diff);
		}
		else {
			return Double.toString(diff);
		}
	}
	
	public String getAverage3PT() {
		double diff = Double.parseDouble(afterAverage3PT) - Double.parseDouble(beforeAverage3PT);
		diff = Math.round(diff * 100) / 100.0;
		if (diff >= 0) {
			return "+" + Double.toString(diff);
		}
		else {
			return Double.toString(diff);
		}
	}
}
