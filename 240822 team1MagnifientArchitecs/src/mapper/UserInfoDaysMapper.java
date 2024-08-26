package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
