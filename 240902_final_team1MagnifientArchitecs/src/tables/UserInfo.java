package tables;

import java.util.Objects;

public class UserInfo {
	
	//유저의 정보를 갖고 있음
	//ID는 중복 불가
	
	private String user_ID;
	private int user_SaveData;

	private String user_PW;
	private int user_Money;
	private int user_Stock;
	private int user_Date;

	private UserInfoDays userInfoDays;

	public UserInfo() {
		
	}
	public UserInfo(String user_ID, int user_SaveData, String user_PW, int user_Money, int user_Stock, int user_Date,
			UserInfoDays userInfoDays) {
		super();
		this.user_ID = user_ID;
		this.user_SaveData = user_SaveData;
		this.user_PW = user_PW;
		this.user_Money = user_Money;
		this.user_Stock = user_Stock;
		this.user_Date = user_Date;
		this.userInfoDays = userInfoDays;
	}

	public String getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}

	public String getUser_PW() {
		return user_PW;
	}

	public void setUser_PW(String user_PW) {
		this.user_PW = user_PW;
	}

	public int getUser_Money() {
		return user_Money;
	}

	public void setUser_Money(int user_Money) {
		this.user_Money = user_Money;
	}

	public int getUser_Stock() {
		return user_Stock;
	}

	public void setUser_Stock(int user_Stock) {
		this.user_Stock = user_Stock;
	}

	public int getUser_Date() {
		return user_Date;
	}

	public void setUser_Date(int user_Date) {
		this.user_Date = user_Date;
	}

	public int getUser_SaveData() {
		return user_SaveData;
	}

	public void setUser_SaveData(int user_SaveData) {
		this.user_SaveData = user_SaveData;
	}

	public UserInfoDays getUserInfoDays() {
		return userInfoDays;
	}

	public void setUserInfoDays(UserInfoDays userInfoDays) {
		this.userInfoDays = userInfoDays;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userInfoDays, user_Date, user_ID, user_Money, user_PW, user_SaveData, user_Stock);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserInfo)) {
			return false;
		}
		UserInfo other = (UserInfo) obj;
		return Objects.equals(userInfoDays, other.userInfoDays) && user_Date == other.user_Date
				&& Objects.equals(user_ID, other.user_ID) && user_Money == other.user_Money
				&& Objects.equals(user_PW, other.user_PW) && user_SaveData == other.user_SaveData
				&& user_Stock == other.user_Stock;
	}

	@Override
	public String toString() {
		return "UserInfo [user_ID=" + user_ID + ", user_SaveData=" + user_SaveData + ", user_PW=" + user_PW
				+ ", user_Money=" + user_Money + ", user_Stock=" + user_Stock + ", user_Date=" + user_Date
				+ ", userInfoDays=" + userInfoDays + "]";
	}

}
