package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.StockChangeHistoryDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.CompanyInfo;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class ClickMyInfoBtnPnl extends JPanel {
	UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	StockChangeHistoryDAO stockChangeHistoryDAO = new StockChangeHistoryDAO();
	UserInfo userInfo;
	private List<UserMoneyHistory> userMoneyHistory;
	private JLabel principallbl;
	private JLabel startMoneylbl;
	private JLabel profitMoneylbl;
	private JLabel profitRatelbl;
	private JLabel myInvestMoneylbl;
	private CompanyInfoPnl companyInfoPnl1;
	private CompanyInfoPnl companyInfoPnl2;
	private CardLayout cardLayout;
	private JPanel pnlCenter;
	private List<CompanyInfoPnl> companyInfoPnlList = new ArrayList<>();

	public ClickMyInfoBtnPnl(UserInfo userInfo, CardLayout cardLayout, JPanel pnlCenter) {
		this.userInfo = userInfo;
		this.cardLayout = cardLayout;
		this.pnlCenter = pnlCenter;

		setLayout(new BorderLayout());
		setSize(500, 500);

		// 내 투자, 원금, 총 수익, 총 수익률
		createNorthPnl();
		updateNorthPnl();

		// 회사 이름, 100주, 53456원, +3456원(6.91%)
		createCenterPnl(userInfo);
		// TODO CompanyInfoPnl 클래스에서 update 작성해야 한다.
		updateCenterPnl(userInfo);

		// 나의 거래 내역 보기 버튼
		createSouthPnl();
	}

	public void updateAll(UserInfo userInfo) {
		updateNorthPnl();
		updateCenterPnl(userInfo);
	}

	// TODO 작성 필요
	public void updateCenterPnl(UserInfo userInfo) {
		UserInfo userInfoUpdate = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<UserMoneyHistory> userMoneyHistoryList = userMoneyHistoryDAO.findByID(userInfoUpdate.getUser_ID(),
				userInfoUpdate.getUser_SaveData());

		for (int i = 0; i < companyInfoPnlList.size(); i++) {
			if (userMoneyHistoryList.get(i).getStock_Count() > 0) {
			companyInfoPnlList.get(i).update(userInfo, i);
			}
		}

//		companyInfoPnl1.update(userInfo,0);
//		companyInfoPnl2.update(userInfo,1);
	}

	private void createCenterPnl(UserInfo userInfo) {
		UserInfo userInfoUpdate = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<UserMoneyHistory> userMoneyHistoryList = userMoneyHistoryDAO.findByID(userInfoUpdate.getUser_ID(),
				userInfoUpdate.getUser_SaveData());

		JPanel pnlCenter = new JPanel();
		add(pnlCenter, "Center");
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// 유저가 갖고 있는 주식 현황 테이블에서 
		for (int i = 0; i < userMoneyHistoryList.size(); i++) {
			if (userMoneyHistoryList.get(i).getStock_Count() > 0) {
				CompanyInfoPnl companyInfoPnl = new CompanyInfoPnl(userInfo, i);
				pnlCenter.add(companyInfoPnl);
				companyInfoPnlList.add(companyInfoPnl);
			}

		}

//		companyInfoPnl1 = new CompanyInfoPnl(userInfo);
//		pnlCenter.add(companyInfoPnl1);
//		companyInfoPnl2 = new CompanyInfoPnl(userInfo);
//		pnlCenter.add(companyInfoPnl2);

	}

	private void createSouthPnl() {
		JPanel pnlSouth = new JPanel();
		add(pnlSouth, "South");
		pnlSouth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JButton seeMyTradingHistory = new JButton("나의 거래 내역 보기");
		pnlSouth.add(seeMyTradingHistory);

		seeMyTradingHistory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlCenter, "seeMyTradingHistoryPnl");
			}
		});
	}

	private void updateNorthPnl() {
		UserInfo userInfoUpdate = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<UserMoneyHistory> userMoneyHistoryList = userMoneyHistoryDAO.findByID(userInfoUpdate.getUser_ID(),
				userInfoUpdate.getUser_SaveData());

		int buyPriceAll = 0;// 원금
		int plusMoney = 0;// 수익
		int realMoney = 0; // 평가금액
		double stockMoneyRate = 0; // 총수익률

		// 전체 원금, 수익금,평가금액,수익률 계산
		for (int i = 0; i < userMoneyHistoryList.size(); i++) {
			buyPriceAll += stockChangeHistoryDAO.findStockMoneyAllBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
					userInfoUpdate.getUser_ID(), userInfoUpdate.getUser_SaveData());
			plusMoney += stockChangeHistoryDAO.findPlusStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
					userInfoUpdate.getUser_ID(), userInfoUpdate.getUser_SaveData());
			realMoney += stockChangeHistoryDAO.findFinalStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
					userInfoUpdate.getUser_ID(), userInfoUpdate.getUser_SaveData());

		}

		if (buyPriceAll > 0) {
			stockMoneyRate = (double) plusMoney / (double) buyPriceAll * 100.0;// 총수익률
			stockMoneyRate = (Math.round(stockMoneyRate * 100) / 100.0);// 소수점 둘째자리까지 계산
		}

//		userMoneyHistory = userMoneyHistoryDAO.findByID(userInfoUpdate.getUser_ID(), userInfoUpdate.getUser_SaveData());

		principallbl.setText(userInfoUpdate.getUser_Money() + "원");

		startMoneylbl.setText(buyPriceAll + "원");

		profitMoneylbl.setText(plusMoney + "원");

		profitRatelbl.setText(stockMoneyRate + "%");

		myInvestMoneylbl.setText(realMoney + "원");
	}

	private void createNorthPnl() {
		// 북쪽 패널 생성
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, "North");
		pnlNorth.setLayout(new GridLayout(5, 2));
		pnlNorth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// 현재 잔고
		JLabel principalNamelbl = makeLbl("현재  통장 잔고");
		principallbl = makeLbl("");

		pnlNorth.add(principalNamelbl);
		pnlNorth.add(principallbl);

		// 현재 원금
		JLabel startMoneyNamelbl = makeLbl("원금");
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
		JLabel myInvestMoneyNamelbl = makeLbl("평가 금액");
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
