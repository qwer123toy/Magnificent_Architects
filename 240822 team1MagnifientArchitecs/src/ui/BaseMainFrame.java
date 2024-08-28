package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import DAO.UserInfoDAO;
import priceGUI.BuyPriceGUI;
import priceGUI.SellPriceGUI;
import tables.UserInfo;

public class BaseMainFrame extends JFrame implements ActionListener {
	private CardLayout cardLayout;
	private JPanel pnlCenter;
	private UserInfo userInfo;
	

	public BaseMainFrame(UserInfo userInfo) {
		this.userInfo = userInfo;
		
		// 가장 큰 패널
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());

		// 북쪽 패널
		setPnlNorth(contentPane);

		// 센터에 설정할 pnl들
		setPnlCenter();
		contentPane.add(pnlCenter, "Center");

		// 남쪽 패널
		setPnlSouth(contentPane);

		// 가장 큰 패널(contentPane) 설정
		setSize(500, 650);
		add(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void setPnlSouth(JPanel contentPane) {
		JPanel pnlSouth = new JPanel();
		pnlSouth.setPreferredSize(new Dimension(500, 100));
		contentPane.add(pnlSouth, "South");
		pnlSouth.setBackground(Color.BLACK);
		pnlSouth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// 버튼 간격 설정
		FlowLayout pnlSouthLayout = new FlowLayout();
		pnlSouthLayout.setHgap(30);
		pnlSouth.setLayout(pnlSouthLayout);

		JButton btnSouth1 = new JButton();
		JButton btnSouth2 = new JButton();
		JButton btnSouth3 = new JButton();
		btnSouth1.setText("내 정보");
//		btnSouth1.setFont(new Font("Dialog.bold", Font.BOLD, 20));

		btnSouth2.setText("오늘의 뉴스");
		btnSouth3.setText("종료");
		pnlSouth.add(btnSouth1);
		pnlSouth.add(btnSouth2);
		pnlSouth.add(btnSouth3);
	}

	private void setPnlNorth(JPanel contentPane) {
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
	}

	private void setPnlCenter() {
		pnlCenter = new JPanel();
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		cardLayout = new CardLayout();
		pnlCenter.setLayout(cardLayout);

		// 총 매수, 평가손익, 총 평가, 수익률, 회사들 주식 상황 보여주는 패널
		CompanyStockBoardPnl companyStockBoardPnl = new CompanyStockBoardPnl(userInfo);

		// 하단의 내 정보를 누르면 나오는 패널
		ClickMyInfoBtnPnl clickMyInfoBtnPnl = new ClickMyInfoBtnPnl(userInfo);

		// 주식 거래 상황을 보여주는 패널
		SeeMyTradingHistoryPnl seeMyTradingHistoryPnl = new SeeMyTradingHistoryPnl();

		// 이번 날짜 뉴스 패널
		NewsPnl newsPnl = new NewsPnl();

		// 그래프랑 회사 정보 패널
		GraphAndCompanyInfoPnl graphAndCompanyInfoPnl = new GraphAndCompanyInfoPnl();

		// 매수 패널
		BuyPriceGUI buyPriceGUI = new BuyPriceGUI();

		// 매도 패널
		SellPriceGUI sellPriceGUI = new SellPriceGUI();

//		pnlCenter.add(pnlCompanyInfo, "First");
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
//		System.out.println(command);

		if (command.equals("이전")) {
			cardLayout.previous(pnlCenter);
		} else if (command.equals("다음")) {
			cardLayout.next(pnlCenter);
		}
	}

	public static void main(String[] args) {
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		UserInfo id = userInfoDAO .findByIDAndData("asd", 1);
		
		new BaseMainFrame(id).setVisible(true);
		
	}

}
