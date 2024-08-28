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
import tables.UserInfo;
import tables.UserMoneyHistory;

public class ClickMyInfoBtnPnl extends JPanel {
	UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	UserInfoDAO UserInfoDAO = new UserInfoDAO();
	UserInfo userInfo;
	private List<UserMoneyHistory> userMoneyHistory;
	
	public ClickMyInfoBtnPnl(UserInfo userInfo) {
		this.userInfo = userInfo;
		
		userMoneyHistory = userMoneyHistoryDAO.findByID(userInfo.getUser_ID(),
				userInfo.getUser_SaveData());
		setLayout(new BorderLayout());
		setSize(500, 500);

		// 내 투자, 원금, 총 수익, 총 수익률
		createNorthPnl();

		// 회사 이름, 100주, 53456원, +3456원(6.91%)
		createCenterPnl();

		// 나의 거래 내역 보기 버튼
		createSouthPnl();
	}

	private void createCenterPnl() {
		JPanel pnlCenter = new JPanel();
		add(pnlCenter, "Center");
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JPanel companyInfoPnl1 = makeCompanyInfoPnl("A회사", 100, 53456, +3456, 6.91);
		pnlCenter.add(companyInfoPnl1);
		JPanel companyInfoPnl2 = makeCompanyInfoPnl("B회사", 10, 70000, +20000, 40.00);
		pnlCenter.add(companyInfoPnl2);

	}

	private JPanel makeCompanyInfoPnl(String companyName, int stockCount, int profitAllMoney, int profitMoeny,
			double profitRate) {
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(1, 4));

		JLabel companyNamelbl = makeLbl("");
		companyNamelbl.setText(companyName);
		JLabel stockCountlbl = makeLbl("");
		stockCountlbl.setText(stockCount + "주");
		JLabel profitAllMoneylbl = makeLbl("");
		profitAllMoneylbl.setText(profitAllMoney + "원");
		JLabel profitMoneyAndRatelbl = makeLbl("");
		profitMoneyAndRatelbl.setText(profitMoeny + "원" + "(" + profitRate + "%)");

		pnl.add(companyNamelbl);
		pnl.add(stockCountlbl);
		pnl.add(profitAllMoneylbl);
		pnl.add(profitMoneyAndRatelbl);
		
		pnl.setPreferredSize(new Dimension(450, 40));

		// 굳이 2줄 짜리 라벨 쓰고 싶다면
//		JPanel pnl2 = new JPanel();
//		pnl2.setPreferredSize(new Dimension(100, 40));
//		JLabel lbl = makeLbl("+3456원");
//		lbl.setPreferredSize(new Dimension(100, 20));
//		JLabel lbl2 = makeLbl("6.91%");
//		
//		pnl2.add(lbl);
//		pnl2.add(lbl2);
//		
//		pnl.add(pnl2);

		return pnl;
	}

	private void createSouthPnl() {
		JPanel pnlSouth = new JPanel();
		add(pnlSouth, "South");
		pnlSouth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JButton seeMyTradingHistory = new JButton("나의 거래 내역 보기");
		pnlSouth.add(seeMyTradingHistory);
	}

	private void createNorthPnl() {
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, "North");
		pnlNorth.setLayout(new GridLayout(5, 2));
		pnlNorth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// 현재 잔고
		JLabel principalNamelbl = makeLbl("현재 잔고");
		JLabel principallbl = makeLbl("");
		principallbl.setText(userInfo.getUser_Money() + "원입니다.");
		pnlNorth.add(principalNamelbl);
		pnlNorth.add(principallbl);

		// 현재 원금
		JLabel startMoneyNamelbl = makeLbl("현재 원금");
		JLabel startMoneylbl = makeLbl("");
		startMoneylbl.setText(userMoneyHistory.get(0).getBuyPrice() * userMoneyHistory.get(0).getStock_Count()
				+ userMoneyHistory.get(1).getBuyPrice() * userMoneyHistory.get(1).getStock_Count() + "원");
		pnlNorth.add(startMoneyNamelbl);
		pnlNorth.add(startMoneylbl);

		// 현재 수익(원)
		JLabel profitMoneyNamelbl = makeLbl("총 수익");
		JLabel profitMoneylbl = makeLbl("");
		profitMoneylbl.setText(userMoneyHistory.get(0).getMy_Stock_Money() + userMoneyHistory.get(1).getMy_Stock_Money() + "원");
		pnlNorth.add(profitMoneyNamelbl);
		pnlNorth.add(profitMoneylbl);

		// 현재 수익률(%)
		JLabel profitRateNamelbl = makeLbl("총 수익률");
		JLabel profitRatelbl = makeLbl("");
		double stockMoneyRate = 0;
		if (userMoneyHistory.get(1).getBuyPrice() * userMoneyHistory.get(1).getStock_Count() != 0) {
			stockMoneyRate = (userMoneyHistory.get(0).getMy_Stock_Money() + userMoneyHistory.get(1).getMy_Stock_Money())
					/ (userMoneyHistory.get(0).getBuyPrice() * userMoneyHistory.get(0).getStock_Count()
							+ userMoneyHistory.get(1).getBuyPrice() * userMoneyHistory.get(1).getStock_Count());
		}
		profitRatelbl.setText(stockMoneyRate + "%");
		pnlNorth.add(profitRateNamelbl);
		pnlNorth.add(profitRatelbl);

		// 현재 보유 금액
		JLabel myInvestMoneyNamelbl = makeLbl("총 재산");
		JLabel myInvestMoneylbl = makeLbl("");
		myInvestMoneylbl.setText(userMoneyHistory.get(0).getMy_Stock_Money() * userMoneyHistory.get(0).getStock_Count()
				+ userMoneyHistory.get(1).getMy_Stock_Money() * userMoneyHistory.get(1).getStock_Count() + "원");
		pnlNorth.add(myInvestMoneyNamelbl);
		pnlNorth.add(myInvestMoneylbl);

	}

	private static JLabel makeLbl(String name) {
		JLabel lbl = new JLabel(name);
		lbl.setHorizontalAlignment(JLabel.CENTER);

		return lbl;
	}
}
