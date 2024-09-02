package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import mapper.UserInfoMapper;
import mapper.UserMoneyHistoryMapper;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class UserMoneyHistoryDAO {
	public static final IResultMapper<UserMoneyHistory> userMoneyHistoryMapper = new UserMoneyHistoryMapper();

	public List<UserMoneyHistory> findByID(String user_ID, int saveData) {
		String sql = "SELECT * FROM UserMoneyHistory WHERE user_ID = ? and userSaveData = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<UserMoneyHistory> userMoneyHistoryList = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, saveData);
			rs = stmt.executeQuery();

			while (rs.next()) {
				UserMoneyHistory userMoneyHistory = userMoneyHistoryMapper.resultMapping(rs);
				userMoneyHistoryList.add(userMoneyHistory);
			}
			return userMoneyHistoryList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	
	public UserMoneyHistory findByCompany(String companyName, String user_ID, int saveData) {
		String sql = "SELECT * FROM UserMoneyHistory WHERE user_Stock = ? and user_ID = ? and userSaveData = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, companyName);
			stmt.setString(2, user_ID);
			stmt.setInt(3, saveData);
			rs = stmt.executeQuery();

			if (rs.next()) {
				UserMoneyHistory userMoneyHistory = userMoneyHistoryMapper.resultMapping(rs);
				return userMoneyHistory;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	

	public void insert(String user_ID, int user_saveData, String user_Stock, int buyPrice, int stock_Price_Now,
			int my_Stock_Money, int my_Profit_Money, double my_Profit_Rate, int stock_Count, int user_Date) throws SQLException {
		String sql = "INSERT INTO usermoneyhistory (user_ID, userSaveData, user_Stock,"
				+ "buyPrice, stock_Price_Now, my_Stock_Money, my_Profit_Money, my_Profit_Rate"
				+ ", stock_Count, user_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, user_saveData);
			stmt.setString(3, user_Stock);
			stmt.setInt(4, buyPrice);
			stmt.setInt(5, stock_Price_Now);
			stmt.setInt(6, my_Stock_Money);
			stmt.setInt(7, my_Profit_Money);
			stmt.setDouble(8, my_Profit_Rate);
			stmt.setInt(9, stock_Count);
			stmt.setInt(10, user_Date);

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

	}

	public void update(String user_ID, int user_saveData, String user_Stock, int buyPrice, int stock_Price_Now,
			int my_Stock_Money, int my_Profit_Money, double my_Profit_Rate, int stock_Count, int user_Date) throws SQLException {
		String sql = "UPDATE UserMoneyHistory SET buyPrice = ? , stock_Price_Now = ?,\r\n"
				+ "	my_Stock_Money = ?, my_Profit_Money =?, my_Profit_Rate=?,\r\n"
				+ "	stock_Count= ?, user_Date = ?\r\n"
				+ "	where (user_ID = ?) and (userSaveData = ?) and (user_Stock= ?);";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, buyPrice);
			stmt.setInt(2, stock_Price_Now);
			stmt.setInt(3, my_Stock_Money);
			stmt.setInt(4, my_Profit_Money);
			stmt.setDouble(5, my_Profit_Rate);
			stmt.setInt(6, stock_Count);
			stmt.setInt(7, user_Date);
			stmt.setString(8, user_ID);
			stmt.setInt(9, user_saveData);
			stmt.setString(10, user_Stock);
			
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

	}
	
	
	
	public void updatePriceAndDate(int changeStockPrice, String user_ID, int user_saveData, String user_Stock,
			int stock_Price_Now) throws SQLException {
		String sql = "update usermoneyhistory set stock_Price_Now = stock_Price_Now + (?), user_Date = user_Date + 1\r\n" + 
				"			where user_id = ? and userSaveData = ? and user_Stock = ?;";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, changeStockPrice);
			stmt.setString(2, user_ID);
			stmt.setInt(3, user_saveData);
			stmt.setString(4, user_Stock);
			
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

	}

}
