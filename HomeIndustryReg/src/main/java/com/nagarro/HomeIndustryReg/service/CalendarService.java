package com.nagarro.HomeIndustryReg.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nagarro.HomeIndustryReg.dto.SlotDetailsDto;
import com.nagarro.HomeIndustryReg.interfaces.CalendarServiceInterface;
import com.nagarro.HomeIndustryReg.model.CalendarDetails;
import com.nagarro.HomeIndustryReg.model.RecordedProgress;
import com.nagarro.HomeIndustryReg.model.SlotDetails;
import com.nagarro.HomeIndustryReg.model.UserBasicDetails;
import com.nagarro.HomeIndustryReg.repository.CalendarRepository;
import com.nagarro.HomeIndustryReg.repository.RecordedProgressRepository;
import com.nagarro.HomeIndustryReg.repository.SlotRepository;
import com.nagarro.HomeIndustryReg.repository.UserRepository;


/**
 * Calendar services
 * contains all the methods which provides services 
 * for slots and calendar models
 *
 */
@Service
public class CalendarService implements CalendarServiceInterface {

	@Autowired
	private CalendarRepository calendarRepository;

	@Autowired
	private SlotRepository slotRepository;

	@Autowired
	private UserRepository userBasicDetailsRepository;

	@Autowired
	private RecordedProgressRepository recordedProgressRepository;

	@Override
	public List<CalendarDetails> getAllDates(String cityName) {
		return calendarRepository.findByCity(cityName);
	}

	@Override
	public List<SlotDetails> getAllSlots(String cityName, LocalDateTime dateTime) {

		return slotRepository.findByCalendarDetailsCityAndCalendarDetailsDate(cityName, dateTime);
	}

	@Override
	public String blockSlot(SlotDetailsDto slotDetailsDto, LocalDateTime date) {
		String message = "";
		try {
			SlotDetails slotDetail = slotRepository.findByCalendarDetailsCityAndCalendarDetailsDateAndSlotNumber(
					slotDetailsDto.getCityName(), date, slotDetailsDto.getSlotNumber());
			Optional<UserBasicDetails> existingUser = userBasicDetailsRepository.findById(slotDetailsDto.getUserId());
			setCalendarDateStatus(slotDetailsDto.getCityName(), date);
			slotDetail.setUserBasicDetails(existingUser.get());
			slotDetail.setBooked(true);
			slotRepository.save(slotDetail);
			RecordedProgress recPro = recordedProgressRepository
					.findByUserBasicDetailsUserId(slotDetailsDto.getUserId());
			System.out.println(slotDetailsDto.getUserId());
			recPro.setSlotBookingCompleted(true);
			recordedProgressRepository.save(recPro);
			message = "Slot Booked successfully!";
		} catch (Exception e) {
			message = "Could Not Booked Slot!";
		}
		return message;
	}

	@Override
	public List<String> getAllCities() {
		return calendarRepository.findCities();
	}

	private void setCalendarDateStatus(String cityName, LocalDateTime date) {
		CalendarDetails calendarDet = calendarRepository.findByCityAndDate(cityName, date);
		int count = 1;
		System.out.println(calendarDet.getDateStatus());
		List<SlotDetails> slotDetails = slotRepository.findByCalendarDetailsCityAndCalendarDetailsDate(cityName, date);
		for (SlotDetails slotDetail : slotDetails) {
			if (slotDetail.isBooked()) {
				count += 1;
				System.out.println(count);
			}
		}
		System.out.println("...................");
		System.out.println(count);
		System.out.println(slotDetails.size());
		if (count == slotDetails.size()) {
			calendarDet.setDateStatus("All_Slots_Booked");
		} else if (count == 0) {
			calendarDet.setDateStatus("All_Slots_Vacant");
		} else {
			calendarDet.setDateStatus("Some_Slots_Available");
		}

	}

	public String checkUserSlots(Long userId) {
		String message = "";
		try {
			SlotDetails slotDetail = slotRepository.findByUserBasicDetailsUserId(userId);
			if (slotDetail != null) {
				message = "Already Booked Slot";
			}
		} catch (Exception e) {
			message = "Slot booked Successfully!";
		}
		return message;
	}

}
