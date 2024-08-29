package ui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tables.CompanyInfo;

public class CompanyInfoPnlforgraph extends JPanel {

	public CompanyInfoPnlforgraph(CompanyInfo info) {
//		setBackground(Color.GREEN);

		setLayout(new GridLayout(5, 1));

//		CompanyInfo companyInfo = info;

		JLabel companyNamelbl = new JLabel();
		companyNamelbl.setText("회사명: " + info.getCompanyName());
		companyNamelbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		JLabel companyInfolbl = new JLabel();
		companyInfolbl.setText(info.getCompanyInfo());
		companyInfolbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		JLabel lastYearSaleslbl = new JLabel();
		lastYearSaleslbl.setText("작년 매출: " + info.getLastYearSales() + "억원");
		lastYearSaleslbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		JLabel companyCategorylbl = new JLabel();
		companyCategorylbl.setText("회사계열: " + info.getCompanyCategory());
		companyCategorylbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		JLabel companyProductslbl = new JLabel();
		companyProductslbl.setText("회사제품: " + info.getCompanyProducts());
		companyProductslbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		add(companyNamelbl);
		add(companyInfolbl);
		add(lastYearSaleslbl);
		add(companyCategorylbl);
		add(companyProductslbl);

	}

	
}
