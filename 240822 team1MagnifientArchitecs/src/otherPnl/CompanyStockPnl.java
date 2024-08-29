package otherPnl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class CompanyStockPnl extends JPanel {
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	UserMoneyHistoryDAO usermoneyHistoryDAO = new UserMoneyHistoryDAO();
	AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
	private JButton companyNameBtn;
	private JLabel priceNowLbl;
	private JLabel comparePrevDayLbl;
	private JLabel stockCountLbl;
	private int companyIndex;
	
	public CompanyStockPnl(UserInfo userInfo, int companyIndex) {
		this.companyIndex = companyIndex;
		UserInfo userInfoStockFrame =  userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<UserMoneyHistory> umhStockFrame = usermoneyHistoryDAO.findByID(userInfoStockFrame.getUser_ID(), userInfoStockFrame.getUser_SaveData());
		List<AllCompanyBackdata> allCompanyBackdataList = allCompanyBackdataDAO.findAllByID(userInfoStockFrame.getUser_ID(), userInfoStockFrame.getUser_SaveData());
		List<AllCompany> allCompanyList = allCompanyDAO.findAllByID(userInfoStockFrame.getUser_ID(), userInfoStockFrame.getUser_SaveData());
		
		List<AllCompanyBackdata> findACompanyBackdata = new ArrayList<>();
		List<AllCompanyBackdata> findBCompanyBackdata = new ArrayList<>();
		
		// 참고
//		showCompanyData(userInfo, allCompanyList, findACompanyBackdata, 0);
		
		setLayout(new GridLayout(1, 4));

		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setPreferredSize(new Dimension(480, 60));
		
		companyNameBtn = new JButton();
		companyNameBtn.setHorizontalAlignment(JLabel.CENTER);
		companyIndex = 0;
		companyNameBtn.setText(allCompanyList.get(companyIndex).getCompanyName());
		

		priceNowLbl = new JLabel();
		priceNowLbl.setHorizontalAlignment(JLabel.CENTER);
		priceNowLbl.setText(allCompanyList.get(companyIndex).getCompanyStockPrice() + "원");

		comparePrevDayLbl = new JLabel();
		comparePrevDayLbl.setHorizontalAlignment(JLabel.CENTER);

		stockCountLbl = new JLabel();
		stockCountLbl.setHorizontalAlignment(JLabel.CENTER);
		stockCountLbl.setText(allCompanyList.get(companyIndex).getCompanyStockCount() + "주");
		
		add(companyNameBtn);
		add(priceNowLbl);
		add(comparePrevDayLbl);
		add(stockCountLbl);
	}
	
	public void updateTextAll(UserInfo userInfo) {
		UserInfo userInfoStockFrame =  userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<UserMoneyHistory> umhStockFrame = usermoneyHistoryDAO.findByID(userInfoStockFrame.getUser_ID(), userInfoStockFrame.getUser_SaveData());
		List<AllCompanyBackdata> allCompanyBackdataList = allCompanyBackdataDAO.findAllByID(userInfoStockFrame.getUser_ID(), userInfoStockFrame.getUser_SaveData());
		List<AllCompany> allCompanyList = allCompanyDAO.findAllByID(userInfoStockFrame.getUser_ID(), userInfoStockFrame.getUser_SaveData());
		
		List<AllCompanyBackdata> findACompanyBackdata = new ArrayList<>();
		List<AllCompanyBackdata> findBCompanyBackdata = new ArrayList<>();
		
		// 회사 이름 버튼
		companyNameBtn.setText(allCompanyList.get(companyIndex).getCompanyName());
		// 현재가
		priceNowLbl.setText(allCompanyList.get(companyIndex).getCompanyStockPrice() + "원");
		// 전일대비
		comparePrevDayLbl.setText("미완성, 추가 필요");
		// 잔여수량
		stockCountLbl.setText(allCompanyList.get(companyIndex).getCompanyStockCount() + "주");
	}
	
//	private void showCompanyData(UserInfo userInfo, List<AllCompany> allCompanyList,
//			List<AllCompanyBackdata> findCompanyBackdata, int companyIndex) {
//		
//		System.out.printf("회사 이름 : %s\n", allCompanyList.get(companyIndex).getCompanyName());
//		System.out.printf("현재 주가 : %d원\n", allCompanyList.get(companyIndex).getCompanyStockPrice());
//		System.out.printf("현재 주가 수량 : %d 주 \n", allCompanyList.get(companyIndex).getCompanyStockCount());
//
//	}
}
