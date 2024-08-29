package MainSystem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.CompanyInfo;
import tables.UserInfo;
import tables.UserInfoDays;
import tables.UserMoneyHistory;

public class MainProgram {
	Scanner scanner = new Scanner(System.in);
	List<UserInfo> userList = new ArrayList<>();
	List<List<UserMoneyHistory>> userMoneyHistoryListList = new ArrayList<>();
	List<CompanyInfo> companyInfoList = new ArrayList<>();
	List<AllCompanyBackdata> allCompanyBackdataList = new ArrayList<>();
	List<AllCompany> allCompanyList = new ArrayList<>();
	UserInfoDAO UserInfoDAO = new UserInfoDAO();
	UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
	int userCount = 0;
//	Connection conn = null;

	public MainProgram() {
//		companyInfoList.add(new CompanyInfo("A회사", "A 회사 정보", 100, "통신", "스마트폰"));
//		companyInfoList.add(new CompanyInfo("B회사", "B 회사 정보", 120, "건설", "아파트"));
//		companyInfoList.add(new CompanyInfo("C회사", "C 회사 정보", 130, "운송", "항공사"));
//		companyInfoList.add(new CompanyInfo("D회사", "D 회사 정보", 90, "철강", "철강"));
//		companyInfoList.add(new CompanyInfo("E회사", "E 회사 정보", 80, "화학", "화학제품"));
//		companyInfoList.add(new CompanyInfo("F회사", "F 회사 정보", 50, "반도체", "기계장비"));

		//		try {
//			Connection conn = DBUtil.getConnection("go_db");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
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
			UserInfo userInfo = UserInfoDAO.findByID(userName);
			if (userInfo != null) {
				System.out.println("중복된 아이디가 있어요.");
				chkDupID = true;
			}
//			for (UserInfo u : userList) {
//				if (u.getUser_ID().equals(userName)) {
//					System.out.println("중복된 아이디가 있어요.");
//					chkDupID = true;
//					break;
//				}
//			}
		}
		

		if (!chkDupID) {
			System.out.println("비밀번호를 입력하세요");
			System.out.print("입력 : ");
			String password = scanner.nextLine();
			// 일단 savedata는 1
			try {
//				List<UserInfo> loginUserInfoList = UserInfoDAO.checkExistIDAndPW(userName, password);
//				if (loginUserInfoList.size() == 0)
				UserInfoDAO.insert(userName, password, 1);
//				else if (loginUserInfoList.size() == 1)
//					UserInfoDAO.insert(userName, password, 2);
//				else if (loginUserInfoList.size() == 2)
//					UserInfoDAO.insert(userName, password, 3);
//				else {
//					System.out.println("한 아이디에는 3개까지만 저장 가능합니다.");
//				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			userList.add(new UserInfo(userName, 1, password, 100000, 0, 1, new UserInfoDays(userName, 1)));

			insertInfoByID(userName, 1);

//			userMoneyHistoryListList.add(new ArrayList<UserMoneyHistory>());
//
//			userMoneyHistoryListList.get(userCount).add(new UserMoneyHistory(userName, 1, "A 회사", 0, 0, 0, 0, 0, 0, 1));
//			userMoneyHistoryListList.get(userCount).add(new UserMoneyHistory(userName, 1, "B 회사", 0, 0, 0, 0, 0, 0, 1));
//			userMoneyHistoryListList.get(userCount).add(new UserMoneyHistory(userName, 1, "C 회사", 0, 0, 0, 0, 0, 0, 1));
//			userMoneyHistoryListList.get(userCount).add(new UserMoneyHistory(userName, 1, "D 회사", 0, 0, 0, 0, 0, 0, 1));
//			userMoneyHistoryListList.get(userCount).add(new UserMoneyHistory(userName, 1, "E 회사", 0, 0, 0, 0, 0, 0, 1));
//			userMoneyHistoryListList.get(userCount).add(new UserMoneyHistory(userName, 1, "F 회사", 0, 0, 0, 0, 0, 0, 1));
//
//			allCompanyBackdataList.add(new AllCompanyBackdata("A 회사", 100, 200, userName, 1, 1));
//			allCompanyBackdataList.add(new AllCompanyBackdata("B 회사", 150, 300, userName, 1, 1));
//
//			allCompanyList.add(new AllCompany("A 회사", 100, 200, userName, 1, 1, companyInfoList.get(0)));
//			allCompanyList.add(new AllCompany("B 회사", 150, 300, userName, 1, 1, companyInfoList.get(1)));
			userCount++;

		}

	}

	private void insertInfoByID(String userName, int saveData) {
		try {
			userMoneyHistoryDAO.insert(userName, saveData, "A 회사", 0, 0, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userName, saveData, "B 회사", 0, 0, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userName, saveData, "C 회사", 0, 0, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userName, saveData, "D 회사", 0, 0, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userName, saveData, "E 회사", 0, 0, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userName, saveData, "F 회사", 0, 0, 0, 0, 0, 0, 1);
			allCompanyDAO.insert("A 회사", 100, 200, userName, saveData, 1);
			allCompanyDAO.insert("B 회사", 150, 300, userName, saveData, 1);
			allCompanyBackdataDAO.insert("A 회사", 100, 200, userName, saveData, 1);
			allCompanyBackdataDAO.insert("B 회사", 150, 300, userName, saveData, 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loginUser() {
		System.out.print("아이디 : ");
		String id = scanner.next();
		scanner.nextLine();
		System.out.print("비밀번호 : ");
		String password = scanner.next();

		try {
			List<UserInfo> loginUserInfoList = UserInfoDAO.checkExistIDAndPW(id, password);
			if (loginUserInfoList == null) {
				System.out.println("아이디 혹은 비밀번호가 틀렸습니다.");
			} else {
				int chkSaveData1 = 0;
				String userName1 = null;
				int userDate1 = 0;

				int chkSaveData2 = 0;
				String userName2 = null;
				int userDate2 = 0;

				int chkSaveData3 = 0;
				String userName3 = null;
				int userDate3 = 0;

				for (UserInfo u : loginUserInfoList) {
					if (u.getUser_SaveData() == 1) {
						chkSaveData1 = 1;
						userName1 = u.getUser_ID();
						userDate1 = u.getUser_Date();
					}
					if (u.getUser_SaveData() == 2) {
						chkSaveData2 = 2;
						userName2 = u.getUser_ID();
						userDate2 = u.getUser_Date();
					}
					if (u.getUser_SaveData() == 3) {
						chkSaveData3 = 3;
						userName3 = u.getUser_ID();
						userDate3 = u.getUser_Date();
					}
				}
				int userFindCount = 0;
				for (UserInfo u : userList) {
					if (u.getUser_ID().equals(id) && u.getUser_PW().equals(password)) {
						break;
					}
					userFindCount++;

				}
				System.out.println("\n======================");
				System.out.println("저장 데이터를 선택하세요.");
				System.out.printf("저장 데이터 1 : %s\n", (chkSaveData1 == 1) ? userName1 + ", " + userDate1 + "일차" : "없음");
				System.out.printf("저장 데이터 2 : %s\n", (chkSaveData2 == 2) ? userName2 + ", " + userDate2 + "일차" : "없음");
				System.out.printf("저장 데이터 3 : %s\n", (chkSaveData3 == 3) ? userName3 + ", " + userDate3 + "일차" : "없음");
				System.out.print("입력 : ");

				int chooseData = scanner.nextInt();
				if (chooseData < 1 || chooseData > 3)
					System.out.println("올바른 입력을 하세요");
				else if (chooseData == 1) {
					List<UserMoneyHistory> umhList = userMoneyHistoryDAO.findByID(id, 1);
//					List<AllCompany> acList = allCompanyDAO.findAllByID(id, 1);
					List<AllCompanyBackdata> acbdList = allCompanyBackdataDAO.findAllByID(id, 1);

					StockFrame stockFrame1 = new StockFrame(loginUserInfoList.get(0));
					userFindCount = -1;
					return;
				} else if (chooseData == 2 && chkSaveData2 == 0) {
					UserInfoDAO.insert(id, password, 2);
					insertInfoByID(id, 2);
					List<UserMoneyHistory> umhList = userMoneyHistoryDAO.findByID(id, 2);
					List<AllCompany> acList = allCompanyDAO.findAllByID(id, 2);
					List<AllCompanyBackdata> acbdList = allCompanyBackdataDAO.findAllByID(id, 2);

					StockFrame stockFrame2 = new StockFrame(loginUserInfoList.get(1));
					userFindCount = -1;
					return;
				}
				else if (chooseData == 2 && chkSaveData2 == 2) {
					List<UserMoneyHistory> umhList = userMoneyHistoryDAO.findByID(id, 2);
					List<AllCompany> acList = allCompanyDAO.findAllByID(id, 2);
					List<AllCompanyBackdata> acbdList = allCompanyBackdataDAO.findAllByID(id, 2);

					StockFrame stockFrame2 = new StockFrame(loginUserInfoList.get(1));
					userFindCount = -1;
					return;
				}
				
				else if (chooseData == 3 && chkSaveData3 == 0) {
					UserInfoDAO.insert(id, password, 3);
					insertInfoByID(id, 3);
					List<UserMoneyHistory> umhList = userMoneyHistoryDAO.findByID(id, 3);
					List<AllCompany> acList = allCompanyDAO.findAllByID(id, 3);
					List<AllCompanyBackdata> acbdList = allCompanyBackdataDAO.findAllByID(id, 3);

					StockFrame stockFrame3 = new StockFrame(loginUserInfoList.get(2));
					userFindCount = -1;
					return;
				}
				else if (chooseData == 3 && chkSaveData3 == 3) {
					List<UserMoneyHistory> umhList = userMoneyHistoryDAO.findByID(id, 3);
					List<AllCompany> acList = allCompanyDAO.findAllByID(id, 3);
					List<AllCompanyBackdata> acbdList = allCompanyBackdataDAO.findAllByID(id, 3);

					StockFrame stockFrame3 = new StockFrame(loginUserInfoList.get(2));
					userFindCount = -1;
					return;
				}
//				else if (chooseData == 3) {
//					StockFrame stockFrame3 = new StockFrame(loginUserInfoList.get(0), 1,
//							userMoneyHistoryListList.get(userFindCount), allCompanyList, allCompanyBackdataList);
//					userFindCount = -1;
//					return;
//				}

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

//		int userFindCount = 0;
//		for (UserInfo u : userList) {
//			if (u.getUser_ID().equals(id) && u.getUser_PW().equals(password)) {
//				System.out.println("\n======================");
//				System.out.println("저장 데이터를 선택하세요.");
//				System.out.printf("저장 데이터 1 : %s\n", (u.getUser_SaveData() == 1) ? u.getUser_SaveData() : "없음");
//				System.out.printf("저장 데이터 2 : %s\n", (u.getUser_SaveData() == 2) ? u.getUser_SaveData() : "없음");
//				System.out.printf("저장 데이터 3 : %s\n", (u.getUser_SaveData() == 3) ? u.getUser_SaveData() : "없음");
//				System.out.print("입력 : ");
//
//				try {
//					int chooseData = scanner.nextInt();
//					if (chooseData < 1 && chooseData > 3)
//						System.out.println("올바른 입력을 하세요");
//					else if (chooseData == 1) {
//						StockFrame stockFrame1 = new StockFrame(u, u.getUser_SaveData(),
//								userMoneyHistoryListList.get(userFindCount), allCompanyList, allCompanyBackdataList);
//						userFindCount = -1;
//						return;
//					} else if (chooseData == 2) {
//						StockFrame stockFrame2 = new StockFrame(u, u.getUser_SaveData(),
//								userMoneyHistoryListList.get(userFindCount), allCompanyList, allCompanyBackdataList);
//						userFindCount = -1;
//						return;
//					} else if (chooseData == 3) {
//						StockFrame stockFrame3 = new StockFrame(u, u.getUser_SaveData(),
//								userMoneyHistoryListList.get(userFindCount), allCompanyList, allCompanyBackdataList);
//						userFindCount = -1;
//						return;
//					}
//
//				} catch (InputMismatchException e) {
//					System.out.println("숫자만 입력하세요");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			} else if (u.getUser_ID().equals(id) && !u.getUser_PW().equals(password)) {
//				System.out.println("비밀번호가 틀렸습니다.");
//				return;
//			}
//			userFindCount++;
//		}
//		if (userFindCount != -1) {
//			System.out.println("해당하는 아이디가 없습니다.");
//		}
	}

	public void FirstFrame() {

//		System.out.printf("보유하신 금액은 현재 %d원입니다.\n", money);
		System.out.println("\n======================");
		System.out.println("하고싶은 작업을 선택하세요.");
		System.out.println("======================");
		System.out.println("1. 회원가입하기");
		System.out.println("2. 로그인하기");
		System.out.println("3. 랭킹보기");
		System.out.println("0. 종료");
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
			case 0:
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
