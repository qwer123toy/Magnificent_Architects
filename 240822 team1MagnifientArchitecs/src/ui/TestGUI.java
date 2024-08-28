package ui;

import javax.swing.JFrame;

public class TestGUI extends JFrame {

	public TestGUI() {
//		CompanyStockBoardPnl pnl = new CompanyStockBoardPnl();
//		ClickMyInfoBtnPnl pnl = new ClickMyInfoBtnPnl();
//		SeeMyTradingHistoryPnl pnl = new SeeMyTradingHistoryPnl();
//		NewsPnl pnl = new NewsPnl();
		GraphAndCompanyInfoPnl pnl = new GraphAndCompanyInfoPnl();
		
		add(pnl);
		
		
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TestGUI().setVisible(true);
		
	}
}
