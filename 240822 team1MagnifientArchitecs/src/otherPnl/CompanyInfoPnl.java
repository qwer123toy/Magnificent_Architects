package otherPnl;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tables.UserInfo;

public class CompanyInfoPnl extends JPanel {
	private JLabel companyNamelbl;
	private JLabel stockCountlbl;
	private JLabel profitAllMoneylbl;
	private JLabel profitMoneyAndRatelbl;

	public CompanyInfoPnl(UserInfo userInfo) {
		setLayout(new GridLayout(1, 4));

		companyNamelbl = makeLbl("");
		stockCountlbl = makeLbl("");
		profitAllMoneylbl = makeLbl("");
		profitMoneyAndRatelbl = makeLbl("");

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

		add(companyNamelbl);
		add(stockCountlbl);
		add(profitAllMoneylbl);
		add(profitMoneyAndRatelbl);

		update(userInfo);

		setPreferredSize(new Dimension(450, 40));
	}

	public void update(UserInfo userInfo) {
//		String companyName, int stockCount, int profitAllMoney, int profitMoeny,
//		double profitRate

		companyNamelbl.setText("미구현");
		stockCountlbl.setText("미구현" + "주");
		profitAllMoneylbl.setText("미구현" + "원");
		profitMoneyAndRatelbl.setText("미구현" + "원" + "(" + "미구현" + "%)");

	}

	private static JLabel makeLbl(String name) {
		JLabel lbl = new JLabel(name);
		lbl.setHorizontalAlignment(JLabel.CENTER);

		return lbl;
	}
}
