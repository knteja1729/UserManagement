package in.aitacs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.aitacs.entity.UserAccountEntity;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Integer> {

	public UserAccountEntity findByEmailAndPassword(String email, String pwd);
	
	public UserAccountEntity findByEmail(String email);
}
