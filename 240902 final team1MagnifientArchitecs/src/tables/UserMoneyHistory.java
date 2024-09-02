package tables;

import java.util.Objects;

public class UserMoneyHistory {
	// 유저의 주식 보유현황을 확인할 수 있음
	// 모든 회사의 주식을 일단 0주로 들고있다는 가정하로 시작
	// 해당 회사의 주식을 사면 그때 유저 정보에서 확인 가능
	// user_date에 맞게 현재가가 변경될 예정이고
	// 평가금액, 평가 손익, 수익률 등을 계산해서
	// 넣어줄 예정
	
	private String user_ID;
	private int userSaveData;
	private String user_Stock; // 보유주식
	private int buyPrice; // 매입가
	private int stock_Price_now; // 현재가
	private int my_Stock_Money; // 평가금액
	private int my_Profit_Money; // 평가손익
	private double my_Profit_Rate; // 유저 수익률
	private int stock_Count; 
	private int user_Date; 

	public UserMoneyHistory(String user_ID, int userSaveData, String user_Stock, int buyPrice, int stock_Price_now,
			int my_Stock_Money, int my_Profit_Money, double my_Profit_Rate, int stock_Count, int user_Date) {
		super();
		this.user_ID = user_ID;
		this.userSaveData = userSaveData;
		this.user_Stock = user_Stock;
		this.buyPrice = buyPrice;
		this.stock_Price_now = stock_Price_now;
		this.my_Stock_Money = my_Stock_Money;
		this.my_Profit_Money = my_Profit_Money;
		this.my_Profit_Rate = my_Profit_Rate;
		this.stock_Count = stock_Count;
		this.user_Date = user_Date;
	}

	public String getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}

	public int getUserSaveData() {
		return userSaveData;
	}

	public void setUserSaveData(int userSaveData) {
		this.userSaveData = userSaveData;
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

	public int getStock_Price_now() {
		return stock_Price_now;
	}

	public void setStock_Price_now(int stock_Price_now) {
		this.stock_Price_now = stock_Price_now;
	}

	public int getMy_Stock_Money() {
		return my_Stock_Money;
	}

	public void setMy_Stock_Money(int my_Stock_Money) {
		this.my_Stock_Money = my_Stock_Money;
	}

	public int getMy_Profit_Money() {
		return my_Profit_Money;
	}

	public void setMy_Profit_Money(int my_Profit_Money) {
		this.my_Profit_Money = my_Profit_Money;
	}

	public double getMy_Profit_Rate() {
		return my_Profit_Rate;
	}

	public void setMy_Profit_Rate(double my_Profit_Rate) {
		this.my_Profit_Rate = my_Profit_Rate;
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

	@Override
	public int hashCode() {
		return Objects.hash(buyPrice, my_Profit_Money, my_Profit_Rate, my_Stock_Money, stock_Count, stock_Price_now,
				userSaveData, user_Date, user_ID, user_Stock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserMoneyHistory)) {
			return false;
		}
		UserMoneyHistory other = (UserMoneyHistory) obj;
		return buyPrice == other.buyPrice && my_Profit_Money == other.my_Profit_Money
				&& Double.doubleToLongBits(my_Profit_Rate) == Double.doubleToLongBits(other.my_Profit_Rate)
				&& my_Stock_Money == other.my_Stock_Money && stock_Count == other.stock_Count
				&& stock_Price_now == other.stock_Price_now && userSaveData == other.userSaveData
				&& user_Date == other.user_Date && Objects.equals(user_ID, other.user_ID)
				&& Objects.equals(user_Stock, other.user_Stock);
	}

	@Override
	public String toString() {
		return "UserMoneyHistory [user_ID=" + user_ID + ", userSaveData=" + userSaveData + ", user_Stock=" + user_Stock
				+ ", buyPrice=" + buyPrice + ", stock_Price_now=" + stock_Price_now + ", my_Stock_Money="
				+ my_Stock_Money + ", my_Profit_Money=" + my_Profit_Money + ", my_Profit_Rate=" + my_Profit_Rate
				+ ", stock_Count=" + stock_Count + ", user_Date=" + user_Date + "]";
	}

}
