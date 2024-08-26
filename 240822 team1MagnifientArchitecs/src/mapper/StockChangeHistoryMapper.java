package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import tables.StockChangeHistory;

public class StockChangeHistoryMapper implements IResultMapper<StockChangeHistory>{
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
			int user_Money = rs.getInt("user_Money");
			String companyName = rs.getString("CompanyName");
			int sellStockPrice = rs.getInt("sellStockPrice");
			int sellStockCount = rs.getInt("sellStockCount");
			int buyStockPrice = rs.getInt("buyStockPrice");
			int buyStockCount = rs.getInt("buyStockCount");
			int date = rs.getInt("date");
			
			return new StockChangeHistory(user_ID, user_SaveData, user_Money, companyName, sellStockPrice, sellStockCount, buyStockPrice, buyStockCount, date);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("StockChangeHistory 매핑 중 예외 발생", e);
		}
	}

}
