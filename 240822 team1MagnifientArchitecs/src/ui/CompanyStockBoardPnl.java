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
import otherPnl.CompanyStockPnl;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class CompanyStockBoardPnl extends JPanel {
	private UserInfo userInfo;
	private List<AllCompany> allCompanyList;
	private AllCompanyDAO allCompanyDAO;
	private UserInfoDAO userInfoDAO;
	private UserMoneyHistoryDAO usermoneyHistoryDAO;
	private AllCompanyBackdataDAO allCompanyBackdataDAO;
	private UserInfo userInfoStockFrame;
	private List<UserMoneyHistory> umhStockFrame;
	private List<AllCompanyBackdata> allCompanyBackdataList;
	private JLabel pricipal;
	private JLabel allProfitMoney;
	private JLabel profitRate;
	private JLabel allProperty;
	private int size;
	private CompanyStockPnl[] comapanyInfoPnls;

	public CompanyStockBoardPnl(UserInfo userInfo) {
		this.userInfo = userInfo;

		// 사이즈랑 레이아웃
		setSize(500, 500);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		// 현재 원금, 현재 수익, 현재 수익률, 현재 보유 금액
		setbaseMainPnl();
		updatebaseMainPnl();

		// 회사명, 현재가, 전일대비, 잔여수량 패널
		columnNamePnl();

		// A B C D 회사 정보 표시 패널
		setAllComapanyInfoPnl();
		updateAllComapanyInfoPnl();

	}

	private void columnNamePnl() {
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
	}

	private void setAllComapanyInfoPnl() {
		size = allCompanyDAO.getRowCount(userInfo.getUser_ID(), userInfo.getUser_SaveData());

		comapanyInfoPnls = new CompanyStockPnl[size];
		for (int i = 0; i < comapanyInfoPnls.length; i++) {
			comapanyInfoPnls[i] = new CompanyStockPnl(userInfo, i);
			add(comapanyInfoPnls[i]);
		}

	}

	public void updateAllComapanyInfoPnl() {
		for (int i = 0; i < comapanyInfoPnls.length; i++) {
			comapanyInfoPnls[i].updateTextAll(userInfo);
		}
	}

	public void updatebaseMainPnl() {
		// TODO Auto-generated method stub
		userInfoStockFrame = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		umhStockFrame = usermoneyHistoryDAO.findByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());
		allCompanyBackdataList = allCompanyBackdataDAO.findAllByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());
		allCompanyList = allCompanyDAO.findAllByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());

		// 현재 원금 업데이트
		String pricipalText = "" + umhStockFrame.get(0).getBuyPrice() * umhStockFrame.get(0).getStock_Count()
				+ umhStockFrame.get(1).getBuyPrice() * umhStockFrame.get(1).getStock_Count();
		pricipal.setText("현재 원금: " + pricipalText);

		// 현재 수익 업데이트
		String allProfitMoneyText = "" + umhStockFrame.get(0).getMy_Stock_Money()
				+ umhStockFrame.get(1).getMy_Stock_Money();
		allProfitMoney.setText("현재 수익: " + allProfitMoneyText + "원");

		// 현재 수익률 업데이트
		double stockMoneyRate = 0;
		if (umhStockFrame.get(1).getBuyPrice() * umhStockFrame.get(1).getStock_Count() != 0) {
			stockMoneyRate = (umhStockFrame.get(0).getMy_Stock_Money() + umhStockFrame.get(1).getMy_Stock_Money())
					/ (umhStockFrame.get(0).getBuyPrice() * umhStockFrame.get(0).getStock_Count()
							+ umhStockFrame.get(1).getBuyPrice() * umhStockFrame.get(1).getStock_Count());
		}
		profitRate.setText("현재 수익률: " + stockMoneyRate + "%");

		// 현재 보유 금액 업데이트
		String allPropertyText = "" + umhStockFrame.get(0).getMy_Stock_Money() * umhStockFrame.get(0).getStock_Count()
				+ umhStockFrame.get(1).getMy_Stock_Money() * umhStockFrame.get(1).getStock_Count();
		allProperty.setText("현재 보유 금액: " + allPropertyText + "원");
	}

	private void setbaseMainPnl() {
		userInfoDAO = new UserInfoDAO();
		allCompanyDAO = new AllCompanyDAO();
		usermoneyHistoryDAO = new UserMoneyHistoryDAO();
		allCompanyBackdataDAO = new AllCompanyBackdataDAO();

		JPanel baseMainPnl = new JPanel();
		baseMainPnl.setLayout(new GridLayout(2, 2));
		baseMainPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		baseMainPnl.setPreferredSize(new Dimension(480, 100));

		pricipal = new JLabel();
		pricipal.setHorizontalAlignment(JLabel.CENTER);

		allProfitMoney = new JLabel();
		allProfitMoney.setHorizontalAlignment(JLabel.CENTER);

		profitRate = new JLabel();
		profitRate.setHorizontalAlignment(JLabel.CENTER);

		allProperty = new JLabel();

		allProperty.setHorizontalAlignment(JLabel.CENTER);

		baseMainPnl.add(pricipal);
		baseMainPnl.add(allProfitMoney);
		baseMainPnl.add(profitRate);
		baseMainPnl.add(allProperty);
		add(baseMainPnl);
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
