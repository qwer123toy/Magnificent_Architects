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
import javax.swing.JLabel;
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
	private UserInfoDAO userInfoDAO;
	private JLabel daylbl;
	private GraphAndCompanyInfoPnl graphAndCompanyInfoPnl;
	private BuyPriceGUI buyPriceGUI;
	private SellPriceGUI sellPriceGUI;

	public BaseMainFrame(UserInfo userInfo) {
		this.userInfo = userInfo;

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());

		// 북쪽 패널
		// 메인화면, 이전, 일차, 다음, 장 마감 버튼
		setPnlNorth();

		// 센터에 설정할 pnl들
		// 모든 필요한 패널이 다 담겨있다.
		setPnlCenter();

		// 남쪽 패널
		// 내 정보, 오늘의 뉴스, 종료 버튼
		setPnlSouth();

		// 가장 큰 패널(contentPane) 설정
		setSize(500, 673);
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
		btnSouth1.addActionListener(this);
		JButton btnSouth2 = new JButton("오늘의 뉴스");
		btnSouth2.addActionListener(this);
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
		btnNorth1.addActionListener(this);
		JButton btnNorth2 = new JButton();
		btnNorth2.setText("이전");
		btnNorth2.addActionListener(this);

		// 제일 위 가운데 일차 표시
		daylbl = new JLabel();
		daylbl.setForeground(Color.WHITE);
		updateDay();

		JButton btnNorth3 = new JButton();
		btnNorth3.setText("다음");
		btnNorth3.addActionListener(this);
		JButton btnNorth4 = new JButton();
		btnNorth4.setText("장 마감");
		pnlNorth.add(btnNorth1);
		pnlNorth.add(btnNorth2);
		pnlNorth.add(daylbl);
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

					listAndDAO.userInfoDAO.updateDate(userInfo.getUser_ID(), userInfo.getUser_SaveData());

					for (int i = 0; i < userMoneyHistoryList.size(); i++) {
						int changeComMoney = (int) (Math.random() * 20) - 10;
						
						listAndDAO.usermoneyHistoryDAO.updatePriceAndDate(changeComMoney, userInfo.getUser_ID(),
								userInfo.getUser_SaveData(), userMoneyHistoryList.get(i).getUser_Stock(),
								userMoneyHistoryList.get(i).getStock_Price_now());
						
						listAndDAO.allCompanyDAO.updatePriceAndDate(userMoneyHistoryList.get(i).getUser_Stock(),
								changeComMoney, userInfo.getUser_ID(), userInfo.getUser_SaveData());
						listAndDAO.allCompanyBackdataDAO.insert(userMoneyHistoryList.get(i).getUser_Stock(),
								listAndDAO.allCompanyDAO
										.findCompByID(userMoneyHistoryList.get(i).getUser_Stock(),
												userInfo.getUser_ID(), userInfo.getUser_SaveData())
										.getCompanyStockPrice(),
								listAndDAO.allCompanyDAO
										.findCompByID(userMoneyHistoryList.get(i).getUser_Stock(),
												userInfo.getUser_ID(), userInfo.getUser_SaveData())
										.getCompanyStockCount(),
								userInfo.getUser_ID(), userInfo.getUser_SaveData(), chageDay);
					}

					JOptionPane.showMessageDialog(BaseMainFrame.this, "오늘 장이 마감되었습니다.");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				updateMainPnl();
			}
		});
	}

	private void updateDay() {
		daylbl.setText(userInfo.getUser_Date() + "일차");
	}

	private void updateMainPnl() {
		// 제일 처음 화면 업데이트
		companyStockBoardPnl.updatebaseMainPnl();
		companyStockBoardPnl.updateAllComapanyInfoPnl();
		clickMyInfoBtnPnl.updateAll(userInfo);
		seeMyTradingHistoryPnl.updateAll(userInfo);
		graphAndCompanyInfoPnl.updateAll("A 회사", userInfo);
		graphAndCompanyInfoPnl.updateAll("B 회사", userInfo);
		graphAndCompanyInfoPnl.updateAll("C 회사", userInfo);
		graphAndCompanyInfoPnl.updateAll("D 회사", userInfo);
		graphAndCompanyInfoPnl.updateAll("E 회사", userInfo);
		graphAndCompanyInfoPnl.updateAll("F 회사", userInfo);
		updateDay();
	}

	private void setPnlCenter() {
		pnlCenter = new JPanel();
		contentPane.add(pnlCenter, "Center");
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		cardLayout = new CardLayout();
		pnlCenter.setLayout(cardLayout);

		// TODO 모든 패널 생성
		// 그래프랑 회사 정보 패널
		graphAndCompanyInfoPnl = new GraphAndCompanyInfoPnl(userInfo, cardLayout, pnlCenter, 0);

		// 매수 패널
		buyPriceGUI = new BuyPriceGUI(userInfo, "A 회사", 0);

		// 매도 패널
		sellPriceGUI = new SellPriceGUI();

		// 총 매수, 평가손익, 총 평가, 수익률, 회사들 주식 상황 보여주는 패널
		companyStockBoardPnl = new CompanyStockBoardPnl(userInfo, cardLayout, pnlCenter, graphAndCompanyInfoPnl, buyPriceGUI, sellPriceGUI);

		// 하단의 내 정보를 누르면 나오는 패널
		clickMyInfoBtnPnl = new ClickMyInfoBtnPnl(userInfo, cardLayout, pnlCenter);

		// 주식 거래 상황을 보여주는 패널
		seeMyTradingHistoryPnl = new SeeMyTradingHistoryPnl(userInfo, cardLayout, pnlCenter);

		// 이번 날짜 뉴스 패널
		NewsPnl newsPnl = new NewsPnl();

		pnlCenter.add(companyStockBoardPnl, "companyStockBoardPnl");
		pnlCenter.add(clickMyInfoBtnPnl, "clickMyInfoBtnPnl");
		pnlCenter.add(seeMyTradingHistoryPnl, "seeMyTradingHistoryPnl");
		pnlCenter.add(newsPnl, "newsPnl");
		pnlCenter.add(graphAndCompanyInfoPnl, "graphAndCompanyInfoPnl");
		pnlCenter.add(buyPriceGUI, "buyPriceGUI");
		pnlCenter.add(sellPriceGUI, "sellPriceGUI");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 공통으로 사용할 액션 리스너 설정 중
		String command = e.getActionCommand();

		if (command.equals("이전")) {
			cardLayout.previous(pnlCenter);
		} else if (command.equals("다음")) {
			cardLayout.next(pnlCenter);
		} else if (command.equals("종료")) {
			this.dispose();
		} else if (command.equals("메인화면")) {
			cardLayout.show(pnlCenter, "companyStockBoardPnl");
		} else if (command.equals("내 정보")) {
			cardLayout.show(pnlCenter, "clickMyInfoBtnPnl");
		} else if (command.equals("오늘의 뉴스")) {
			cardLayout.show(pnlCenter, "newsPnl");
		}
	}

//	public static void main(String[] args) {
//		UserInfoDAO userInfoDAO = new UserInfoDAO();
//		UserInfo id = userInfoDAO.findByIDAndData("asdf", 1);
//
//		new BaseMainFrame(id).setVisible(true);
//	}
}
