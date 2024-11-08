

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import loginUI.Login;
import loginUI.SignUP;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.CompanyInfo;
import tables.UserInfo;
import tables.UserMoneyHistory;

class MainGUI extends JFrame {
	Login login = new Login();
	SignUP signUP = new SignUP();

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

	public MainGUI() {
		getContentPane().setBackground(Color.WHITE);
		setBounds(0, 0, 450, 460);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false); // 앱솔루트 만든 후, 크기수정불가

		JButton btnNewStart = btnSetting("새로 시작", 60);
		JButton btnLogin = btnSetting("로그인", 130);
		JButton btnRank = btnSetting("랭킹조회", 200);
		JButton btnEnd = btnSetting("종료", 270);
		
		btnEnd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// 로그인 버튼
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login.setVisible(true);
			}
		});

		btnNewStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				signUP.setVisible(true);

			}
		});
	}

	public JButton btnSetting(String butText, int y) {
		JButton btn = new JButton(butText);
		btn.setBounds(135, y, 200, 50);
		btn.setFocusable(false); // 버튼 포커스 제거
		add(btn);

		return btn;
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
			e.printStackTrace();
		}
	}

}

public class StartMainUI {
	public static void main(String[] args) {
		MainGUI init = new MainGUI();
		init.setVisible(true);
	}
}
