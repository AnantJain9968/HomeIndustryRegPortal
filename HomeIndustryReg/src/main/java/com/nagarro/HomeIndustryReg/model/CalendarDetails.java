package com.nagarro.HomeIndustryReg.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * calendar entity class
 * 
 * @author Anantjain
 *
 */
@Entity
public class CalendarDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="calendarId")
	private long calendarId;
	
	private LocalDateTime date;
	
	private String city;
	
	private String dateStatus;
	
	public CalendarDetails() {}

	/**
	 * @param calendarId
	 * @param date
	 * @param city
	 * @param dateStatus
	 */
	public CalendarDetails(long calendarId, LocalDateTime date, String city, String dateStatus) {
		super();
		this.calendarId = calendarId;
		this.date = date;
		this.city = city;
		this.dateStatus = dateStatus;
	}

	public long getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(long calendarId) {
		this.calendarId = calendarId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDateStatus() {
		return dateStatus;
	}

	public void setDateStatus(String dateStatus) {
		this.dateStatus = dateStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
