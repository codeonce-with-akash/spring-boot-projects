package com.codeones.warehouse.service;

import java.util.List;

import com.codeones.warehouse.entity.OrderMethod;
import com.codeones.warehouse.payloads.OrderMethodRequest;

public interface IOrderMethodService {
	public String createOrderMethod(OrderMethodRequest request);
	public OrderMethod getMethodOrderById(Long id);
	public List<OrderMethod> getAllOrderMethods();
	public void deleteOrderMethodById(Long id);
	public String updateMethodOrder(Long id, OrderMethodRequest orderMethodRequest);
	public List<Object[]> getMethodOrderModeAndCount();
}
