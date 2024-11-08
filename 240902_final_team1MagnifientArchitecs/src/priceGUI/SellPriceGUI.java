package priceGUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.StockChangeHistoryDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class SellPriceGUI extends JPanel {
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
	StockChangeHistoryDAO stockChangeHistoryDAO = new StockChangeHistoryDAO();
	UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();

	private JLabel lblSellMax;
	private JLabel lblCompanyName;
	private JLabel lblPrice;
	private JLabel lblSellData;
	private String companyName;
	
	private int companyIndex;

	public SellPriceGUI(UserInfo parentUserInfo, String companyName, int companyIndex) {
		this.companyName = companyName;
		this.companyIndex = companyIndex;

		UserInfo userInfo = userInfoDAO.findByIDAndData(parentUserInfo.getUser_ID(), parentUserInfo.getUser_SaveData());
		List<AllCompany> allCompanyList = allCompanyDAO.findAllByID(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		int today = 0;
		AllCompany allCompany = allCompanyDAO.findCompByID(companyName, userInfo.getUser_ID(),
				userInfo.getUser_SaveData());
		int changeStockPrice = 0;
		if (userInfo.getUser_Date() == 1) {
			System.out.printf("전일 대비  0원  \n");
		} else {
			today = userInfo.getUser_Date();
			int yesterday = today - 1;

			AllCompanyBackdata acbdYesterday = allCompanyBackdataDAO.findCompanyByDate(companyName, yesterday,
					userInfo.getUser_ID(), userInfo.getUser_SaveData());

			int todaysStockPrice = allCompany.getCompanyStockPrice();
			int yesterdayStockPrice = acbdYesterday.getCompanyStockPrice();
			changeStockPrice = todaysStockPrice - yesterdayStockPrice;

		}

		setLayout(new BorderLayout(0, 0));
		setSize(420, 510);

		JPanel pnlCenter = new JPanel();
		pnlCenter.setLayout(new GridLayout(0, 1, 0, 0));
		add(pnlCenter, BorderLayout.CENTER);

		JPanel pnlInformation = new JPanel();
		pnlInformation.setBackground(SystemColor.window);
		pnlCenter.add(pnlInformation);
		pnlInformation.setLayout(null);

		JLabel lblSellPrice = new JLabel();
		lblSellPrice.setText("매도 가격");
		lblSellPrice.setBounds(46, 110, 72, 15);
		pnlInformation.add(lblSellPrice);

		JLabel lblSellCount = new JLabel("매도 수량");
		lblSellCount.setBounds(46, 135, 72, 15);
		pnlInformation.add(lblSellCount);

		// 사용자 매도 수량 입력
		JTextField tfSellPrice = new JTextField();
		tfSellPrice.setBounds(130, 134, 116, 21);
		pnlInformation.add(tfSellPrice);
		tfSellPrice.setColumns(10);
		

		// 매도 가격: 사용자 선택 수량 * 회사 주식 금액
		String sellStockString = tfSellPrice.getText();
		int sellStock = 0;
		if (!sellStockString.isEmpty()) {
			sellStock = Integer.parseInt(sellStockString);
		}

		int sellMoney = (sellStock * allCompanyList.get(companyIndex).getCompanyStockPrice());
		JLabel lblSellPrice2 = new JLabel();
		lblSellPrice2.setBounds(130, 110, 155, 15);
		pnlInformation.add(lblSellPrice2);

		lblSellMax = new JLabel();

		// TODO
//		lblSellMax.setText("최대 매도 가능 수량: " + allCompanyList.get(companyIndex).getCompanyStockCount() + "주");

		lblSellMax.setFont(new Font("굴림", Font.PLAIN, 11));
		lblSellMax.setBounds(130, 162, 155, 15);
		pnlInformation.add(lblSellMax);

		lblCompanyName = new JLabel();

		// TODO
//		lblCompanyName.setText(allCompanyList.get(companyIndex).getCompanyName() + " 회사");

		lblCompanyName.setBounds(46, 14, 57, 15);
		pnlInformation.add(lblCompanyName);

		JPanel pnlMyMoney = new JPanel();
		pnlMyMoney.setBackground(SystemColor.activeCaption);
		pnlMyMoney.setBounds(237, 14, 155, 31);
		pnlInformation.add(pnlMyMoney);
		pnlMyMoney.setLayout(new BorderLayout(0, 0));

		// 사용자 현재 보유 금액
		JLabel lblMoney = new JLabel("현재 보유 금액: " + userInfo.getUser_Money());
		lblMoney.setBackground(SystemColor.window);
		pnlMyMoney.add(lblMoney, BorderLayout.CENTER);

		lblPrice = new JLabel();

		// TODO
//		lblPrice.setText(allCompanyList.get(companyIndex).getCompanyStockPrice() + "원");

		lblPrice.setBounds(46, 34, 57, 15);
		pnlInformation.add(lblPrice);

		lblSellData = new JLabel();

		// TODO
//		lblSellData.setText(changeStockPrice + "원");

		lblSellData.setFont(new Font("굴림", Font.PLAIN, 11));
		lblSellData.setBounds(46, 54, 116, 15);
		pnlInformation.add(lblSellData);

		String btnName[] = { "1", "2", "3", "+10", "4", "5", "6", "-10", "7", "8", "9", "지우기", "00", "0", "<-", "입력" };
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
//						 tfSellPrice가 0일 경우 + 클릭 시 10으로 설정
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

		JButton btnSell = new JButton("매수");
		btnSell.setBackground(SystemColor.activeCaption);
		pnlBtnSet.add(btnSell);

		btnSell.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String companyName = lblCompanyName.getText();
//				System.out.println(companyName);
//				companyName = companyName.substring(0, 10);
			

				String sellStockString = tfSellPrice.getText();
				int sellStock = 0;
				if (!sellStockString.isEmpty()) {
					sellStock = Integer.parseInt(sellStockString);
				}
				if (sellStock < 0) {
					return;
				} else {
					int sellStockCount = 0;// 판 수량이 적용된 회사의 주식 수량
					int sellStockPrice = 0;
					AllCompany allCompany = allCompanyDAO.findCompByID(companyName, userInfo.getUser_ID(), userInfo.getUser_SaveData());
					sellStockCount = allCompany.getCompanyStockCount() - sellStock;
					sellStockPrice = allCompany.getCompanyStockPrice();
					int sellPrice = 0;

					// 평가금액
					int realMoney = 0;

					// 평가손익
					int profitMoney = 0;

					// 수익률
					double profitRate = 0;

					try {
						
						allCompanyDAO.update(companyName, sellStockPrice, sellStockCount, userInfo.getUser_ID(),
								userInfo.getUser_SaveData(), userInfo.getUser_Date());
						
						UserMoneyHistory umh = userMoneyHistoryDAO.findByCompany(companyName, userInfo.getUser_ID(),
								userInfo.getUser_SaveData());

						// 매입가
						sellPrice = stockChangeHistoryDAO.findStockMoneyAvgBycompName(companyName, userInfo.getUser_ID(),
								userInfo.getUser_SaveData());

						// 평가금액
						realMoney = stockChangeHistoryDAO.findFinalStockMoneyNowBycompName(companyName,
								userInfo.getUser_ID(), userInfo.getUser_SaveData());

						// 평가손익
						profitMoney = stockChangeHistoryDAO.findPlusStockMoneyNowBycompName(companyName,
								userInfo.getUser_ID(), userInfo.getUser_SaveData());

						// 수익률
						profitRate = stockChangeHistoryDAO.findFinalStockMoneyRateNowBycompName(companyName,
								userInfo.getUser_ID(), userInfo.getUser_SaveData());

						// 회원 별 주식 보유 상황 업데이트
						userMoneyHistoryDAO.update(userInfo.getUser_ID(), userInfo.getUser_SaveData(), companyName,
								sellPrice, sellStockPrice, realMoney, profitMoney, profitRate,
								umh.getStock_Count() + sellStock, userInfo.getUser_Date());

						int user_Money = userInfo.getUser_Money() + (sellStockPrice * sellStock);

						// 유저가 가진 돈에 판 돈을 더해줌
						// 업데이트
						userInfoDAO.update(user_Money, userInfo.getUser_ID(), userInfo.getUser_SaveData());

						// 회사 별 주식 보유 내용 저장
						allCompanyBackdataDAO.insert(companyName, sellStockPrice, sellStockCount, userInfo.getUser_ID(),
								userInfo.getUser_SaveData(), userInfo.getUser_Date());

						// 전체 거래 내역 저장
						stockChangeHistoryDAO.insertSell(userInfo.getUser_ID(), userInfo.getUser_SaveData(), companyName,
								sellStockPrice, sellStock, userInfo.getUser_Date());

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JButton btnBack = new JButton("뒤로가기");
		btnBack.setBackground(SystemColor.activeCaption);
		pnlBtnSet.add(btnBack);
//		System.out.println(companyName);
//	    updateComInfo(parentUserInfo, companyName);

	}

	public void updateComInfo(UserInfo parentUserInfo, String companyName) {
		int companyIndex = selectCompanyIndex(companyName);

		UserInfo userInfo = userInfoDAO.findByIDAndData(parentUserInfo.getUser_ID(), parentUserInfo.getUser_SaveData());
		List<AllCompany> allCompanyList = allCompanyDAO.findAllByID(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		int today = 0;
		AllCompany allCompany = allCompanyDAO.findCompByID(companyName, userInfo.getUser_ID(),
				userInfo.getUser_SaveData());
		int changeStockPrice = 0;
		if (userInfo.getUser_Date() == 1) {
			System.out.printf("전일 대비  0원  \n");
		} else {
			today = userInfo.getUser_Date();
			int yesterday = today - 1;

			AllCompanyBackdata acbdYesterday = allCompanyBackdataDAO.findCompanyByDate(companyName, yesterday,
					userInfo.getUser_ID(), userInfo.getUser_SaveData());

			int todaysStockPrice = allCompany.getCompanyStockPrice();
			int yesterdayStockPrice = acbdYesterday.getCompanyStockPrice();
			changeStockPrice = todaysStockPrice - yesterdayStockPrice;

		}

		lblSellMax.setText("최대 매도 가능 수량: " + allCompanyList.get(companyIndex).getCompanyStockCount() + "주");
		lblCompanyName.setText(allCompanyList.get(companyIndex).getCompanyName());
		lblPrice.setText(allCompanyList.get(companyIndex).getCompanyStockPrice() + "원");
		lblSellData.setText(changeStockPrice + "원");
	}

	private int selectCompanyIndex(String companyName) {
		int companyIndex;
		if (companyName.equals("A 회사")) {
			companyIndex = 0;
		} else if (companyName.equals("B 회사")) {
			companyIndex = 1;
		} else if (companyName.equals("C 회사")) {
			companyIndex = 2;
		} else if (companyName.equals("D 회사")) {
			companyIndex = 3;
		} else if (companyName.equals("E 회사")) {
			companyIndex = 4;
		} else if (companyName.equals("F 회사")) {
			companyIndex = 5;
		} else {
			companyIndex = 0;
		}
		return companyIndex;
	}
}
