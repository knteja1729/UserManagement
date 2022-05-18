package in.aitacs.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import in.aitacs.service.UserMgmtService;

@RestController
public class ForgotPwdRestController {

	@Autowired
	private UserMgmtService service;
	
	@GetMapping("/forgotpwd/{emailId}")
	public String forgotPwd(@PathVariable("emailId") String emailId) {
		return service.forgotPwd(emailId);
	}
}
