package com.dev.security.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dev.security.api.input.ActivationForm;
import com.dev.security.api.output.ActivationResult;
import com.dev.security.model.service.AccountService;

@RestController
@RequestMapping("auth/activate")
public class ActivationApi {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping
	ActivationResult activate(@Validated @RequestBody ActivationForm form) {
		 return accountService.activate(form);
	}
	
}
