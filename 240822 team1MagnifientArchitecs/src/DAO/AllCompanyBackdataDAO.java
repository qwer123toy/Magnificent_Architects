package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import mapper.AllCompanyBackdataMapper;
import tables.AllCompany;
import tables.AllCompanyBackdata;

public class AllCompanyBackdataDAO {
	public static final IResultMapper<AllCompanyBackdata> allCompanyBackdataMapper = new AllCompanyBackdataMapper();

	// ID와 saveData를 통해 AllCompanyBackdata의 모든 값을 찾기
	public List<AllCompanyBackdata> findAllByID(String userID, int saveData) {
		String sql = "SELECT * FROM AllCompanyBackdata WHERE simulation_ID = ? and simulation_ID_SaveData = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<AllCompanyBackdata> allCompanyBackdataList = new ArrayList<>();
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userID);
			stmt.setInt(2, saveData);
			rs = stmt.executeQuery();

			while (rs.next()) {
				AllCompanyBackdata allCompanyBackdata = allCompanyBackdataMapper.resultMapping(rs);
				allCompanyBackdataList.add(allCompanyBackdata);
			}
			return allCompanyBackdataList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	// ID와 saveData를 통해 AllCompanyBackdata의 모든 값을 찾기
	public List<AllCompanyBackdata> findCompanyAllByID(String companyName, String userID, int saveData) {
		String sql = "SELECT * FROM AllCompanyBackdata WHERE companyName =? and simulation_ID = ? and simulation_ID_SaveData = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<AllCompanyBackdata> allCompanyBackdataList = new ArrayList<>();
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, companyName);
			stmt.setString(2, userID);
			stmt.setInt(3, saveData);
			rs = stmt.executeQuery();

			while (rs.next()) {
				AllCompanyBackdata allCompanyBackdata = allCompanyBackdataMapper.resultMapping(rs);
				allCompanyBackdataList.add(allCompanyBackdata);
			}
			return allCompanyBackdataList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	// 해당하는 날짜의 특정 회사 주가 상황 찾기
	public AllCompanyBackdata findCompanyByDate(String companyName, int date, String userID, int saveData) {
		String sql = "SELECT * FROM AllCompanyBackdata WHERE companyName =? and date =? "
				+ "and simulation_ID = ? and simulation_ID_SaveData = ?"
				+ " order by no desc limit 1";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, companyName);
			stmt.setInt(2, date);
			stmt.setString(3, userID);
			stmt.setInt(4, saveData);
			rs = stmt.executeQuery();

			if (rs.next()) {
				AllCompanyBackdata allCompanyBackdata = allCompanyBackdataMapper.resultMapping(rs);
				return allCompanyBackdata;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	// allcompanybackdata에 데이터 삽입하기
	public void insert(String companyName, int companyStockPrice, int companyStockCount, String simulation_ID,
			int simulation_ID_SaveData, int date) throws SQLException {
		String sql = "INSERT INTO allcompanybackdata (companyName, companyStockPrice,\r\n"
				+ "companyStockCount, simulation_ID, simulation_ID_SaveData, date\r\n" + ") VALUES (?, ?, ?, ?, ?, ?);";

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


}
