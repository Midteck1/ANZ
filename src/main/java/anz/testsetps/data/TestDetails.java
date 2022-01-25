package anz.testsetps.data;

public class TestDetails {


	public String NoDependents;
	public String AnnualIncome;
	public String AnnualOtherIncome;
	public String LivingExp;
	public String CurrentHLRepa;
	public String OtherLP;
	public String OtherComm;
	public String TotalCC;



	public TestDetails(String noDependents,String annualIncome,String annualOtherIncome,String livingExp,
			String currentHLRepa,String otherLP,String otherComm,String totalCC) {

		this.NoDependents=noDependents;
		this.AnnualIncome = annualIncome;
		this.AnnualOtherIncome = annualOtherIncome;
		this.LivingExp = livingExp;
		this.CurrentHLRepa=currentHLRepa;
		this.OtherLP = otherLP;
		this.OtherComm = otherComm;
		this.TotalCC = totalCC;

	}
}

