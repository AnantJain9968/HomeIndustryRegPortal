package com.nagarro.HomeIndustryReg.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.HomeIndustryReg.model.CalendarDetails;

/**
 * calendar repository
 * 
 * @author Anantjain
 *
 */
@Repository
public interface CalendarRepository extends JpaRepository<CalendarDetails, Long> {
	
	List<CalendarDetails> findByCity(String cityName);
	CalendarDetails findByCityAndDate(String cityName, LocalDateTime date);
	@Query("SELECT DISTINCT a.city FROM CalendarDetails a")
	List<String> findCities();
	
}
