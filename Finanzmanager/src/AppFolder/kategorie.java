package AppFolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class kategorie  {
	
	private List<String> incomeCat;
	private List<String> expenseCat;
	
	public kategorie() {
		incomeCat = new ArrayList<String>();
		expenseCat = new ArrayList<String>();
	}
	
	public int incomeMaxIndex() {
		return incomeCat.size()-1;
	}
	
	public int expenseMaxIndex() {
		return expenseCat.size()-1;
	}
	
	public List<String> getIncomeCats() {
		return incomeCat;
	}
	
	public List<String> getExpenceCats() {
		return expenseCat;
	}
	
	public String getExpAtIndex(int i) {
		if (i >= expenseCat.size()) {
			throw new IllegalArgumentException("Dieses Element befindet sich noch nicht in der Liste");
		}
		return expenseCat.get(i);
	}
	
	public String getIncAtIndex(int i) {
		if (i >= incomeCat.size()) {
			throw new IllegalArgumentException("Dieses Element befindet sich noch nicht in der Liste");
		}
		return incomeCat.get(i);
	}
	
	public void addIncomeCat(String add) {
		incomeCat.add(add);
	}
	
	public void addExpenseCat(String add) {
		expenseCat.add(add);
	}
	
	public void printIncome() {
		Iterator<String> itr = incomeCat.iterator();
		System.out.println("Kategorie auswaehlen und Zahl eingeben: ");
		int counter = 1;
		while(itr.hasNext()) {
			System.out.println(incomeCat.get(counter-1) + " :" + counter);
			counter++;
			itr.next();
		}
		
	}
	
	public void printExpense() {
		Iterator<String> itr = expenseCat.iterator();
		System.out.println("Kategorie auswaehlen und Zahl eingeben: ");
		int counter = 1;
		while(itr.hasNext()) {
			System.out.println(expenseCat.get(counter-1) + " :" + counter);
			counter++;
			itr.next();
		}
	}

	public String getNameIncome(int i) {
		return incomeCat.get(i);
	}
	
	public String getNameExpense(int i) {
		return expenseCat.get(i);
	}

}
