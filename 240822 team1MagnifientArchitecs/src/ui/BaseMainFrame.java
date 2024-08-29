package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAO.UserInfoDAO;
import priceGUI.BuyPriceGUI;
import priceGUI.SellPriceGUI;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class BaseMainFrame extends JFrame implements ActionListener {
	private CardLayout cardLayout;
	private JPanel pnlCenter;
	private UserInfo userInfo;
	private List<UserMoneyHistory> userMoneyHistoryList;
	private AllCompany allCompany;
	private AllCompanyBackdata allCompanyBackdata;

	private static ListAndDAO listAndDAO = new ListAndDAO();
	private CompanyStockBoardPnl companyStockBoardPnl;
	private ClickMyInfoBtnPnl clickMyInfoBtnPnl;
	private SeeMyTradingHistoryPnl seeMyTradingHistoryPnl;
	private JPanel contentPane;

	public BaseMainFrame(UserInfo userInfo) {
		this.userInfo = userInfo;

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());

		// 북쪽 패널
		setPnlNorth();

		// 센터에 설정할 pnl들
		setPnlCenter();

		// 남쪽 패널
		setPnlSouth();

		// 가장 큰 패널(contentPane) 설정
		setSize(500, 650);
		add(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void setPnlSouth() {
		JPanel pnlSouth = new JPanel();
		pnlSouth.setPreferredSize(new Dimension(500, 100));
		contentPane.add(pnlSouth, "South");
		pnlSouth.setBackground(Color.BLACK);
		pnlSouth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// 버튼 간격 설정
		FlowLayout pnlSouthLayout = new FlowLayout();
		pnlSouthLayout.setHgap(30);
		pnlSouth.setLayout(pnlSouthLayout);

		JButton btnSouth1 = new JButton("내 정보");
		JButton btnSouth2 = new JButton("오늘의 뉴스");
		JButton btnSouth3 = new JButton("종료");
		btnSouth3.addActionListener(this);
//		btnSouth1.setFont(new Font("Dialog.bold", Font.BOLD, 20));
		
		pnlSouth.add(btnSouth1);
		pnlSouth.add(btnSouth2);
		pnlSouth.add(btnSouth3);
	}

	private void setPnlNorth() {
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, "North");
		pnlNorth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JButton btnNorth1 = new JButton();
		btnNorth1.setText("메인화면");
		JButton btnNorth2 = new JButton();
		btnNorth2.setText("이전");
		btnNorth2.addActionListener(this);
		JButton btnNorth3 = new JButton();
		btnNorth3.setText("다음");
		btnNorth3.addActionListener(this);
		JButton btnNorth4 = new JButton();
		btnNorth4.setText("장 마감");
		pnlNorth.add(btnNorth1);
		pnlNorth.add(btnNorth2);
		pnlNorth.add(btnNorth3);
		pnlNorth.add(btnNorth4);
		pnlNorth.setBackground(Color.BLACK);

		btnNorth4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				-- update userInfo의 user_Date
//				-- usermoneyhistory의 user_Date, stock_Price_Now
//				-- allcompany의 companyStockPrice, date
//				-- allcompanybackdata 다 넣어줘야됨
//				-- insert (companyName, companyStockPrice, companyStockCount, 
//				simulation_ID, simulation_ID_SaveData, date)

				int chageDay = userInfo.getUser_Date() + 1;

				userInfo.setUser_Date(chageDay);
				
				userMoneyHistoryList = listAndDAO.usermoneyHistoryDAO.findByID(userInfo.getUser_ID(),
						userInfo.getUser_SaveData());
				try {
//					int changeAComMoney = listAndDAO.allCompanyDAO.findCompByID("A 회사", userInfo.getUser_ID(), userInfo.getUser_SaveData())
//							.getCompanyStockPrice() + 10;
//					int changeBComMoney = listAndDAO.allCompanyDAO.findCompByID("A 회사", userInfo.getUser_ID(), userInfo.getUser_SaveData())
//							.getCompanyStockPrice() - 8;
					int changeAComMoney = 10;
					int changeBComMoney = -8;
					
					listAndDAO.userInfoDAO.updateDate(userInfo.getUser_ID(), userInfo.getUser_SaveData());
					
					listAndDAO.usermoneyHistoryDAO.updatePriceAndDate(changeAComMoney, userInfo.getUser_ID(),
							userInfo.getUser_SaveData(), "A 회사", userMoneyHistoryList.get(0).getStock_Price_now());
					
					listAndDAO.usermoneyHistoryDAO.updatePriceAndDate(changeBComMoney, userInfo.getUser_ID(),
							userInfo.getUser_SaveData(), "B 회사", userMoneyHistoryList.get(1).getStock_Price_now());

					listAndDAO.allCompanyDAO.updatePriceAndDate("A 회사", changeAComMoney, userInfo.getUser_ID(),
							userInfo.getUser_SaveData());
					
					listAndDAO.allCompanyDAO.updatePriceAndDate("B 회사", changeBComMoney, userInfo.getUser_ID(),
							userInfo.getUser_SaveData());

					listAndDAO.allCompanyBackdataDAO.insert("A 회사",
							listAndDAO.allCompanyDAO.findCompByID("A 회사", userInfo.getUser_ID(), userInfo.getUser_SaveData())
									.getCompanyStockPrice(),
							listAndDAO.allCompanyDAO.findCompByID("A 회사", userInfo.getUser_ID(), userInfo.getUser_SaveData())
									.getCompanyStockCount(),
							userInfo.getUser_ID(), userInfo.getUser_SaveData(), chageDay);

					listAndDAO.allCompanyBackdataDAO.insert("B 회사",
							listAndDAO.allCompanyDAO.findCompByID("B 회사", userInfo.getUser_ID(), userInfo.getUser_SaveData())
									.getCompanyStockPrice(),
							listAndDAO.allCompanyDAO.findCompByID("B 회사", userInfo.getUser_ID(), userInfo.getUser_SaveData())
									.getCompanyStockCount(),
							userInfo.getUser_ID(), userInfo.getUser_SaveData(), chageDay);
					JOptionPane.showMessageDialog(BaseMainFrame.this, "오늘 장이 마감되었습니다.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				companyStockBoardPnl.updatebaseMainPnl();
				companyStockBoardPnl.updateAllComapanyInfoPnl();
			}
		});
	}

	private void setPnlCenter() {
		pnlCenter = new JPanel();
		contentPane.add(pnlCenter, "Center");
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		cardLayout = new CardLayout();
		pnlCenter.setLayout(cardLayout);

		// TODO companyStockBoardPnl, clickMyInfoBtnPnl, seeMyTradingHistoryPnl
		// 3가지 업데이트 만들어야 한다.
		
		// 총 매수, 평가손익, 총 평가, 수익률, 회사들 주식 상황 보여주는 패널
		companyStockBoardPnl = new CompanyStockBoardPnl(userInfo);

		// 하단의 내 정보를 누르면 나오는 패널
		clickMyInfoBtnPnl = new ClickMyInfoBtnPnl(userInfo);

		// 주식 거래 상황을 보여주는 패널
		seeMyTradingHistoryPnl = new SeeMyTradingHistoryPnl();

		// 이번 날짜 뉴스 패널
		NewsPnl newsPnl = new NewsPnl();

		// 그래프랑 회사 정보 패널
		GraphAndCompanyInfoPnl graphAndCompanyInfoPnl = new GraphAndCompanyInfoPnl();

		// 매수 패널
		BuyPriceGUI buyPriceGUI = new BuyPriceGUI();

		// 매도 패널
		SellPriceGUI sellPriceGUI = new SellPriceGUI();

		// TODO 뒤에 이름 패널이름으로 바꾸자
		pnlCenter.add(companyStockBoardPnl, "First");
		pnlCenter.add(clickMyInfoBtnPnl, "Second");
		pnlCenter.add(seeMyTradingHistoryPnl, "Third");
		pnlCenter.add(newsPnl, "Forth");
		pnlCenter.add(graphAndCompanyInfoPnl, "Fifth");
		pnlCenter.add(buyPriceGUI, "Sixth");
		pnlCenter.add(sellPriceGUI, "Seventh");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 공통으로 사용할 액션 리스너 설정 중
		String command = e.getActionCommand();

		if (command.equals("이전")) {
			cardLayout.previous(pnlCenter);
		} else if (command.equals("다음")) {
			cardLayout.next(pnlCenter);
		} else if (command.equals("종료") ) {
			this.dispose();
		}
	}

	public static void main(String[] args) {
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		UserInfo id = userInfoDAO .findByIDAndData("asd", 1);

		new BaseMainFrame(id).setVisible(true);

	}

}
