
public class Player {
	private String name;
	private String height;
	private String weight;
	private String birthday;
	
	public Player(String name, String height, String weight, String birthday) {
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.birthday = birthday;
	}
	
	public String getName() {
		return name;
	}
	
	public String getHeight() {
		return height;
	}
	
	public String getWeight() {
		return weight;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public String getHeightFormatted() {
		int total_inches = Integer.valueOf(height);
		int feet = total_inches / 12;
		int inches = total_inches % 12;
		return Integer.toString(feet) + "'" + Integer.toString(inches);
	}
	
	public String getWeightFormatted() {
		return weight + "lbs";
	}
}
