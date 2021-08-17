package com.nagarro.HomeIndustryReg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.HomeIndustryReg.model.RecordedProgress;

/**
 * progress repository
 * 
 * @author Anantjain
 *
 */
@Repository
public interface RecordedProgressRepository extends JpaRepository<RecordedProgress, Long> {

	RecordedProgress findByUserBasicDetailsUserId(long userId);

}
