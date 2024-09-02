package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import mapper.RankStockMapper;
import tables.AllCompany;
import tables.CompanyInfo;
import tables.RankStock;

public class RankStockDAO {
	public static final IResultMapper<RankStock> rankStockMapper = new RankStockMapper();

	public List<RankStock> findAllByID(String userId, int saveData) {
		String sql = "SELECT * FROM rankStock order by rank_money";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<RankStock> rankStockList = new ArrayList<>();
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			if (rs.next()) {
				RankStock rankStock = rankStockMapper.resultMapping(rs);
				rankStockList.add(rankStock);
			}
			return rankStockList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	public void insert(String rank_ID, int rank_Money) throws SQLException {
		String sql = "INSERT INTO rankStock (rank_ID, rank_Money) VALUES (?, ?);";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, rank_ID);
			stmt.setInt(2, rank_Money);

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DBUtil.closeAll(rs, stmt, conn);
		}

	}

}
