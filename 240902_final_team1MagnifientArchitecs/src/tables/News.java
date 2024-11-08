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
	private int stockPlus;
	private int stockMinus;
	private String companyCategory;
	
	
	
	public News(int info_num, String info_News, String info_NewsUp, String info_NewsDown, int stockPlus, int stockMinus,
			String companyCategory) {
		super();
		this.info_num = info_num;
		this.info_News = info_News;
		this.info_NewsUp = info_NewsUp;
		this.info_NewsDown = info_NewsDown;
		this.stockPlus = stockPlus;
		this.stockMinus = stockMinus;
		this.companyCategory = companyCategory;
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
	public int getStockPlus() {
		return stockPlus;
	}
	public void setStockPlus(int stockPlus) {
		this.stockPlus = stockPlus;
	}
	public int getStockMinus() {
		return stockMinus;
	}
	public void setStockMinus(int stockMinus) {
		this.stockMinus = stockMinus;
	}
	public String getCompanyCategory() {
		return companyCategory;
	}
	public void setCompanyCategory(String companyCategory) {
		this.companyCategory = companyCategory;
	}

	

}
