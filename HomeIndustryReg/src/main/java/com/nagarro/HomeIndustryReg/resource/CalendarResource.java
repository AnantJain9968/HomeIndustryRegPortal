package com.nagarro.HomeIndustryReg.resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.HomeIndustryReg.dto.SlotDetailsDto;
import com.nagarro.HomeIndustryReg.message.ResponseMessage;
import com.nagarro.HomeIndustryReg.model.CalendarDetails;
import com.nagarro.HomeIndustryReg.model.SlotDetails;
import com.nagarro.HomeIndustryReg.service.CalendarService;

/**
 * calendar resource  class
 * 
 * @author Anantjain
 *
 */
@CrossOrigin
@RestController
public class CalendarResource {

	@Autowired
	private CalendarService calendarService;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

	
	@GetMapping("getCityNames")
	/**
	 * type-method (Get)
	 * returns  list of CityNames 
	 * 
	 * @author Anantjain
	 *
	 */
	public ResponseEntity<List<String>> getCityNames() {
		return new ResponseEntity<>(calendarService.getAllCities(), HttpStatus.OK);
	}

	@GetMapping("getCalendarDetails/{cityName}")
	/**
	 * type-method (Get)
	 * returns  list of CalendarDetails 
	 * 
	 * @author Anantjain
	 *
	 */
	public ResponseEntity<List<CalendarDetails>> getCalendarDetails(@PathVariable("cityName") String cityName) {
		return new ResponseEntity<>(calendarService.getAllDates(cityName), HttpStatus.OK);
	}

	@GetMapping("getSlotsForDate/{cityName}/{date}")
	/**
	 * type-method (Get)
	 * returns  list of slotDetails 
	 * 
	 * @author Anantjain
	 *
	 */
	public ResponseEntity<List<SlotDetails>> getSlotDetails(@PathVariable("cityName") String cityName,
			@PathVariable("date") String date) {
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		return new ResponseEntity<>(calendarService.getAllSlots(cityName, dateTime), HttpStatus.OK);
	}
	
	
	@GetMapping("checkSlotsBy/{userId}")
	/**
	 * type-method (Get)
	 * returns message, checks if slot is present for user 
	 * 
	 * @author Anantjain
	 *
	 */
	public ResponseEntity<ResponseMessage> checkSlotsByUserId(@PathVariable("userId") Long userId) {
		System.out.println("in check slots");
	 try {String message= calendarService.checkUserSlots(userId);
	return ResponseEntity.status(HttpStatus.OK)
			.body(new ResponseMessage(message));
	}catch (Exception e) {
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Cannot Book Slot"));
	}
	}

	
	@PostMapping("blockSlotForUser")
	/**
	 * type-method (post)
	 * argument type-slotdetailsdto
	 * blocks the slot for user
	 * 
	 * @author Anantjain
	 *
	 */
	public ResponseEntity<ResponseMessage> blockSlotForUser(@RequestBody SlotDetailsDto slotDetails) {

		LocalDateTime date = LocalDateTime.parse(slotDetails.getDateTime(), formatter);

		try {
			String blockingResult = calendarService.blockSlot(slotDetails, date);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseMessage(blockingResult));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Booking Of Slot Failed!"));
		}

	}


}
