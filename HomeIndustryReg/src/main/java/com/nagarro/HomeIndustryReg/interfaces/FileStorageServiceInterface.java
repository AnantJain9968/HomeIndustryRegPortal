package com.nagarro.HomeIndustryReg.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.HomeIndustryReg.dto.ExperienceDocInfo;
import com.nagarro.HomeIndustryReg.model.IdentificationDocuments;
import com.nagarro.HomeIndustryReg.model.MediaDocsExperienceDetails;

/**
 * file service interface
 * 
 * @author Anantjain
 *
 */
public interface FileStorageServiceInterface {

	IdentificationDocuments storeIdDocs(MultipartFile file, String typeOfDoc, long userId) throws IOException;

	List<String> fetchIdentificationDocsName(long userId);

	MediaDocsExperienceDetails storeMediaDocs(MultipartFile file, long userId) throws IOException;

	void deleteIdentificationDoc(String typeOfDoc, long userId);

	List<ExperienceDocInfo> getMediaDocsDetails(long userId);

	void deleteMediaDoc(long userId, long docId);



}
