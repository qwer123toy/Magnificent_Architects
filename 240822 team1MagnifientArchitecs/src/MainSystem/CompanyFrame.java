package MainSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.UserInfo;

public class CompanyFrame {
	public CompanyFrame(UserInfo userInfo, List<AllCompany> allCompanyList, List<AllCompanyBackdata> allCompanyBackdataList,
			List<AllCompanyBackdata> findCompanyBackdata, String companyName, int companyIndex) {

//		List<AllCompanyBackdata> findCompanyBackdata = new ArrayList<>();
//
//		for (AllCompanyBackdata acbd : allCompanyBackdataList) {
//			if (acbd.getSimulation_ID().equals(userInfo.getUser_ID())
//					&& acbd.getSimulation_ID_SaveData() == userInfo.getUser_SaveData()
//					&& acbd.getCompanyName().equals(companyName)) {
//				findCompanyBackdata.add(acbd);
//			}
//		}

		System.out.printf("회사 이름 : %s\n", allCompanyList.get(companyIndex).getCompanyName());
		System.out.printf("현재 주가 : %d원\n", allCompanyList.get(companyIndex).getCompanyStockPrice());
		System.out.printf("현재 주가 수량 : %d 주 \n", allCompanyList.get(companyIndex).getCompanyStockCount());
		
		if (findCompanyBackdata.get(userInfo.getUser_Date() - 1).getDate() == 1) {
			System.out.printf("전일 대비  0원  \n");
			System.out.printf("전일 대비  0%% \n");
		} else {
			System.out.printf("전일 대비  %d원  \n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStockPrice()
					- findCompanyBackdata.get(userInfo.getUser_Date() - 2).getCompanyStockPrice());
			System.out.printf("전일 대비 %.2f%%\n", ((findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStockPrice())
					/ (findCompanyBackdata.get(userInfo.getUser_Date() - 2).getCompanyStockPrice()) - 1) * 100);
		}

		while (true) {
			System.out.println("\n==============");
			System.out.println("어떤 작업을 수행하시겠습니까?");
			System.out.println("1. 매수하기");
			System.out.println("2. 매도하기");
			System.out.println("3. 뒤로가기");
			Scanner sc = new Scanner(System.in);
			int choose = sc.nextInt();

			switch (choose) {
			case 1:
				System.out.println("매수하기를 선택하셨습니다.");
				BuyFrame buyFrame = new BuyFrame(userInfo, findCompanyBackdata, companyName,
									allCompanyList,allCompanyBackdataList);
				break;
			case 2:
				System.out.println("매도하기를 선택하셨습니다.");
				break;
			case 3:
				System.out.println("뒤로가기를 선택하셨습니다.");
				return;
			default:
				System.out.println("올바른 번호를 입력하세요.");
				break;
			}
		}

	}
}
