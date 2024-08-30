package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import tables.StockChangeHistory;

public class StockChangeHistoryMapper implements IResultMapper<StockChangeHistory> {
//	private String user_ID;
//	private int user_SaveData;
//	private int user_Money;
//	private String companyName;
//	private int sellStockPrice;
//	private int sellStockCount;
//	private int buyStockPrice;
//	private int buyStockCount;
//	private int date;

	@Override
	public StockChangeHistory resultMapping(ResultSet rs) {
		try {
			String user_ID = rs.getString("user_ID");
			int user_SaveData = rs.getInt("user_SaveData");
			String companyName = rs.getString("CompanyName");
			int sellStockPrice = rs.getInt("sellStockPrice");
			int sellStockCount = rs.getInt("sellStockCount");
			int buyStockPrice = rs.getInt("buyStockPrice");
			int buyStockCount = rs.getInt("buyStockCount");
			int date = rs.getInt("date");

			return new StockChangeHistory(user_ID, user_SaveData, companyName, sellStockPrice,
					sellStockCount, buyStockPrice, buyStockCount, date);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("StockChangeHistory 매핑 중 예외 발생", e);
		}
	}

	public List<StockChangeHistory> selectAllRow() {
		String sql = "SELECT * FROM `StockChangeHistory`;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<StockChangeHistory> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				StockChangeHistory stockChangeHistory = resultMapping(rs);
				list.add(stockChangeHistory);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

//	public static void main(String[] args) {
//		StockChangeHistoryMapper mapper = new StockChangeHistoryMapper();
//		List<StockChangeHistory> list = mapper.selectAllRow();
//		System.out.println(list.toString());
//	}

}
