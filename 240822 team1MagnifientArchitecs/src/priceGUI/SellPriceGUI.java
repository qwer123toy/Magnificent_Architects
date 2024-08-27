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

public class SellPriceGUI extends JFrame {
	private JTextField tfSellCount;

	public SellPriceGUI() {
		setSize(420, 510);

		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel pnlCenter = new JPanel();

		pnlCenter.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pnlInformation = new JPanel();
		pnlInformation.setBackground(Color.WHITE);
		pnlCenter.add(pnlInformation);
		pnlInformation.setLayout(null);

		JLabel lblSellPrice = new JLabel("매도 가격");
		lblSellPrice.setBounds(46, 110, 72, 15);
		pnlInformation.add(lblSellPrice);

		JLabel lblSellCount = new JLabel("매도 수량");
		lblSellCount.setBounds(46, 135, 72, 15);
		pnlInformation.add(lblSellCount);

		JLabel lblSellPrice2 = new JLabel("100000원");
		lblSellPrice2.setBounds(130, 110, 155, 15);
		pnlInformation.add(lblSellPrice2);

		tfSellCount = new JTextField();
		tfSellCount.setBounds(130, 134, 116, 21);
		pnlInformation.add(tfSellCount);
		tfSellCount.setColumns(10);

		JLabel lblSellMaxCount = new JLabel("최대 매도 가능 수량: 500주");
		lblSellMaxCount.setFont(new Font("굴림", Font.PLAIN, 11));
		lblSellMaxCount.setBounds(130, 162, 155, 15);
		pnlInformation.add(lblSellMaxCount);

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
		lblMoney.setForeground(SystemColor.window);
		pnlMyMoney.add(lblMoney, BorderLayout.CENTER);

		JLabel lblComMoney = new JLabel("100원");
		lblComMoney.setBounds(46, 34, 57, 15);
		pnlInformation.add(lblComMoney);

		JLabel lblCom = new JLabel("전월대비 0원(0%)");
		lblCom.setFont(new Font("굴림", Font.PLAIN, 11));
		lblCom.setBounds(46, 54, 116, 15);
		pnlInformation.add(lblCom);

		String btnName[] = { "1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "지우기", "00", "0", "<-", "입력" };
		JButton btnNum[] = new JButton[btnName.length];

		getContentPane().add(pnlCenter);

		JPanel pnlBtn = new JPanel();
		pnlCenter.add(pnlBtn);
		pnlBtn.setLayout(new BorderLayout(0, 0));

		JPanel pnlNumber = new JPanel();
		pnlNumber.setBackground(Color.WHITE);
		pnlBtn.add(pnlNumber);
		pnlNumber.setLayout(new GridLayout(0, 4, 5, 5));

		for (int i = 0; i < btnName.length; i++) {
			btnNum[i] = new JButton(btnName[i]);
			pnlNumber.add(btnNum[i]);
		}

		JPanel pnlBtnSet = new JPanel();
		pnlBtnSet.setBackground(Color.WHITE);
		pnlBtn.add(pnlBtnSet, BorderLayout.SOUTH);

		JButton btnSell = new JButton("매도");
		pnlBtnSet.add(btnSell);

		JButton btnBack = new JButton("뒤로가기");
		pnlBtnSet.add(btnBack);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
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
		getContentPane().add(pnlMainSouth, BorderLayout.SOUTH);

		JButton btnMy = new JButton("내 정보");
		pnlMainSouth.add(btnMy);

		JButton btnNews = new JButton("오늘의 뉴스");
		pnlMainSouth.add(btnNews);

		JButton btnEnd = new JButton("종료");
		pnlMainSouth.add(btnEnd);

	}

	public static void main(String[] args) {
		new SellPriceGUI().setVisible(true);
	}
}
