package tables;

import java.util.Objects;

public class UserInfoDays {
	private String userId;
	private int UserSaveData;

	public UserInfoDays(String userId, int userSaveData) {
		super();
		this.userId = userId;
		this.UserSaveData = userSaveData;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getUserSaveData() {
		return UserSaveData;
	}

	public void setUserSaveData(int userSaveData) {
		UserSaveData = userSaveData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(UserSaveData, userId);
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
		return UserSaveData == other.UserSaveData && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "UserInfoDays [userId=" + userId + ", UserSaveData=" + UserSaveData + "]";
	}

}
