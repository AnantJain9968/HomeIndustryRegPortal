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
 * account entity class
 * 
 * @author Anantjain
 *
 */
@Entity
public class AccountDetails {


		private static final long serialVersionUID = 1L;

		@Id
		@JsonIgnore
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="accDetId")
		private long accDetId;
		
		private String accountNumber;
		private String ifscCode;
		private String altAccountNumber;
		private String altIfscCode;
		private String nomineeName;
		private String nomineeRelation;
		private String nomineeContactNumber;
		private String nomineeEmail;
		private String nomineeAddress;
		
		@JsonIgnore
		@OneToOne(targetEntity = UserBasicDetails.class, fetch = FetchType.EAGER)
	    @JoinColumn(nullable = false, name = "userId")
	    private UserBasicDetails userBasicDetails;
		
		public AccountDetails() {}

		/**
		 * @param accDetId
		 * @param accountNumber
		 * @param ifscCode
		 * @param altAccountNumber
		 * @param altIfscCode
		 * @param nomineeName
		 * @param nomineeRelation
		 * @param nomineeContactNumber
		 * @param nomineeEmail
		 * @param nomineeAddress
		 * @param userBasicDetails
		 */
		public AccountDetails(long accDetId, String accountNumber, String ifscCode, String altAccountNumber,
				String altIfscCode, String nomineeName, String nomineeRelation, String nomineeContactNumber,
				String nomineeEmail, String nomineeAddress, UserBasicDetails userBasicDetails) {
			super();
			this.accDetId = accDetId;
			this.accountNumber = accountNumber;
			this.ifscCode = ifscCode;
			this.altAccountNumber = altAccountNumber;
			this.altIfscCode = altIfscCode;
			this.nomineeName = nomineeName;
			this.nomineeRelation = nomineeRelation;
			this.nomineeContactNumber = nomineeContactNumber;
			this.nomineeEmail = nomineeEmail;
			this.nomineeAddress = nomineeAddress;
			this.userBasicDetails = userBasicDetails;
		}

		public long getAccDetId() {
			return accDetId;
		}

		public void setAccDetId(long accDetId) {
			this.accDetId = accDetId;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public String getIfscCode() {
			return ifscCode;
		}

		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}

		public String getAltAccountNumber() {
			return altAccountNumber;
		}

		public void setAltAccountNumber(String altAccountNumber) {
			this.altAccountNumber = altAccountNumber;
		}

		public String getAltIfscCode() {
			return altIfscCode;
		}

		public void setAltIfscCode(String altIfscCode) {
			this.altIfscCode = altIfscCode;
		}

		public String getNomineeName() {
			return nomineeName;
		}

		public void setNomineeName(String nomineeName) {
			this.nomineeName = nomineeName;
		}

		public String getNomineeRelation() {
			return nomineeRelation;
		}

		public void setNomineeRelation(String nomineeRelation) {
			this.nomineeRelation = nomineeRelation;
		}

		public String getNomineeContactNumber() {
			return nomineeContactNumber;
		}

		public void setNomineeContactNumber(String nomineeContactNumber) {
			this.nomineeContactNumber = nomineeContactNumber;
		}

		public String getNomineeEmail() {
			return nomineeEmail;
		}

		public void setNomineeEmail(String nomineeEmail) {
			this.nomineeEmail = nomineeEmail;
		}

		public String getNomineeAddress() {
			return nomineeAddress;
		}

		public void setNomineeAddress(String nomineeAddress) {
			this.nomineeAddress = nomineeAddress;
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
