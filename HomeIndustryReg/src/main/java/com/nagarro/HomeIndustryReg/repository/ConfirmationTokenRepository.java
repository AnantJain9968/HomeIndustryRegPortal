package com.nagarro.HomeIndustryReg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.HomeIndustryReg.model.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
 