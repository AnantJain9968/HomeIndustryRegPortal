package com.nagarro.HomeIndustryReg.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * user entity class
 * 
 * @author Anantjain
 *
 */
@Entity
@Table(name = "UserBasicDetails")
public class UserBasicDetails implements UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private long userId;

	@Column(name = "UserEmail", unique = true)
	private String userEmail;
//	@JsonIgnore
	@Column(name = "Password")
	private String password;
	private String salutation;
	private String firstName;
	private String middleName;
	private String lastName;
	private String houseNo;
	private String streetName;
	private String area;
	private String city;
	private String state;
	private String country;
	private String landmark;
	private long phoneNumber;
	private long altNumber;
	private String altEmail;
	private String fax;
	private String designation;
	private String taxId;
	
	private boolean isEnabled;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getAltNumber() {
		return altNumber;
	}

	public void setAltNumber(long altNumber) {
		this.altNumber = altNumber;
	}

	public String getAltEmail() {
		return altEmail;
	}

	public void setAltEmail(String altEmail) {
		this.altEmail = altEmail;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public boolean getIsEnabled() {
		return isEnabled;
	}
	
	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * @param userId
	 * @param userEmail
	 * @param password
	 * @param salutation
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param houseNo
	 * @param streetName
	 * @param area
	 * @param city
	 * @param state
	 * @param country
	 * @param landmark
	 * @param phoneNumber
	 * @param altNumber
	 * @param altEmail
	 * @param fax
	 * @param designation
	 * @param taxId
	 */
	public UserBasicDetails(Long userId, String userEmail, String password, String salutation, String firstName,
			String middleName, String lastName, String houseNo, String streetName, String area, String city,
			String state, String country, String landmark, long phoneNumber, long altNumber, String altEmail,
			String fax, String designation, String taxId, boolean isEnabled) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.password = password;
		this.salutation = salutation;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.houseNo = houseNo;
		this.streetName = streetName;
		this.area = area;
		this.city = city;
		this.state = state;
		this.country = country;
		this.landmark = landmark;
		this.phoneNumber = phoneNumber;
		this.altNumber = altNumber;
		this.altEmail = altEmail;
		this.fax = fax;
		this.designation = designation;
		this.taxId = taxId;
		this.isEnabled = isEnabled;
	}
	

	public UserBasicDetails() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList<GrantedAuthority>();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isEnabled;
	}

}
