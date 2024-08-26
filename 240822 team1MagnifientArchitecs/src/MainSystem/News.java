package MainSystem;

public class News {
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

}
