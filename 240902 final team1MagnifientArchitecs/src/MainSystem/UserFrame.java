package MainSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import DAO.AllCompanyDAO;
import DAO.StockChangeHistoryDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.AllCompany;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class UserFrame {
	UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	UserInfoDAO UserInfoDAO = new UserInfoDAO();
	StockChangeHistoryDAO stockChangeHistoryDAO = new StockChangeHistoryDAO();

	public UserFrame(String user_ID, int user_SaveData) {
		while (true) {
			UserInfo userInfo = UserInfoDAO.findByIDAndData(user_ID, user_SaveData);
			List<UserMoneyHistory> userMoneyHistoryList = userMoneyHistoryDAO.findByID(userInfo.getUser_ID(),
					userInfo.getUser_SaveData());

			System.out.printf("%d일차 진행중\n", userInfo.getUser_Date());
			System.out.printf("반갑습니다. %s 회원님\n", userInfo.getUser_ID());
			System.out.printf("현재 통장 잔고는 %d원입니다.\n", userInfo.getUser_Money());

			int buyPriceAll = 0;// 원금
			int plusMoney = 0;// 수익
			int realMoney = 0; // 평가금액
			double stockMoneyRate = 0; // 총수익률
			
			//전체 원금, 수익금,평가금액,수익률 계산
			for (int i = 0; i < userMoneyHistoryList.size(); i++) {
				buyPriceAll += stockChangeHistoryDAO.findStockMoneyAllBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
						userInfo.getUser_ID(), userInfo.getUser_SaveData());
				plusMoney += stockChangeHistoryDAO.findPlusStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
						userInfo.getUser_ID(), userInfo.getUser_SaveData());
				realMoney += stockChangeHistoryDAO.findFinalStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
						userInfo.getUser_ID(), userInfo.getUser_SaveData());
				
			}
			if(buyPriceAll>0)
				stockMoneyRate = (double)plusMoney/(double)buyPriceAll*100.0;// 총수익률
			
			System.out.printf("현재 원금은 %d원입니다.\n", buyPriceAll);

			System.out.printf("현재 수익금은 %d원입니다.\n", plusMoney);

			System.out.printf("현재 수익률은 %.2f%%입니다.\n", stockMoneyRate);

			System.out.printf("현재 평가 금액은 %d원입니다\n", realMoney);

			System.out.println("보유 주식 현황입니다.");

			for (int i = 0; i < userMoneyHistoryList.size(); i++) {
				if (userMoneyHistoryList.get(i).getStock_Count() > 0) {
					System.out.printf("%s, 보유주식 : %d 주, 평단가  %d원, 구매금액 %d원, 보유 금액 %d원, 현재가 %d원,  현재 수익 %d원, 현재 수익률 %.2f%%\n",
							userMoneyHistoryList.get(i).getUser_Stock(), userMoneyHistoryList.get(i).getStock_Count(),
							stockChangeHistoryDAO.findStockMoneyAvgBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
									userInfo.getUser_ID(), userInfo.getUser_SaveData()),
							stockChangeHistoryDAO.findStockMoneyAllBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
									userInfo.getUser_ID(), userInfo.getUser_SaveData()),
							stockChangeHistoryDAO.findFinalStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
									userInfo.getUser_ID(), userInfo.getUser_SaveData()),
							stockChangeHistoryDAO.findStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
									userInfo.getUser_ID(), userInfo.getUser_SaveData()),
							stockChangeHistoryDAO.findPlusStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(),
									userInfo.getUser_ID(), userInfo.getUser_SaveData()),
							stockChangeHistoryDAO.findFinalStockMoneyRateNowBycompName(
									userMoneyHistoryList.get(i).getUser_Stock(), userInfo.getUser_ID(),
									userInfo.getUser_SaveData()) * 100);
				}
			}

			System.out.println("\n=======================");
			System.out.println("어떤 작업을 실행하시겠습니까?");
			System.out.println("1. 메인화면 보기");
			System.out.println("2. 거래 내역 보기");
			System.out.println("3. 오늘의 뉴스 보기");
			System.out.println("0. 뒤로가기");
			System.out.print("입력 : ");

			try {
				Scanner sc = new Scanner(System.in);
				int choose = sc.nextInt();

				switch (choose) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 0:
					return;
				default:
					System.out.println("올바른 숫자를 입력하세요");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
