package in.aitacs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.aitacs.entity.CityMasterEntity;

public interface CityRepository extends JpaRepository<CityMasterEntity, Integer> {
	
	public List<CityMasterEntity> findByStateId(Integer stateId);

}
