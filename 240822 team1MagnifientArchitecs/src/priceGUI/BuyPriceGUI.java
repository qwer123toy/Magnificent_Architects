package priceGUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;

public class BuyPriceGUI extends JFrame {
	private JTextField tfBuyPrice;

	public BuyPriceGUI() {
		setSize(420, 510);

		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel pnlCenter = new JPanel();

		pnlCenter.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pnlInformation = new JPanel();
		pnlInformation.setBackground(SystemColor.window);
		pnlCenter.add(pnlInformation);
		pnlInformation.setLayout(null);

		JLabel lblBuyPrice = new JLabel("매수 가격");
		lblBuyPrice.setBounds(46, 110, 72, 15);
		pnlInformation.add(lblBuyPrice);

		JLabel lblBuyCount = new JLabel("매수 수량");
		lblBuyCount.setBounds(46, 135, 72, 15);
		pnlInformation.add(lblBuyCount);

		JLabel lblBuyPrice2 = new JLabel("100000원");
		lblBuyPrice2.setBounds(130, 110, 155, 15);
		pnlInformation.add(lblBuyPrice2);

		tfBuyPrice = new JTextField();
		tfBuyPrice.setBounds(130, 134, 116, 21);
		pnlInformation.add(tfBuyPrice);
		tfBuyPrice.setColumns(10);

		JLabel lblBuyMax = new JLabel("최대 매수 가능 수량: 500주");
		lblBuyMax.setFont(new Font("굴림", Font.PLAIN, 11));
		lblBuyMax.setBounds(130, 162, 155, 15);
		pnlInformation.add(lblBuyMax);

		JLabel lblCompanyName = new JLabel("A 회사");
		lblCompanyName.setBounds(46, 14, 57, 15);
		pnlInformation.add(lblCompanyName);

		JPanel pnlMyMoney = new JPanel();
		pnlMyMoney.setBackground(SystemColor.activeCaption);
		pnlMyMoney.setBounds(237, 14, 155, 31);
		pnlInformation.add(pnlMyMoney);
		pnlMyMoney.setLayout(new BorderLayout(0, 0));

		JLabel lblMoney = new JLabel("현재 보유 금액: 100000원");
		lblMoney.setBackground(SystemColor.window);
		pnlMyMoney.add(lblMoney, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("100원");
		lblNewLabel.setBounds(46, 34, 57, 15);
		pnlInformation.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("전월대비 0원(0%)");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(46, 54, 116, 15);
		pnlInformation.add(lblNewLabel_1);

		String btnName[] = { "1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "지우기", "00", "0", "<-", "입력" };
		JButton btnNum[] = new JButton[btnName.length];

		getContentPane().add(pnlCenter);

		JPanel pnlBtn = new JPanel();
		pnlCenter.add(pnlBtn);
		pnlBtn.setLayout(new BorderLayout(0, 0));

		JPanel pnlNumber = new JPanel();
		pnlNumber.setBackground(SystemColor.window);
		pnlBtn.add(pnlNumber);
		pnlNumber.setLayout(new GridLayout(0, 4, 5, 5));

		for (int i = 0; i < btnName.length; i++) {
			btnNum[i] = new JButton(btnName[i]);
			btnNum[i].setBackground(SystemColor.window);
			btnNum[i].setFocusable(false); // 버튼 선택시 가운데 상자 제거
			pnlNumber.add(btnNum[i]);
			
		}

		JPanel pnlBtnSet = new JPanel();
		pnlBtnSet.setBackground(SystemColor.window);
		pnlBtn.add(pnlBtnSet, BorderLayout.SOUTH);

		JButton btnBuy = new JButton("매수");
		btnBuy.setBackground(SystemColor.window);
		pnlBtnSet.add(btnBuy);

		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBackground(SystemColor.window);
		pnlBtnSet.add(btnBack);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.window);
		pnlBtn.add(panel_1, BorderLayout.NORTH);

		JPanel pnlMainNorth = new JPanel();
		pnlMainNorth.setBackground(SystemColor.activeCaption);
		getContentPane().add(pnlMainNorth, BorderLayout.NORTH);

		JButton btnMain = new JButton("메인화면");
		btnMain.setBackground(SystemColor.window);
		pnlMainNorth.add(btnMain);

		JLabel lblDay = new JLabel("13일차");
		pnlMainNorth.add(lblDay);

		JButton btnDataEnd = new JButton("장 마감");
		btnDataEnd.setBackground(SystemColor.window);
		pnlMainNorth.add(btnDataEnd);

		JPanel pnlMainSouth = new JPanel();
		pnlMainSouth.setBackground(SystemColor.activeCaption);
		getContentPane().add(pnlMainSouth, BorderLayout.SOUTH);

		JButton btnMy = new JButton("내 정보");
		btnMy.setBackground(SystemColor.window);
		pnlMainSouth.add(btnMy);

		JButton btnNews = new JButton("오늘의 뉴스");
		btnNews.setBackground(SystemColor.window);
		pnlMainSouth.add(btnNews);

		JButton btnEnd = new JButton("종료");
		btnEnd.setBackground(SystemColor.window);
		pnlMainSouth.add(btnEnd);

	}

	public static void main(String[] args) {
		new BuyPriceGUI().setVisible(true);
	}
}
