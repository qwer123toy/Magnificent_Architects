package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.StockChangeHistoryDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import priceGUI.BuyPriceGUI;
import priceGUI.SellPriceGUI;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class CompanyStockBoardPnl extends JPanel {
	private UserInfo userInfo;
	private List<AllCompany> allCompanyList;
	private AllCompanyBackdataDAO allCompanyBackdataDAO= new AllCompanyBackdataDAO();
	private UserInfo userInfoStockFrame;
	private List<UserMoneyHistory> umhStockFrame;
	private List<AllCompanyBackdata> allCompanyBackdataList;
	private JLabel pricipal;
	private JLabel allProfitMoney;
	private JLabel profitRate;
	private JLabel allProperty;
	private int size;
	private CompanyStockPnl[] comapanyInfoPnls;
	
	private AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	private UserInfoDAO userInfoDAO = new UserInfoDAO();
	private StockChangeHistoryDAO stockChangeHistoryDAO = new StockChangeHistoryDAO();
	private UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	private CardLayout cardLayout;
	private JPanel pnlCenter;
	private GraphAndCompanyInfoPnl graphAndCompanyInfoPnl;
	private BuyPriceGUI buyPriceGUI;
	private SellPriceGUI sellPriceGUI;

	
	public CompanyStockBoardPnl(UserInfo userInfo, CardLayout cardLayout, JPanel pnlCenter, GraphAndCompanyInfoPnl graphAndCompanyInfoPnl,
			BuyPriceGUI buyPriceGUI, SellPriceGUI sellPriceGUI) {
		this.userInfo = userInfo;
		this.cardLayout = cardLayout;
		this.pnlCenter = pnlCenter;
		this.graphAndCompanyInfoPnl = graphAndCompanyInfoPnl;
		this.buyPriceGUI = buyPriceGUI;
		this.sellPriceGUI = sellPriceGUI;

		// 사이즈랑 레이아웃
		setSize(500, 500);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		// 현재 원금, 현재 수익, 현재 수익률, 현재 보유 금액
		setbaseMainPnl();
		updatebaseMainPnl();

		// 회사명, 현재가, 전일대비, 잔여수량 라벨 이름을 설정
		columnNamePnl();

		// A B C D 회사 정보 표시 패널
		setAllComapanyInfoPnl();
		updateAllComapanyInfoPnl();

	}

	private void columnNamePnl() {
		JPanel pnl2 = new JPanel();
		pnl2.setLayout(new GridLayout(1, 4));

		pnl2.setBackground(Color.CYAN);
		pnl2.setPreferredSize(new Dimension(480, 30));
		pnl2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JLabel lbl1 = new JLabel("회사명");
		lbl1.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbl2 = new JLabel("현재가");
		lbl2.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbl3 = new JLabel("전일대비");
		lbl3.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbl4 = new JLabel("잔여수량");
		lbl4.setHorizontalAlignment(JLabel.CENTER);

		pnl2.add(lbl1);
		pnl2.add(lbl2);
		pnl2.add(lbl3);
		pnl2.add(lbl4);
		add(pnl2);
	}

	private void setAllComapanyInfoPnl() {
		size = allCompanyDAO.getRowCount(userInfo.getUser_ID(), userInfo.getUser_SaveData());

		comapanyInfoPnls = new CompanyStockPnl[size];
		for (int i = 0; i < comapanyInfoPnls.length; i++) {
			comapanyInfoPnls[i] = new CompanyStockPnl(userInfo, i, cardLayout, pnlCenter, graphAndCompanyInfoPnl, buyPriceGUI, sellPriceGUI);
			add(comapanyInfoPnls[i]);
		}

	}

	public void updateAllComapanyInfoPnl() {
		for (int i = 0; i < comapanyInfoPnls.length; i++) {
			comapanyInfoPnls[i].updateTextAll(userInfo);
		}
	}

	public void updatebaseMainPnl() {
		userInfoStockFrame = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		umhStockFrame = userMoneyHistoryDAO.findByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());
		allCompanyBackdataList = allCompanyBackdataDAO.findAllByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());
		allCompanyList = allCompanyDAO.findAllByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());

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
		
		// 현재 원금 업데이트
		pricipal.setText("현재 원금: " + buyPriceAll + "");

		// 현재 수익 업데이트
		allProfitMoney.setText("현재 수익: " + plusMoney + "원");

		// 현재 수익률
		if(buyPriceAll>0) {
			stockMoneyRate = (double)plusMoney/(double)buyPriceAll*100.0;// 총수익률
			stockMoneyRate = (Math.round(stockMoneyRate*100)/100.0);// 소수점 둘째자리까지 계산
		}
		profitRate.setText("현재 수익률: " + stockMoneyRate + "%");

		// 현재 보유 금액 업데이트
		allProperty.setText("평가 금액: " + realMoney + "원");
	}

	private void setbaseMainPnl() {
		JPanel baseMainPnl = new JPanel();
		baseMainPnl.setLayout(new GridLayout(2, 2));
		baseMainPnl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		baseMainPnl.setPreferredSize(new Dimension(480, 100));

		pricipal = new JLabel();
		pricipal.setHorizontalAlignment(JLabel.CENTER);

		allProfitMoney = new JLabel();
		allProfitMoney.setHorizontalAlignment(JLabel.CENTER);

		profitRate = new JLabel();
		profitRate.setHorizontalAlignment(JLabel.CENTER);

		allProperty = new JLabel();
		allProperty.setHorizontalAlignment(JLabel.CENTER);

		baseMainPnl.add(pricipal);
		baseMainPnl.add(allProfitMoney);
		baseMainPnl.add(profitRate);
		baseMainPnl.add(allProperty);
		add(baseMainPnl);
	}

//	private JPanel returnCompnayInfoPnl(String companyName, int priceNow, int comparePrevDay, int stockCount) {
//		JPanel pnl = new JPanel();
//		pnl.setLayout(new GridLayout(1, 4));
//
//		pnl.setBackground(Color.WHITE);
//		pnl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
//		pnl.setPreferredSize(new Dimension(480, 60));
//
//		JLabel companyNameLbl = new JLabel();
//		companyNameLbl.setText(companyName);
//		companyNameLbl.setHorizontalAlignment(JLabel.CENTER);
//
//		JLabel priceNowLbl = new JLabel();
//		priceNowLbl.setText("" + priceNow);
//		priceNowLbl.setHorizontalAlignment(JLabel.CENTER);
//
//		JLabel comparePrevDayLbl = new JLabel();
//		comparePrevDayLbl.setText(comparePrevDay + "원");
//		comparePrevDayLbl.setHorizontalAlignment(JLabel.CENTER);
//
//		JLabel stockCountLbl = new JLabel();
//		stockCountLbl.setText("" + stockCount);
//		stockCountLbl.setHorizontalAlignment(JLabel.CENTER);
//
//		pnl.add(companyNameLbl);
//		pnl.add(priceNowLbl);
//		pnl.add(comparePrevDayLbl);
//		pnl.add(stockCountLbl);
//		return pnl;
//	}
}
