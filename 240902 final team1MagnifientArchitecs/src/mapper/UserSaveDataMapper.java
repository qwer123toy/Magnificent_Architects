package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import tables.UserSaveData;

public class UserSaveDataMapper implements IResultMapper<UserSaveData>{
//	private String user_Id;
//	private int saveData;

	@Override
	public UserSaveData resultMapping(ResultSet rs) {
		try {
			String user_Id = rs.getString("user_Id");
			int saveData = rs.getInt("saveData");
			
			return new UserSaveData(user_Id, saveData);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("UserSaveData 매핑 중 예외 발생", e);
		}
	}
	
	public List<UserSaveData> selectAllRow() {
		String sql = "SELECT * FROM `UserSaveData`;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		List<UserSaveData> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				UserSaveData userSaveData = resultMapping(rs);
				list.add(userSaveData);
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
//		UserSaveDataMapper mapper = new UserSaveDataMapper();
//		List<UserSaveData> list = mapper.selectAllRow();
//		System.out.println(list.toString());
//	}
}
