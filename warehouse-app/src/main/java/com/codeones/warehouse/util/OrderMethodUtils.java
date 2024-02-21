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
public class OrderMethodUtils {
	
	public void generatePieChart(String path, List<Object[]> data) {
		//1. create data set object using list data
		DefaultPieDataset dataset = new DefaultPieDataset();
		
		for(Object[] obj : data) {
			dataset.setValue(obj[0].toString(), Double.valueOf(obj[1].toString()));
		}
		
		//2. convert data set into JFreeChart object using ChartFactory
		JFreeChart pieChart = ChartFactory.createPieChart("ORDER-METHODS", dataset);
		
		//3. Convert JFreeChart object into image using ChartUtils
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/ordermethodpiechart.jpg"), pieChart, 350, 350);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generateBarChart(String path, List<Object[]> list) {
		//1. create data set object using list data
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(Object[] obj : list) {
			dataset.setValue(Double.valueOf(obj[1].toString()), obj[0].toString(), "");
		}
		
		//2. convert data set into JFreeChart object using ChartFactory
		JFreeChart barChart = ChartFactory.createBarChart("ORDERMETHODS", "MODES", "COUNTES", dataset);
		
		//3. Convert JFreeChart object into image using ChartUtils
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/ordermethodbarchart.jpg"), barChart, 350, 350);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
