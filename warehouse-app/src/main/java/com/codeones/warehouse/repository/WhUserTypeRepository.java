package com.codeones.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codeones.warehouse.entity.WhUserType;

public interface WhUserTypeRepository extends JpaRepository<WhUserType, Long> {

	@Query("SELECT COUNT(userEmail) FROM WhUserType WHERE userEmail=:whUserEmail")
	public Integer getWhUserEmailCount(String whUserEmail);

	@Query("SELECT COUNT(userEmail) FROM WhUserType WHERE userEmail=:whUserEmail AND id!=:id")
	public Integer getWhUserEmailCountForUpdate(String whUserEmail, Long id);

	@Query("SELECT COUNT(userContact) FROM WhUserType WHERE userContact=:whUserContact")
	public Integer getWhUserContactCount(String whUserContact); 
	
	@Query("SELECT COUNT(userContact) FROM WhUserType WHERE userContact=:userContact AND id!=:whId")
	public Integer getWhUserContactCountForUpdate(String userContact, Long whId); 
	
	@Query("SELECT COUNT(userIdNum) FROM WhUserType WHERE userIdNum=:userIdNum")
	public Integer getWhUserIdNumCount(String userIdNum); 
	
	@Query("SELECT COUNT(userIdNum) FROM WhUserType WHERE userIdNum=:userIdNum AND id!=:whId")
	public Integer getWhUserIdNumCountForUpdate(String userIdNum, Long whId); 
	
	
}
