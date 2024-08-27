package tables;

import java.util.Objects;

public class UserInfoDays {
	private String userId;
	private int userSaveData;

	public UserInfoDays(String userId, int userSaveData) {
		super();
		this.userId = userId;
		this.userSaveData = userSaveData;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getUserSaveData() {
		return userSaveData;
	}

	public void setUserSaveData(int userSaveData) {
		this.userSaveData = userSaveData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userSaveData);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserInfoDays)) {
			return false;
		}
		UserInfoDays other = (UserInfoDays) obj;
		return Objects.equals(userId, other.userId) && userSaveData == other.userSaveData;
	}

	@Override
	public String toString() {
		return "UserInfoDays [userId=" + userId + ", userSaveData=" + userSaveData + "]";
	}

}
