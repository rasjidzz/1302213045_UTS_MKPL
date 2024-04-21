package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee implements TaxCalculable{

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private Gender gender;
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	@Override
	public void setMonthlySalary(int grade) {
		switch (grade) {
			case 1:
				monthlySalary = calculateBaseSalary(3000000);
				break;
			case 2:
				monthlySalary = calculateBaseSalary(5000000);
				break;
			case 3:
				monthlySalary = calculateBaseSalary(7000000);
				break;
			default:
				throw new IllegalArgumentException("Invalid grade: " + grade);
		}
	}
	private int calculateBaseSalary(int baseSalary) {
		return isForeigner ? (int) (baseSalary * 1.5) : baseSalary;
	}
	@Override
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	@Override
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	@Override
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	@Override
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	@Override
	public int getAnnualIncomeTax() {
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
