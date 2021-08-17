package com.nagarro.HomeIndustryReg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * progress entity class
 * 
 * @author Anantjain
 *
 */
@Entity
public class RecordedProgress {
	
	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="recProId")
	private long recProId;
	
	private boolean isUploadedDocCompleted;
	private boolean isAccountDetailsCompleted;
	private boolean isExperienceDetailsCompleted;
	private boolean isSlotBookingCompleted;
	private boolean isOrganisationDetailsCompleted;
	
	@JsonIgnore
	@OneToOne(targetEntity = UserBasicDetails.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private UserBasicDetails userBasicDetails;
	
	public RecordedProgress() {}

	/**
	 * @param recProId
	 * @param isUploadedDocCompleted
	 * @param isAccountDetailsCompleted
	 * @param isExperienceDetailsCompleted
	 * @param isSlotBookingCompleted
	 * @param isOrganisationDetailsCompleted
	 * @param userBasicDetails
	 */
	public RecordedProgress( boolean isUploadedDocCompleted, boolean isAccountDetailsCompleted,
			boolean isExperienceDetailsCompleted, boolean isSlotBookingCompleted,
			boolean isOrganisationDetailsCompleted, UserBasicDetails userBasicDetails) {
		super();
		this.isUploadedDocCompleted = isUploadedDocCompleted;
		this.isAccountDetailsCompleted = isAccountDetailsCompleted;
		this.isExperienceDetailsCompleted = isExperienceDetailsCompleted;
		this.isSlotBookingCompleted = isSlotBookingCompleted;
		this.isOrganisationDetailsCompleted = isOrganisationDetailsCompleted;
		this.userBasicDetails = userBasicDetails;
	}

	public long getRecProId() {
		return recProId;
	}

	public void setRecProId(long recProId) {
		this.recProId = recProId;
	}

	public boolean isUploadedDocCompleted() {
		return isUploadedDocCompleted;
	}

	public void setUploadedDocCompleted(boolean isUploadedDocCompleted) {
		this.isUploadedDocCompleted = isUploadedDocCompleted;
	}

	public boolean isAccountDetailsCompleted() {
		return isAccountDetailsCompleted;
	}

	public void setAccountDetailsCompleted(boolean isAccountDetailsCompleted) {
		this.isAccountDetailsCompleted = isAccountDetailsCompleted;
	}

	public boolean isExperienceDetailsCompleted() {
		return isExperienceDetailsCompleted;
	}

	public void setExperienceDetailsCompleted(boolean isExperienceDetailsCompleted) {
		this.isExperienceDetailsCompleted = isExperienceDetailsCompleted;
	}

	public boolean isSlotBookingCompleted() {
		return isSlotBookingCompleted;
	}

	public void setSlotBookingCompleted(boolean isSlotBookingCompleted) {
		this.isSlotBookingCompleted = isSlotBookingCompleted;
	}

	public boolean isOrganisationDetailsCompleted() {
		return isOrganisationDetailsCompleted;
	}

	public void setOrganisationDetailsCompleted(boolean isOrganisationDetailsCompleted) {
		this.isOrganisationDetailsCompleted = isOrganisationDetailsCompleted;
	}

	public UserBasicDetails getUserBasicDetails() {
		return userBasicDetails;
	}

	public void setUserBasicDetails(UserBasicDetails userBasicDetails) {
		this.userBasicDetails = userBasicDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	

}
