
public class HeightPerform {
	private String height;
	private String performance;
	
	public HeightPerform(String height, String performance) {
		this.height = height;
		this.performance = performance;
	}
	
	public String getHeight() {
		return height;
	}
	
	public String getPerformance() {
		return performance;
	}
	
	public String getHeightFormatted() {
		int total_inches = Integer.valueOf(height);
		int feet = total_inches / 12;
		int inches = total_inches % 12;
		return Integer.toString(feet) + "'" + Integer.toString(inches);
	}
}
