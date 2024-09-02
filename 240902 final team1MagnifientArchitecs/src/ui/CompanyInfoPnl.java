package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.StockChangeHistoryDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class CompanyInfoPnl extends JPanel {
	private JLabel companyNamelbl;
	private JLabel stockCountlbl;
	private JLabel profitAllMoneylbl;
	private JLabel profitMoneyAndRatelbl;
	UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	UserInfoDAO UserInfoDAO = new UserInfoDAO();
	StockChangeHistoryDAO stockChangeHistoryDAO = new StockChangeHistoryDAO();
	
	public CompanyInfoPnl(UserInfo userInfo, int companyIndex) {
		UserInfo userInfoUpdate = UserInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<UserMoneyHistory> userMoneyHistoryList = userMoneyHistoryDAO.findByID(userInfoUpdate.getUser_ID(),
				userInfoUpdate.getUser_SaveData());
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

		update(userInfo, companyIndex);

		setPreferredSize(new Dimension(450, 40));
	}

	public void update(UserInfo userInfo, int companyIndex) {
		
//		String companyName, int stockCount, int profitAllMoney, int profitMoeny,
//		double profitRate
		UserInfo userInfoUpdate = UserInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<UserMoneyHistory> userMoneyHistoryList = userMoneyHistoryDAO.findByID(userInfoUpdate.getUser_ID(),
				userInfoUpdate.getUser_SaveData());
		
		double stockMoneyRate = stockChangeHistoryDAO.findFinalStockMoneyRateNowBycompName(
				userMoneyHistoryList.get(companyIndex).getUser_Stock(), userInfo.getUser_ID(),
				userInfo.getUser_SaveData()) * 100;
		stockMoneyRate = (Math.round(stockMoneyRate*100)/100.0);
		companyNamelbl.setText(userMoneyHistoryList.get(companyIndex).getUser_Stock());
		
		stockCountlbl.setText(userMoneyHistoryList.get(companyIndex).getStock_Count() + "주");
		profitAllMoneylbl.setText(stockChangeHistoryDAO.findFinalStockMoneyNowBycompName(userMoneyHistoryList.get(companyIndex).getUser_Stock(),
				userInfo.getUser_ID(), userInfo.getUser_SaveData()) + "원");
		profitMoneyAndRatelbl.setText(stockChangeHistoryDAO.findPlusStockMoneyNowBycompName(userMoneyHistoryList.get(companyIndex).getUser_Stock(),
				userInfo.getUser_ID(), userInfo.getUser_SaveData()) + "원" 
				+ "(" + stockMoneyRate + "%)");

	}

	private static JLabel makeLbl(String name) {
		JLabel lbl = new JLabel(name);
		lbl.setHorizontalAlignment(JLabel.CENTER);

		return lbl;
	}
}
