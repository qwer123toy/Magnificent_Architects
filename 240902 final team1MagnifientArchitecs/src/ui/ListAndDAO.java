package ui;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.CompanyInfoDAO;
import DAO.NewsDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;

public class ListAndDAO {
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	UserMoneyHistoryDAO usermoneyHistoryDAO = new UserMoneyHistoryDAO();
	AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
	NewsDAO newsDAO = new NewsDAO();
	CompanyInfoDAO companyDAO = new CompanyInfoDAO();		
	
}
