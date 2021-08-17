package com.nagarro.HomeIndustryReg.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.HomeIndustryReg.model.SlotDetails;

/**
 * slots repository
 * 
 * @author Anantjain
 *
 */
@Repository
public interface SlotRepository extends JpaRepository<SlotDetails, Long> {
	List<SlotDetails> findByCalendarDetailsCityAndCalendarDetailsDate(String cityName, LocalDateTime dateTime);
	SlotDetails findByCalendarDetailsCityAndCalendarDetailsDateAndSlotNumber(String cityName, LocalDateTime dateTime, int slotNumber);
	SlotDetails findByUserBasicDetailsUserId(Long userId);

}
