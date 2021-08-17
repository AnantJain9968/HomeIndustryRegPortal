package com.nagarro.HomeIndustryReg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * identification documents entity class
 * 
 * @author Anantjain
 *
 */
@Entity
public class IdentificationDocuments {

	public final static long perfileSizeLimit = 1024*1024*10;
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="identityDocId")
	private long identityDocId;
	
	
	private String identityDocName;
	private String identityDocType;
	
	@Lob
	private byte[] identityDoc;
	
	private float identityDocSize;
	
	private String taxIdDocName;
	private String taxIdDocType;
	
	@Lob
	private byte[] taxId;
	
	private float taxIdSize;
	@OneToOne(targetEntity = UserBasicDetails.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private UserBasicDetails userBasicDetails;
	
	public IdentificationDocuments() {
		
	}
	
	/**
	 * @param identityDocId
	 * @param identityDocName
	 * @param identityDocType
	 * @param identityDoc
	 * @param identityDocSize
	 * @param taxIdDocName
	 * @param taxIdDocType
	 * @param taxId
	 * @param taxIdSize
	 * @param userBasicDetails
	 */
	public IdentificationDocuments(long identityDocId, String identityDocName, String identityDocType,
			byte[] identityDoc, float identityDocSize, String taxIdDocName, String taxIdDocType, byte[] taxId,
			float taxIdSize, UserBasicDetails userBasicDetails) {
		super();
		this.identityDocId = identityDocId;
		this.identityDocName = identityDocName;
		this.identityDocType = identityDocType;
		this.identityDoc = identityDoc;
		this.identityDocSize = identityDocSize;
		this.taxIdDocName = taxIdDocName;
		this.taxIdDocType = taxIdDocType;
		this.taxId = taxId;
		this.taxIdSize = taxIdSize;
		this.userBasicDetails = userBasicDetails;
	}
	public long getIdentityDocId() {
		return identityDocId;
	}
	public void setIdentityDocId(long identityDocId) {
		this.identityDocId = identityDocId;
	}
	public byte[] getIdentityDoc() {
		return identityDoc;
	}
	public void setIdentityDoc(byte[] identityDoc) {
		this.identityDoc = identityDoc;
	}
	public float getIdentityDocSize() {
		return (float) (Math.round((identityDocSize/perfileSizeLimit) * 100.0) / 100.0);
	}
	public void setIdentityDocSize(float identityDocSize) {
		this.identityDocSize = identityDocSize;
	}
	public byte[] getTaxId() {
		return taxId;
	}
	public void setTaxId(byte[] taxId) {
		this.taxId = taxId;
	}
	public float getTaxIdSize() {
		return (float) (Math.round((taxIdSize/perfileSizeLimit) * 100.0) / 100.0);
	}
	public void setTaxIdSize(float taxIdSize) {
		this.taxIdSize = taxIdSize;
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
	public String getIdentityDocName() {
		return identityDocName;
	}
	public void setIdentityDocName(String identityDocName) {
		this.identityDocName = identityDocName;
	}
	public String getIdentityDocType() {
		return identityDocType;
	}
	public void setIdentityDocType(String identityDocType) {
		this.identityDocType = identityDocType;
	}
	public String getTaxIdDocName() {
		return taxIdDocName;
	}
	public void setTaxIdDocName(String taxIdDocName) {
		this.taxIdDocName = taxIdDocName;
	}
	public String getTaxIdDocType() {
		return taxIdDocType;
	}
	public void setTaxIdDocType(String taxIdDocType) {
		this.taxIdDocType = taxIdDocType;
	}
	

	
}
