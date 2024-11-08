package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import mapper.CompanyInfoMapper;
import tables.AllCompany;
import tables.CompanyInfo;
import tables.UserInfo;

public class CompanyInfoDAO {
	public static final IResultMapper<CompanyInfo> companyInfoMapper = new CompanyInfoMapper();
	
	//이름으로 회사 정보 찾기
	public CompanyInfo findByCompanyName(String companyName) {
		String sql = "SELECT * FROM companyInfo WHERE companyName = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, companyName);
			rs = stmt.executeQuery();

			if (rs.next()) {
				CompanyInfo companyInfo = companyInfoMapper.resultMapping(rs);
				return companyInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	
	public CompanyInfo findCompanyCategoryByComName(String companyName) {
		String sql = "SELECT CompanyCategory FROM companyInfo WHERE companyName = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, companyName);
			rs = stmt.executeQuery();

			if (rs.next()) {
				CompanyInfo companyInfo = companyInfoMapper.resultMapping(rs);
				return companyInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
}
