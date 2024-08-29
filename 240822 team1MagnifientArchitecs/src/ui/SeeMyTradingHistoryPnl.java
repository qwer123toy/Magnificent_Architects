package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import otherPnl.TradeHistoryPnl;
import tables.UserInfo;

public class SeeMyTradingHistoryPnl extends JPanel {
	private TradeHistoryPnl tradeHistoryPnl1;
	private TradeHistoryPnl tradeHistoryPnl2;
	private JLabel leftMoneylbl;
	private JLabel myInvestMoneylbl;
	
	private UserInfo userInfo;
	private CardLayout cardLayout;
	private JPanel pnlCenter;

	public SeeMyTradingHistoryPnl(UserInfo userInfo, CardLayout cardLayout, JPanel pnlCenter) {
		this.userInfo = userInfo;
		this.cardLayout = cardLayout;
		this.pnlCenter = pnlCenter;
		
		
		setLayout(new BorderLayout());
		setSize(500, 500);

		// 내 투자, 원금, 총 수익, 총 수익률
		createNorthPnl();
		//TODO 추가 수정 필요
		updateNorthPnal();

		// 회사 이름, 100주, 53456원, +3456원(6.91%)
		createCenterPnl();
		//TODO 추가 수정 필요
		updateCenterPnl();

		// 뒤로가기 버튼
		createSouthPnl();
	}

	//TODO 추가 수정 필요
	public void updateCenterPnl() {
		tradeHistoryPnl1.update();
		tradeHistoryPnl2.update();
	}

	private void createCenterPnl() {
		JPanel pnlCenter = new JPanel();
		add(pnlCenter, "Center");
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		tradeHistoryPnl1 = new TradeHistoryPnl();
		pnlCenter.add(tradeHistoryPnl1);
		tradeHistoryPnl2 = new TradeHistoryPnl();
		pnlCenter.add(tradeHistoryPnl2);

	}

	public void updateAll(UserInfo userinfo) {
		updateNorthPnal();
		updateCenterPnl();
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
	private void updateNorthPnal() {
		myInvestMoneylbl.setText("123,456원 (23.46%)");
		leftMoneylbl.setText("30000원");
	}

	private void createNorthPnl() {
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, "North");
		pnlNorth.setLayout(new GridLayout(2, 2));
		pnlNorth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JLabel myInvestMoneyNamelbl = makeLbl("내 투자");
		myInvestMoneylbl = makeLbl("");
		

		JLabel leftMoneyNamelbl = makeLbl("잔고");
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
