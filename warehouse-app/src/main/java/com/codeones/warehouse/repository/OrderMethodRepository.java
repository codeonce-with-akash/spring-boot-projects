package com.codeones.warehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codeones.warehouse.entity.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Long> {
	@Query("SELECT count(orderCode) from OrderMethod where orderCode=:orderCode")
	Integer getOrderMethodCodeCount(String orderCode);
	
	@Query("SELECT count(orderCode) from OrderMethod where orderCode=:orderCode and id!=:id")
	Integer getOrderMethodCodeCountForUpdate(Long id, String orderCode);
	
	@Query("SELECT orderMode, count(orderMode) FROM OrderMethod GROUP BY orderMode")
	List<Object[]> getOrderMethodModeAndCount();
}
