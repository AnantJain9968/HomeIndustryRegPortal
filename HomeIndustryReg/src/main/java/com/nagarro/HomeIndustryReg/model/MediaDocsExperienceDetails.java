package com.nagarro.HomeIndustryReg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * Media documents entity class
 * 
 * @author Anantjain
 *
 */
@Entity
public class MediaDocsExperienceDetails {

	public final static long perfileSizeLimit = 1024 * 1024 * 100;
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mediaDocsExperienceDetailsId")
	private long mediaDocsExperienceDetailsId;


	@Lob
	private byte[] mediaDoc;

	private float mediaDocSize;

	private String mediaDocName;
	private String mediaDocType;

	
	@ManyToOne(targetEntity = UserBasicDetails.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "userId")
	private UserBasicDetails userBasicDetails;

	public MediaDocsExperienceDetails() {
			
		}

	/**
	 * @param mediaDocsExperienceDetailsId
	 * @param mediaDoc
	 * @param mediaDocSize
	 * @param mediaDocName
	 * @param mediaDocType
	 * @param userBasicDetails
	 */
	public MediaDocsExperienceDetails(long mediaDocsExperienceDetailsId,
			byte[] mediaDoc, float mediaDocSize, String mediaDocName, String mediaDocType,
			UserBasicDetails userBasicDetails) {
		super();
		this.mediaDocsExperienceDetailsId = mediaDocsExperienceDetailsId;
		this.mediaDoc = mediaDoc;
		this.mediaDocSize = mediaDocSize;
		this.mediaDocName = mediaDocName;
		this.mediaDocType = mediaDocType;
		this.userBasicDetails = userBasicDetails;
	}

	public long getMediaDocsExperienceDetailsId() {
		return mediaDocsExperienceDetailsId;
	}

	public void setMediaDocsExperienceDetailsId(long mediaDocsExperienceDetailsId) {
		this.mediaDocsExperienceDetailsId = mediaDocsExperienceDetailsId;
	}

	public byte[] getMediaDoc() {
		return mediaDoc;
	}

	public void setMediaDoc(byte[] mediaDoc) {
		this.mediaDoc = mediaDoc;
	}

	public float getMediaDocSize() {
		return (float) (Math.round((mediaDocSize/perfileSizeLimit) * 100.0) / 100.0);
	}

	public void setMediaDocSize(float mediaDocSize) {
		this.mediaDocSize = mediaDocSize;
	}

	public String getMediaDocName() {
		return mediaDocName;
	}

	public void setMediaDocName(String mediaDocName) {
		this.mediaDocName = mediaDocName;
	}

	public String getMediaDocType() {
		return mediaDocType;
	}

	public void setMediaDocType(String mediaDocType) {
		this.mediaDocType = mediaDocType;
	}

	public UserBasicDetails getUserBasicDetails() {
		return userBasicDetails;
	}

	public void setUserBasicDetails(UserBasicDetails userBasicDetails) {
		this.userBasicDetails = userBasicDetails;
	}

	

}
