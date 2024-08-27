package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import tables.AllCompany;
import tables.UserInfo;
import mapper.AllCompanyMapper;

public class AllCompanyDAO {
	public static final IResultMapper<AllCompany> allCompanyMapper = new AllCompanyMapper();
	CompanyInfoDAO companyInfoDAO = new CompanyInfoDAO();
//	new AllCompany("A 회사", 100, 200, userName, 1, 1, companyInfoList.get(0));
	
	public List<AllCompany> findAllByID(String userId, int saveData) {
		String sql = "SELECT * FROM AllCompany WHERE "
				+ "simulation_ID = ? and simulation_ID_SaveData = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<AllCompany> allCompanyList = new ArrayList<>();
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userId);
			stmt.setInt(2, saveData);
			rs = stmt.executeQuery();

			while (rs.next()) {
				AllCompany allCompany = allCompanyMapper.resultMapping(rs);
				allCompanyList.add(allCompany);
			}
			return allCompanyList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	
	public AllCompany findCompByID(String companyName, String userId, int saveData) {
		String sql = "SELECT * FROM AllCompany WHERE "
				+ "companyName = ? and simulation_ID = ? and simulation_ID_SaveData = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, companyName);
			stmt.setString(2, userId);
			stmt.setInt(3, saveData);
			rs = stmt.executeQuery();

			if (rs.next()) {
				AllCompany allCompany = allCompanyMapper.resultMapping(rs);
				return allCompany;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	
	
	public void insert(String companyName, int companyStockPrice,
			int companyStockCount, String simulation_ID, 
			int simulation_ID_SaveData, int date) throws SQLException {
		String sql = "INSERT INTO allcompany (companyName, companyStockPrice,\r\n" + 
				"companyStockCount, simulation_ID, simulation_ID_SaveData, date\r\n" + 
				") VALUES (?, ?, ?, ?, ?, ?);";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, companyName);
			stmt.setInt(2, companyStockPrice);
			stmt.setInt(3, companyStockCount);
			stmt.setString(4, simulation_ID);
			stmt.setInt(5, simulation_ID_SaveData);
			stmt.setInt(6, date);

			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

	}
	
	
	public void update(String companyName, int companyStockPrice,
			int companyStockCount, String simulation_ID, 
			int simulation_ID_SaveData, int date) throws SQLException {
		String sql = "UPDATE allcompany SET companyStockPrice = ?, companyStockCount = ? \r\n" + 
				"	where (simulation_ID =?) and (simulation_ID_SaveData=?) and (companyName = ?);";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, companyStockPrice);
			stmt.setInt(2, companyStockCount);
			stmt.setString(3, simulation_ID);
			stmt.setInt(4, simulation_ID_SaveData);
			stmt.setString(5, companyName);

			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

	}

}
