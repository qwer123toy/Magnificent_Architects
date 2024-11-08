package MainSystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.StockChangeHistoryDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class SellFrame {

	private UserInfoDAO UserInfoDAO = new UserInfoDAO();
	private UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();

	public SellFrame(UserInfo parentUserInfo, String companyName, int companyIndex) {

		UserInfoDAO userInfoDAO = new UserInfoDAO();
		AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
		AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
		StockChangeHistoryDAO stockChangeHistoryDAO = new StockChangeHistoryDAO();

		UserInfo userInfo = userInfoDAO.findByIDAndData(parentUserInfo.getUser_ID(), parentUserInfo.getUser_SaveData());
		List<AllCompany> allCompanyList = allCompanyDAO.findAllByID(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		int today = 0;
		AllCompany allCompany = allCompanyDAO.findCompByID(companyName, userInfo.getUser_ID(), userInfo.getUser_SaveData());

		// 수량*주가 만큼의 돈이 있어야됨 조건문 추가

		System.out.println("\n==========================");
		System.out.printf("회사 이름 : %s\n", allCompanyList.get(companyIndex).getCompanyName());
		System.out.printf("현재 주가 : %d원\n", allCompanyList.get(companyIndex).getCompanyStockPrice());
		System.out.printf("현재 주가 수량 : %d 주 \n", allCompanyList.get(companyIndex).getCompanyStockCount());

		if (userInfo.getUser_Date() == 1) {
			System.out.printf("전일 대비  0원  \n");
		} else {
			today = userInfo.getUser_Date();
			int yesterday = today-1; 
			
			AllCompanyBackdata acbdYesterday =  allCompanyBackdataDAO.findCompanyByDate(companyName, yesterday,userInfo.getUser_ID(),
					userInfo.getUser_SaveData());
			
			int todaysStockPrice = allCompany.getCompanyStockPrice();
//			int todaysStockCount = allCompany.getCompanyStockCount();
			
//			int yesterdayStockCount = acbdYesterday.getCompanyStockCount();
			int yesterdayStockPrice = acbdYesterday.getCompanyStockPrice();
			int changeStockPrice = todaysStockPrice- yesterdayStockPrice;
//			int changeStockCount = yesterdayStockCount-todaysStockCount;
			
			System.out.printf("전일 대비  %d원  \n", changeStockPrice);
		}
		System.out.println("==========================");
		System.out.println("판매하실 수량을 입력하세요.");
		System.out.println("뒤로 가기를 원하시면 -1을 입력하세요.");
		System.out.print("입력 : ");
		Scanner sc = new Scanner(System.in);

		int sellStock = sc.nextInt();// 판 수량
		if (sellStock < 0) {
			return;
		} else {
			int sellStockCount = 0;// 판 수량이 적용된 회사의 주식 수량
			int sellStockPrice = 0;
			
			sellStockCount = allCompany.getCompanyStockCount() + sellStock;
			sellStockPrice = allCompany.getCompanyStockPrice();

			

			try {
				allCompanyDAO.update(companyName, sellStockPrice, sellStockCount, userInfo.getUser_ID(),
						userInfo.getUser_SaveData(), userInfo.getUser_Date());
				UserMoneyHistory umh = userMoneyHistoryDAO.findByCompany(companyName, userInfo.getUser_ID(),
						userInfo.getUser_SaveData());

				
				if (today == 1) {

				} else {


					// 매입가
					int buyPrice = stockChangeHistoryDAO.findStockMoneyAvgBycompName(companyName, userInfo.getUser_ID(),
							userInfo.getUser_SaveData());

					// 평가금액
					int realMoney = stockChangeHistoryDAO.findFinalStockMoneyNowBycompName(companyName, userInfo.getUser_ID(),
							userInfo.getUser_SaveData());

					// 평가손익
					int profitMoney = stockChangeHistoryDAO.findPlusStockMoneyNowBycompName(companyName, userInfo.getUser_ID(),
							userInfo.getUser_SaveData());

					// 수익률
					double profitRate = stockChangeHistoryDAO.findFinalStockMoneyRateNowBycompName(companyName,
							userInfo.getUser_ID(), userInfo.getUser_SaveData());

					// 회원 별 주식 보유 상황 업데이트
					System.out.println(umh.getStock_Count());
					System.out.println(sellStock);
					userMoneyHistoryDAO.update(userInfo.getUser_ID(), userInfo.getUser_SaveData(), companyName, buyPrice,
							sellStockPrice, realMoney, profitMoney, profitRate, umh.getStock_Count() - sellStock,
							userInfo.getUser_Date());

//					userMoneyHistoryDAO.update(userInfo.getUser_ID(), userInfo.getUser_SaveData(), companyName, sellStockPrice,
//							sellStockPrice, 0, 0, 0, sellStock, userInfo.getUser_Date());

					int user_Money = userInfo.getUser_Money() + (sellStockPrice * sellStock);

					// 유저가 가진 돈에 판 돈을 더해줌
					// 업데이트
					UserInfoDAO.update(user_Money, userInfo.getUser_ID(), userInfo.getUser_SaveData());

					// 회사 별 주식 보유 내용 저장
					allCompanyBackdataDAO.insert(companyName, sellStockPrice, sellStockCount, userInfo.getUser_ID(),
							userInfo.getUser_SaveData(), userInfo.getUser_Date());

					// 전체 거래 내역 저장
					stockChangeHistoryDAO.insertSell(userInfo.getUser_ID(), userInfo.getUser_SaveData(), companyName,
							sellStockPrice, sellStock, userInfo.getUser_Date());

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}