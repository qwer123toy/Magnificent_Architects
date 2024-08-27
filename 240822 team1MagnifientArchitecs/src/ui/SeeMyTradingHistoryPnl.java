package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeeMyTradingHistoryPnl extends JPanel {
	public SeeMyTradingHistoryPnl() {
		setLayout(new BorderLayout());
		setSize(500, 500);

		// 내 투자, 원금, 총 수익, 총 수익률
		createNorthPnl();

		// 회사 이름, 100주, 53456원, +3456원(6.91%)
		createCenterPnl();

		// 뒤로가기 버튼
		createSouthPnl();
	}

	private void createCenterPnl() {
		JPanel pnlCenter = new JPanel();
		add(pnlCenter, "Center");
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JPanel tradeHistoryPnl1 = makeTradeHistoryPnl(1, "A회사", "매수", 100, 3);
		pnlCenter.add(tradeHistoryPnl1);
		JPanel tradeHistoryPnl2 = makeTradeHistoryPnl(2, "B회사", "매수", 150, 12);
		pnlCenter.add(tradeHistoryPnl2);
		JPanel tradeHistoryPnl3 = makeTradeHistoryPnl(3, "B회사", "매도", 160, 12);
		pnlCenter.add(tradeHistoryPnl3);

	}

	private JPanel makeTradeHistoryPnl(int day, String companyName, String buyOrSell, int money, int count) {
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(1, 5));
		pnl.setPreferredSize(new Dimension(400, 20));

		JLabel daylbl = makeLbl("");
		daylbl.setText(day + "");
		JLabel companyNamelbl = makeLbl("");
		companyNamelbl.setText(companyName);
		JLabel buyOrSelllbl = makeLbl("");
		buyOrSelllbl.setText(buyOrSell);
		JLabel moneylbl = makeLbl("");
		moneylbl.setText(money + "");
		JLabel countlbl = makeLbl("");
		countlbl.setText(count + "");

		pnl.add(daylbl);
		pnl.add(companyNamelbl);
		pnl.add(buyOrSelllbl);
		pnl.add(moneylbl);
		pnl.add(countlbl);

		return pnl;
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
		pnlEast.add(prevBtn);

	}

	private void createNorthPnl() {
		JPanel pnlNorth = new JPanel();
		add(pnlNorth, "North");
		pnlNorth.setLayout(new GridLayout(2, 2));
		pnlNorth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JLabel myInvestMoneyNamelbl = makeLbl("내 투자");
		JLabel myInvestMoneylbl = makeLbl("");
		myInvestMoneylbl.setText("123,456원 (23.46%)");

		JLabel leftMoneyNamelbl = makeLbl("잔고");
		JLabel leftMoneylbl = makeLbl("");
		leftMoneylbl.setText("30000원");

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
