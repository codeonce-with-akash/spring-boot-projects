package com.codeones.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codeones.warehouse.entity.Part;

public interface PartRepository extends JpaRepository<Part, Long> {

	@Query("SELECT COUNT(partCode) FROM Part WHERE partCode=:partCode")
	public Integer getPartCodeCount(String partCode);
	
	
	@Query("SELECT COUNT(partCode) FROM Part WHERE partCode=:partCode AND id!=:id")
	public Integer getPartCodeCountForUpdate(Long id, String partCode);

}
