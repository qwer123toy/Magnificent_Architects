package ui;

import javax.swing.JFrame;

import DAO.UserInfoDAO;
import otherPnl.CompanyStockPnl;
import tables.UserInfo;

public class TestGUI extends JFrame {

	public TestGUI() {
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		UserInfo id = userInfoDAO .findByIDAndData("asd", 1);
//		CompanyStockBoardPnl pnl = new CompanyStockBoardPnl();
//		ClickMyInfoBtnPnl pnl = new ClickMyInfoBtnPnl();
//		SeeMyTradingHistoryPnl pnl = new SeeMyTradingHistoryPnl();
//		NewsPnl pnl = new NewsPnl();
//		GraphAndCompanyInfoPnl pnl = new GraphAndCompanyInfoPnl();
		
//		CompanyStockPnl pnl = new CompanyStockPnl(id);
		
//		add(pnl);
		
		
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TestGUI().setVisible(true);
		
	}
}
