package tables;

import java.util.Objects;

public class StockChangeHistory {
	private String user_ID;
	private int user_SaveData;
	private int user_Money;
	private String CompanyName;
	private int sellStockPrice;
	private int sellStockCount;
	private int buyStockPrice;
	private int buyStockCount;
	private int date;

	public StockChangeHistory(String user_ID, int user_SaveData, int user_Money, String companyName, int sellStockPrice,
			int sellStockCount, int buyStockPrice, int buyStockCount, int date) {
		super();
		this.user_ID = user_ID;
		this.user_SaveData = user_SaveData;
		this.user_Money = user_Money;
		CompanyName = companyName;
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

	public int getUser_Money() {
		return user_Money;
	}

	public void setUser_Money(int user_Money) {
		this.user_Money = user_Money;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String companyName) {
		CompanyName = companyName;
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
		return Objects.hash(CompanyName, buyStockCount, buyStockPrice, date, sellStockCount, sellStockPrice, user_ID,
				user_Money, user_SaveData);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof StockChangeHistory)) {
			return false;
		}
		StockChangeHistory other = (StockChangeHistory) obj;
		return Objects.equals(CompanyName, other.CompanyName) && buyStockCount == other.buyStockCount
				&& buyStockPrice == other.buyStockPrice && date == other.date && sellStockCount == other.sellStockCount
				&& sellStockPrice == other.sellStockPrice && Objects.equals(user_ID, other.user_ID)
				&& user_Money == other.user_Money && user_SaveData == other.user_SaveData;
	}

	@Override
	public String toString() {
		return "StockChangeHistory [user_ID=" + user_ID + ", user_SaveData=" + user_SaveData + ", user_Money="
				+ user_Money + ", CompanyName=" + CompanyName + ", sellStockPrice=" + sellStockPrice
				+ ", sellStockCount=" + sellStockCount + ", buyStockPrice=" + buyStockPrice + ", buyStockCount="
				+ buyStockCount + ", date=" + date + "]";
	}

}
