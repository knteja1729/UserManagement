package in.aitacs.service;

import java.util.Map;

import in.aitacs.binding.LoginForm;
import in.aitacs.binding.UnlockForm;
import in.aitacs.binding.UserForm;

public interface UserMgmtService {                                                                                                                                                                                                                                                                                                            

	public String Login(LoginForm loginForm);
	
	public String emailCheck(String email);
	
	public Map<Integer,String> loadCountries();
	
	public Map<Integer,String> loadStates(Integer countryId);
	
	public Map<Integer,String> loadCities(Integer stateId);
	
	public String registerUser(UserForm userForm);
	
	public String unlockAccount(UnlockForm unlockForm);
	
	public String forgotPwd(String email);
}
