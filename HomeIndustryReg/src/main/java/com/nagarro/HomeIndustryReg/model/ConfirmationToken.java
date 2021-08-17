package com.nagarro.HomeIndustryReg.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ConfirmationToken {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = UserBasicDetails.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId")
    private UserBasicDetails userBasicDetails;
    
    public ConfirmationToken() {}

    public ConfirmationToken(UserBasicDetails userBasicDetails) {
        this.userBasicDetails = userBasicDetails;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

	

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public UserBasicDetails getUserBasicDetails() {
		return userBasicDetails;
	}

	public void setUser(UserBasicDetails userBasicDetails) {
		this.userBasicDetails = userBasicDetails;
	}

	public long getTokenid() {
		return tokenid;
	}

	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}
}