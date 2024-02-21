package com.codeones.warehouse.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codeones.warehouse.entity.OrderMethod;
import com.codeones.warehouse.entity.ShipmentType;
import com.codeones.warehouse.entity.Uom;
import com.codeones.warehouse.repository.ShipmentTypeRepository;
import com.codeones.warehouse.repository.UomRepository;
import com.codeones.warehouse.service.IOrderMethodService;
import com.codeones.warehouse.service.IShipmentTypeService;
import com.codeones.warehouse.service.IUomService;
import com.codeones.warehouse.view.OrderMethodExcelView;
import com.codeones.warehouse.view.ShipmentTypeExcelView;
import com.codeones.warehouse.view.UomTypeExcelView;

@Controller
@RequestMapping("/api/v1/export")
public class ExcelDataExportController {
	
	@Autowired
	private IShipmentTypeService shipmentTypeService;
	
	@Autowired
	private IUomService uomService;
	
	@Autowired
	private IOrderMethodService orderMethodService;
	
	@GetMapping("/view")
	public String showWhView() {
		return "WarehouseView";
	}
	
	@GetMapping("/st/excel")
	public ModelAndView shipmentTypeExcelExport() {
		ModelAndView m = new ModelAndView();
		
		//set view name where we want to send the data
		m.setView(new ShipmentTypeExcelView());
		
		//fetch data
		List<ShipmentType> list = shipmentTypeService.getShipmentTypes();
		
		//set data to model memory
		m.addObject("list", list);
		
		return m;
	}
	
	@GetMapping("/uom/excel")
	public ModelAndView exportUomData() {
		ModelAndView mav = new ModelAndView();
		
		//1. set view name
		mav.setView(new UomTypeExcelView());
		
		//2. fetch data from database
		List<Uom> list = uomService.getAllUom();
		
		//3. set model data
		mav.addObject("uomList", list);
		
		return mav;
	}
	
	@GetMapping("/om/excel")
	public ModelAndView exportOrderMethod() {
		//1. create ModelAndView object
		ModelAndView mav = new ModelAndView();
		
		//2. fetch all order methods data from database
		List<OrderMethod> list = orderMethodService.getAllOrderMethods();
		
		//3. set Model data
		mav.addObject("list", list);
		
		//4. set view name
		mav.setView(new OrderMethodExcelView());
		
		return mav;
	}
		
}
