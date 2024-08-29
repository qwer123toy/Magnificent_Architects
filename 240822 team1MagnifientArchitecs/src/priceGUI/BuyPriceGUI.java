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

public class BuyPriceGUI extends JPanel {

	public BuyPriceGUI() {
		setLayout(new BorderLayout(0, 0));
		setSize(420, 510);

		JPanel pnlCenter = new JPanel();
		pnlCenter.setLayout(new GridLayout(0, 1, 0, 0));
		add(pnlCenter, BorderLayout.CENTER);

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

		JTextField tfBuyPrice = new JTextField();
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

		JLabel lblPrice = new JLabel("100원");
		lblPrice.setBounds(46, 34, 57, 15);
		pnlInformation.add(lblPrice);

		JLabel lblBuyData = new JLabel("전월대비 0원(0%)");
		lblBuyData.setFont(new Font("굴림", Font.PLAIN, 11));
		lblBuyData.setBounds(46, 54, 116, 15);
		pnlInformation.add(lblBuyData);

		String btnName[] = { "1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "지우기", "00", "0", "<-", "입력" };
		JButton btnNum[] = new JButton[btnName.length];

		JPanel pnlBtn = new JPanel();
		pnlCenter.add(pnlBtn);
		pnlBtn.setLayout(new BorderLayout(0, 0));

		JPanel pnlNumber = new JPanel();
		pnlNumber.setBackground(SystemColor.window);
		pnlBtn.add(pnlNumber, BorderLayout.CENTER);
		pnlNumber.setLayout(new GridLayout(0, 4, 5, 5));

		// 배열로 설정하여 내부 클래스에서 변경 가능
		int[] firstNumber = {0};
		String[] operator = {null};
		for (int i = 0; i < btnName.length; i++) {
			btnNum[i] = new JButton(btnName[i]);
			btnNum[i].setBackground(SystemColor.window);
			btnNum[i].setFocusable(false);
			// 계산기 작동
			btnNum[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String command = e.getActionCommand();
					if (command.matches("[0-9]")) { // 0-9 숫자 입력
						tfBuyPrice.setText(tfBuyPrice.getText() + command);

					} else if (command.equals("00")) {
						tfBuyPrice.setText(tfBuyPrice.getText() + "00");

					} else if (command.equals("지우기")) {
						tfBuyPrice.setText("");
						firstNumber[0] = 0; // 초기화
						operator[0] = null; // 초기화

					} else if (command.equals("<-")) {
						String currentText = tfBuyPrice.getText();
						if (currentText.length() > 0) {
							tfBuyPrice.setText(currentText.substring(0, currentText.length() - 1));
						}

					} else if (command.equals("+") || command.equals("-")) {
						firstNumber[0] = Integer.parseInt(tfBuyPrice.getText());
						operator[0] = command;
						tfBuyPrice.setText(""); // 입력 필드 초기화

					} else if (command.equals("입력")) {
						Integer secondNumber = Integer.parseInt(tfBuyPrice.getText());
						Integer result = 0;
						if (operator[0] != null) { // operator가 null이 아닐 때만 계산
							if (operator[0].equals("+")) {
								result = firstNumber[0] + secondNumber;
							} else if (operator[0].equals("-")) {
								result = firstNumber[0] - secondNumber;
							}
							tfBuyPrice.setText(String.valueOf(result)); // 결과 표시
						}
					}
				}
			});
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
	}
}
