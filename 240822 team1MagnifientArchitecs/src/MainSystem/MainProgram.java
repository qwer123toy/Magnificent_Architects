package MainSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.CompanyInfo;
import tables.UserInfo;
import tables.UserInfoDays;
import tables.UserMoneyHistory;

public class MainProgram {
	Scanner scanner = new Scanner(System.in);
	List<UserInfo> userList = new ArrayList<>();
	List<List<UserMoneyHistory>> userMoneyHistoryList = new ArrayList<>();
	List<CompanyInfo> companyInfoList = new ArrayList<>();
	List<AllCompanyBackdata> allCompanyBackdataList = new ArrayList<>();
	List<AllCompany> allCompanyList = new ArrayList<>();
	
	public MainProgram() {
		companyInfoList.add(new CompanyInfo("A회사", "A 회사 정보", 100, "통신", "스마트폰"));
		companyInfoList.add(new CompanyInfo("B회사", "B 회사 정보", 120, "건설", "아파트"));
		companyInfoList.add(new CompanyInfo("C회사", "C 회사 정보", 130, "운송", "항공사"));
		companyInfoList.add(new CompanyInfo("D회사", "D 회사 정보", 90, "철강", "철강"));
		companyInfoList.add(new CompanyInfo("E회사", "E 회사 정보", 80, "화학", "화학제품"));
		companyInfoList.add(new CompanyInfo("F회사", "F 회사 정보", 50, "반도체", "기계장비"));

	}

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
			// 일단 savedata는 1
			userList.add(new UserInfo(userName, 1, password, 100000, 0, 1, new UserInfoDays(userName, 1)));
			userMoneyHistoryList.add(new ArrayList<UserMoneyHistory>());
			userMoneyHistoryList.get(0).add(new UserMoneyHistory(userName, 1, "A 회사", 0, 0, 0, 0, 0, 0, 1));
			userMoneyHistoryList.get(0).add(new UserMoneyHistory(userName, 1, "B 회사", 0, 0, 0, 0, 0, 0, 1));
			userMoneyHistoryList.get(0).add(new UserMoneyHistory(userName, 1, "C 회사", 0, 0, 0, 0, 0, 0, 1));
			userMoneyHistoryList.get(0).add(new UserMoneyHistory(userName, 1, "D 회사", 0, 0, 0, 0, 0, 0, 1));
			userMoneyHistoryList.get(0).add(new UserMoneyHistory(userName, 1, "E 회사", 0, 0, 0, 0, 0, 0, 1));
			userMoneyHistoryList.get(0).add(new UserMoneyHistory(userName, 1, "F 회사", 0, 0, 0, 0, 0, 0, 1));
			
			allCompanyBackdataList.add(new AllCompanyBackdata("A 회사", 100, 200, userName, 1, 1));
			allCompanyBackdataList.add(new AllCompanyBackdata("B 회사", 150, 300, userName, 1, 1));
			
			
			allCompanyList.add(new AllCompany("A 회사", 100, 200, userName, 1, 1, companyInfoList.get(0)));
			allCompanyList.add(new AllCompany("B 회사", 150, 300, userName, 1, 1, companyInfoList.get(1)));
			
		}

	}

	public void loginUser() {
		System.out.print("아이디 : ");
		String id = scanner.next();
		scanner.nextLine();
		System.out.print("비밀번호 : ");
		String password = scanner.next();

		int userFindCount = 0;
		for (UserInfo u : userList) {
			if (u.getUser_ID().equals(id) && u.getUser_PW().equals(password)) {
				System.out.println("\n======================");
				System.out.println("저장 데이터를 선택하세요.");
				System.out.printf("저장 데이터 1 : %s\n", (u.getUser_SaveData() == 1) ? u.getUser_SaveData() : "없음");
				System.out.printf("저장 데이터 2 : %s\n", (u.getUser_SaveData() == 2) ? u.getUser_SaveData() : "없음");
				System.out.printf("저장 데이터 3 : %s\n", (u.getUser_SaveData() == 3) ? u.getUser_SaveData() : "없음");
				System.out.print("입력 : ");

				try {
					int chooseData = scanner.nextInt();
					if (chooseData < 1 && chooseData > 3)
						System.out.println("올바른 입력을 하세요");
					else if(chooseData ==1){
						StockFrame stockFrame1 = new StockFrame(u, u.getUser_SaveData(), userMoneyHistoryList.get(userFindCount),
								 allCompanyList, allCompanyBackdataList);
						userFindCount = -1;
						return;
					}
					else if(chooseData ==2){
						StockFrame stockFrame2 = new StockFrame(u, u.getUser_SaveData(),userMoneyHistoryList.get(userFindCount),
								 allCompanyList, allCompanyBackdataList);
						userFindCount = -1;
						return;
					}
					else if(chooseData ==3){
						StockFrame stockFrame3 = new StockFrame(u, u.getUser_SaveData(), userMoneyHistoryList.get(userFindCount),
								 allCompanyList, allCompanyBackdataList);
						userFindCount = -1;
						return;
					}

				} catch (InputMismatchException e) {
					System.out.println("숫자만 입력하세요");
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (u.getUser_ID().equals(id) && !u.getUser_PW().equals(password)) {
				System.out.println("비밀번호가 틀렸습니다.");
				return;
			}
			userFindCount++;
		}
		if (userFindCount != -1) {
			System.out.println("해당하는 아이디가 없습니다.");
		}
	}

	public void FirstFrame() {

//		System.out.printf("보유하신 금액은 현재 %d원입니다.\n", money);
		System.out.println("\n======================");
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
				loginUser();
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
			main.FirstFrame();
		}
	}
}
