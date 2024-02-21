package com.codeones.warehouse.view;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.codeones.warehouse.entity.Uom;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UomTypeExcelView extends AbstractXlsxView implements View {

	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) 
					throws Exception 
	{
		//1. create sheet
		Sheet sheet = workbook.createSheet("UOMS");
		
		//2. read data from model memory added at controller
		List<Uom> list = (List<Uom>)model.get("uomList");
		
		//3. set excel file name
		response.addHeader("Content-Disposition", "attachment;filename=UOMS.xlsx");
		
		addHeader(sheet);
		addBody(sheet, list);
	}


	private void addHeader(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("TYPE");
		row.createCell(2).setCellValue("MODEL");
		row.createCell(3).setCellValue("NOTE");
	}

	private void addBody(Sheet sheet, List<Uom> list) {
		Integer rowNum = 1;
		for(Uom uom : list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(uom.getId());
			row.createCell(1).setCellValue(uom.getUomType());
			row.createCell(2).setCellValue(uom.getUomModel());
			row.createCell(3).setCellValue(uom.getDesc());
		}
	}
}
