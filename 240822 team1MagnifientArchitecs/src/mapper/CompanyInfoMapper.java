package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import tables.CompanyInfo;

public class CompanyInfoMapper implements IResultMapper<CompanyInfo>{
//	private String companyName;
//	private String companyInfo;
//	private int lastYearSales;
//	private String companyCategory;
//	private String companyProducts;

	@Override
	public CompanyInfo resultMapping(ResultSet rs) {
		try {
			String companyName = rs.getString("companyName");
			String companyInfo = rs.getString("companyInfo");
			int lastYearSales = rs.getInt("lastYearSales");
			String companyCategory = rs.getString("companyCategory");
			String companyProducts = rs.getString("companyProducts");
			
			return new CompanyInfo(companyName, companyInfo, lastYearSales, companyCategory, companyProducts);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("CompanyInfo 매핑 중 예외 발생", e);
		}
	}
	
	public List<CompanyInfo> selectAllRow() {
		String sql = "SELECT * FROM CompanyInfo;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<CompanyInfo> companyInfos = new ArrayList<>();

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				CompanyInfo companyInfo = resultMapping(rs);
				companyInfos.add(companyInfo);
			}
			return companyInfos;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
//	public static void main(String[] args) {
//		List<CompanyInfo> list = new ArrayList<>();
//		CompanyInfoMapper companyInfoMapper = new CompanyInfoMapper();
//		list = companyInfoMapper.selectAllRow();
//		System.out.println(list.toString());
//	}
}
