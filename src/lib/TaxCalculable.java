package lib;

public interface TaxCalculable {
    void setMonthlySalary(int grade);
    void setAnnualDeductible(int deductible);
    void setAdditionalIncome(int income);
    void setSpouse(String spouseName, String spouseIdNumber);
    void addChild(String childName, String childIdNumber);
    int getAnnualIncomeTax();
} 
