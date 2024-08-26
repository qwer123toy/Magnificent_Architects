package MainSystem;

public class UserMoneyHistory {
	private String user_ID;
	private int userSaveData;
	private String user_Stock;
	private int buyPrice;
	private int my_Stock_Money;
	private double my_Money_Rate;
	private int stock_Price_now;
	private int stock_Count;
	private int user_Date;

	

	public UserMoneyHistory(String user_ID, int userSaveData, String user_Stock, int buyPrice, int my_Stock_Money,
			double my_Money_Rate, int stock_Price_now, int stock_Count, int user_Date) {
		super();
		this.user_ID = user_ID;
		this.userSaveData = userSaveData;
		this.user_Stock = user_Stock;
		this.buyPrice = buyPrice;
		this.my_Stock_Money = my_Stock_Money;
		this.my_Money_Rate = my_Money_Rate;
		this.stock_Price_now = stock_Price_now;
		this.stock_Count = stock_Count;
		this.user_Date = user_Date;
	}

	public String getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}

	public String getUser_Stock() {
		return user_Stock;
	}

	public void setUser_Stock(String user_Stock) {
		this.user_Stock = user_Stock;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getMy_Stock_Money() {
		return my_Stock_Money;
	}

	public void setMy_Stock_Money(int my_Stock_Money) {
		this.my_Stock_Money = my_Stock_Money;
	}

	public double getMy_Money_Rate() {
		return my_Money_Rate;
	}

	public void setMy_Money_Rate(double my_Money_Rate) {
		this.my_Money_Rate = my_Money_Rate;
	}

	public int getStock_Price_now() {
		return stock_Price_now;
	}

	public void setStock_Price_now(int stock_Price_now) {
		this.stock_Price_now = stock_Price_now;
	}

	public int getStock_Count() {
		return stock_Count;
	}

	public void setStock_Count(int stock_Count) {
		this.stock_Count = stock_Count;
	}

	public int getUser_Date() {
		return user_Date;
	}

	public void setUser_Date(int user_Date) {
		this.user_Date = user_Date;
	}

	public int getUserSaveData() {
		return userSaveData;
	}

	public void setUserSaveData(int userSaveData) {
		this.userSaveData = userSaveData;
	}

	
}
