package AppFolder;

public class Einnahme extends Position {
	
	// der Counter ist static sodass jedes mal wenn ein Objekt der Klasse erstellt wird der counter nicht wieder bei null beginnt
	private int id;
	static private int idCounter = 0;
	
	public Einnahme(Date date, double value, boolean recurring, String category) {
		super(date, value, recurring, category);
		this.id = idCounter;
		idCounter++;
	}
	
	public void printIncome() {
		System.out.print("Id: " + id + ", Datum: ");
		super.printPosition();		
	}
	
}
