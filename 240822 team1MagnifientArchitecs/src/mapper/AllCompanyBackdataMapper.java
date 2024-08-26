package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import tables.AllCompanyBackdata;

public class AllCompanyBackdataMapper implements IResultMapper<AllCompanyBackdata>{
//	private String companyName;
//	private int companyStockPrice;
//	private int companyStockCount;
//	private String simulation_ID;
//	private int simulation_ID_SaveData;
//	private int date;
	
	@Override
	public AllCompanyBackdata resultMapping(ResultSet rs) {
		try {
			String companyName = rs.getString("companyName");
			int companyStockPrice = rs.getInt("companyStockPrice");
			int companyStockCount = rs.getInt("companyStockCount");
			String simulation_ID = rs.getString("simulation_ID");
			int simulation_ID_SaveData = rs.getInt("simulation_ID_SaveData");
			int date = rs.getInt("date");
			
			AllCompanyBackdata allCompanyBackdata = new AllCompanyBackdata(companyName, companyStockPrice, companyStockCount, simulation_ID, simulation_ID_SaveData, date);
			
			return allCompanyBackdata;
			
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("AllCompanyBackData 매핑 중 예외 발생", e);
			}
	}
}
