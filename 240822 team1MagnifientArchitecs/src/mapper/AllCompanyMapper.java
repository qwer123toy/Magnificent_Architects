package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import tables.AllCompany;
import tables.CompanyInfo;

public class AllCompanyMapper implements IResultMapper<AllCompany>{
	@Override
	public AllCompany resultMapping(ResultSet rs) {
		try {
		String companyName = rs.getString("companyName");
		int companyStockPrice = rs.getInt("companyStockPrice");
		int companyStockCount = rs.getInt("companyStockCount");
		String simulation_ID = rs.getString("simulation_ID");
		int simulation_ID_SaveData = rs.getInt("simulation_ID_SaveData");
		int date = rs.getInt("date");
		// companyInfo 매퍼는 수정 필요함
//		CompanyInfo companyInfo = rs.
//		CompanyInfo companyInfo = new CompanyInfo(companyName, companyInfo, lastYearSales, companyCategory, companyProducts);
		CompanyInfo companyInfo = new CompanyInfo("임시회사이름", "임시회사정보", 100, "임시회사카테고리", "임시회사제품");
		
		AllCompany allCompany = new AllCompany(companyName, companyStockPrice, companyStockCount, simulation_ID, simulation_ID_SaveData, date, companyInfo); 
		
		return allCompany;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("AllCompany 매핑 중 예외 발생", e);
		}
	}
}
