package com.codeones.warehouse.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class ShipmentTypeUtils {
	
	public void generateShipmentTypePieChart(String path, List<Object[]> data) {
		
		//1. Create Data Set object using List data given by Controller.
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(Object[] obj:data) {
			dataset.setValue(obj[0].toString(), Double.valueOf(obj[1].toString()));
		}
		
		//2. Convert Data Set into JFreeChart object using ChartFactory
		// set title and data set
		JFreeChart pieChart = ChartFactory.createPieChart("SHIPMENT TYPE MODE",dataset);
		
		//3. Convert JFreeChart object into Image using ChartUtils
		/**
		 * @param file + name
		 * @param jfree chart object
		 * @param width
		 * @param height
		 */
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/shipmenttymodepie.jpg"), pieChart, 350, 350);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generateShipmentTypeBarChart(String path, List<Object[]> data) {
		
		//1. Create Data Set object using List data given by controller
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(Object[] obj : data) {
			dataset.setValue(Double.valueOf(obj[1].toString()), obj[0].toString(), "");
		}
		
		//2. Convert Data Set into JFreeChart object using ChartFactory
		//Input => title, x-axis label, y-axis-label, dataset
		JFreeChart barChart = ChartFactory.createBarChart("SHIPMENT TYPE MODE", "MODES", "COUNTES", dataset);
		
		//3. Convert JFreeChart object into Image using ChartUtils
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/shipmenttypemodebar.jpg"), barChart, 350, 350);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
