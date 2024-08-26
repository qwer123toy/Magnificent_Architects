package MainSystem;

import java.util.List;
import java.util.Scanner;

import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.UserInfo;

public class SellFrame {
	public SellFrame(UserInfo userInfo, List<AllCompanyBackdata> findCompanyBackdata, String companyName,
			List<AllCompany> allCompanyList, List<AllCompanyBackdata> allCompanyBackdataList) {
		
		//해당 주식을 보유하고 있어야되며, 들고있는 수량만큼만 매도 가능
		
		System.out.println("\n==========================");
		System.out.printf("회사 이름 : %s\n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyName());
		System.out.printf("현재 주가 : %d원\n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStock());
		System.out.printf("현재 주가 수량 : %d 주 \n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStockCount());
		
		if (findCompanyBackdata.get(userInfo.getUser_Date() - 1).getDate() == 1) {
			System.out.printf("전일 대비  0원  \n");
		} else {

			System.out.printf("전일 대비  %d원  \n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStock()
					- findCompanyBackdata.get(userInfo.getUser_Date() - 2).getCompanyStock());
		}
		System.out.println("==========================");
		System.out.println("매도하실 수량을 입력하세요.");
		System.out.println("뒤로 가기를 원하시면 -1을 입력하세요.");
		System.out.print("입력 : ");
		Scanner sc = new Scanner(System.in);
		int buyStock = sc.nextInt();
		if (buyStock < 0) {
			return;
		} else {
			for (AllCompany a : allCompanyList) {
				if (a.getCompanyName().equals(companyName)) {
					a.setCompanyStockCount(a.getCompanyStockCount() + buyStock);
					allCompanyBackdataList.add(new AllCompanyBackdata(companyName, a.getCompanyStock(), a.getCompanyStockCount(),
							userInfo.getUser_ID(), userInfo.getUser_SaveData(), userInfo.getUser_Date()));

				}

			}

		}

	}
}