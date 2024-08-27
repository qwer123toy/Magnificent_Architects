package ui;

import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import mapper.CompanyInfoMapper;
import tables.CompanyInfo;

public class CompanyInfoPnl extends JPanel {
	private String targetCompany = null;

	public String getTargetCompnay() {
		return targetCompany;
	}

	public void setTargetCompnay(String targetCompnay) {
		this.targetCompany = targetCompnay;
	}

	public CompanyInfoPnl(String s) {
//		setBackground(Color.GREEN);

		setLayout(new GridLayout(5, 1));

		targetCompany = s;
		CompanyInfo companyInfo = selectCompany(targetCompany);

		JLabel companyNamelbl = new JLabel();
		companyNamelbl.setText("회사명: " + companyInfo.getCompanyName());
		companyNamelbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		JLabel companyInfolbl = new JLabel();
		companyInfolbl.setText(companyInfo.getCompanyInfo());
		companyInfolbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		JLabel lastYearSaleslbl = new JLabel();
		lastYearSaleslbl.setText("작년 매출: " + companyInfo.getLastYearSales() + "억원");
		lastYearSaleslbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		JLabel companyCategorylbl = new JLabel();
		companyCategorylbl.setText("회사계열: " + companyInfo.getCompanyCategory());
		companyCategorylbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		JLabel companyProductslbl = new JLabel();
		companyProductslbl.setText("회사제품: " + companyInfo.getCompanyProducts());
		companyProductslbl.setFont(new Font("Dialog.bold", Font.PLAIN, 20));

		add(companyNamelbl);
		add(companyInfolbl);
		add(lastYearSaleslbl);
		add(companyCategorylbl);
		add(companyProductslbl);

	}

	private static CompanyInfo selectCompany(String companyName) {
		IResultMapper<CompanyInfo> mapper = new CompanyInfoMapper();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM CompanyInfo WHERE companyName = ?; ";
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, companyName);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return mapper.resultMapping(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

}
