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

	private AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	private AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
	private UserInfoDAO UserInfoDAO = new UserInfoDAO();
	private UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	private StockChangeHistoryDAO stockChangeHistoryDAO = new StockChangeHistoryDAO();

	public SellFrame(UserInfo parentUserInfo, String companyName, int companyIndex) {

		UserInfoDAO userInfoDAO = new UserInfoDAO();
		AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
		UserMoneyHistoryDAO usermoneyHistoryDAO = new UserMoneyHistoryDAO();
		AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
		StockChangeHistoryDAO stockChangeHistoryDAO = new StockChangeHistoryDAO();

		UserInfo userInfo = userInfoDAO.findByIDAndData(parentUserInfo.getUser_ID(), parentUserInfo.getUser_SaveData());
		List<AllCompany> allCompanyList = allCompanyDAO.findAllByID(userInfo.getUser_ID(), userInfo.getUser_SaveData());

		// 수량*주가 만큼의 돈이 있어야됨 조건문 추가

		System.out.println("\n==========================");
//		System.out.printf("회사 이름 : %s\n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyName());
//		System.out.printf("현재 주가 : %d원\n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStockPrice());
//		System.out.printf("현재 주가 수량 : %d 주 \n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStockCount());

		System.out.printf("회사 이름 : %s\n", allCompanyList.get(companyIndex).getCompanyName());
		System.out.printf("현재 주가 : %d원\n", allCompanyList.get(companyIndex).getCompanyStockPrice());
		System.out.printf("현재 주가 수량 : %d 주 \n", allCompanyList.get(companyIndex).getCompanyStockCount());

//		if (findCompanyBackdata.get(userInfo.getUser_Date() - 1).getDate() == 1) {
//			System.out.printf("전일 대비  0원  \n");
//		} else {
//
//			System.out.printf("전일 대비  %d원  \n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStockPrice()
//					- findCompanyBackdata.get(userInfo.getUser_Date() - 2).getCompanyStockPrice());
//		}
		System.out.println("==========================");
		System.out.println("구매하실 수량을 입력하세요.");
		System.out.println("뒤로 가기를 원하시면 -1을 입력하세요.");
		System.out.print("입력 : ");
		Scanner sc = new Scanner(System.in);
		int sellStock = sc.nextInt();
		if (sellStock < 0) {
			return;
		} else {
			int sellStockCount = 0;
			int sellStockPrice = 0;
			AllCompany allCompany = allCompanyDAO.findCompByID(companyName, userInfo.getUser_ID(), userInfo.getUser_SaveData());
			sellStockCount = allCompany.getCompanyStockCount() + sellStock;
			sellStockPrice = allCompany.getCompanyStockPrice();

			try {
				allCompanyDAO.update(companyName, sellStockPrice, sellStockCount, userInfo.getUser_ID(),
						userInfo.getUser_SaveData(), userInfo.getUser_Date());
				UserMoneyHistory umh = userMoneyHistoryDAO.findByCompany(companyName, userInfo.getUser_ID(),
						userInfo.getUser_SaveData());

				// ((기존매입가*보유주식수량)+(방금산매입가+구매주식수량))/(총 주식 수량)
				double my_Stock_MoneyDBL = ((umh.getBuyPrice() * umh.getStock_Count()) + (buyStockPrice * buyStockCount))
						/ (umh.getStock_Count() + buyStockCount);
				int my_Stock_Money = (int) Math.round(my_Stock_MoneyDBL);

//				 (현재가-매입가)*보유주식수량
				double my_Profit_MoneyDBL = (umh.getStock_Price_now() - my_Stock_Money) * (umh.getStock_Count() + sellStockCount);
				int my_Profit_Money = (int) Math.round(my_Profit_MoneyDBL);

//				 (현재가*보유수량)/(매입가*보유수량)-1
				double my_Profit_RateDBL = (umh.getStock_Price_now() * (umh.getStock_Count() + sellStockCount))
						/ ((umh.getBuyPrice() * (umh.getStock_Count() + sellStockCount)) - 1);
				int my_Profit_Rate = (int) Math.round(my_Profit_RateDBL);

				
				// 회원 별 주식 보유 상황 업데이트
				userMoneyHistoryDAO.update(userInfo.getUser_ID(), userInfo.getUser_SaveData(), companyName, sellStockPrice,
						sellStockPrice, my_Stock_Money, my_Profit_Money, my_Profit_Rate, sellStock, userInfo.getUser_Date());

				
				int user_Money = userInfo.getUser_Money() + (sellStockPrice * sellStock);
				
				//유저가 가진 돈에 판 돈을 더해줌
				//업데이트
				UserInfoDAO.update(user_Money, userInfo.getUser_ID(), userInfo.getUser_SaveData());

				
				// 회사 별 주식 보유 내용 저장
				allCompanyBackdataDAO.insert(companyName, sellStockPrice, sellStockCount, userInfo.getUser_ID(),
						userInfo.getUser_SaveData(), userInfo.getUser_Date());

				//전체 거래 내역 저장
				stockChangeHistoryDAO.insertSell(userInfo.getUser_ID(), userInfo.getUser_SaveData(), companyName, sellStockPrice,
						sellStockCount, userInfo.getUser_Date());

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}