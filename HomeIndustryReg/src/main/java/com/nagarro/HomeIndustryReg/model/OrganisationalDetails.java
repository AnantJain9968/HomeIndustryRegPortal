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
 * organisation entity class
 * 
 * @author Anantjain
 *
 */
@Entity
public class OrganisationalDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orgDetId")
	private long orgDetId;
	
	private String licenseNumber;
	private String registeredName;
	private String ownershipDetails;
	
	@JsonIgnore
	@OneToOne(targetEntity = UserBasicDetails.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private UserBasicDetails userBasicDetails;
	
	public OrganisationalDetails() {}

	/**
	 * @param orgDetId
	 * @param licenseNumber
	 * @param registeredName
	 * @param ownershipDetails
	 * @param userBasicDetails
	 */
	public OrganisationalDetails(long orgDetId, String licenseNumber, String registeredName, String ownershipDetails,
			UserBasicDetails userBasicDetails) {
		super();
		this.orgDetId = orgDetId;
		this.licenseNumber = licenseNumber;
		this.registeredName = registeredName;
		this.ownershipDetails = ownershipDetails;
		this.userBasicDetails = userBasicDetails;
	}

	public long getOrgDetId() {
		return orgDetId;
	}

	public void setOrgDetId(long orgDetId) {
		this.orgDetId = orgDetId;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getRegisteredName() {
		return registeredName;
	}

	public void setRegisteredName(String registeredName) {
		this.registeredName = registeredName;
	}

	public String getOwnershipDetails() {
		return ownershipDetails;
	}

	public void setOwnershipDetails(String ownershipDetails) {
		this.ownershipDetails = ownershipDetails;
	}

	public UserBasicDetails getUserBasicDetails() {
		return userBasicDetails;
	}

	public void setUserBasicDetails(UserBasicDetails userBasicDetails) {
		this.userBasicDetails = userBasicDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
