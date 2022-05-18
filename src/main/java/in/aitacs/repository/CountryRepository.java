package in.aitacs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.aitacs.entity.CountryMasterEntity;

public interface CountryRepository extends JpaRepository<CountryMasterEntity, Integer> {

}
