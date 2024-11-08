package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import tables.RankStock;

public class RankStockMapper implements IResultMapper<RankStock> {
//	private String rank_ID;
//	private String rank_Money;

	@Override
	public RankStock resultMapping(ResultSet rs) {
		try
		{
			String rank_ID = rs.getString("rank_ID");
			String rank_Money = rs.getString("rank_Money");
			
			return new RankStock(rank_ID, rank_Money);
		}catch(
		SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Rank 매핑 중 예외 발생", e);
		}
	}
	
	public List<RankStock> selectAllRow() {
		String sql = "SELECT * FROM `RankStock`;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<RankStock> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				RankStock rank = resultMapping(rs);
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
