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
	AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	public UserFrame(String user_ID, int user_SaveData) {
		while (true) {
			UserInfo userInfo = UserInfoDAO.findByIDAndData(user_ID,user_SaveData);
			List<UserMoneyHistory> userMoneyHistoryList = userMoneyHistoryDAO.findByID(userInfo.getUser_ID(), userInfo.getUser_SaveData());
			List<AllCompany> allCompanyList = allCompanyDAO.findAllByID(userInfo.getUser_ID(), userInfo.getUser_SaveData());
			
			System.out.printf("반갑습니다. %s 회원님\n", userInfo.getUser_ID());
			System.out.printf("현재 잔고는 %d원입니다.\n", userInfo.getUser_Money());

			
			
			System.out.printf("현재 원금은 %d원입니다.\n", stockChangeHistoryDAO.findStockMoneyAllBycompName("A 회사", userInfo.getUser_ID(), userInfo.getUser_SaveData())
							+ stockChangeHistoryDAO.findStockMoneyAllBycompName("B 회사", userInfo.getUser_ID(), userInfo.getUser_SaveData()));


			System.out.printf("현재 수익은 %d원입니다.\n",
					stockChangeHistoryDAO.findPlusStockMoneyNowBycompName("A 회사", userInfo.getUser_ID(), userInfo.getUser_SaveData()));

			double stockMoneyRate = 0;

			System.out.printf("현재 수익률은 %f%%입니다.\n", stockMoneyRate);

			System.out.printf("현재 보유 금액은 %d원입니다\n",
					stockChangeHistoryDAO.findFinalStockMoneyNowBycompName("A 회사", userInfo.getUser_ID(), userInfo.getUser_SaveData()));
					

		System.out.printf("현재 잔고는 %d원입니다\n", userInfo.getUser_Money());
			System.out.println("보유 주식 현황입니다.");

			for(int i=0; i<userMoneyHistoryList.size(); i++ ) {
				if (userMoneyHistoryList.get(i).getStock_Count() > 0) {
					System.out.printf("%s, 보유주식 : %d 주, 구매 금액  %d원, 보유 금액 %d원, 현재가 %d원,  현재 수익 %d원, 현재 수익률 %f\n",
							userMoneyHistoryList.get(i).getUser_Stock(),
							userMoneyHistoryList.get(i).getStock_Count(),
							stockChangeHistoryDAO.findStockMoneyAvgBycompName(userMoneyHistoryList.get(i).getUser_Stock(), userInfo.getUser_ID(), userInfo.getUser_SaveData()),
							stockChangeHistoryDAO.findFinalStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(), userInfo.getUser_ID(), userInfo.getUser_SaveData()),
							stockChangeHistoryDAO.findStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(), userInfo.getUser_ID(), userInfo.getUser_SaveData()),
							stockChangeHistoryDAO.findPlusStockMoneyNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(), userInfo.getUser_ID(), userInfo.getUser_SaveData()),
							stockChangeHistoryDAO.findFinalStockMoneyRateNowBycompName(userMoneyHistoryList.get(i).getUser_Stock(), userInfo.getUser_ID(), userInfo.getUser_SaveData()));
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
