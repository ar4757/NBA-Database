import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

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
		if (birthday.contains("-")) {
			String year = birthday.substring(2, 4);
			String month = birthday.substring(5, 7);
			if (month.substring(0, 1).equals("0")) {
				month = month.substring(1);
			}
			String day = birthday.substring(8, 10);
			if (day.substring(0, 1).equals("0")) {
				day = day.substring(1);
			}
			return month + "/" + day + "/" + year;
		}
		else {
			return birthday;
		}
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
