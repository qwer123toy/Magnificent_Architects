package ui;

import javax.swing.JFrame;

public class TestGUI extends JFrame {
	public TestGUI() {
//		CompanyStockBoardPnl pnl = new CompanyStockBoardPnl();
//		ClickMyInfoBtnPnl pnl = new ClickMyInfoBtnPnl();
//		SeeMyTradingHistoryPnl pnl = new SeeMyTradingHistoryPnl();
//		NewsPnl pnl = new NewsPnl();
		ImageNewsPnl pnl = new ImageNewsPnl("S&P, '연준, 드라마틱한 변화 필요성 없어 내년 동결");
		
		add(pnl);
		
		
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TestGUI().setVisible(true);
	}
}
