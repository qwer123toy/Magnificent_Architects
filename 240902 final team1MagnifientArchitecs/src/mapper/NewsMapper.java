package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import tables.News;

public class NewsMapper implements IResultMapper<News>{
//	private int info_num;
//	private String info_News;
//	private String info_NewsUp;
//	private String info_NewsDown;
//	private String info_Expect;
	@Override
	public News resultMapping(ResultSet rs) {
		try {
			int info_Num = rs.getInt("info_num");
			String info_News = rs.getString("info_News");
			String info_NewsUp = rs.getString("info_NewsUp");
			String info_NewsDown = rs.getString("info_NewsDown");
			int stockPlus = rs.getInt("stockPlus");
			int stockMinus = rs.getInt("stockMinus");
			String companyCategory =  rs.getString("companyCategory");
			
			return new News(info_Num, info_News, info_NewsUp, info_NewsDown, stockPlus, stockMinus, companyCategory);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("News 매핑 중 예외 발생", e);
		}
	}
	
	public List<News> selectAllRow() {
		String sql = "SELECT * FROM News;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<News> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				News news = resultMapping(rs);
				list.add(news);
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
//		NewsMapper mapper = new NewsMapper();
//		List<News> list = mapper.selectAllRow();
//		System.out.println(list.toString());
//	}
}
