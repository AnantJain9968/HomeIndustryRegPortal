package com.nagarro.HomeIndustryReg.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.HomeIndustryReg.model.RecordedProgress;
import com.nagarro.HomeIndustryReg.model.UserBasicDetails;
import com.nagarro.HomeIndustryReg.repository.RecordedProgressRepository;
import com.nagarro.HomeIndustryReg.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RecordedProgressRepository recordedProgressRepository;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

		UserBasicDetails existingUser = userRepository.findByUserEmailIgnoreCase(userEmail);
		if (existingUser == null) {
			throw new UsernameNotFoundException("User doesn't exist" + userEmail);
		}
		System.out.println("HERE IN LOADUSERBYNAME");
		return new org.springframework.security.core.userdetails.User(existingUser.getUserEmail(),
				existingUser.getPassword(), existingUser.getIsEnabled(), true, true, true, getAuthority(existingUser));

	}

	private Set<SimpleGrantedAuthority> getAuthority(UserBasicDetails user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getDesignation()));
//        user.getRoles().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//        });
		return authorities;
	}

	public UserBasicDetails findUser(String userEmail) {
		UserBasicDetails user = userRepository.findByUserEmailIgnoreCase(userEmail);
		if (user != null) {
			return user;
		}

		return null;
	}

	public void createProgressRecorder(UserBasicDetails savedUser) {
		RecordedProgress recPro = new RecordedProgress(false, false, false, false, false, savedUser);
		recordedProgressRepository.save(recPro);
	}

}
