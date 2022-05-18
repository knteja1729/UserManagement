package in.aitacs.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="USER_ACCOUNTS")
@Data
public class UserAccountEntity {
	
	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private Integer userId;
	
	@Column(name="FIRST_NAME")
	private String fname;
	
	@Column(name="LAST_NAME")
	private String lname;
	
	@Column(name="USER_EMAIL", unique = true)
	private String email;
	
	@Column(name="USER_PWD")
	private String password;
	
	@Column(name="USER_PHNO")
	private Long phno;
	
	@Column(name="USER_DOB")
	private LocalDate dob;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="COUNTRY_ID")
	private Integer countryID; 
	
	@Column(name="STATE_ID")
	private Integer stateID;
	
	@Column(name="CITY_ID")
	private Integer cityID;
	
	@Column(name="ACC_STATUS")
	private String accStatus;
	
	@Column(name="CREATED_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name="UPDATED_DATE", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
}
