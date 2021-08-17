package com.nagarro.HomeIndustryReg.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import com.nagarro.HomeIndustryReg.dto.SlotDetailsDto;
import com.nagarro.HomeIndustryReg.model.CalendarDetails;
import com.nagarro.HomeIndustryReg.model.SlotDetails;

/**
 * calendar details interface
 * 
 * @author Anantjain
 *
 */
public interface CalendarServiceInterface {

	List<CalendarDetails> getAllDates(String cityName);

	List<SlotDetails> getAllSlots(String cityName, LocalDateTime dateTime);

	String blockSlot(SlotDetailsDto slotDetailsDto, LocalDateTime date);

	List<String> getAllCities();

}
