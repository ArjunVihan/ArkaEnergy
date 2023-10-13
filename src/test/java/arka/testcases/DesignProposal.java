package arka.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;

import arka.base.BaseClass;
import arka.dataProvider.CustomDataProvider;
import arka.pages.DesignProposalPage;
import arka.pages.LeadSearchPage;
import arka.pages.Loginpagen;

public class DesignProposal extends BaseClass 
{


	@Test(dataProvider = "ProposalData",dataProviderClass = CustomDataProvider.class)
	public void loginWithValidCredentials(String uname,String pass,String lName,String priceRate,String tariffRate,String criticalLoad,String consumptionValue,String energyValue,String priceProposal,String gazeboModule,String fileName,String projectReq,String phnNo,String emailAdd)
	{
		Loginpagen login=new Loginpagen(driver);
		login.loginToApplication(uname,pass);
		LeadSearchPage leadSearch= new LeadSearchPage(driver);
		leadSearch.leadSearch(lName);
		DesignProposalPage design= new DesignProposalPage(driver);
		design.designOrderCreated();
		design.isdesignOrderCreated();
		design.preliminiaryOrderProposal();
		design.designOrderProposal(priceRate,tariffRate,criticalLoad, consumptionValue, energyValue,priceProposal, gazeboModule,fileName, projectReq, phnNo,emailAdd);
        Assert.assertTrue(design.isPreProposalCreated(), "Preliminary Proposal Not created");
}
}
