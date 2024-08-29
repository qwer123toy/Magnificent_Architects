package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import otherPnl.CompanyInfoPnl;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class ClickMyInfoBtnPnl extends JPanel {
	UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	UserInfoDAO UserInfoDAO = new UserInfoDAO();
	UserInfo userInfo;
	private List<UserMoneyHistory> userMoneyHistory;
	private JLabel principallbl;
	private JLabel startMoneylbl;
	private JLabel profitMoneylbl;
	private JLabel profitRatelbl;
	private JLabel myInvestMoneylbl;
	private CompanyInfoPnl companyInfoPnl1;
	private CompanyInfoPnl companyInfoPnl2;

	public ClickMyInfoBtnPnl(UserInfo userInfo) {
		this.userInfo = userInfo;

		setLayout(new BorderLayout());
		setSize(500, 500);

		// 내 투자, 원금, 총 수익, 총 수익률
		createNorthPnl();
		updateNorthPnl();

		// 회사 이름, 100주, 53456원, +3456원(6.91%)
		createCenterPnl();
		// TODO CompanyInfoPnl 클래스에서 update 작성해야 한다.
		updateCenterPnl();

		// 나의 거래 내역 보기 버튼
		createSouthPnl();
	}

	public void updateAll() {
		updateNorthPnl();
		updateCenterPnl();
	}

	// TODO 작성 필요
	public void updateCenterPnl() {
		companyInfoPnl1.update(userInfo);
		companyInfoPnl2.update(userInfo);
	}

	private void createCenterPnl() {
		JPanel pnlCenter = new JPanel();
		add(pnlCenter, "Center");
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		companyInfoPnl1 = new CompanyInfoPnl(userInfo);
		pnlCenter.add(companyInfoPnl1);
		companyInfoPnl2 = new CompanyInfoPnl(userInfo);
		pnlCenter.add(companyInfoPnl2);

	}

	private void createSouthPnl() {
		JPanel pnlSouth = new JPanel();
		add(pnlSouth, "South");
		pnlSouth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JButton seeMyTradingHistory = new JButton("나의 거래 내역 보기");
		pnlSouth.add(seeMyTradingHistory);
	}

	private void updateNorthPnl() {
		userMoneyHistory = userMoneyHistoryDAO.findByID(userInfo.getUser_ID(), userInfo.getUser_SaveData());

		principallbl.setText(userInfo.getUser_Money() + "원입니다.");

		startMoneylbl.setText(userMoneyHistory.get(0).getBuyPrice() * userMoneyHistory.get(0).getStock_Count()
				+ userMoneyHistory.get(1).getBuyPrice() * userMoneyHistory.get(1).getStock_Count() + "원");

		profitMoneylbl.setText(
				userMoneyHistory.get(0).getMy_Stock_Money() + userMoneyHistory.get(1).getMy_Stock_Money() + "원");

		double stockMoneyRate = 0;
		if (userMoneyHistory.get(1).getBuyPrice() * userMoneyHistory.get(1).getStock_Count() != 0) {
			stockMoneyRate = (userMoneyHistory.get(0).getMy_Stock_Money() + userMoneyHistory.get(1).getMy_Stock_Money())
					/ (userMoneyHistory.get(0).getBuyPrice() * userMoneyHistory.get(0).getStock_Count()
							+ userMoneyHistory.get(1).getBuyPrice() * userMoneyHistory.get(1).getStock_Count());
		}
		profitRatelbl.setText(stockMoneyRate + "%");

		myInvestMoneylbl.setText(userMoneyHistory.get(0).getMy_Stock_Money() * userMoneyHistory.get(0).getStock_Count()
				+ userMoneyHistory.get(1).getMy_Stock_Money() * userMoneyHistory.get(1).getStock_Count() + "원");
	}

	private void createNorthPnl() {
		// 북쪽 패널 생성
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, "North");
		pnlNorth.setLayout(new GridLayout(5, 2));
		pnlNorth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// 현재 잔고
		JLabel principalNamelbl = makeLbl("현재 잔고");
		principallbl = makeLbl("");

		pnlNorth.add(principalNamelbl);
		pnlNorth.add(principallbl);

		// 현재 원금
		JLabel startMoneyNamelbl = makeLbl("현재 원금");
		startMoneylbl = makeLbl("");

		pnlNorth.add(startMoneyNamelbl);
		pnlNorth.add(startMoneylbl);

		// 현재 수익(원)
		JLabel profitMoneyNamelbl = makeLbl("총 수익");
		profitMoneylbl = makeLbl("");

		pnlNorth.add(profitMoneyNamelbl);
		pnlNorth.add(profitMoneylbl);

		// 현재 수익률(%)
		JLabel profitRateNamelbl = makeLbl("총 수익률");
		profitRatelbl = makeLbl("");

		pnlNorth.add(profitRateNamelbl);
		pnlNorth.add(profitRatelbl);

		// 현재 보유 금액
		JLabel myInvestMoneyNamelbl = makeLbl("총 재산");
		myInvestMoneylbl = makeLbl("");

		pnlNorth.add(myInvestMoneyNamelbl);
		pnlNorth.add(myInvestMoneylbl);

	}

	private static JLabel makeLbl(String name) {
		JLabel lbl = new JLabel(name);
		lbl.setHorizontalAlignment(JLabel.CENTER);

		return lbl;
	}
}
