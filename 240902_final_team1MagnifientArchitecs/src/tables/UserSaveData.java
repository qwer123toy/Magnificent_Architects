package tables;

import java.util.Objects;

public class UserSaveData {
	private String user_Id;
	private int saveData;

	public UserSaveData(String user_Id, int saveData) {
		super();
		this.user_Id = user_Id;
		this.saveData = saveData;
	}

	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public int getSaveData() {
		return saveData;
	}

	public void setSaveData(int saveData) {
		this.saveData = saveData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(saveData, user_Id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UserSaveData)) {
			return false;
		}
		UserSaveData other = (UserSaveData) obj;
		return saveData == other.saveData && Objects.equals(user_Id, other.user_Id);
	}

	@Override
	public String toString() {
		return "UserSaveData [user_Id=" + user_Id + ", saveData=" + saveData + "]";
	}

}
