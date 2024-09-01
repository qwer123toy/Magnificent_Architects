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
import tables.StockChangeHistory;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class SeeMyTradingHistoryPnl extends JPanel {
	private TradeHistoryPnl tradeHistoryPnl1;
	private TradeHistoryPnl tradeHistoryPnl2;
	private JLabel leftMoneylbl;
	private JLabel myInvestMoneylbl;
	
	UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	StockChangeHistoryDAO stockChangeHistoryDAO = new StockChangeHistoryDAO();
	
	private UserInfo userInfo;
	private CardLayout cardLayout;
	private JPanel pnlCenter;
	private List<TradeHistoryPnl> tradeHistoryPnlList = new ArrayList<>();
	
	public SeeMyTradingHistoryPnl(UserInfo userInfo, CardLayout cardLayout, JPanel pnlCenter) {
		UserInfo userInfoTrading = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		this.cardLayout = cardLayout;
		this.pnlCenter = pnlCenter;
		
		setLayout(new BorderLayout());
		setSize(500, 500);

		// 내 투자, 원금, 총 수익, 총 수익률
		createNorthPnl();
		//TODO 추가 수정 필요
		updateNorthPnal(userInfoTrading);

		// 회사 이름, 100주, 53456원, +3456원(6.91%)
		createCenterPnl(userInfoTrading);
		//TODO 추가 수정 필요
		updateCenterPnl(userInfoTrading);

		// 뒤로가기 버튼
		createSouthPnl();
	}

	//TODO 추가 수정 필요
	public void updateCenterPnl(UserInfo userInfo) {
		UserInfo userInfoUpdate = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<StockChangeHistory> stockChangeHistoryList = stockChangeHistoryDAO.findByID(userInfoUpdate.getUser_ID(), userInfoUpdate.getUser_SaveData());
		System.out.println(stockChangeHistoryList.size());	
		
		for(int i=0; i<tradeHistoryPnlList.size(); i++) {
			tradeHistoryPnlList.get(i).update(userInfoUpdate, i);
			
		}
		
//		tradeHistoryPnl1.update(userInfoUpdate);
//		tradeHistoryPnl2.update(userInfoUpdate);
	}

	private void createCenterPnl(UserInfo userInfo) {
		UserInfo userInfoCreate = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<StockChangeHistory> stockChangeHistoryList = stockChangeHistoryDAO.findByID(userInfoCreate.getUser_ID(), userInfoCreate.getUser_SaveData());
		
		JPanel pnlCenter = new JPanel();
		add(pnlCenter, "Center");
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		for(int i=0; i<stockChangeHistoryList.size(); i++) {
			TradeHistoryPnl tradeHistoryPnl = new TradeHistoryPnl(userInfoCreate,i);
			tradeHistoryPnlList.add(tradeHistoryPnl);
			pnlCenter.add(tradeHistoryPnl);

		}
		
		
//		pnlCenter.add(tradeHistoryPnl1);
//		tradeHistoryPnl2 = new TradeHistoryPnl(userInfoCreate);
//		pnlCenter.add(tradeHistoryPnl2);

	}

	public void updateAll(UserInfo userInfo) {
		updateNorthPnal(userInfo);
		updateCenterPnl(userInfo);
	}

	private void createSouthPnl() {
		JPanel pnlSouth = new JPanel();
		add(pnlSouth, "South");
		pnlSouth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		pnlSouth.setLayout(new BorderLayout());
		pnlSouth.setPreferredSize(new Dimension(500, 50));

		JPanel pnlEast = new JPanel();
		pnlSouth.add(pnlEast, "East");

		JButton prevBtn = new JButton("뒤로가기");
		prevBtn.setPreferredSize(new Dimension(100, 35));
		prevBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlCenter, "clickMyInfoBtnPnl");
			}
		});
		
		pnlEast.add(prevBtn);

	}

	// TODO 미구현 상태
	private void updateNorthPnal(UserInfo userInfo) {
		UserInfo userInfoUpdate = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<UserMoneyHistory> userMoneyHistoryList = userMoneyHistoryDAO.findByID(userInfoUpdate.getUser_ID(),
				userInfoUpdate.getUser_SaveData());
		
		int buyPriceAll = 0;// 원금
		int plusMoney = 0;// 수익
		int realMoney = 0; // 평가금액
		double stockMoneyRate = 0; // 총수익률
		
		//전체 원금, 수익금,평가금액,수익률 계산
		for (int i = 0; i < userMoneyHistoryList.size(); i++) {
			buyPriceAll += stockChangeHistoryDAO.findStockMoneyAllBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
					userInfoUpdate.getUser_ID(), userInfoUpdate.getUser_SaveData());
			plusMoney += stockChangeHistoryDAO.findPlusStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
					userInfoUpdate.getUser_ID(), userInfoUpdate.getUser_SaveData());
			realMoney += stockChangeHistoryDAO.findFinalStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
					userInfoUpdate.getUser_ID(), userInfoUpdate.getUser_SaveData());
			
		}
		
		if(buyPriceAll>0) {
			stockMoneyRate = (double)plusMoney/(double)buyPriceAll*100.0;// 총수익률
			stockMoneyRate = (Math.round(stockMoneyRate*100)/100.0);// 소수점 둘째자리까지 계산
		}
		myInvestMoneylbl.setText(realMoney + "원 (" + stockMoneyRate + "%)");
		leftMoneylbl.setText(userInfoUpdate.getUser_Money()+"원");
	}

	private void createNorthPnl() {
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, "North");
		pnlNorth.setLayout(new GridLayout(2, 2));
		pnlNorth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JLabel myInvestMoneyNamelbl = makeLbl("내 투자");
		myInvestMoneylbl = makeLbl("");
		

		JLabel leftMoneyNamelbl = makeLbl("통장 잔고");
		leftMoneylbl = makeLbl("");
		

		pnlNorth.add(myInvestMoneyNamelbl);
		pnlNorth.add(leftMoneyNamelbl);
		pnlNorth.add(myInvestMoneylbl);
		pnlNorth.add(leftMoneylbl);
	}

	private static JLabel makeLbl(String name) {
		JLabel lbl = new JLabel(name);
		lbl.setHorizontalAlignment(JLabel.CENTER);

		return lbl;
	}
}
