package in.aitacs.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserForm {

	private String fname;

	private String lname;

	private String email;

	private Long phno;

	private LocalDate dob;

	private String gender;

	private Integer countryID;

	private Integer stateID;

	private Integer cityID;
}
