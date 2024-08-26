package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import tables.UserSaveData;

public class UserSaveDataMapper implements IResultMapper<UserSaveData>{
//	private String user_Id;
//	private int saveData;

	@Override
	public UserSaveData resultMapping(ResultSet rs) {
		try {
			String user_Id = rs.getString("user_Id");
			int saveData = rs.getInt("saveData");
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("UserSaveData 매핑 중 예외 발생", e);
		}
	}
}
