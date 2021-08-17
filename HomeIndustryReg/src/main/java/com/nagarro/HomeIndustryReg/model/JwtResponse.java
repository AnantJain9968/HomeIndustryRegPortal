package com.nagarro.HomeIndustryReg.model;

import java.io.Serializable;

public class JwtResponse  implements Serializable{
	
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private long userId;
	private String designation;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	
	public JwtResponse(String jwttoken, long userId, String designation) {
		this.jwttoken = jwttoken;
		this.userId = userId;
		this.designation = designation;
	}
	

	public String getToken() {
		return this.jwttoken;
	}
	
	public String getDesignation() {
		return this.designation;
	}
	
	public long getUserId() {
		return this.userId;
	}

}
