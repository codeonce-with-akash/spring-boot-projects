package com.codeones.warehouse.view;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.codeones.warehouse.entity.OrderMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OrderMethodExcelView extends AbstractXlsxView {
	@Override
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 1. create sheet using workbook
		Sheet sheet = workbook.createSheet("METHODORDER");

		// 2. set excel file name
		response.addHeader("Content-Disposition", "attachment;filename=ORDERMETHODS.xlsx");

		// 3. read data from model
		@SuppressWarnings("unchecked")
		List<OrderMethod> list = (List<OrderMethod>) model.get("list");

		// 4. addHeader
		addHeader(sheet);

		// 5. addBody
		addBody(sheet, list);
	}

	private void addHeader(Sheet sheet) {
		//1. Add header row
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("MODE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("TYPE");
		row.createCell(4).setCellValue("ACCEPT");
		row.createCell(5).setCellValue("NOTE");
	}

	private void addBody(Sheet sheet, List<OrderMethod> list) {
		int rowNum = 1;
		
		//1. Add body
		for(OrderMethod orderMethod:list) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(orderMethod.getId());
			row.createCell(1).setCellValue(orderMethod.getOrderMode());
			row.createCell(2).setCellValue(orderMethod.getOrderCode());
			row.createCell(3).setCellValue(orderMethod.getOrderType());
			row.createCell(4).setCellValue(orderMethod.getOrderAccept().toString());
			row.createCell(5).setCellValue(orderMethod.getDescription());
		}
	}
}
