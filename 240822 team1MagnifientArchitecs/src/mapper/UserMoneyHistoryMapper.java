package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import tables.UserMoneyHistory;

public class UserMoneyHistoryMapper implements IResultMapper<UserMoneyHistory> {
//	private String user_ID;
//	private int userSaveData;
//	private String user_Stock; // 보유주식
//	private int buyPrice; // 매입가
//	private int stock_Price_now; // 현재가
//	private int my_Stock_Money; // 평가금액
//	private int my_Profit_Money; // 평가손익
//	private double my_Profit_Rate; // 유저 수익률
//	private int stock_Count; 
//	private int user_Date; 

	@Override
	public UserMoneyHistory resultMapping(ResultSet rs) {
		try {
			String user_ID = rs.getString("user_ID");
			int userSaveData = rs.getInt("userSaveData");
			String user_Stock = rs.getString("user_Stock");
			int buyPrice = rs.getInt("buyPrice");
			int stock_Price_now = rs.getInt("stock_Price_now");
			int my_Stock_Money = rs.getInt("my_Stock_Money");
			int my_Profit_Money = rs.getInt("my_Profit_Money");
			double my_Profit_Rate = rs.getDouble("my_Profit_Rate");
			int stock_Count = rs.getInt("stock_Count");
			int user_Date = rs.getInt("user_Date");
			
			return new UserMoneyHistory(user_ID, userSaveData, user_Stock, buyPrice, stock_Price_now, my_Stock_Money, my_Profit_Money, my_Profit_Rate, stock_Count, user_Date);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("UserMoneyHistory 매핑 중 예외 발생", e);
		}
	}
	
	public List<UserMoneyHistory> selectAllRow() {
		String sql = "SELECT * FROM `UserMoneyHistory`;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<UserMoneyHistory> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				UserMoneyHistory userMoneyHistory = resultMapping(rs);
				list.add(userMoneyHistory);
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
//		UserMoneyHistoryMapper mapper = new UserMoneyHistoryMapper();
//		List<UserMoneyHistory> list = mapper.selectAllRow();
//		System.out.println(list.toString());
//	}
}
