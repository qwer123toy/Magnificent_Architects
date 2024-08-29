package priceGUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SellPriceGUI extends JPanel {

	public SellPriceGUI() {
		setLayout(new BorderLayout(0, 0));
		setSize(420, 510);

		JPanel pnlCenter = new JPanel();
		pnlCenter.setLayout(new GridLayout(0, 1, 0, 0));
		add(pnlCenter, BorderLayout.CENTER);

		JPanel pnlInformation = new JPanel();
		pnlInformation.setBackground(SystemColor.window);
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

		JTextField tfSellPrice = new JTextField();
		tfSellPrice.setBounds(130, 134, 116, 21);
		pnlInformation.add(tfSellPrice);
		tfSellPrice.setColumns(10);

		JLabel lblSellMax = new JLabel("최대 매도 가능 수량: 500주");
		lblSellMax.setFont(new Font("굴림", Font.PLAIN, 11));
		lblSellMax.setBounds(130, 162, 155, 15);
		pnlInformation.add(lblSellMax);

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

		JLabel lblPrice = new JLabel("100원");
		lblPrice.setBounds(46, 34, 57, 15);
		pnlInformation.add(lblPrice);

		JLabel lblSellData = new JLabel("전월대비 0원(0%)");
		lblSellData.setFont(new Font("굴림", Font.PLAIN, 11));
		lblSellData.setBounds(46, 54, 116, 15);
		pnlInformation.add(lblSellData);

		String btnName[] = { "1", "2", "3", "+10", "4", "5", "6", "-10", "7", "8", "9",
							"지우기", "00", "0", "<-", "입력" };
		JButton btnNum[] = new JButton[btnName.length];

		JPanel pnlBtn = new JPanel();
		pnlCenter.add(pnlBtn);
		pnlBtn.setLayout(new BorderLayout(0, 0));

		JPanel pnlNumber = new JPanel();
		pnlNumber.setBackground(SystemColor.window);
		pnlBtn.add(pnlNumber, BorderLayout.CENTER);
		pnlNumber.setLayout(new GridLayout(0, 4, 5, 5));

		int[] firstNumber = { 0 };
		String[] operator = { null };
		for (int i = 0; i < btnName.length; i++) {
			btnNum[i] = new JButton(btnName[i]);
			btnNum[i].setBackground(SystemColor.activeCaption);
			btnNum[i].setFocusable(false);
			btnNum[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String command = e.getActionCommand();
					if (command.matches("[0-9]")) { // 0-9 숫자 입력
						tfSellPrice.setText(tfSellPrice.getText() + command);

					} else if (command.equals("00")) {
						tfSellPrice.setText(tfSellPrice.getText() + "00");

					} else if (command.equals("지우기")) {
						tfSellPrice.setText("");
						firstNumber[0] = 0; // 초기화
						operator[0] = null; // 초기화

					} else if (command.equals("<-")) {
						String currentText = tfSellPrice.getText();
						if (currentText.length() > 0) {
							tfSellPrice.setText(currentText.substring(0, currentText.length() - 1));
						}

					} else if (command.equals("+10")) {
//						 tfBuyPrice가 0일 경우 + 클릭 시 10으로 설정
						String s = tfSellPrice.getText() + "1000";

						if (Integer.parseInt(s) == 1000) {
							tfSellPrice.setText("10");
						} else {
							Integer currentNumber = Integer.parseInt(tfSellPrice.getText());
							Integer result = currentNumber + 10; // 10 증가
							tfSellPrice.setText(String.valueOf(result)); // 결과 표시
						}
						
					} else if (command.equals("-10")) {
						String currentText = tfSellPrice.getText();
						if (currentText.isEmpty()) {
							return;
						}
						Integer currentNumber = Integer.parseInt(currentText);
						if (currentNumber > 10) {
							Integer result = currentNumber - 10;
							tfSellPrice.setText(String.valueOf(result));
						} else {
							tfSellPrice.setText("");
						}
					}
				}
			});
			
			pnlNumber.add(btnNum[i]);
		}

		JPanel pnlBtnSet = new JPanel();
		pnlBtnSet.setBackground(SystemColor.window);
		pnlBtn.add(pnlBtnSet, BorderLayout.SOUTH);

		JButton btnSell = new JButton("매도");
		btnSell.setBackground(SystemColor.activeCaption);
		pnlBtnSet.add(btnSell);

		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBackground(SystemColor.activeCaption);
		pnlBtnSet.add(btnBack);
	}
}
