package AppFolder;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// Variablen für die Eingabe
		Integer status = 0;
		String category;
		Date date;
		String newCat;
		double value = 123.34;
		String categoryName;
		
		// Scanner fürs auslesen aus der Konsole
		Scanner scan = new Scanner(System.in);
		
		// neuer Account erstellt und kategorielisten erstellt
		PositionList account = new PositionList();
		kategorie categories = new kategorie();
		
		categories.addExpenseCat("Miete");
		categories.addExpenseCat("Lebensmittel");
		categories.addExpenseCat("Transportmittel");
		
		categories.addIncomeCat("Gehalt");
		categories.addIncomeCat("Taschengeld");
		categories.addIncomeCat("Geschenk");
		
		// status wird null wenn programm beendet werden soll
		while (status!=null) {
			Integer inputMain = null;
			Integer inputNew = null;
			Integer inputCat = null;
			System.out.println("Finanzmanager: ");
			System.out.println("--------------------------------\n");
			System.out.println("Option auswählen: ");
			System.out.println("Programm beenden: 1");
			System.out.println("neue Position hinzufügen: 2");
			System.out.println("nach Kategorie anzeigen: 3");
			
			inputMain = scan.nextInt();
			
			switch (inputMain) {
			// Programm beenden	
			case 1:
					status = null;
					break;
					
				// neue Position hinzufügen
				case 2:
					System.out.println("--------------------------------\n");
					System.out.print("Einnahme (1) oder Ausgabe (2): ");
					inputNew = scan.nextInt();
					switch (inputNew) {
						case 1: // Einnahme
							categories.printIncome();
							System.out.println("Um eine neue Kategorie einzufügen bitte '-1' eingeben");
							inputCat = scan.nextInt();
							if (inputCat == -1) {
								System.out.print("Geben Sie die neue Kategorie ein: ");
								newCat = scan.next();
								categories.addIncomeCat(newCat);
								inputCat = categories.incomeMaxIndex()+1;
							}
							category = categories.getIncAtIndex(inputCat-1);
							date = new Date(1,1,2019);
							account.addIncome(date, value, false, category);
							value = value + 100;
							break;
						case 2: // Ausgabe
							categories.printExpense();
							System.out.println("Um eine neue Kategorie einzufügen bitte '-1' eingeben");
							inputCat = scan.nextInt();
							if (inputCat == -1) {
								System.out.print("Geben Sie die neue Kategorie ein: ");
								newCat = scan.next();
								categories.addExpenseCat(newCat);
								inputCat = categories.expenseMaxIndex()+1;
							}
							category = categories.getExpAtIndex(inputCat-1);
							date = new Date(1,1,2019);
							account.addExpense(date, 542.78, false, category);
							break;
							
					}
					account.printIncome();
					account.printExpense();
					System.out.println("--------------------------------\n");
					break;
				// Filter	
				case 3:
					System.out.println("--------------------------------\n");
					System.out.print("Einnahme (1) oder Ausgabe (2): ");
					inputNew = scan.nextInt();
					switch (inputNew) {
						case 1: // Einnahme
							categories.printIncome();
							System.out.print("Geben Sie die gesuchte Kategorie ein: ");
							inputCat = scan.nextInt();
							categoryName = categories.getNameIncome(inputCat-1);
							account.printIncomeCondition(categoryName);
							break;
						case 2: // Ausgabe
							categories.printExpense();
							System.out.print("Geben Sie die gesuchte Kategorie ein: ");
							inputCat = scan.nextInt();
							categoryName = categories.getNameExpense(inputCat-1);
							account.printExpenseCondition(categoryName);
							break;
					}
				default:
					System.out.println("falsche Eingabe");
			}
			
		}
		scan.close();
	}
}
