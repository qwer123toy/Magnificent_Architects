package MainSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StockFrame {
	public StockFrame(UserInfo userInfo, int SaveData, List<UserMoneyHistory> userMoneyHistory, List<AllCompany> allCompanyList,
			List<AllCompanyBackdata> allCompanyBackdataList) {
		System.out.println("로그인 되었습니다.");
		while (true) {
			System.out.println("\n====================");
			System.out.printf("%d일차 진행 중\n", userInfo.getUser_Date());
			System.out.printf("반갑습니다. %s 회원님\n", userInfo.getUser_ID());
//			System.out.printf("현재 잔고는 %d원입니다.\n", userInfo.getUser_Money());

			System.out.printf("현재 원금은 %d원입니다.\n",
					userMoneyHistory.get(0).getBuyPrice() * userMoneyHistory.get(0).getStock_Count()
							+ userMoneyHistory.get(1).getBuyPrice() * userMoneyHistory.get(1).getStock_Count());

			System.out.printf("현재 수익은 %d원입니다.\n",
					userMoneyHistory.get(0).getMy_Stock_Money() + userMoneyHistory.get(1).getMy_Stock_Money());

			double stockMoneyRate = 0;
			if (userMoneyHistory.get(1).getBuyPrice() * userMoneyHistory.get(1).getStock_Count() != 0) {
				stockMoneyRate = (userMoneyHistory.get(0).getMy_Stock_Money() + userMoneyHistory.get(1).getMy_Stock_Money())
						/ (userMoneyHistory.get(0).getBuyPrice() * userMoneyHistory.get(0).getStock_Count()
								+ userMoneyHistory.get(1).getBuyPrice() * userMoneyHistory.get(1).getStock_Count());
			}

			System.out.printf("현재 수익률은 %f%%입니다.\n", stockMoneyRate);

			System.out.printf("현재 보유 금액은 %d원입니다\n",
					userMoneyHistory.get(0).getMy_Stock_Money() * userMoneyHistory.get(0).getStock_Count()
							+ userMoneyHistory.get(1).getMy_Stock_Money() * userMoneyHistory.get(1).getStock_Count());

			List<AllCompanyBackdata> findACompanyBackdata = new ArrayList<>();
			List<AllCompanyBackdata> findBCompanyBackdata = new ArrayList<>();
			
			findACompanyBackdata = findCompany(userInfo, allCompanyBackdataList, "A 회사");
			findBCompanyBackdata = findCompany(userInfo, allCompanyBackdataList, "B 회사");
			
//			extracted(userInfo, allCompanyBackdataList,"C 회사");
//			extracted(userInfo, allCompanyBackdataList,"D 회사");
//			extracted(userInfo, allCompanyBackdataList,"E 회사");

			showCompanyData(userInfo, allCompanyList, findACompanyBackdata, 0);
			showCompanyData(userInfo, allCompanyList, findBCompanyBackdata, 1);

			System.out.println("\n=======================");
			System.out.println("어떤 작업을 실행하시겠습니까?");
			System.out.println("1. A 회사 정보 보기");
			System.out.println("2. B 회사 정보 보기");
			System.out.println("3. 내정보 보기");
			System.out.println("4. 오늘의 뉴스 보기");
			System.out.println("5. 장 마감하기");
			System.out.println("0. 종료하기");
			System.out.print("입력 : ");

			try {
				Scanner sc = new Scanner(System.in);
				int choose = sc.nextInt();
				switch (choose) {
				case 1:
					CompanyFrame companyFrameA = new CompanyFrame(userInfo, allCompanyList, allCompanyBackdataList,
							findACompanyBackdata, "A 회사", 0);
					break;
				case 2:
					CompanyFrame companyFrameB = new CompanyFrame(userInfo, allCompanyList, allCompanyBackdataList,
							findBCompanyBackdata, "B 회사", 1);
					break;
				case 3:
					System.out.println("내 정보 보기를 선택하셨습니다.");
					UserFrame userFrame = new UserFrame(userInfo, userMoneyHistory);
					break;
				case 4:
					break;
				case 5:
					break;
				case 0:
					return;
				default:
					System.out.println("올바른 입력을 하세요");
					break;

				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// 회사 별 데이터를 불러와서 보여줌
	private void showCompanyData(UserInfo userInfo, List<AllCompany> allCompanyList, List<AllCompanyBackdata> findCompanyBackdata,
			int companyIndex) {
		System.out.printf("회사 이름 : %s\n", allCompanyList.get(companyIndex).getCompanyName());
		System.out.printf("현재 주가 : %d원\n", allCompanyList.get(companyIndex).getCompanyStock());
		System.out.printf("현재 주가 수량 : %d 주 \n",
				allCompanyList.get(companyIndex).getCompanyStockCount());
		
		if (allCompanyList.get(userInfo.getUser_Date() - 1).getDate() == 1) {
			System.out.printf("전일 대비  0원  \n");
			System.out.printf("전일 대비  0%%  \n");
		} else {
			System.out.printf("전일 대비  %d원  \n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStock()
					- findCompanyBackdata.get(userInfo.getUser_Date() - 2).getCompanyStock());
			System.out.printf("전일 대비  %d원  \n", findCompanyBackdata.get(userInfo.getUser_Date() - 1).getCompanyStock()
					- findCompanyBackdata.get(userInfo.getUser_Date() - 2).getCompanyStock());

		}
	}

	// 회사 백데이터에서 특정 회사의 백데이터만 불러오기
	private List<AllCompanyBackdata> findCompany(UserInfo userInfo, List<AllCompanyBackdata> allCompanyBackdataList,
			String companyName) {
		List<AllCompanyBackdata> findACompanyBackdata = new ArrayList<>();

		for (AllCompanyBackdata acbd : allCompanyBackdataList) {
			if (acbd.getSimulation_ID().equals(userInfo.getUser_ID())
					&& acbd.getSimulation_ID_SaveData() == userInfo.getUser_SaveData()
					&& acbd.getCompanyName().equals(companyName)) {
				findACompanyBackdata.add(acbd);
			}
		}
		return findACompanyBackdata;
	}
}
