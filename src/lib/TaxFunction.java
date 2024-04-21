package lib;

public class TaxFunction {	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
			return 0;
		}
		numberOfChildren = Math.min(numberOfChildren, 3); 
		int taxBase = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible;
		int taxDeduction = 54000000 + 4500000 + (numberOfChildren * 1500000);
		int tax = (int) Math.round(0.05 * (isMarried ? (taxBase - taxDeduction) : (taxBase - 54000000)));
		return Math.max(tax, 0); 
	}
}
