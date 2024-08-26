package MainSystem;

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
	
	
}
