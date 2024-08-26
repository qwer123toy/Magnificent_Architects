package MainSystem;

public class AllCompanyBackdata {
	private String companyName;
	private int companyStock;
	private int companyStockCount;
	private String simulation_ID;
	private int simulation_ID_SaveData;
	private int date;

	public AllCompanyBackdata() {
		
	}
	
	public AllCompanyBackdata(String companyName, int companyStock, int companyStockCount, String simulation_ID,
			int simulation_ID_SaveData, int date) {
		super();
		this.companyName = companyName;
		this.companyStock = companyStock;
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

	public int getCompanyStock() {
		return companyStock;
	}

	public void setCompanyStock(int companyStock) {
		this.companyStock = companyStock;
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

}
