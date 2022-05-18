package in.aitacs.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.aitacs.binding.LoginForm;
import in.aitacs.binding.UnlockForm;
import in.aitacs.binding.UserForm;
import in.aitacs.entity.CityMasterEntity;
import in.aitacs.entity.CountryMasterEntity;
import in.aitacs.entity.StateMasterEntity;
import in.aitacs.entity.UserAccountEntity;
import in.aitacs.repository.CityRepository;
import in.aitacs.repository.CountryRepository;
import in.aitacs.repository.StateRepository;
import in.aitacs.repository.UserAccountRepository;
import in.aitacs.util.EmailUtils;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private UserAccountRepository userRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String Login(LoginForm loginForm) {

		UserAccountEntity entity = userRepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPwd());

		if (entity == null) {
			return "Invalid Credentials";
		}

		if (entity != null && entity.getAccStatus().equals("LOCKED")) {

			return "Account Locked";
		}

		return "SUCCESS";
	}

	@Override
	public String emailCheck(String email) {

		UserAccountEntity entity = userRepo.findByEmail(email);
		if (entity == null) {
			return "UNIQUE";
		}

		return "DUPLICATE";
	}

	@Override
	public Map<Integer, String> loadCountries() {

		List<CountryMasterEntity> entity = countryRepo.findAll();
		Map<Integer, String> countries = new HashMap<Integer, String>();

		for (CountryMasterEntity country : entity) {

			countries.put(country.getCountrryId(), country.getCountryName());

		}

		return countries;
	}

	@Override
	public Map<Integer, String> loadStates(Integer countryId) {

		List<StateMasterEntity> entity = stateRepo.findByCountryId(countryId);
		Map<Integer, String> states = new HashMap<Integer, String>();

		for (StateMasterEntity state : entity) {

			states.put(state.getStateId(), state.getStateName());
		}
		return states;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {

		List<CityMasterEntity> entity = cityRepo.findByStateId(stateId);
		Map<Integer, String> cities = new HashMap<Integer, String>();

		for (CityMasterEntity city : entity) {
			cities.put(city.getCityId(), city.getCityName());
		}

		return cities;
	}

	@Override
	public String registerUser(UserForm userForm) {

		UserAccountEntity userAcc = new UserAccountEntity();

		BeanUtils.copyProperties(userForm, userAcc);

		userAcc.setAccStatus("LOCKED");

		userAcc.setPassword(generateRandomPwd());

		userRepo.save(userAcc);

		String email = userForm.getEmail();
		String subject = "User Registration Aitacs";
		String fileName = "UNLOCK-ACC-EMIAL-BODY.txt";

		String body = readMailBodyContent(fileName, userAcc);

		boolean isSend = emailUtils.sendMail(email, subject, body);

		if (isSend) {

			return "SUCCESS";
		}
		return "FAIL";
	}

	@Override
	public String unlockAccount(UnlockForm unlockForm) {

		if (!(unlockForm.getNewPwd().equals(unlockForm.getConfirmNewPwd()))) {
			return "New Passwoed And Confirm Password Should be Same";
		}

		UserAccountEntity entity = userRepo.findByEmailAndPassword(unlockForm.getEmail(), unlockForm.getTempPwd());

		if (entity == null) {
			return "Invalid Tempory Password";
		}

		entity.setPassword(unlockForm.getNewPwd());
		entity.setAccStatus("UNLOCKED");

		userRepo.save(entity);

		return "Account Unlocked";
	}

	@Override
	public String forgotPwd(String email) {

		UserAccountEntity entity = userRepo.findByEmail(email);

		if (entity == null) {
			return "Invalid Email Id";
		}
		String fileName = "RECOVER-PWD-EMAIL-BODY.txt";
		String body = readMailBodyContent(fileName, entity);
		System.out.println("*********** :"+body);
		String subject = "Recover Password- Aitacs";

		boolean isSent = emailUtils.sendMail(email, subject, body);

		if (isSent) {
			return "Password Sent To Registered Mail";
		}

		return "Fail";
	}

	private String generateRandomPwd() {
		int leftLimit = 48;
		int rightLimit = 122;
		int targetLimit = 6;

		Random random = new Random();

		String genratedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetLimit)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return genratedString;
	}

	private String readMailBodyContent(String fileName, UserAccountEntity userAcc) {

		String mailBody = null;

		try {

			StringBuffer sb = new StringBuffer();
			//File f = new File(fileName);
			FileReader reader = new FileReader(fileName);
			BufferedReader br = new BufferedReader(reader);

			String readLine = br.readLine();

			while (readLine != null) {

				sb.append(readLine);
				readLine = br.readLine();

			}

			mailBody = sb.toString();

			mailBody = mailBody.replace("{FNAME}", userAcc.getFname());
			mailBody = mailBody.replace("{LNAME}", userAcc.getLname());
			mailBody = mailBody.replace("{PWD}", userAcc.getPassword());
			mailBody = mailBody.replace("{EMAIL}", userAcc.getEmail());
					
					
					
					
					
					
					
					
					

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mailBody;
	}

}
