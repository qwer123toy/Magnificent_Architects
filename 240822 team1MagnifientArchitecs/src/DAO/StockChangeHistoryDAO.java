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
		String sql = "SELECT * FROM stockchangehistory WHERE user_ID = ? and user_SaveData = ?";
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

	// 거래 내역에 구매를 추가
	public void insertBuy(String user_ID, int user_saveData, String companyName, int buyStockPrice, int buyStockCount, int date)
			throws SQLException {
		String sql = "insert into stockchangehistory"
				+ "(user_ID, user_SaveData, companyName, buyStockPrice, buyStockCount, date) \r\n" + "	value(?, ?, ?, ?, ?, ?)";

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

	public void insertSell(String user_ID, int user_saveData, String companyName, int sellStockPrice, int sellStockCount,
			int date) throws SQLException {
		String sql = "insert into stockchangehistory"
				+ "(user_ID, user_SaveData, companyName, sellStockPrice, sellStockCount, date) \r\n"
				+ "	value(?, ?, ?, ?, ?, ?)";

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

	// 내가 가진 특정 회사 총 주식 수량(모든날)
	public int findStockCountAllBycompName(String companyName, String user_ID, int saveData) {
		String sql = "select sum(buyStockCount)-sum(sellStockCount) as '주식수량' from stockchangehistory \r\n"
				+ "	where user_ID = ? and user_SaveData= ? and companyName = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int stockCountAll = 0;
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, saveData);
			stmt.setString(3, companyName);
			rs = stmt.executeQuery();

			if (rs.next()) {
				stockCountAll = rs.getInt(1);
			}
			return stockCountAll;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return -1;
	}
	
	// 내가 가진 A 회사 구매금액 평단가
	public int findStockMoneyAvgBycompName(String companyName, String user_ID, int saveData) {
		String sql = "select Round((sum(buyStockPrice*buyStockCount) - sum(sellStockPrice* sellStockCount))/(sum(buyStockCount)\r\n" + 
				"	-sum(sellstockCount))) as '보유금액' from stockchangehistory \r\n" + 
				"where user_ID = ? and user_SaveData= ? and companyName = ?;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int stockCountAll = 0;
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, saveData);
			stmt.setString(3, companyName);
			rs = stmt.executeQuery();

			if (rs.next()) {
				stockCountAll = rs.getInt(1);
			}
			return stockCountAll;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return -1;
	}

	
	
	

	// 내가 가진 특정 회사 총 주식금액(모든날) : 특정 회사 주식 구매 총금액
	public int findStockMoneyAllBycompName(String companyName, String user_ID, int saveData) {
		String sql = "select sum(buyStockPrice*buyStockCount) - sum(sellStockPrice* sellStockCount) as '보유금액' from stockchangehistory "
				+ "	where user_ID = ? and user_SaveData= ? and companyName = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int stockCountAll = 0;
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, saveData);
			stmt.setString(3, companyName);
			rs = stmt.executeQuery();

			if (rs.next()) {
				stockCountAll = rs.getInt(1);
			}
			return stockCountAll;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return -1;
	}

	
	// 내가 가진 특정 회사의 현재 주가
	public int findStockMoneyNowBycompName(String companyName, String user_ID, int saveData) {
		String sql = "select companyStockPrice from allcompany \r\n"
				+ "	where simulation_ID = ? and simulation_ID_SaveData= ? and companyName = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int stockCountAll = 0;
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, saveData);
			stmt.setString(3, companyName);
			rs = stmt.executeQuery();

			if (rs.next()) {
				stockCountAll = rs.getInt(1);
			}
			return stockCountAll;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return -1;
	}

	// 내가 가진 특정 회사 손익 금액
	public int findPlusStockMoneyNowBycompName(String companyName, String user_ID, int saveData) {
		String sql = "select ((select companyStockPrice from allcompany \r\n"
				+ "	where simulation_ID = ? and simulation_ID_SaveData= ? and companyName = ? )*\r\n"
				+ "    (select sum(buyStockCount)-sum(sellStockCount) from stockchangehistory where user_ID = ? and user_SaveData= ? \r\n"
				+ "		and companyName = ? ) - \r\n"
				+ "(sum(buyStockPrice*buyStockCount) - sum(sellStockPrice* sellStockCount))) as '손익금액' from stockchangehistory \r\n"
				+ "where user_ID = ? and user_SaveData = ? and companyName = ? ;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int stockCountAll = 0;
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, saveData);
			stmt.setString(3, companyName);
			stmt.setString(4, user_ID);
			stmt.setInt(5, saveData);
			stmt.setString(6, companyName);
			stmt.setString(7, user_ID);
			stmt.setInt(8, saveData);
			stmt.setString(9, companyName);
			rs = stmt.executeQuery();

			if (rs.next()) {
				stockCountAll = rs.getInt(1);
			}
			return stockCountAll;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return -1;
	}

	// 내가 가진 특정 회사 평가 금액
	public int findFinalStockMoneyNowBycompName(String companyName, String user_ID, int saveData) {
		String sql = "select (select ((select companyStockPrice from allcompany \r\n"
				+ "	where simulation_ID = ? and simulation_ID_SaveData= ? and companyName = ?)*\r\n"
				+ "    (select sum(buyStockCount)-sum(sellStockCount) from stockchangehistory where user_ID = ? and user_SaveData= ? \r\n"
				+ "		and companyName = ?) - (sum(buyStockPrice*buyStockCount) - sum(sellStockPrice* sellStockCount))) as '손익금액' from stockchangehistory \r\n"
				+ "where user_ID = ? and user_SaveData= ? and companyName = ?) + (sum(buyStockPrice*buyStockCount) - sum(sellStockPrice* sellStockCount)) as '보유금액' from stockchangehistory \r\n"
				+ "where user_ID = ? and user_SaveData= ? and companyName = ?;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int stockCountAll = 0;
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, saveData);
			stmt.setString(3, companyName);
			stmt.setString(4, user_ID);
			stmt.setInt(5, saveData);
			stmt.setString(6, companyName);
			stmt.setString(7, user_ID);
			stmt.setInt(8, saveData);
			stmt.setString(9, companyName);
			stmt.setString(10, user_ID);
			stmt.setInt(11, saveData);
			stmt.setString(12, companyName);
			rs = stmt.executeQuery();

			if (rs.next()) {
				stockCountAll = rs.getInt(1);
			}
			return stockCountAll;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return -1;
	}
	
	// 내가 가진 A 회사 수익률
	public double findFinalStockMoneyRateNowBycompName(String companyName, String user_ID, int saveData) {
		String sql = "select (select companyStockPrice from allcompany \r\n" + 
				"	where simulation_ID = ? and simulation_ID_SaveData= ? and companyName = ? )*\r\n" + 
				"    (select sum(buyStockCount)-sum(sellStockCount) from stockchangehistory where user_ID = ? and user_SaveData= ?\r\n" + 
				"		and companyName = ? )/\r\n" + 
				"(sum(buyStockPrice*buyStockCount) - sum(sellStockPrice* sellStockCount))-1 as '수익률' from stockchangehistory \r\n" + 
				"where user_ID = ? and user_SaveData= ? and companyName = ?;\r\n";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		double stockMoneyRate = 0;
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_ID);
			stmt.setInt(2, saveData);
			stmt.setString(3, companyName);
			stmt.setString(4, user_ID);
			stmt.setInt(5, saveData);
			stmt.setString(6, companyName);
			stmt.setString(7, user_ID);
			stmt.setInt(8, saveData);
			stmt.setString(9, companyName);
			rs = stmt.executeQuery();

			if (rs.next()) {
	            stockMoneyRate = rs.getDouble(1);  // 소수점 값 가져오기

			}
			return stockMoneyRate;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return -1;
	}

}
