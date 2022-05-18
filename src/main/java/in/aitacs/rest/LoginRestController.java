package in.aitacs.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.aitacs.binding.LoginForm;
import in.aitacs.service.UserMgmtService;

@RestController
public class LoginRestController {
	
	@Autowired
	private UserMgmtService service;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {
		return service.Login(loginForm);
	}

}
