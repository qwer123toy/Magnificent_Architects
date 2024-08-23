package MainSystem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainProgram {
	Scanner scanner = new Scanner(System.in);
	List<UserInfo> userList = new ArrayList<>();

	public void makeUser() {

		boolean chkDupID = true;
		String userName = null;
		while (chkDupID) {
			System.out.println("아이디를 입력하세요.");
			System.out.print("입력 : ");
			userName = scanner.next();
			scanner.nextLine();
			chkDupID = false;
			for (UserInfo u : userList) {
				if (u.getUser_ID().equals(userName)) {
					System.out.println("중복된 아이디가 있어요.");
					chkDupID = true;
					break;
				}
			}
		}
		if (!chkDupID) {
			System.out.println("비밀번호를 입력하세요");
			System.out.print("입력 : ");
			String password = scanner.nextLine();
			userList.add(new UserInfo(userName, password, 100000, 0, 1));
		}

	}

	public void FirstFrame(int money) {

		System.out.printf("보유하신 금액은 현재 %d원입니다.\n", money);
		System.out.println("하고싶은 작업을 선택하세요.");
		System.out.println("======================");
		System.out.println("1. 회원가입하기");
		System.out.println("2. 로그인하기");
		System.out.println("3. 랭킹보기");
		System.out.println("4. 종료");
		System.out.print("입력 : ");
		try {
			int choose = scanner.nextInt();
			switch (choose) {
			case 1:
				System.out.println("회원가입을 실행합니다.");
				makeUser();
				break;
			case 2:
				System.out.println("로그인을 실행합니다.");
				break;
			case 3:
				System.out.println("랭킹보기를 실행합니다.");
				break;
			case 4:
				System.exit(0);
				break;
			default:
				System.out.println("올바른 숫자를 입력하세요.");
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력하세요");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		MainProgram main = new MainProgram();
		System.out.println("가상 주식 프로그램을 시작합니다.");
		while (true) {
			main.FirstFrame(100000);
		}
	}
}
