package ui;

import java.util.List;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class ListAndDAO {
	public UserInfoDAO userInfoDAO = new UserInfoDAO();
	public AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	public UserMoneyHistoryDAO usermoneyHistoryDAO = new UserMoneyHistoryDAO();
	public AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
	public UserInfo userInfo;
	public List<UserMoneyHistory> userMoneyHistory;
	public List<AllCompanyBackdata> allCompanyBackdataList;
	public List<AllCompany> allCompanyList;
}
