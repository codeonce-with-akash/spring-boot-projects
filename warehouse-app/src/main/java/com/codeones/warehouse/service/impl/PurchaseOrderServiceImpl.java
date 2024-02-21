package com.codeones.warehouse.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeones.warehouse.entity.PurchaseOrder;
import com.codeones.warehouse.exception.PurchaseOrderCodeAlreadyExist;
import com.codeones.warehouse.exception.PurchaseOrderNotFoundException;
import com.codeones.warehouse.payloads.PurchaseOrderRequest;
import com.codeones.warehouse.repository.PurchaseOrderRepository;
import com.codeones.warehouse.service.IPurchaseOrderService;
import com.codeones.warehouse.service.IShipmentTypeService;
import com.codeones.warehouse.service.IWhUserTypeService;


@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository poRepo;

	@Autowired
	private IShipmentTypeService stService;

	@Autowired
	private IWhUserTypeService whService;

	@Override
	@Transactional
	public String createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest) {
		PurchaseOrder po = new PurchaseOrder();
		if (poRepo.getPurchaseOrderCodeCount(purchaseOrderRequest.getPoCode()) > 0)
			throw new PurchaseOrderCodeAlreadyExist(purchaseOrderRequest.getPoCode() + " already exist");
		po.setPoCode(purchaseOrderRequest.getPoCode());
		po.setSt(stService.getShipmentTypeById(purchaseOrderRequest.getShipmentCode()));
		po.setWh(whService.getWhUserTypeById(purchaseOrderRequest.getVendor()));
		po.setPoRefNum(purchaseOrderRequest.getPoRefNum());
		po.setPoQltyCheck(purchaseOrderRequest.getPoQltyCheck());
		po.setPoDesc(purchaseOrderRequest.getPoDesc());
		Long poId = poRepo.save(po).getPoId();
		return new StringBuilder("Purchase Order Created Successfully With Id : ").append(poId).toString();
	}

	@Override
	public List<PurchaseOrder> getAllPOs() {
		return poRepo.findAll();
	}

	@Override
	public PurchaseOrder getOnePO(Long poId) {
		Optional<PurchaseOrder> opt = poRepo.findById(poId);
		if (opt.isEmpty())
			throw new PurchaseOrderNotFoundException(new StringBuilder("Purchase Order With Id : ").append(poId).append("Not Exist!").toString());
		else
			return opt.get();
	}
	
	@Override
	@Transactional
	public PurchaseOrder updatePO(Long poId, PurchaseOrderRequest request) {
		PurchaseOrder dbPurchaseOrder = getOnePO(poId);
		if(request.getPoCode()!=null) {
			if(poRepo.getPurchaseOrderCodeCountForEdit(request.getPoCode(), poId) > 0)
				throw new PurchaseOrderCodeAlreadyExist(request.getPoCode()+" Already Exist!");
			dbPurchaseOrder.setPoCode(request.getPoCode());
		}
		if(request.getPoDesc()!=null)
			dbPurchaseOrder.setPoDesc(request.getPoDesc());
		if(request.getPoQltyCheck()!=null)
			dbPurchaseOrder.setPoQltyCheck(request.getPoQltyCheck());
		if(request.getPoRefNum()!=null)
			dbPurchaseOrder.setPoRefNum(request.getPoRefNum());
		if(request.getShipmentCode()!=null)
			dbPurchaseOrder.setSt(stService.getShipmentTypeById(request.getShipmentCode()));
		if(request.getVendor()!=null)
			dbPurchaseOrder.setWh(whService.getWhUserTypeById(request.getVendor()));
		return poRepo.save(dbPurchaseOrder);
	}

}
