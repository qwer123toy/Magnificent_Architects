package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
