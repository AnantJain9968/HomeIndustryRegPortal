package com.nagarro.HomeIndustryReg.interfaces;

import com.nagarro.HomeIndustryReg.model.AccountDetails;
import com.nagarro.HomeIndustryReg.model.FreeTextExperienceDetails;
import com.nagarro.HomeIndustryReg.model.OrganisationalDetails;


/**
 * input details interface
 * 
 * @author Anantjain
 *
 */
public interface DetailsInputServiceInterface {

	String submitOrganisationalDetails(OrganisationalDetails organisationalDetails, long userId);

	String submitAccountDetails(AccountDetails accountDetails, long userId);

	String submitFreeText(FreeTextExperienceDetails freeTextExperienceDetails, long userId);

}
