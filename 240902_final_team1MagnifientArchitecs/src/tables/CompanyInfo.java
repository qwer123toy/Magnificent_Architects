package tables;

import java.util.Objects;

public class CompanyInfo {
	
	// 회사의 정보를 저장하고 있는 테이블
	// 변동 없음
	//추가만 해줄 예정
	
	private String companyName;
	private String companyInfo;
	private int lastYearSales;
	private String companyCategory;
	private String companyProducts;

	public CompanyInfo(String companyName, String companyInfo, int lastYearSales, String companyCategory,
			String companyProducts) {
		super();
		this.companyName = companyName;
		this.companyInfo = companyInfo;
		this.lastYearSales = lastYearSales;
		this.companyCategory = companyCategory;
		this.companyProducts = companyProducts;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}

	public int getLastYearSales() {
		return lastYearSales;
	}

	public void setLastYearSales(int lastYearSales) {
		this.lastYearSales = lastYearSales;
	}

	public String getCompanyCategory() {
		return companyCategory;
	}

	public void setCompanyCategory(String companyCategory) {
		this.companyCategory = companyCategory;
	}

	public String getCompanyProducts() {
		return companyProducts;
	}

	public void setCompanyProducts(String companyProducts) {
		this.companyProducts = companyProducts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(companyCategory, companyInfo, companyName, companyProducts, lastYearSales);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof CompanyInfo)) {
			return false;
		}
		CompanyInfo other = (CompanyInfo) obj;
		return Objects.equals(companyCategory, other.companyCategory) && Objects.equals(companyInfo, other.companyInfo)
				&& Objects.equals(companyName, other.companyName)
				&& Objects.equals(companyProducts, other.companyProducts) && lastYearSales == other.lastYearSales;
	}

	@Override
	public String toString() {
		return "CompanyInfo [companyName=" + companyName + ", companyInfo=" + companyInfo + ", lastYearSales="
				+ lastYearSales + ", companyCategory=" + companyCategory + ", companyProducts=" + companyProducts + "]";
	}

}
