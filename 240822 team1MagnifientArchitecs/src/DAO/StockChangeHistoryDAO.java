package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import mapper.StockChangeHistoryMapper;
import tables.StockChangeHistory;

public class StockChangeHistoryDAO {
	public static final IResultMapper<StockChangeHistory> stockChangeHistoryMapper = new StockChangeHistoryMapper();

	public List<StockChangeHistory> findByID(String user_ID, int saveData) {
		String sql = "SELECT * FROM stockchangehistory WHERE user_ID = ? and userSaveData = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<StockChangeHistory> StockChangeHistoryList = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, saveData);
			rs = stmt.executeQuery();

			while (rs.next()) {
				StockChangeHistory stockChangeHistory = stockChangeHistoryMapper.resultMapping(rs);
				StockChangeHistoryList.add(stockChangeHistory);
			}
			return StockChangeHistoryList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	
	public List<StockChangeHistory> findAllByCompany(String companyName, String user_ID, int saveData) {
		String sql = "SELECT * FROM stockchangehistory WHERE companyName = ? and user_ID = ? and user_SaveData = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<StockChangeHistory> StockChangeHistoryList = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, companyName);
			stmt.setString(2, user_ID);
			stmt.setInt(3, saveData);
			rs = stmt.executeQuery();

			while (rs.next()) {
				StockChangeHistory stockChangeHistory = stockChangeHistoryMapper.resultMapping(rs);
				StockChangeHistoryList.add(stockChangeHistory);
			}
			return StockChangeHistoryList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	

	//거래 내역에 구매를 추가
	public void insertBuy(String user_ID, int user_saveData, String companyName, 
			int buyStockPrice, int buyStockCount, int date) throws SQLException {
		String sql = "insert into stockchangehistory"
				+ "(user_ID, user_SaveData, companyName, buyStockPrice, buyStockCount, date) \r\n" + 
				"	value(?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, user_saveData);
			stmt.setString(3, companyName);
			stmt.setInt(4, buyStockPrice);
			stmt.setInt(5, buyStockCount);
			stmt.setInt(6, date);


			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

	}
	
	public void insertSell(String user_ID, int user_saveData, String companyName, 
			int sellStockPrice, int sellStockCount, int date) throws SQLException {
		String sql = "insert into stockchangehistory"
				+ "(user_ID, user_SaveData, companyName, sellStockPrice, sellStockCount, date) \r\n" + 
				"	value(?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, user_saveData);
			stmt.setString(3, companyName);
			stmt.setInt(4, sellStockPrice);
			stmt.setInt(5, sellStockCount);
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
