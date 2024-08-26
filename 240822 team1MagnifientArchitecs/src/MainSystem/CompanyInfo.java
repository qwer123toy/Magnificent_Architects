package MainSystem;

public class CompanyInfo {
	private String companyName;
	private String companyInfo;
	private String companyCategory;
	private String companyProducts;

	public CompanyInfo(String companyName, String companyInfo, String companyCategory, String companyProducts) {
		super();
		this.companyName = companyName;
		this.companyInfo = companyInfo;
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

}
