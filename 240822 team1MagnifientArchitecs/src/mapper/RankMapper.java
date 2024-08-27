package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import tables.Rank;

public class RankMapper implements IResultMapper<Rank> {
//	private String rank_ID;
//	private String rank_Money;
//	private String rank_Rank;

	@Override
	public Rank resultMapping(ResultSet rs) {
		try
		{
			String rank_ID = rs.getString("rank_ID");
			String rank_Money = rs.getString("rank_Money");
			String rank_Rank = rs.getString("rank_Rank");
			
			return new Rank(rank_ID, rank_Money, rank_Rank);
		}catch(
		SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Rank 매핑 중 예외 발생", e);
		}
	}
	
	public List<Rank> selectAllRow() {
		String sql = "SELECT * FROM `rank`;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Rank> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Rank rank = resultMapping(rs);
				list.add(rank);
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
//		RankMapper mapper = new RankMapper();
//		List<Rank> list = mapper.selectAllRow();
//		System.out.println(list.toString());
//	}
}
