package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import priceGUI.BuyPriceGUI;
import priceGUI.SellPriceGUI;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class CompanyStockPnl extends JPanel {
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	UserMoneyHistoryDAO usermoneyHistoryDAO = new UserMoneyHistoryDAO();
	AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
	private JButton companyNameBtn;
	private JLabel priceNowLbl;
	private JLabel comparePrevDayLbl;
	private JLabel stockCountLbl;
	private int companyIndex;
	private int changeStockPrice;
	private CardLayout cardLayout;
	private JPanel pnlCenter;
	private GraphAndCompanyInfoPnl graphAndCompanyInfoPnl;
	private BuyPriceGUI buyPriceGUI;
	private SellPriceGUI sellPriceGUI;

	public CompanyStockPnl(UserInfo userInfo, int companyIndex, CardLayout cardLayout, JPanel pnlCenter,
			GraphAndCompanyInfoPnl graphAndCompanyInfoPnl, BuyPriceGUI buyPriceGUI, SellPriceGUI sellPriceGUI) {
		this.companyIndex = companyIndex;
		this.cardLayout = cardLayout;
		this.pnlCenter = pnlCenter;
		this.graphAndCompanyInfoPnl = graphAndCompanyInfoPnl;
		this.buyPriceGUI = buyPriceGUI;
		this.sellPriceGUI = sellPriceGUI;

		UserInfo userInfoStockFrame = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<UserMoneyHistory> umhStockFrame = usermoneyHistoryDAO.findByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());
		List<AllCompanyBackdata> allCompanyBackdataList = allCompanyBackdataDAO
				.findAllByID(userInfoStockFrame.getUser_ID(), userInfoStockFrame.getUser_SaveData());
		List<AllCompany> allCompanyList = allCompanyDAO.findAllByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());


		// 참고
//		showCompanyData(userInfo, allCompanyList, findACompanyBackdata, 0);

		setLayout(new GridLayout(1, 4));

		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setPreferredSize(new Dimension(480, 60));

		// 회사 이름 버튼
		companyNameBtn = new JButton();
		companyNameBtn.setHorizontalAlignment(JLabel.CENTER);
//		companyIndex = 0;
		companyNameBtn.setText(allCompanyList.get(companyIndex).getCompanyName());

		// 현재가
		priceNowLbl = new JLabel();
		priceNowLbl.setHorizontalAlignment(JLabel.CENTER);
		priceNowLbl.setText(allCompanyList.get(companyIndex).getCompanyStockPrice() + "원");

		// 전일대비
		comparePrevDayLbl = new JLabel();
		comparePrevDayLbl.setHorizontalAlignment(JLabel.CENTER);

		// 잔여수량
		stockCountLbl = new JLabel();
		stockCountLbl.setHorizontalAlignment(JLabel.CENTER);
		stockCountLbl.setText(allCompanyList.get(companyIndex).getCompanyStockCount() + "주");

		add(companyNameBtn);
		add(priceNowLbl);
		add(comparePrevDayLbl);
		add(stockCountLbl);
	}

	public void updateTextAll(UserInfo userInfo) {
		UserInfo userInfoStockFrame = userInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		List<UserMoneyHistory> umhStockFrame = usermoneyHistoryDAO.findByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());
		List<AllCompanyBackdata> allCompanyBackdataList = allCompanyBackdataDAO
				.findAllByID(userInfoStockFrame.getUser_ID(), userInfoStockFrame.getUser_SaveData());
		List<AllCompany> allCompanyList = allCompanyDAO.findAllByID(userInfoStockFrame.getUser_ID(),
				userInfoStockFrame.getUser_SaveData());

		String companyName = allCompanyList.get(companyIndex).getCompanyName();
		AllCompany allCompany = allCompanyDAO.findCompByID(companyName, userInfo.getUser_ID(),
				userInfo.getUser_SaveData());

		// 회사 이름 버튼
		companyNameBtn.setText(allCompanyList.get(companyIndex).getCompanyName());
		
		companyNameBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				graphAndCompanyInfoPnl.updateAll(companyName, userInfo);
				buyPriceGUI.updateComInfo(userInfo, companyName);
				sellPriceGUI.updateComInfo(userInfo, companyName);
				cardLayout.show(pnlCenter, "graphAndCompanyInfoPnl");
				
//				CardLayout cl = graphAndCompanyInfoPnl.getGandIcardLayout();
//				JPanel pnl = graphAndCompanyInfoPnl.getGandIpnlCenter();
//				cl.show(pnl, "graphpnl");
			}
		});

		// 현재가
		priceNowLbl.setText(allCompanyList.get(companyIndex).getCompanyStockPrice() + "원");

		// 전일대비
		if (userInfo.getUser_Date() == 1) {
			changeStockPrice = 0;
//			System.out.printf("전일 대비  0원  \n");
		} else {
			int today = userInfo.getUser_Date();
			int yesterday = today - 1;

			AllCompanyBackdata acbdYesterday = allCompanyBackdataDAO.findCompanyByDate(companyName, yesterday,
					userInfo.getUser_ID(), userInfo.getUser_SaveData());

			int todaysStockPrice = allCompany.getCompanyStockPrice();
			int yesterdayStockPrice = acbdYesterday.getCompanyStockPrice();
			changeStockPrice = todaysStockPrice - yesterdayStockPrice;
		}
		if (changeStockPrice > 0) {
			comparePrevDayLbl.setText("+" + changeStockPrice + "원");
		} else {
			comparePrevDayLbl.setText(changeStockPrice + "원");
		}

		// 잔여수량
		stockCountLbl.setText(allCompanyList.get(companyIndex).getCompanyStockCount() + "주");
	}

}
