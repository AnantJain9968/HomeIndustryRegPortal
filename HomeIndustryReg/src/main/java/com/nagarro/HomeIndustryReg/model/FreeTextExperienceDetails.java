package com.nagarro.HomeIndustryReg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * free text entity class
 * 
 * @author Anantjain
 *
 */
@Entity
public class FreeTextExperienceDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "freeTextExperienceDetailsId")
	private long freeTextExperienceDetailsId;

	private String freeText;
	
	@OneToOne(targetEntity = UserBasicDetails.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "userId")
	private UserBasicDetails userBasicDetails;
	
	public FreeTextExperienceDetails() {
		
	}

	/**
	 * @param freeTextExperienceDetailsId
	 * @param freeText
	 * @param userBasicDetails
	 */
	public FreeTextExperienceDetails(long freeTextExperienceDetailsId, String freeText,
			UserBasicDetails userBasicDetails) {
		super();
		this.freeTextExperienceDetailsId = freeTextExperienceDetailsId;
		this.freeText = freeText;
		this.userBasicDetails = userBasicDetails;
	}

	public long getFreeTextExperienceDetailsId() {
		return freeTextExperienceDetailsId;
	}

	public void setFreeTextExperienceDetailsId(long freeTextExperienceDetailsId) {
		this.freeTextExperienceDetailsId = freeTextExperienceDetailsId;
	}

	public String getFreeText() {
		return freeText;
	}

	public void setFreeText(String freeText) {
		this.freeText = freeText;
	}

	public UserBasicDetails getUserBasicDetails() {
		return userBasicDetails;
	}

	public void setUserBasicDetails(UserBasicDetails userBasicDetails) {
		this.userBasicDetails = userBasicDetails;
	}
}
