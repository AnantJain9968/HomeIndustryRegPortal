package com.nagarro.HomeIndustryReg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.HomeIndustryReg.interfaces.DetailsInputServiceInterface;
import com.nagarro.HomeIndustryReg.model.AccountDetails;
import com.nagarro.HomeIndustryReg.model.FreeTextExperienceDetails;
import com.nagarro.HomeIndustryReg.model.OrganisationalDetails;
import com.nagarro.HomeIndustryReg.model.RecordedProgress;
import com.nagarro.HomeIndustryReg.model.UserBasicDetails;
import com.nagarro.HomeIndustryReg.repository.AccountDetailsRepository;
import com.nagarro.HomeIndustryReg.repository.FreeTextExperienceDetailsRepository;
import com.nagarro.HomeIndustryReg.repository.OrganisationalDetailsRepository;
import com.nagarro.HomeIndustryReg.repository.RecordedProgressRepository;
import com.nagarro.HomeIndustryReg.repository.UserRepository;

/**
 * Input details services
 * contains all the methods which provides services 
 * for slots and calendar models
 *
 */
@Service
public class DetailsInputService implements DetailsInputServiceInterface {

	@Autowired
	private UserRepository userBasicDetailsRepository;
	@Autowired
	private OrganisationalDetailsRepository organisationalDetailsRepository;
	@Autowired
	private AccountDetailsRepository accountDetailsRepository;
	@Autowired
	private FreeTextExperienceDetailsRepository freeTextRepository;
	@Autowired
	private RecordedProgressRepository recordedProgressRepository;

	@Override
	public String submitOrganisationalDetails(OrganisationalDetails organisationalDetails, long userId) {
		String message = "";
		Optional<UserBasicDetails> existingUser = userBasicDetailsRepository.findById(userId);
		if (existingUser.isPresent()) {
			Optional<OrganisationalDetails> orgDetails = organisationalDetailsRepository
					.findByUserBasicDetailsUserId(userId);
			if (orgDetails.isPresent()) {
				orgDetails.get().setLicenseNumber(organisationalDetails.getLicenseNumber());
				orgDetails.get().setOwnershipDetails(organisationalDetails.getOwnershipDetails());
				orgDetails.get().setRegisteredName(organisationalDetails.getRegisteredName());

				organisationalDetailsRepository.save(orgDetails.get());
				message = "Organisational Details Updated Successfully!";
			} else {
				organisationalDetails.setUserBasicDetails(existingUser.get());
				organisationalDetailsRepository.save(organisationalDetails);
				message = "Organisational Details saved successfully!";

				RecordedProgress recPro = recordedProgressRepository.findByUserBasicDetailsUserId(userId);
				recPro.setOrganisationDetailsCompleted(true);
				recordedProgressRepository.save(recPro);
			}

		} else {
			message = "User Doesn't Exist!";
		}

		return message;
	}

	@Override
	public String submitAccountDetails(AccountDetails accountDetails, long userId) {
		String message = "";
		Optional<UserBasicDetails> existingUser = userBasicDetailsRepository.findById(userId);
		if (existingUser.isPresent()) {
			Optional<AccountDetails> accDetails = accountDetailsRepository.findByUserBasicDetailsUserId(userId);
			if (accDetails.isPresent()) {
				System.out.println(userId);
				System.out.println("_________________________BBBBBBBBBBBB________________________________");

				accDetails.get().setAccountNumber(accountDetails.getAccountNumber());
				accDetails.get().setIfscCode(accountDetails.getIfscCode());
				accDetails.get().setAltAccountNumber(accountDetails.getAltAccountNumber());
				accDetails.get().setAltIfscCode(accountDetails.getAltIfscCode());
				accDetails.get().setNomineeName(accountDetails.getNomineeName());
				accDetails.get().setNomineeAddress(accountDetails.getNomineeAddress());
				accDetails.get().setNomineeContactNumber(accountDetails.getNomineeContactNumber());
				accDetails.get().setNomineeEmail(accountDetails.getNomineeEmail());
				accDetails.get().setNomineeRelation(accountDetails.getNomineeRelation());
				accountDetailsRepository.save(accDetails.get());
				message = "Account Details Updated Successfully!";

			} else {
				accountDetails.setUserBasicDetails(existingUser.get());
				accountDetailsRepository.save(accountDetails);
				message = "Account Details saved successfully!";
				System.out.println("______________aaaaaaaaaaaaaaaaaaaaaaaaa_____________________________________");
				RecordedProgress recPro = recordedProgressRepository.findByUserBasicDetailsUserId(userId);
				System.out.println(recPro.isAccountDetailsCompleted());
				recPro.setAccountDetailsCompleted(true);
				System.out.println(recPro.isAccountDetailsCompleted());
				recordedProgressRepository.save(recPro);
			}
		} else {
			message = "User Doesn't Exist!";
		}

		return message;
	}

	@Override
	public String submitFreeText(FreeTextExperienceDetails freeTextExperienceDetails, long userId) {
		String message = "";
		Optional<UserBasicDetails> existingUser = userBasicDetailsRepository.findById(userId);
		if (existingUser.isPresent()) {
			Optional<FreeTextExperienceDetails> freeText = freeTextRepository.findByUserBasicDetailsUserId(userId);
			if (freeText.isPresent()) {
				freeText.get().setFreeText(freeTextExperienceDetails.getFreeText());

				freeTextRepository.save(freeText.get());
				message = "Free Text Updated Successfully!";
			} else {
				freeTextExperienceDetails.setUserBasicDetails(existingUser.get());
				freeTextRepository.save(freeTextExperienceDetails);
				message = "Free Text saved successfully!";
			}

		} else {
			message = "User Doesn't Exist!";
		}

		return message;
	}

	public RecordedProgress getProgress(long userId) {
		return recordedProgressRepository.findByUserBasicDetailsUserId(userId);
	}

}
