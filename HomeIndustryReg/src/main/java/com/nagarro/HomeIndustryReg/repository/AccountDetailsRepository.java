package com.nagarro.HomeIndustryReg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.HomeIndustryReg.model.AccountDetails;

/**
 *account details repository
 * 
 * @author Anantjain
 *
 */
@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {
	Optional<AccountDetails> findByUserBasicDetailsUserId(long userId);
}
