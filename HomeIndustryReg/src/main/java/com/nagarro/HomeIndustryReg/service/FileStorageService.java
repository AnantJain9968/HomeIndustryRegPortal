package com.nagarro.HomeIndustryReg.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.HomeIndustryReg.dto.ExperienceDocInfo;
import com.nagarro.HomeIndustryReg.interfaces.FileStorageServiceInterface;
import com.nagarro.HomeIndustryReg.model.IdentificationDocuments;
import com.nagarro.HomeIndustryReg.model.MediaDocsExperienceDetails;
import com.nagarro.HomeIndustryReg.model.RecordedProgress;
import com.nagarro.HomeIndustryReg.model.UserBasicDetails;
import com.nagarro.HomeIndustryReg.repository.IdentificationDocumentsRepository;
import com.nagarro.HomeIndustryReg.repository.MediaDocsExperienceDetailsRepository;
import com.nagarro.HomeIndustryReg.repository.RecordedProgressRepository;
import com.nagarro.HomeIndustryReg.repository.UserRepository;

/**
 * FileStorage  services
 * contains all the methods which provides services 
 * for slots and calendar models
 *
 */
@Service
public class FileStorageService  implements FileStorageServiceInterface{
	
	private final long MAX_SIZE_ALLOWED = 2*1024*1024;

	@Autowired
	private IdentificationDocumentsRepository identificationDocumentsRepository;

	@Autowired
	private UserRepository userBasicDetailsRepository;

	@Autowired
	private MediaDocsExperienceDetailsRepository mediaDocsExperienceDetailsRepository;
	
	@Autowired
	private RecordedProgressRepository recordedProgressRepository;

	@Override
	public IdentificationDocuments storeIdDocs(MultipartFile file, String typeOfDoc, long userId) throws IOException {
		if(file.getSize() > MAX_SIZE_ALLOWED) {
			return null;
		}
		
		IdentificationDocuments existingDoc = identificationDocumentsRepository.findByUserBasicDetailsUserId(userId);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Optional<UserBasicDetails> existingUser = userBasicDetailsRepository.findById(userId);
		if (existingDoc == null) {
			IdentificationDocuments docs = new IdentificationDocuments();
			if (typeOfDoc.equals("identityDoc")) {
				docs.setIdentityDocName(fileName);
				docs.setIdentityDoc(file.getBytes());
				docs.setIdentityDocType(file.getContentType());
				docs.setIdentityDocSize(file.getSize());
				docs.setUserBasicDetails(existingUser.get());

				return identificationDocumentsRepository.save(docs);
			} else if (typeOfDoc.equals("taxIdDoc")) {
				docs.setTaxIdDocName(fileName);
				docs.setTaxId(file.getBytes());
				docs.setTaxIdDocType(file.getContentType());
				docs.setTaxIdSize(file.getSize());
				docs.setUserBasicDetails(existingUser.get());

				return identificationDocumentsRepository.save(docs);
			}

		} else {
			if (typeOfDoc.equals("identityDoc")) {
				existingDoc.setIdentityDocName(fileName);
				existingDoc.setIdentityDoc(file.getBytes());
				existingDoc.setIdentityDocType(file.getContentType());
				existingDoc.setIdentityDocSize(file.getSize());

			} else if (typeOfDoc.equals("taxIdDoc")) {
				existingDoc.setTaxIdDocName(fileName);
				existingDoc.setTaxId(file.getBytes());
				existingDoc.setTaxIdDocType(file.getContentType());
				existingDoc.setTaxIdSize(file.getSize());

			}
			RecordedProgress recPro = recordedProgressRepository.findByUserBasicDetailsUserId(userId);
			recPro.setUploadedDocCompleted(true);
			recordedProgressRepository.save(recPro);
			return identificationDocumentsRepository.save(existingDoc);
		}
		return existingDoc;
	}

	@Override
	public List<String> fetchIdentificationDocsName(long userId) {
		IdentificationDocuments existingDoc = identificationDocumentsRepository.findByUserBasicDetailsUserId(userId);
		List<String> docsName = new ArrayList<String>();
		if (existingDoc != null) {
			docsName.add(existingDoc.getIdentityDocName());
			docsName.add(existingDoc.getTaxIdDocName());
		}

		return docsName;
	}

	@Override
	public MediaDocsExperienceDetails storeMediaDocs(MultipartFile file, long userId) throws IOException {
		List<MediaDocsExperienceDetails> mediaDocs = mediaDocsExperienceDetailsRepository.findByUserBasicDetailsUserId(userId);
		System.out.println(mediaDocs.size());
		if(mediaDocs.size() > 9) {
			return null;
		}
		MediaDocsExperienceDetails newDoc = new MediaDocsExperienceDetails();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Optional<UserBasicDetails> existingUser = userBasicDetailsRepository.findById(userId);

		newDoc.setMediaDocName(fileName);
		newDoc.setMediaDoc(file.getBytes());
		newDoc.setMediaDocType(file.getContentType());
		newDoc.setMediaDocSize(file.getSize());
		newDoc.setUserBasicDetails(existingUser.get());
		
		RecordedProgress recPro = recordedProgressRepository.findByUserBasicDetailsUserId(userId);
		recPro.setExperienceDetailsCompleted(true);
		recordedProgressRepository.save(recPro);

		return mediaDocsExperienceDetailsRepository.save(newDoc);
	}

	@Override
	public void deleteIdentificationDoc(String typeOfDoc, long userId) {

		IdentificationDocuments existingDoc = identificationDocumentsRepository.findByUserBasicDetailsUserId(userId);
		if (typeOfDoc.equals("identityDoc")) {
			existingDoc.setIdentityDoc(null);
			existingDoc.setIdentityDocName(null);
			existingDoc.setIdentityDocSize(0);
			existingDoc.setIdentityDocType(null);
		} else {
			existingDoc.setTaxId(null);
			existingDoc.setTaxIdDocName(null);
			existingDoc.setTaxIdSize(0);
			existingDoc.setTaxIdDocType(null);
		}

		if (existingDoc.getIdentityDoc() == null && existingDoc.getTaxId() == null) {
			identificationDocumentsRepository.deleteById(existingDoc.getIdentityDocId());
		} else {
			identificationDocumentsRepository.save(existingDoc);
		}

	}

	@Override
	public List<ExperienceDocInfo> getMediaDocsDetails(long userId) {
		List<MediaDocsExperienceDetails> existingDocs = mediaDocsExperienceDetailsRepository
				.findByUserBasicDetailsUserId(userId);

		List<ExperienceDocInfo> docsDetails = new ArrayList<>();
		for (MediaDocsExperienceDetails mediaDoc : existingDocs) {
			ExperienceDocInfo doc = new ExperienceDocInfo(mediaDoc.getMediaDocName(), mediaDoc.getMediaDocsExperienceDetailsId());
			docsDetails.add(doc);
		}
		System.out.println(docsDetails);
		
		return docsDetails;
	}

	@Override
	public void deleteMediaDoc(long userId, long docId) {
		mediaDocsExperienceDetailsRepository.deleteById(docId);
	}

}