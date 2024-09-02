package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import jdk.nashorn.internal.ir.CatchNode;
import mapper.UserInfoMapper;
import tables.UserInfo;

public class UserInfoDAO {
	public static final IResultMapper<UserInfo> UserInfoMapper = new UserInfoMapper();

	public UserInfo findByID(String user_ID) {
		String sql = "SELECT * FROM UserInfo WHERE user_ID = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			rs = stmt.executeQuery();

			if (rs.next()) {
				UserInfo userInfo = UserInfoMapper.resultMapping(rs);
				return userInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	
	public UserInfo findByIDAndPW(String user_ID, String user_PW) {
		String sql = "SELECT * FROM UserInfo WHERE user_ID = ? and user_PW = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setString(2, user_PW);
			rs = stmt.executeQuery();

			if (rs.next()) {
				UserInfo userInfo = UserInfoMapper.resultMapping(rs);
				return userInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	
	
	public UserInfo findByIDAndData(String user_ID, int user_SaveData) {
		String sql = "SELECT * FROM UserInfo WHERE user_ID = ? and user_SaveData = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, user_SaveData);
			rs = stmt.executeQuery();

			if (rs.next()) {
				UserInfo userInfo = UserInfoMapper.resultMapping(rs);
				return userInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	public void insert(String user_ID, String user_PW, int user_saveData) throws SQLException {
		String sql = "INSERT INTO userInfo (user_ID, user_PW, user_saveData) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setString(2, user_PW);
			stmt.setInt(3, user_saveData);

			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

	}
	
	public List<UserInfo> checkExistIDAndPW(String user_ID, String user_PW) throws SQLException {
		String sql = "SELECT * FROM userInfo WHERE user_id = ? and user_PW = ? order by user_SaveData";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<UserInfo> userInfoList = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setString(2, user_PW);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				UserInfo userInfo = UserInfoMapper.resultMapping(rs);
				userInfoList.add(userInfo);
			}
			
			return userInfoList;
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	
	public UserInfo logIn(String user_ID, String user_PW, int user_saveData) throws SQLException {
		String sql = "SELECT * FROM userInfo WHERE user_id = ? and user_PW = ?"
				+ "and user_SaveData =?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setString(2, user_PW);
			stmt.setInt(3, user_saveData);
			rs = stmt.executeQuery();

			if (rs.next()) {
				UserInfo userInfo = UserInfoMapper.resultMapping(rs);
				return userInfo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	
	public void update(int user_Money, String user_ID, 
			int user_SaveData) throws SQLException {
		String sql = "UPDATE userInfo SET user_Money = ? where (user_ID =?) and (user_SaveData=?);";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, user_Money);
			stmt.setString(2, user_ID);
			stmt.setInt(3, user_SaveData);
			

			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

	}
	
	public void updateDate(String user_ID, 
			int user_SaveData) throws SQLException {
		String sql = "UPDATE userInfo SET user_Date = user_Date+1 where (user_ID =?) and (user_SaveData=?);";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, user_SaveData);
			

			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

	}
	
}
