package tables;

import java.util.Objects;

public class StockChangeHistory {
	
	//주식 거래 내역
	// 해당 사용자의 정보에서 발생한 모든 
	// 주식 거래 내역을 저장하고 있음
	// 추후 사용자의 거래 내역 확인을 할 때
	// 사용할 예정
	
	private String user_ID;
	private int user_SaveData;
	private String companyName;
	private int sellStockPrice;
	private int sellStockCount;
	private int buyStockPrice;
	private int buyStockCount;
	private int date;

	public StockChangeHistory(String user_ID, int user_SaveData, String companyName, int sellStockPrice,
			int sellStockCount, int buyStockPrice, int buyStockCount, int date) {
		super();
		this.user_ID = user_ID;
		this.user_SaveData = user_SaveData;
		this.companyName = companyName;
		this.sellStockPrice = sellStockPrice;
		this.sellStockCount = sellStockCount;
		this.buyStockPrice = buyStockPrice;
		this.buyStockCount = buyStockCount;
		this.date = date;
	}

	public String getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}

	public int getUser_SaveData() {
		return user_SaveData;
	}

	public void setUser_SaveData(int user_SaveData) {
		this.user_SaveData = user_SaveData;
	}

	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getSellStockPrice() {
		return sellStockPrice;
	}

	public void setSellStockPrice(int sellStockPrice) {
		this.sellStockPrice = sellStockPrice;
	}

	public int getSellStockCount() {
		return sellStockCount;
	}

	public void setSellStockCount(int sellStockCount) {
		this.sellStockCount = sellStockCount;
	}

	public int getBuyStockPrice() {
		return buyStockPrice;
	}

	public void setBuyStockPrice(int buyStockPrice) {
		this.buyStockPrice = buyStockPrice;
	}

	public int getBuyStockCount() {
		return buyStockCount;
	}

	public void setBuyStockCount(int buyStockCount) {
		this.buyStockCount = buyStockCount;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(buyStockCount, buyStockPrice, companyName, date, sellStockCount, sellStockPrice, user_ID,
				 user_SaveData);
	}


}
