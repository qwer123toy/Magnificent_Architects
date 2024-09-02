package ui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tables.CompanyInfo;

public class CompanyInfoPnlforgraph extends JPanel {
	private CompanyInfo info;
	private JLabel companyNamelbl;
	private JLabel companyInfolbl;
	private JLabel lastYearSaleslbl;
	private JLabel companyCategorylbl;
	private JLabel companyProductslbl;

	public CompanyInfoPnlforgraph(CompanyInfo info) {
		this.info = info;
//		setBackground(Color.GREEN);

		setLayout(new GridLayout(5, 1));

//		CompanyInfo companyInfo = info;

		companyNamelbl = new JLabel();
		companyNamelbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		companyInfolbl = new JLabel();
		companyInfolbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		lastYearSaleslbl = new JLabel();
		lastYearSaleslbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		companyCategorylbl = new JLabel();
		companyCategorylbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		companyProductslbl = new JLabel();
		companyProductslbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		add(companyNamelbl);
		add(companyInfolbl);
		add(lastYearSaleslbl);
		add(companyCategorylbl);
		add(companyProductslbl);
		
		update(info);

	}
	
	public void update(CompanyInfo OutInfo) {
		companyNamelbl.setText("회사명: " + OutInfo.getCompanyName());
		companyInfolbl.setText(OutInfo.getCompanyInfo());
		lastYearSaleslbl.setText("작년 매출: " + OutInfo.getLastYearSales() + "억원");
		companyCategorylbl.setText("회사계열: " + OutInfo.getCompanyCategory());
		companyProductslbl.setText("회사제품: " + OutInfo.getCompanyProducts());
		
	}

	
}
