package otherPnl;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TradeHistoryPnl extends JPanel {
	private JLabel daylbl;
	private JLabel companyNamelbl;
	private JLabel buyOrSelllbl;
	private JLabel moneylbl;
	private JLabel countlbl;

	public TradeHistoryPnl() {
		setLayout(new GridLayout(1, 5));
		setPreferredSize(new Dimension(400, 20));

		daylbl = makeLbl("");
		companyNamelbl = makeLbl("");
		buyOrSelllbl = makeLbl("");
		moneylbl = makeLbl("");
		countlbl = makeLbl("");
		
		update();
		
		add(daylbl);
		add(companyNamelbl);
		add(buyOrSelllbl);
		add(moneylbl);
		add(countlbl);

	}
	
	public void update() {
//		daylbl.setText(day + "");
//		companyNamelbl.setText(companyName);
//		buyOrSelllbl.setText(buyOrSell);
//		moneylbl.setText(money + "");
//		countlbl.setText(count + "");
		
		daylbl.setText("일차 미구현" + "");
		companyNamelbl.setText("회사 이름 미구현");
		buyOrSelllbl.setText("매수 매도 미구현");
		moneylbl.setText("금액 미구현" + "");
		countlbl.setText("수량 미구현" + "");
		
	}

	private static JLabel makeLbl(String name) {
		JLabel lbl = new JLabel(name);
		lbl.setHorizontalAlignment(JLabel.CENTER);

		return lbl;
	}
}
