package com.codeones.warehouse.view;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.codeones.warehouse.entity.ShipmentType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ShipmentTypeExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		
		//set excel file name
		response.addHeader("Content-Disposition", "attachment;filename=SHIPMENTS.xlsx");
		
		
		//1. read data from model memory given by controller
		@SuppressWarnings("unchecked")
		List<ShipmentType> list = (List<ShipmentType>)model.get("list");
		
		//2. create one new sheet with one name
		Sheet sheet = workbook.createSheet("SHIPMENTS");
		
		//3. add rows to sheet
		addHeader(sheet);
		addBody(sheet, list);
	}

	private void addHeader(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("MODE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("ENABLED");
		row.createCell(4).setCellValue("GRADE");
		row.createCell(5).setCellValue("NOTE");
	}

	private void addBody(Sheet sheet, List<ShipmentType> list) {
		int rowNum = 1;
		for(ShipmentType st : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(st.getId());
			row.createCell(1).setCellValue(st.getShipMode());
			row.createCell(2).setCellValue(st.getShipCode());
			row.createCell(3).setCellValue(st.getEnblShip());
			row.createCell(4).setCellValue(st.getShipGrade());
			row.createCell(5).setCellValue(st.getShipDesc());
		}
	}
}
