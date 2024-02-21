package com.codeones.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codeones.warehouse.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
	
	@Query("SELECT COUNT(poCode) FROM PurchaseOrder WHERE poCode=:poCode")
	public Integer getPurchaseOrderCodeCount(String poCode);
	
	@Query("SELECT COUNT(poCode) FROM PurchaseOrder WHERE poCode=:poCode AND poId!=:poId")
	public Integer getPurchaseOrderCodeCountForEdit(String poCode, Long poId);

}
