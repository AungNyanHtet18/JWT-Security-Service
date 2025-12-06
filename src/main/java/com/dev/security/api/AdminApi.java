package com.dev.security.api;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminApi {

	List<String> index() {
		 return List.of("Message from Admin api.");
	}

}
