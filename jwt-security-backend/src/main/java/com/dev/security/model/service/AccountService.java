package com.dev.security.model.service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.security.api.input.ActivationForm;
import com.dev.security.api.input.SignUpForm;
import com.dev.security.api.output.ActivationResult;
import com.dev.security.api.output.SignUpResult;
import com.dev.security.model.repo.AccountRepo;
import com.dev.security.utils.exception.AppBusinessException;

@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepo accountRepo;
    @Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
	private AccountComfirmationService comfirmationService;
      
	public SignUpResult signUp(SignUpForm form) {
		
		if(accountRepo.findOneByEmail(form.email()).isPresent()) {
			throw new AppBusinessException("Your email is already used.");
		}
		
		//Generate OTP Code
		var otpCode = generateOtp();

		//Create Account
		var entity = form.entity();
		entity.setPassword(passwordEncoder.encode(otpCode));
		accountRepo.save(entity);
		
		//Send Email
		comfirmationService.sendComfirmMail(form.email(), otpCode);
		
	    return new SignUpResult(entity.getId(), "Please check your email");
	}
	
	
	public ActivationResult activate(ActivationForm form) {
        var account = accountRepo.findById(form.userId())
        						 .orElseThrow(() -> new AppBusinessException("Invalid Account Id."));
    	
        
        if(null != account.getActivatedAt()) {
        	 throw new AppBusinessException("Account had been activated.");
        }
        
        if(!passwordEncoder.matches(form.otpCode(), account.getPassword())) {
        	 throw new AppBusinessException("Invalid OTP Code.");
        }
        
        account.setPassword(passwordEncoder.encode(form.password()));
        account.setActivatedAt(LocalDateTime.now());
       
		return new ActivationResult("Your account has been activated.");
	}
    
    private String generateOtp() {
    	var code = ThreadLocalRandom.current().nextInt(999999);  //Generating random 6 numbers from ThreadLocalRandom  ||  Maximum 6 number
    	return "%06d".formatted(code);
    }
}
