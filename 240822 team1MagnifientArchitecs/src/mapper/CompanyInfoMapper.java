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
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("CompanyInfo 매핑 중 예외 발생", e);
		}
	}
}
