package com.nagarro.HomeIndustryReg.repository;

import com.nagarro.HomeIndustryReg.model.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * free text repository
 * 
 * @author Anantjain
 *
 */
@Repository
public interface FreeTextExperienceDetailsRepository extends JpaRepository<FreeTextExperienceDetails , Long> {

	Optional<FreeTextExperienceDetails> findByUserBasicDetailsUserId(long userId);

}
