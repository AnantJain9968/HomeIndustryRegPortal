package com.nagarro.HomeIndustryReg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.HomeIndustryReg.model.IdentificationDocuments;

/**
 * Identification repository
 * 
 * @author Anantjain
 *
 */
@Repository
public interface IdentificationDocumentsRepository extends JpaRepository<IdentificationDocuments, Long> {

	IdentificationDocuments findByUserBasicDetailsUserId(long userId);
}
