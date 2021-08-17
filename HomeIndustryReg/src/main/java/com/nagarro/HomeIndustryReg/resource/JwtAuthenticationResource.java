package com.nagarro.HomeIndustryReg.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.HomeIndustryReg.message.ResponseMessage;
import com.nagarro.HomeIndustryReg.model.ConfirmationToken;
import com.nagarro.HomeIndustryReg.model.JwtRequest;
import com.nagarro.HomeIndustryReg.model.JwtResponse;
import com.nagarro.HomeIndustryReg.model.UserBasicDetails;
import com.nagarro.HomeIndustryReg.repository.ConfirmationTokenRepository;
import com.nagarro.HomeIndustryReg.repository.UserRepository;
import com.nagarro.HomeIndustryReg.service.EmailSenderService;
import com.nagarro.HomeIndustryReg.service.JwtUserDetailsService;
import com.nagarro.HomeIndustryReg.util.JwtTokenUtil;

@CrossOrigin
@RestController
public class JwtAuthenticationResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	
	@Autowired
	private UserRepository userBasicDetailsRepository;

	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	

	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUserEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		UserBasicDetails user = userDetailsService.findUser(authenticationRequest.getUserEmail());

		return ResponseEntity.ok(new JwtResponse(token, user.getUserId(), user.getDesignation()));
	}
		
	private void authenticate(String userEmail, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
//	@PostMapping("register")
//	public ResponseEntity<String> registerUser(@RequestBody UserBasicDetails userBasicDetails) {
//
//		ResponseEntity<String> response;
//
//		UserBasicDetails existingUser = userBasicDetailsRepository
//				.findByUserEmailIgnoreCase(userBasicDetails.getUserEmail());
//
//		if (existingUser != null) {
//			System.out.println("Here In existing User");
//			response = new ResponseEntity<String>("This email already exists!", HttpStatus.CONFLICT);
//		} else {
//			userBasicDetails.setPassword(bcryptEncoder.encode(userBasicDetails.getPassword()));
//			userBasicDetailsRepository.save(userBasicDetails);
//
//			ConfirmationToken confirmationToken = new ConfirmationToken(userBasicDetails);
//
//			confirmationTokenRepository.save(confirmationToken);
//
//			SimpleMailMessage mailMessage = new SimpleMailMessage();
//			mailMessage.setTo(userBasicDetails.getUserEmail());
//			mailMessage.setSubject("Complete Registration!");
//			mailMessage.setFrom("homeindustryreg@gmail.com");
//			mailMessage.setText("To confirm your account, please click here : "
//					+ "http://localhost:8081/confirm-account?token=" + confirmationToken.getConfirmationToken());
//
//			emailSenderService.sendEmail(mailMessage);
//			response = new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
//
//		}
//
//		return response;
//
//	}

	@PostMapping("register")
	public ResponseEntity<ResponseMessage> registerUser(@RequestBody UserBasicDetails userBasicDetails) {


		UserBasicDetails existingUser = userBasicDetailsRepository
				.findByUserEmailIgnoreCase(userBasicDetails.getUserEmail());

		if (existingUser != null) {
			System.out.println("Here In existing User");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage("This email already exists!"));
		} else {
			userBasicDetails.setPassword(bcryptEncoder.encode(userBasicDetails.getPassword()));
			UserBasicDetails savedUser = userBasicDetailsRepository.save(userBasicDetails);
			userDetailsService.createProgressRecorder(savedUser);

			ConfirmationToken confirmationToken = new ConfirmationToken(userBasicDetails);

			confirmationTokenRepository.save(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(userBasicDetails.getUserEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("homeindustryreg@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:8081/confirm-account?token=" + confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("A Verification mail has been sent to your registered emailId!"));

		}


	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String confirmationToken) {
		ResponseEntity<String> response;
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			UserBasicDetails userBasicDetails = userBasicDetailsRepository
					.findByUserEmailIgnoreCase(token.getUserBasicDetails().getUserEmail());
			userBasicDetails.setIsEnabled(true);
			userBasicDetailsRepository.save(userBasicDetails);
			response = new ResponseEntity<String>("Account Verified! http://localhost:4200/login", HttpStatus.ACCEPTED);
		} else {
			response = new ResponseEntity<String>("The link is invalid or broken!", HttpStatus.BAD_GATEWAY);
		}

		return response;
	}
	
}
