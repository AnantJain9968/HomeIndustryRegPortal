package com.nagarro.HomeIndustryReg.dto;

/**
 * slot details dto class
 * 
 * @author Anantjain
 *
 */
public class SlotDetailsDto {

	private String cityName;
	private String dateTime;
	private int slotNumber;
	private long userId;
	/**
	 * @param cityName
	 * @param dateTime
	 * @param slotNumber
	 * @param userId
	 */
	public SlotDetailsDto(String cityName, String dateTime, int slotNumber, long userId) {
		super();
		this.cityName = cityName;
		this.dateTime = dateTime;
		this.slotNumber = slotNumber;
		this.userId = userId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public int getSlotNumber() {
		return slotNumber;
	}
	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
