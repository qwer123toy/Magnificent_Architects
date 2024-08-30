package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.StockChangeHistoryDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.StockChangeHistory;
import tables.UserInfo;

public class TradeHistoryPnl extends JPanel {
	private JLabel daylbl;
	private JLabel companyNamelbl;
	private JLabel buyOrSelllbl;
	private JLabel moneylbl;
	private JLabel countlbl;
	private UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	private UserInfoDAO userInfoDAO = new UserInfoDAO();
	private StockChangeHistoryDAO stockChangeHistoryDAO = new StockChangeHistoryDAO();
	
	public TradeHistoryPnl(UserInfo userInfo, int count) {
		UserInfo userInfoCreate = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());

		setLayout(new GridLayout(1, 5));
		setPreferredSize(new Dimension(400, 20));

		daylbl = makeLbl("");
		companyNamelbl = makeLbl("");
		buyOrSelllbl = makeLbl("");
		moneylbl = makeLbl("");
		countlbl = makeLbl("");
		
		update(userInfoCreate, count);
		
		add(daylbl);
		add(companyNamelbl);
		add(buyOrSelllbl);
		add(moneylbl);
		add(countlbl);

	}
	
	public void update(UserInfo userInfo, int count) {
		UserInfo userInfoUpdate = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<StockChangeHistory> stockChangeHistoryList = stockChangeHistoryDAO.findByID(userInfoUpdate.getUser_ID(), userInfoUpdate.getUser_SaveData());
		String companyName = stockChangeHistoryList.get(count).getCompanyName();
		
		
		daylbl.setText(stockChangeHistoryList.get(count).getDate() + "일차" + "");
		companyNamelbl.setText(companyName);
		
		String chkBuyOrSell = "매수";
		int stockPrice = 0;
		int stockCount=0;
		if(stockChangeHistoryList.get(count).getBuyStockCount() ==0) {
			chkBuyOrSell = "매도";
			stockPrice = stockChangeHistoryList.get(count).getSellStockPrice();
			stockCount = stockChangeHistoryList.get(count).getSellStockCount();
			
		}
		else {
			stockPrice = stockChangeHistoryList.get(count).getBuyStockPrice();
			stockCount = stockChangeHistoryList.get(count).getBuyStockCount();
			
		}
		buyOrSelllbl.setText(chkBuyOrSell);
		moneylbl.setText(stockPrice+ "원");
		countlbl.setText(stockCount+ "주");
		
		
	}

	private static JLabel makeLbl(String name) {
		JLabel lbl = new JLabel(name);
		lbl.setHorizontalAlignment(JLabel.CENTER);

		return lbl;
	}
}
