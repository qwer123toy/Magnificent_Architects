package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import tables.UserInfo;
import tables.UserInfoDays;

public class UserInfoMapper implements IResultMapper<UserInfo>{
//	private String user_ID;
//	private int user_SaveData;
//	private String user_PW;
//	private int user_Money;
//	private int user_Stock;
//	private int user_Date;
//	private UserInfoDays userInfoDays;

	@Override
	public UserInfo resultMapping(ResultSet rs) {
		try {
			String user_ID = rs.getString("user_ID");
			int user_SaveData = rs.getInt("user_SaveData");
			String user_PW = rs.getString("user_PW");
			int user_Money = rs.getInt("user_Money");
			int user_Stock = rs.getInt("user_Stock");
			int user_Date = rs.getInt("user_Date");
			// TODO userInfoDays 생성자 만들어야 한다.
			UserInfoDays userInfoDays = new UserInfoDays("임시 아이디", 5);
			
			return new UserInfo(user_ID, user_SaveData, user_PW, user_Money, user_Stock, user_Date, userInfoDays);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("UserInfo 매핑 중 예외 발생", e);
		}
	}
	
	public List<UserInfo> selectAllRow() {
		String sql = "SELECT * FROM `UserInfo`;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<UserInfo> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				UserInfo userInfo = resultMapping(rs);
				list.add(userInfo);
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
//		UserInfoMapper mapper = new UserInfoMapper();
//		List<UserInfo> list = mapper.selectAllRow();
//		System.out.println(list.toString());
//	}
}
