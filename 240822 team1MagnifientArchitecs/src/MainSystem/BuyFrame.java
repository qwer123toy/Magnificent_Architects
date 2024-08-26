package MainSystem;

import java.util.List;
import java.util.Scanner;

import tables.AllCompanyBackdata;
import tables.UserInfo;
import tables.UserMoneyHistory;
import tables.AllCompany;

public class BuyFrame {

	public BuyFrame(UserInfo userInfo, List<AllCompanyBackdata> findCompanyBackdata, String companyName,
			List<AllCompany> allCompanyList, List<AllCompanyBackdata> allCompanyBackdataList,
			List<UserMoneyHistory> userMoneyHistory) {

		// 수량*주가 만큼의 돈이 있어야됨 조건문 추가

		System.out.println("\n==========================");
		System.out.printf("회사 이름 : %s\n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyName());
		System.out.printf("현재 주가 : %d원\n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStockPrice());
		System.out.printf("현재 주가 수량 : %d 주 \n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStockCount());
		if (findCompanyBackdata.get(userInfo.getUser_Date() - 1).getDate() == 1) {
			System.out.printf("전일 대비  0원  \n");
		} else {

			System.out.printf("전일 대비  %d원  \n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStockPrice()
					- findCompanyBackdata.get(userInfo.getUser_Date() - 2).getCompanyStockPrice());
		}
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

			for (AllCompany a : allCompanyList) {
				if (a.getCompanyName().equals(companyName)) {
					buyStockCount = a.getCompanyStockCount() - buyStock;
					buyStockPrice = a.getCompanyStockPrice();
					a.setCompanyStockCount(buyStockCount);
					allCompanyBackdataList
							.add(new AllCompanyBackdata(companyName, a.getCompanyStockPrice(), a.getCompanyStockCount(),
									userInfo.getUser_ID(), userInfo.getUser_SaveData(), userInfo.getUser_Date()));

					for (UserMoneyHistory u : userMoneyHistory) {
						if (u.getUser_Stock().equals(companyName)) {
							u.setBuyPrice(buyStockPrice);
							u.setStock_Count(buyStock);
							u.setStock_Price_now(a.getCompanyStockPrice());
							userInfo.setUser_Money(userInfo.getUser_Money() - (buyStockPrice * buyStock));
//							u.set

						}
					}

//					userMoneyHistory.get(0).setBuyPrice(a.getCompanyStockPrice());
//					userMoneyHistory.get(0).
				}

			}

		}

	}
}