package MainSystem;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserFrame {

	public UserFrame(UserInfo userInfo, List<UserMoneyHistory> userMoneyHistory) {
		while (true) {
			System.out.printf("반갑습니다. %s 회원님\n", userInfo.getUser_ID());
			System.out.printf("현재 잔고는 %d원입니다.\n", userInfo.getUser_Money());

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

//		System.out.printf("현재 주식 잔고는 %d원입니다\n", userInfo.get);
			System.out.println("보유 주식 현황입니다.");

			// 일단 0번째 인덱스로
			System.out.printf("%s, 보유주식 : %d 주, 구매 금액  %d원, 보유 금액 %d원, 현재가 %d원,  현재 수익 %d원, 현재 수익률 %f\n",
					userMoneyHistory.get(0).getUser_Stock(), userMoneyHistory.get(0).getStock_Count(),
					userMoneyHistory.get(0).getBuyPrice(),
					userMoneyHistory.get(0).getMy_Stock_Money() * userMoneyHistory.get(0).getStock_Count(),
					userMoneyHistory.get(0).getStock_Price_now(), userMoneyHistory.get(0).getMy_Stock_Money(),
					userMoneyHistory.get(0).getMy_Money_Rate());
			
			if (userMoneyHistory.get(1).getStock_Count() > 0) {
				System.out.printf("%s, 보유주식 : %d 주, 구매 금액  %d원, 보유 금액 %d원, 현재가 %d원,  현재 수익 %d원, 현재 수익률 %f\n",
						userMoneyHistory.get(1).getUser_Stock(), userMoneyHistory.get(1).getStock_Count(),
						userMoneyHistory.get(1).getBuyPrice(),
						userMoneyHistory.get(1).getMy_Stock_Money() * userMoneyHistory.get(1).getStock_Count(),
						userMoneyHistory.get(1).getStock_Price_now(), userMoneyHistory.get(1).getMy_Stock_Money(),
						userMoneyHistory.get(1).getMy_Money_Rate());
			}

			System.out.println("\n=======================");
			System.out.println("어떤 작업을 실행하시겠습니까?");
			System.out.println("1. 메인화면 보기");
			System.out.println("2. 거래 내역 보기");
			System.out.println("3. 오늘의 뉴스 보기");
			System.out.println("0. 종료");
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
