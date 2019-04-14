package AppFolder;

// Die Date Klasse hab ich nur deswegen weil ich mich mit dem java.util.date nicht auskenne

public class Date {

	private int day;
	private int month;
	private int year;
	
	public Date(int day, int month, int year) {
		if (day <= 0 || month <= 0 || year <= 0) {
			throw new IllegalArgumentException("Ungueltiges Datum");
		}
		
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public void printDate() {
		System.out.print(day + "." + month + "." + year);
	}
	
}
