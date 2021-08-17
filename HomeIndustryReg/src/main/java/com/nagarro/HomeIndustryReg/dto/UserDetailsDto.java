package com.nagarro.HomeIndustryReg.dto;


/**
 * user details dto class
 * 
 * @author Anantjain
 *
 */
public class UserDetailsDto {
	
	
	private String userEmail;
	private String password;
	/**
	 * @param userEmail
	 * @param password
	 */
	public UserDetailsDto(String userEmail, String password) {
		super();
		this.userEmail = userEmail;
		this.password = password;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
