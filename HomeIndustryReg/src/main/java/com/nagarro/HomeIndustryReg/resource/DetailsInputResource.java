package com.nagarro.HomeIndustryReg.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.HomeIndustryReg.dto.ExperienceDocInfo;
import com.nagarro.HomeIndustryReg.message.ResponseMessage;
import com.nagarro.HomeIndustryReg.model.AccountDetails;
import com.nagarro.HomeIndustryReg.model.FreeTextExperienceDetails;
import com.nagarro.HomeIndustryReg.model.IdentificationDocuments;
import com.nagarro.HomeIndustryReg.model.MediaDocsExperienceDetails;
import com.nagarro.HomeIndustryReg.model.OrganisationalDetails;
import com.nagarro.HomeIndustryReg.model.RecordedProgress;
import com.nagarro.HomeIndustryReg.repository.AccountDetailsRepository;
import com.nagarro.HomeIndustryReg.repository.FreeTextExperienceDetailsRepository;
import com.nagarro.HomeIndustryReg.repository.OrganisationalDetailsRepository;
import com.nagarro.HomeIndustryReg.service.DetailsInputService;
import com.nagarro.HomeIndustryReg.service.FileStorageService;

@CrossOrigin
@RestController
public class DetailsInputResource {

	@Autowired
	private OrganisationalDetailsRepository organisationalDetailsRepository;
	@Autowired
	private AccountDetailsRepository accountDetailsRepository;
	@Autowired
	private FreeTextExperienceDetailsRepository freeTextRepository;
	@Autowired
	private FileStorageService storageService;
	@Autowired
	private DetailsInputService detailsInputService;

	@PostMapping(value = "/{typeOfDoc}/upload/{userId}")
	/**
	 * type-method (post)
	 *uploads identification document
	 * 
	 * @author Anantjain
	 *
	 */
	public ResponseEntity<ResponseMessage> uploadIdFile(@RequestParam("file") MultipartFile file,
			@PathVariable("typeOfDoc") String typeOfDoc, @PathVariable("userId") long userId) {

		System.out.println("IN UPLOAD FILE");
		String message = "";
		try {
			IdentificationDocuments doc = storageService.storeIdDocs(file, typeOfDoc, userId);

			if (doc == null) {
				message = "File should not be greater than 2 MB";
			} else {
				message = "Uploaded the file successfully";

			}
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("getIdentificationDocsName/{userId}")
	public List<String> getIdentificationDocsName(@PathVariable("userId") long userId) {
		return storageService.fetchIdentificationDocsName(userId);
	}

	@DeleteMapping("deleteIdentificationDoc/{typeOfDoc}/{userId}")
	public ResponseEntity<ResponseMessage> deleteIdentificationDocs(@PathVariable("typeOfDoc") String typeOfDoc,
			@PathVariable("userId") long userId) {
		String message = "";
		try {
			storageService.deleteIdentificationDoc(typeOfDoc, userId);
			message = "Deleted Successfully!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not Delete File!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@PreAuthorize("hasRole('Organisation')")
	@GetMapping("getOrganisationalDetails/{userId}")
	public ResponseEntity<OrganisationalDetails> getOrganisationalDetails(@PathVariable("userId") long userId) {

		ResponseEntity<OrganisationalDetails> response;
		Optional<OrganisationalDetails> orgDet = organisationalDetailsRepository.findByUserBasicDetailsUserId(userId);
		if (orgDet.isPresent()) {
			response = new ResponseEntity<>(orgDet.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

		return response;
	}

	@PreAuthorize("hasRole('Organisation')")
	@PostMapping("submitOrganisationalDetails/{userId}")
	/**
	 * type-method (post)
	 * arguments type-organisation details ,long
	 * saves organisation details
	 * 
	 * @author Anantjain
	 *
	 */
	public ResponseEntity<ResponseMessage> saveOrganisationalDetails(
			@RequestBody OrganisationalDetails organisationalDetails, @PathVariable("userId") long userId) {

		try {
			String submissionStatus = detailsInputService.submitOrganisationalDetails(organisationalDetails, userId);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(submissionStatus));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Submission Failed!"));
		}

	}

	@GetMapping("getAccountDetails/{userId}")
	public ResponseEntity<AccountDetails> getAccountDetails(@PathVariable("userId") long userId) {

		ResponseEntity<AccountDetails> response;
		Optional<AccountDetails> accDet = accountDetailsRepository.findByUserBasicDetailsUserId(userId);
		if (accDet.isPresent()) {
			response = new ResponseEntity<>(accDet.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

		return response;
	}

	@PostMapping("submitAccountDetails/{userId}")
	/**
	 * type-method (post)
	 * arguments type-account details ,long
	 * saves account details
	 * 
	 * @author Anantjain
	 *
	 */
	public ResponseEntity<ResponseMessage> saveAccountDetails(@RequestBody AccountDetails accountDetails,
			@PathVariable("userId") long userId) {

		try {
			String submissionStatus = detailsInputService.submitAccountDetails(accountDetails, userId);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(submissionStatus));
//			response = new ResponseEntity<>(new ApiResponse(submissionStatus), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Submission Failed!"));
//			response = new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("getFreeTextExperienceDetails/{userId}")
	public ResponseEntity<FreeTextExperienceDetails> getFreeTextExperienceDetails(@PathVariable("userId") long userId) {

		ResponseEntity<FreeTextExperienceDetails> response;
		Optional<FreeTextExperienceDetails> freeText = freeTextRepository.findByUserBasicDetailsUserId(userId);
		if (freeText.isPresent()) {
			response = new ResponseEntity<>(freeText.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

		return response;
	}

	@PostMapping("submitFreeTextExperienceDetails/{userId}")
	/**
	 * type-method (post)
	 * arguments type-FreeTextExperienceDetails ,long
	 * saves free text experience  details
	 * 
	 * @author Anantjain
	 *
	 */
	public ResponseEntity<ResponseMessage> saveFreeTextExperienceDetails(
			@RequestBody FreeTextExperienceDetails freeTextExperienceDetails, @PathVariable("userId") long userId) {

		try {
			String submissionStatus = detailsInputService.submitFreeText(freeTextExperienceDetails, userId);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(submissionStatus));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Submission Failed"));
		}
	}

	@GetMapping("getMediaDocsDetails/{userId}")
	public List<ExperienceDocInfo> getMediaDocsDetails(@PathVariable("userId") long userId) {

		return storageService.getMediaDocsDetails(userId);
	}

	@PostMapping("submitMediaDocsExperienceDetails/upload/{userId}")
	/**
	 * type-method (post)
	 * arguments type-Multipart ,long
	 * saves media docs details
	 * 
	 * @author Anantjain
	 *
	 */
	public ResponseEntity<ResponseMessage> saveMediaDocsExperienceDetails(@RequestParam("file") MultipartFile file,
			@PathVariable("userId") long userId) {
		String message = "";
		try {
			MediaDocsExperienceDetails medDoc = storageService.storeMediaDocs(file, userId);

			if (medDoc == null) {
				message = "Cannot Upload More than 10 Documents!";
			} else {
				message = "Uploaded the file successfully";
			}

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@DeleteMapping("deleteMediaDoc/{userId}/{docId}")
	public ResponseEntity<ResponseMessage> deleteMediaDoc(@PathVariable("docId") long docId,
			@PathVariable("userId") long userId) {
		String message = "";
		try {
			storageService.deleteMediaDoc(userId, docId);
			message = "Deleted Successfully!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not Delete File!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}

	}

	@GetMapping("getRecordedProgress/{userId}")
	public RecordedProgress getRecordedProgress(@PathVariable("userId") long userId) {
		return detailsInputService.getProgress(userId);
	}

}
