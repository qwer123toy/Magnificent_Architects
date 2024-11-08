package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import mapper.NewsMapper;
import tables.AllCompany;
import tables.News;

public class NewsDAO {
	public static final IResultMapper<News>  newsMapper = new NewsMapper();
	
	public News findCompByID(int info_Num) {
		String sql = "SELECT * FROM News WHERE "
				+ "info_Num = ?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, info_Num);
			rs = stmt.executeQuery();

			if (rs.next()) {
				News news = newsMapper.resultMapping(rs);
				return news;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
}
