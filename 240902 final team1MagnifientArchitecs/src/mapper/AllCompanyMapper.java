package mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.CompanyInfoDAO;
import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import tables.AllCompany;
import tables.CompanyInfo;

public class AllCompanyMapper implements IResultMapper<AllCompany>{
//	CompanyInfoDAO companyInfoDAO = new CompanyInfoDAO();
	@Override
	public AllCompany resultMapping(ResultSet rs) {
		try {
		String companyName = rs.getString("companyName");
		int companyStockPrice = rs.getInt("companyStockPrice");
		int companyStockCount = rs.getInt("companyStockCount");
		String simulation_ID = rs.getString("simulation_ID");
		int simulation_ID_SaveData = rs.getInt("simulation_ID_SaveData");
		int date = rs.getInt("date");
		// TODO companyInfo 매퍼는 수정 필요함
//		CompanyInfo companyInfo = rs.
//		CompanyInfo companyInfo = new CompanyInfo(companyName, companyInfo, lastYearSales, companyCategory, companyProducts);
//		CompanyInfo companyInfo = new CompanyInfo("임시회사이름", "임시회사정보", 100, "임시회사카테고리", "임시회사제품");
		
		AllCompany allCompany = new AllCompany(companyName, companyStockPrice, companyStockCount, simulation_ID, simulation_ID_SaveData, date); 
		
		return allCompany;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("AllCompany 매핑 중 예외 발생", e);
		}
	}
	
	public AllCompany selectRow() {
		String sql = "SELECT * FROM AllCompany;";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			// TODO 일단 row 1줄만 가져오는 걸로
			if (rs.next()) {
				AllCompany allCompany = resultMapping(rs);
				return allCompany;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		AllCompanyMapper allCompanyMapper = new AllCompanyMapper();
//		AllCompany allCompany = allCompanyMapper.selectRow();
//		System.out.println(allCompany);
//	}
}
