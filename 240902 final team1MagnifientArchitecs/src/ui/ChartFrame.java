package ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import DAO.AllCompanyBackdataDAO;
import DAO.UserInfoDAO;
import tables.AllCompanyBackdata;
import tables.StockChangeHistory;
import tables.UserInfo;

public class ChartFrame extends JFrame {

	AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
	private static UserInfoDAO userInfoDAO = new UserInfoDAO();

	public ChartFrame(UserInfo userInfo, String companyName) {
		Map<Integer, Integer> chartMap = new HashMap<>();
		
		String userID = userInfo.getUser_ID();
		int saveData = userInfo.getUser_SaveData();
		
		chartMap = allCompanyBackdataDAO.findChart(companyName, userID, saveData);
//		chartMap = allCompanyBackdataDAO.findChart(companyName, "asd", 1);
//		System.out.println(chartMap.toString());

		// XYSeries(꺾은선 이름)
		// series.add(x값, y값);
		XYSeries series = new XYSeries("Stock option GUI");
//		series.add(1, 100);
//		series.add(2, 120);
//		series.add(3, 90);
//		series.add(4, 150);
//		series.add(5, 130);
		
		for (int i = 0; i < chartMap.size(); i++) {
			int date = i + 1;
			int stockPrice = chartMap.get(date);
			series.add(date, stockPrice);
		}
		
		if (companyName.equals("E 회사")) {
			XYSeriesCollection dataset = new XYSeriesCollection(series);
			// ChartFactory.createXYLineChart(차트제목, x축 이름, y축 이름, )
			JFreeChart chart = ChartFactory.createXYLineChart("Stock", "Date", "StockPrice", dataset);

			// X축 및 Y축 범위 설정
			NumberAxis xAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();
			xAxis.setRange(0, 30); // X축 범위를 설정
			xAxis.setTickUnit(new org.jfree.chart.axis.NumberTickUnit(5)); // X축 단위를 1로 설정

			NumberAxis yAxis = (NumberAxis) chart.getXYPlot().getRangeAxis();
			yAxis.setRange(0, 5000); // Y축 범위를  설정
			
			// 차트 패널 생성
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(500, 500));
			setContentPane(chartPanel);
			
		} else if (companyName.equals("F 회사")) {
			XYSeriesCollection dataset = new XYSeriesCollection(series);
			// ChartFactory.createXYLineChart(차트제목, x축 이름, y축 이름, )
			JFreeChart chart = ChartFactory.createXYLineChart("Stock", "Date", "StockPrice", dataset);

			// X축 및 Y축 범위 설정
			NumberAxis xAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();
			xAxis.setRange(0, 30); // X축 범위를 설정
			xAxis.setTickUnit(new org.jfree.chart.axis.NumberTickUnit(5)); // X축 단위를 1로 설정

			NumberAxis yAxis = (NumberAxis) chart.getXYPlot().getRangeAxis();
			yAxis.setRange(4000, 26000); // Y축 범위를  설정
			
			// 차트 패널 생성
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(500, 500));
			setContentPane(chartPanel);
			
			
		} else {
			XYSeriesCollection dataset = new XYSeriesCollection(series);
			// ChartFactory.createXYLineChart(차트제목, x축 이름, y축 이름, )
			JFreeChart chart = ChartFactory.createXYLineChart("Stock", "Date", "StockPrice", dataset);

			// X축 및 Y축 범위 설정
			NumberAxis xAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();
			xAxis.setRange(0, 30); // X축 범위를 0에서 10으로 설정
			xAxis.setTickUnit(new org.jfree.chart.axis.NumberTickUnit(5)); // X축 단위를 1로 설정

			NumberAxis yAxis = (NumberAxis) chart.getXYPlot().getRangeAxis();
			yAxis.setRange(0, 500); // Y축 범위를 0에서 250으로 설정
			
			// 차트 패널 생성
			ChartPanel chartPanel = new ChartPanel(chart);
			chartPanel.setPreferredSize(new Dimension(500, 500));
			setContentPane(chartPanel);
		}
	}
}

//	public static void main(String[] args) {
//		UserInfo userInfo = userInfoDAO.findByID("asd");
//		SwingUtilities.invokeLater(() -> {
//			ChartFrame example = new ChartFrame(userInfo, "A 회사");
//			example.setTitle("Chart");
//			example.setSize(500, 500);
//			example.setLocationRelativeTo(null);
//			example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//			example.setVisible(true);
//		});
//	}
