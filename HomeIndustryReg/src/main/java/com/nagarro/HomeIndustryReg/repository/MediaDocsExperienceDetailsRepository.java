package com.nagarro.HomeIndustryReg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.HomeIndustryReg.model.MediaDocsExperienceDetails;

/**
 * Media docs repository
 * 
 * @author Anantjain
 *
 */
@Repository
public interface MediaDocsExperienceDetailsRepository extends JpaRepository<MediaDocsExperienceDetails, Long>{

	List<MediaDocsExperienceDetails> findByUserBasicDetailsUserId(long userId);
}
