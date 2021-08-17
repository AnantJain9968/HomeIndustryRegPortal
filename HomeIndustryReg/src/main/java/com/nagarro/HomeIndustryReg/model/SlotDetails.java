package com.nagarro.HomeIndustryReg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * slots entity class
 * 
 * @author Anantjain
 *
 */
@Entity
public class SlotDetails {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="slotId")
	private long slotId;
	
	private int slotNumber;
	
	private boolean isBooked;
	
	
	@ManyToOne(targetEntity = CalendarDetails.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "calendarId")
	private CalendarDetails calendarDetails;
	

	@OneToOne(targetEntity = UserBasicDetails.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "userId")
	@JsonIgnore
    private UserBasicDetails userBasicDetails;

	public SlotDetails() {}
	
	/**
	 * @param slotId
	 * @param slotNumber
	 * @param isBooked
	 * @param calendarDetails
	 * @param userBasicDetails
	 */
	public SlotDetails(long slotId, int slotNumber, boolean isBooked, CalendarDetails calendarDetails,
			UserBasicDetails userBasicDetails) {
		super();
		this.slotId = slotId;
		this.slotNumber = slotNumber;
		this.isBooked = isBooked;
		this.calendarDetails = calendarDetails;
		this.userBasicDetails = userBasicDetails;
	}

	public long getSlotId() {
		return slotId;
	}

	public void setSlotId(long slotId) {
		this.slotId = slotId;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public CalendarDetails getCalendarDetails() {
		return calendarDetails;
	}

	public void setCalendarDetails(CalendarDetails calendarDetails) {
		this.calendarDetails = calendarDetails;
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
