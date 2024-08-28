package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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

public class CompanyStockBoardPnl extends JPanel {
	private UserInfo userInfo;
	private List<AllCompany> allCompanyList;
	private AllCompanyDAO allCompanyDAO;

	public CompanyStockBoardPnl(UserInfo userInfo) {
		this.userInfo = userInfo;
		
		// 사이즈랑 레이아웃
		setSize(500, 500);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		// 총 매수, 평가손익, 총 평가, 수익률 패널
		setPnl1();

		// 회사명, 현재가, 전일대비, 잔여수량 패널
		JPanel pnl2 = new JPanel();
		pnl2.setLayout(new GridLayout(1, 4));

		pnl2.setBackground(Color.CYAN);
		pnl2.setPreferredSize(new Dimension(480, 30));
		pnl2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JLabel lbl1 = new JLabel("회사명");
		lbl1.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbl2 = new JLabel("현재가");
		lbl2.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbl3 = new JLabel("전일대비");
		lbl3.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbl4 = new JLabel("잔여수량");
		lbl4.setHorizontalAlignment(JLabel.CENTER);

		pnl2.add(lbl1);
		pnl2.add(lbl2);
		pnl2.add(lbl3);
		pnl2.add(lbl4);
		add(pnl2);

		// A B C D 회사 정보 표시 패널
//		setAllComapnyInfoPnl();

	}

	private void setAllComapnyInfoPnl() {
		List<AllCompanyBackdata> findACompanyBackdata = new ArrayList<>();
		List<AllCompanyBackdata> findBCompanyBackdata = new ArrayList<>();

		int size = allCompanyDAO.getRowCount(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		JPanel[] companyInfoArray = new JPanel[size];
		for (int i = 0; i < companyInfoArray.length; i++) {
			JPanel companyInfoPnl = companyInfoData(userInfo, allCompanyList, findACompanyBackdata, i);
			add(companyInfoPnl);
		}
	}

	private JPanel companyInfoData(UserInfo userInfo, List<AllCompany> allCompanyList,
			List<AllCompanyBackdata> findCompanyBackdata, int companyIndex) {

		String companyName = allCompanyList.get(companyIndex).getCompanyName();
		int priceNow = allCompanyList.get(companyIndex).getCompanyStockPrice();
		int stockCount = allCompanyList.get(companyIndex).getCompanyStockCount();
		int comparePrevDay;

		if (allCompanyList.get(userInfo.getUser_Date() - 1).getDate() == 1) {
			comparePrevDay = 0;
		} else {
			comparePrevDay = findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStockPrice()
					- findCompanyBackdata.get(userInfo.getUser_Date() - 2).getCompanyStockPrice();
		}
		JPanel pnl = returnCompnayInfoPnl(companyName, priceNow, comparePrevDay, stockCount);
		return pnl;
	}

	private void setPnl1() {
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		allCompanyDAO = new AllCompanyDAO();
		UserMoneyHistoryDAO usermoneyHistoryDAO = new UserMoneyHistoryDAO();
		AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();

		UserInfo userInfoStockFrame = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<UserMoneyHistory> umhStockFrame = usermoneyHistoryDAO.findByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());
		List<AllCompanyBackdata> allCompanyBackdataList = allCompanyBackdataDAO
				.findAllByID(userInfoStockFrame.getUser_ID(), userInfoStockFrame.getUser_SaveData());
		allCompanyList = allCompanyDAO.findAllByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());

		JPanel pnl1 = new JPanel();
		pnl1.setLayout(new GridLayout(2, 2));
		pnl1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		pnl1.setPreferredSize(new Dimension(480, 100));

		JLabel pricipal = new JLabel(); // 원금
		String pricipalText = "" + umhStockFrame.get(0).getBuyPrice() * umhStockFrame.get(0).getStock_Count()
				+ umhStockFrame.get(1).getBuyPrice() * umhStockFrame.get(1).getStock_Count();
		pricipal.setText("총매수: " + pricipalText);
		pricipal.setHorizontalAlignment(JLabel.CENTER);

		JLabel allProfitMoney = new JLabel(); // 수익
		String allProfitMoneyText = "" + umhStockFrame.get(0).getMy_Stock_Money()
				+ umhStockFrame.get(1).getMy_Stock_Money();
		allProfitMoney.setText("평가손익: " + allProfitMoneyText + "원");
		allProfitMoney.setHorizontalAlignment(JLabel.CENTER);

		JLabel profitRate = new JLabel(); // 수익률
		double stockMoneyRate = 0;
		if (umhStockFrame.get(1).getBuyPrice() * umhStockFrame.get(1).getStock_Count() != 0) {
			stockMoneyRate = (umhStockFrame.get(0).getMy_Stock_Money() + umhStockFrame.get(1).getMy_Stock_Money())
					/ (umhStockFrame.get(0).getBuyPrice() * umhStockFrame.get(0).getStock_Count()
							+ umhStockFrame.get(1).getBuyPrice() * umhStockFrame.get(1).getStock_Count());
		}
		profitRate.setText("수익률: " + stockMoneyRate + "%");
		profitRate.setHorizontalAlignment(JLabel.CENTER);

		JLabel allProperty = new JLabel(); // 주식 + 현금 = 총 재산
		String allPropertyText = "" + umhStockFrame.get(0).getMy_Stock_Money() * umhStockFrame.get(0).getStock_Count()
				+ umhStockFrame.get(1).getMy_Stock_Money() * umhStockFrame.get(1).getStock_Count();
		allProperty.setText("총 평가: " + allPropertyText + "원");
		allProperty.setHorizontalAlignment(JLabel.CENTER);

		pnl1.add(pricipal);
		pnl1.add(allProfitMoney);
		pnl1.add(profitRate);
		pnl1.add(allProperty);
		add(pnl1);
	}

	private JPanel returnCompnayInfoPnl(String companyName, int priceNow, int comparePrevDay, int stockCount) {
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(1, 4));

		pnl.setBackground(Color.WHITE);
		pnl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		pnl.setPreferredSize(new Dimension(480, 60));

		JLabel companyNameLbl = new JLabel();
		companyNameLbl.setText(companyName);
		companyNameLbl.setHorizontalAlignment(JLabel.CENTER);

		JLabel priceNowLbl = new JLabel();
		priceNowLbl.setText("" + priceNow);
		priceNowLbl.setHorizontalAlignment(JLabel.CENTER);

		JLabel comparePrevDayLbl = new JLabel();
		comparePrevDayLbl.setText(comparePrevDay + "원");
		comparePrevDayLbl.setHorizontalAlignment(JLabel.CENTER);

		JLabel stockCountLbl = new JLabel();
		stockCountLbl.setText("" + stockCount);
		stockCountLbl.setHorizontalAlignment(JLabel.CENTER);

		pnl.add(companyNameLbl);
		pnl.add(priceNowLbl);
		pnl.add(comparePrevDayLbl);
		pnl.add(stockCountLbl);
		return pnl;
	}
}
