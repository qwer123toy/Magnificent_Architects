package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
