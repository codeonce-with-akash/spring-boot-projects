package com.codeones.warehouse.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeones.warehouse.entity.OrderMethod;
import com.codeones.warehouse.exception.OrderMethodCodeAlreadyExistException;
import com.codeones.warehouse.exception.OrderMethodNotFoundException;
import com.codeones.warehouse.payloads.OrderMethodRequest;
import com.codeones.warehouse.repository.OrderMethodRepository;
import com.codeones.warehouse.service.IOrderMethodService;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService{

	@Autowired
	private OrderMethodRepository orderMethodRepo;
	
	@Override
	public String createOrderMethod(OrderMethodRequest request) {
		Long id = null;
		OrderMethod orderMethod = OrderMethod.builder()
		.orderMode(request.getOrderMode())
		.orderCode(request.getOrderCode())
		.orderType(request.getOrderType())
		.orderAccept(request.getOrderAccept())
		.description(request.getDescription())
		.build();
		if(orderMethodRepo.getOrderMethodCodeCount(request.getOrderCode())>0)
			throw new OrderMethodCodeAlreadyExistException(request.getOrderCode()+" already Exist");
		else
			 id = orderMethodRepo.save(orderMethod).getId();
		return new StringBuilder("OrderMethod Created Successfully With Id : ").append(id).toString();
	}
	
	@Override
	public OrderMethod getMethodOrderById(Long id) {
		return orderMethodRepo.findById(id).orElseThrow(() -> new OrderMethodNotFoundException("Order Method '"+id+"' Not Found!"));
	}
	
	@Override
	public List<OrderMethod> getAllOrderMethods() {
		return orderMethodRepo.findAll();
	}
	
	@Override
	public void deleteOrderMethodById(Long id) {
		OrderMethod orderMethod = getMethodOrderById(id);
		orderMethodRepo.delete(orderMethod);
	}
	
	@Override
	public String updateMethodOrder(Long id, OrderMethodRequest orderMethodRequest) {
		OrderMethod dbOrderMethod = getMethodOrderById(id);
		if(orderMethodRequest.getOrderMode() != null)
			dbOrderMethod.setOrderMode(orderMethodRequest.getOrderMode());
		if(orderMethodRequest.getOrderCode() != null) {
			if(orderMethodRepo.getOrderMethodCodeCountForUpdate(id, orderMethodRequest.getOrderCode())>0)
				throw new OrderMethodCodeAlreadyExistException("OrderMethod Already Exist With OrderMethod Code : "+orderMethodRequest.getOrderCode());
			else 
				dbOrderMethod.setOrderCode(orderMethodRequest.getOrderCode());
		}
		if(orderMethodRequest.getOrderType() != null)
			dbOrderMethod.setOrderType(orderMethodRequest.getOrderType());
		if(orderMethodRequest.getOrderAccept() != null)
			dbOrderMethod.setOrderAccept(orderMethodRequest.getOrderAccept());
		if(orderMethodRequest.getDescription() != null)
			dbOrderMethod.setDescription(orderMethodRequest.getDescription());
		orderMethodRepo.save(dbOrderMethod);
		return new StringBuilder("OrderMethod Updated Successfully With Id : ").append(id).toString();
	}
	
	@Override
	public List<Object[]> getMethodOrderModeAndCount() {
		return orderMethodRepo.getOrderMethodModeAndCount();
	}

}
