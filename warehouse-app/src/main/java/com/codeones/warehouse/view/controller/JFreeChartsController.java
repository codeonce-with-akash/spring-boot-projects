package com.codeones.warehouse.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codeones.warehouse.service.IOrderMethodService;
import com.codeones.warehouse.service.IShipmentTypeService;
import com.codeones.warehouse.util.OrderMethodUtils;
import com.codeones.warehouse.util.ShipmentTypeUtils;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/api/v1/jfree/charts")
public class JFreeChartsController {
	
	@Autowired
	private IShipmentTypeService shipmentTypeService;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ShipmentTypeUtils shipmentTypeUtils;
	
	@Autowired
	private IOrderMethodService orderMethodService;
	
	@Autowired
	private OrderMethodUtils orderMethodUtils;
	
	@GetMapping("/st")
	public String shipmentTypeCharts() {
		//1. fetch shipmentType data (List<Object[]>) from database.
		List<Object[]> data = shipmentTypeService.getShipmentTypeModeAndCount();
		
		//2. get root folder path from servlet context object
		String path = servletContext.getRealPath("/");
		
		//3. send this data and path to ShipmentTypeUtils class methods to create Pie and Bar charts
		shipmentTypeUtils.generateShipmentTypePieChart(path, data);
		shipmentTypeUtils.generateShipmentTypeBarChart(path, data);
		return "ShipmentTypeCharts";
	}
	
	@GetMapping("/om")
	public String orderMethodCharts() {
		//1. fetch list data from database
		List<Object[]> list = orderMethodService.getMethodOrderModeAndCount();
		
		//2. get root folder path from servlet context object
		String path = servletContext.getRealPath("/");
		
		//send this data and path to OrderMethodUtils class methods to create Pie and Bar charts
		orderMethodUtils.generatePieChart(path, list);
		
		orderMethodUtils.generateBarChart(path, list);
		
		return "OrderMethodCharts";
	}

}
