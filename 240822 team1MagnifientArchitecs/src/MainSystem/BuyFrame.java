package MainSystem;

import java.util.List;
import java.util.Scanner;

public class BuyFrame {

	public BuyFrame(UserInfo userInfo, List<AllCompanyBackdata> findCompanyBackdata, String companyName) {

		System.out.println("\n==========================");
		System.out.printf("회사 이름 : %s\n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyName());
		System.out.printf("현재 주가 : %d원\n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStock());
		System.out.printf("현재 주가 수량 : %d 주 \n",
				findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStockCount());
		if (findCompanyBackdata.get(userInfo.getUser_Date() - 1).getDate() == 1) {
			System.out.printf("전일 대비  0원  \n");
		} else {
			System.out.printf("전일 대비  %d원  \n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStock()
					- findCompanyBackdata.get(userInfo.getUser_Date() - 2).getCompanyStock());
		}
		System.out.println("==========================");
		System.out.println("구매하실 수량을 입력하세요.");
		System.out.println("뒤로 가기를 원하시면 -1을 입력하세요.");
		System.out.print("입력 : ");
		Scanner sc = new Scanner(System.in);
		int buyStock = sc.nextInt();
		if (buyStock < 0) {
			return;
		}
		else{
			
		}

	}
}