package MainSystem;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.AllCompanyBackdata;
import tables.UserInfo;
import tables.UserMoneyHistory;
import tables.AllCompany;

public class BuyFrame {
	private AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	private AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
	private UserInfoDAO UserInfoDAO = new UserInfoDAO();
	private UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();

	public BuyFrame(UserInfo userInfo, List<AllCompanyBackdata> findCompanyBackdata, String companyName,
			List<AllCompanyBackdata> allCompanyBackdataList,
			List<UserMoneyHistory> userMoneyHistory, int companyIndex) {
		
		AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
		List<AllCompany> allCompanyList = allCompanyDAO.findAllByID(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		UserInfoDAO UserInfoDAO = new UserInfoDAO();
		UserInfo realUserInfo = UserInfoDAO.findByIDAndData(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		
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
		int buyStock = sc.nextInt();
		if (buyStock < 0) {
			return;
		} else {
			int buyStockCount = 0;
			int buyStockPrice = 0;
			AllCompany allCompany = allCompanyDAO.findCompByID(companyName, userInfo.getUser_ID(), userInfo.getUser_SaveData());
			buyStockCount = allCompany.getCompanyStockCount() - buyStock;
			buyStockPrice = allCompany.getCompanyStockPrice();

			try {
				allCompanyDAO.update(companyName, buyStockPrice, buyStockCount, userInfo.getUser_ID(),
						userInfo.getUser_SaveData(), userInfo.getUser_Date());
				UserMoneyHistory umh = userMoneyHistoryDAO.findByCompany(companyName, userInfo.getUser_ID(),
						userInfo.getUser_SaveData());

				
				// ((기존매입가*보유주식수량)+(방금산매입가+구매주식수량))/(총 주식 수량)
				double my_Stock_MoneyDBL = ((umh.getBuyPrice() * umh.getStock_Count()) + (buyStockPrice * buyStockCount))
						/ (umh.getStock_Count() + buyStockCount);
				int my_Stock_Money = (int) Math.round(my_Stock_MoneyDBL);

//				 (현재가-매입가)*보유주식수량
				double my_Profit_MoneyDBL = (umh.getStock_Price_now() - my_Stock_Money) * (umh.getStock_Count() + buyStockCount);
				int my_Profit_Money = (int) Math.round(my_Profit_MoneyDBL);

//				 (현재가*보유수량)/(매입가*보유수량)-1
				double my_Profit_RateDBL = (umh.getStock_Price_now() * (umh.getStock_Count() + buyStockCount))
						/ ((umh.getBuyPrice() * (umh.getStock_Count() + buyStockCount)) - 1);
				int my_Profit_Rate = (int) Math.round(my_Profit_RateDBL);

				userMoneyHistoryDAO.update(userInfo.getUser_ID(), userInfo.getUser_SaveData(), companyName, buyStockPrice,
						buyStockPrice, my_Stock_Money, my_Profit_Money, my_Profit_Rate, buyStock, userInfo.getUser_Date());
				
				int user_Money = realUserInfo.getUser_Money() - (buyStockPrice * buyStock);
				
				UserInfoDAO.update(user_Money,userInfo.getUser_ID(), userInfo.getUser_SaveData());
				
				allCompanyBackdataDAO.insert(companyName, buyStockPrice, buyStockCount, userInfo.getUser_ID(),
						userInfo.getUser_SaveData(), userInfo.getUser_Date());

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			for (AllCompany a : allCompanyList) {
//				if (a.getCompanyName().equals(companyName)) {
//					
//					a.setCompanyStockCount(buyStockCount);
//					allCompanyBackdataList
//							.add(new AllCompanyBackdata(companyName, a.getCompanyStockPrice(), a.getCompanyStockCount(),
//									userInfo.getUser_ID(), userInfo.getUser_SaveData(), userInfo.getUser_Date()));
//
//					for (UserMoneyHistory u : userMoneyHistory) {
//						if (u.getUser_Stock().equals(companyName)) {
////							UPDATE userInfo SET user_Money = user_Money - ?, 
////									where (simulation_ID ='asd') and (simulation_ID_SaveData=1);
//							
//							
//							u.setBuyPrice(buyStockPrice);
//							u.setStock_Count(buyStock);
//							u.setStock_Price_now(a.getCompanyStockPrice());
//							userInfo.setUser_Money(userInfo.getUser_Money() - (buyStockPrice * buyStock));
////							u.set
//
//						}
//					}

//					userMoneyHistory.get(0).setBuyPrice(a.getCompanyStockPrice());
//					userMoneyHistory.get(0).
			// }

			// }

		}

	}
}