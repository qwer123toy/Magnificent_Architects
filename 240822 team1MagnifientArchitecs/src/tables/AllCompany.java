package tables;

import java.util.Objects;

public class AllCompany {
	private String companyName;
	private int companyStockPrice;
	private int companyStockCount;
	private String simulation_ID;
	private int simulation_ID_SaveData;
	private int date;
	private CompanyInfo companyInfo;

	public AllCompany() {

	}

	public AllCompany(String companyName, int companyStockPrice, int companyStockCount, String simulation_ID,
			int simulation_ID_SaveData, int date, CompanyInfo companyInfo) {
		super();
		this.companyName = companyName;
		this.companyStockPrice = companyStockPrice;
		this.companyStockCount = companyStockCount;
		this.simulation_ID = simulation_ID;
		this.simulation_ID_SaveData = simulation_ID_SaveData;
		this.date = date;
		this.companyInfo = companyInfo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getCompanyStockPrice() {
		return companyStockPrice;
	}

	public void setCompanyStockPrice(int companyStockPrice) {
		this.companyStockPrice = companyStockPrice;
	}

	public int getCompanyStockCount() {
		return companyStockCount;
	}

	public void setCompanyStockCount(int companyStockCount) {
		this.companyStockCount = companyStockCount;
	}

	public String getSimulation_ID() {
		return simulation_ID;
	}

	public void setSimulation_ID(String simulation_ID) {
		this.simulation_ID = simulation_ID;
	}

	public int getSimulation_ID_SaveData() {
		return simulation_ID_SaveData;
	}

	public void setSimulation_ID_SaveData(int simulation_ID_SaveData) {
		this.simulation_ID_SaveData = simulation_ID_SaveData;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(companyInfo, companyName, companyStockCount, companyStockPrice, date, simulation_ID,
				simulation_ID_SaveData);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AllCompany)) {
			return false;
		}
		AllCompany other = (AllCompany) obj;
		return Objects.equals(companyInfo, other.companyInfo) && Objects.equals(companyName, other.companyName)
				&& companyStockCount == other.companyStockCount && companyStockPrice == other.companyStockPrice
				&& date == other.date && Objects.equals(simulation_ID, other.simulation_ID)
				&& simulation_ID_SaveData == other.simulation_ID_SaveData;
	}

	@Override
	public String toString() {
		return "AllCompany [companyName=" + companyName + ", companyStockPrice=" + companyStockPrice
				+ ", companyStockCount=" + companyStockCount + ", simulation_ID=" + simulation_ID
				+ ", simulation_ID_SaveData=" + simulation_ID_SaveData + ", date=" + date + ", companyInfo="
				+ companyInfo + "]";
	}

}