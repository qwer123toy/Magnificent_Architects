package tables;

import java.util.Objects;

public class News {
	
	// 늬우스
	// 추후에 해당 정보를 통해
	// 주가가 상승할지 하락할지
	// 판단할 예정
	// 백퍼는 아님 ㅎ
	private int info_num;
	private String info_News;
	private String info_NewsUp;
	private String info_NewsDown;
	private String info_Expect;

	public News(int info_num, String info_News, String info_NewsUp, String info_NewsDown, String info_Expect) {
		super();
		this.info_num = info_num;
		this.info_News = info_News;
		this.info_NewsUp = info_NewsUp;
		this.info_NewsDown = info_NewsDown;
		this.info_Expect = info_Expect;
	}

	public int getInfo_num() {
		return info_num;
	}

	public void setInfo_num(int info_num) {
		this.info_num = info_num;
	}

	public String getInfo_News() {
		return info_News;
	}

	public void setInfo_News(String info_News) {
		this.info_News = info_News;
	}

	public String getInfo_NewsUp() {
		return info_NewsUp;
	}

	public void setInfo_NewsUp(String info_NewsUp) {
		this.info_NewsUp = info_NewsUp;
	}

	public String getInfo_NewsDown() {
		return info_NewsDown;
	}

	public void setInfo_NewsDown(String info_NewsDown) {
		this.info_NewsDown = info_NewsDown;
	}

	public String getInfo_Expect() {
		return info_Expect;
	}

	public void setInfo_Expect(String info_Expect) {
		this.info_Expect = info_Expect;
	}

	@Override
	public int hashCode() {
		return Objects.hash(info_Expect, info_News, info_NewsDown, info_NewsUp, info_num);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof News)) {
			return false;
		}
		News other = (News) obj;
		return Objects.equals(info_Expect, other.info_Expect) && Objects.equals(info_News, other.info_News)
				&& Objects.equals(info_NewsDown, other.info_NewsDown) && Objects.equals(info_NewsUp, other.info_NewsUp)
				&& info_num == other.info_num;
	}

	@Override
	public String toString() {
		return "News [info_num=" + info_num + ", info_News=" + info_News + ", info_NewsUp=" + info_NewsUp
				+ ", info_NewsDown=" + info_NewsDown + ", info_Expect=" + info_Expect + "]";
	}

}
