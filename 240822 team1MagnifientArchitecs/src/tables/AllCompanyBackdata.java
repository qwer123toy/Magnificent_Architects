package tables;

import java.util.Objects;

public class AllCompanyBackdata {
	
	// 회사별로 발생한 모든 주식 수량 변동 및 주가 변동을 저장함
	// 이때 아이디와 saveData로 값을 찾을 수 있음
	
	private String companyName;
	private int companyStockPrice;
	private int companyStockCount;
	private String simulation_ID;
	private int simulation_ID_SaveData;
	private int date;

	public AllCompanyBackdata() {

	}

	public AllCompanyBackdata(String companyName, int companyStockPrice, int companyStockCount, String simulation_ID,
			int simulation_ID_SaveData, int date) {
		super();
		this.companyName = companyName;
		this.companyStockPrice = companyStockPrice;
		this.companyStockCount = companyStockCount;
		this.simulation_ID = simulation_ID;
		this.simulation_ID_SaveData = simulation_ID_SaveData;
		this.date = date;
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

	@Override
	public int hashCode() {
		return Objects.hash(companyName, companyStockCount, companyStockPrice, date, simulation_ID,
				simulation_ID_SaveData);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AllCompanyBackdata)) {
			return false;
		}
		AllCompanyBackdata other = (AllCompanyBackdata) obj;
		return Objects.equals(companyName, other.companyName) && companyStockCount == other.companyStockCount
				&& companyStockPrice == other.companyStockPrice && date == other.date
				&& Objects.equals(simulation_ID, other.simulation_ID)
				&& simulation_ID_SaveData == other.simulation_ID_SaveData;
	}

	@Override
	public String toString() {
		return "AllCompanyBackdata [companyName=" + companyName + ", companyStockPrice=" + companyStockPrice
				+ ", companyStockCount=" + companyStockCount + ", simulation_ID=" + simulation_ID
				+ ", simulation_ID_SaveData=" + simulation_ID_SaveData + ", date=" + date + "]";
	}

}
