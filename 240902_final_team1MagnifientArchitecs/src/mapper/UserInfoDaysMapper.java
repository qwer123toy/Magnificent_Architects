package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import tables.UserInfoDays;

public class UserInfoDaysMapper implements IResultMapper<UserInfoDays>{
//	private String userId;
//	private int userSaveData;

	@Override
	public UserInfoDays resultMapping(ResultSet rs) {
		try {
			String userId = rs.getString("userId");
			int userSaveData = rs.getInt("UserSaveData");
			
			return new UserInfoDays(userId, userSaveData);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("UserInfoDays 매핑 중 예외 발생", e);
		}
	}
	
	public List<UserInfoDays> selectAllRow() {
		String sql = "SELECT * FROM `UserInfoDays`;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<UserInfoDays> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				UserInfoDays userInfoDays = resultMapping(rs);
				list.add(userInfoDays);
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
//		UserInfoDaysMapper mapper = new UserInfoDaysMapper();
//		List<UserInfoDays> list = mapper.selectAllRow();
//		System.out.println(list.toString());
//	}

}
