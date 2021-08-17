package com.nagarro.HomeIndustryReg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.HomeIndustryReg.model.OrganisationalDetails;

/**
 * organisation repository
 * 
 * @author Anantjain
 *
 */
@Repository
public interface OrganisationalDetailsRepository extends JpaRepository<OrganisationalDetails, Long> {
	
	Optional<OrganisationalDetails> findByUserBasicDetailsUserId(long userId);

}
