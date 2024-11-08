package loginUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.CompanyInfo;
import tables.UserInfo;
import tables.UserMoneyHistory;
import ui.BaseMainFrame;

public class SaveData extends JDialog {
	
	List<UserInfo> userList = new ArrayList<>();
	List<List<UserMoneyHistory>> userMoneyHistoryListList = new ArrayList<>();
	List<CompanyInfo> companyInfoList = new ArrayList<>();
	List<AllCompanyBackdata> allCompanyBackdataList = new ArrayList<>();
	List<AllCompany> allCompanyList = new ArrayList<>();
	UserInfoDAO userInfoDAO = new UserInfoDAO();
	UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();
	List<UserInfo> userinfoList;
	
	private void insertInfoByID(UserInfo userInfo, int saveData) {
		try {
			userInfoDAO.insert(userInfo.getUser_ID(), userInfo.getUser_PW(), saveData);
			userMoneyHistoryDAO.insert(userInfo.getUser_ID(), saveData, "A 회사", 0, 100, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userInfo.getUser_ID(), saveData, "B 회사", 0, 150, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userInfo.getUser_ID(), saveData, "C 회사", 0, 400, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userInfo.getUser_ID(), saveData, "D 회사", 0, 70, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userInfo.getUser_ID(), saveData, "E 회사", 0, 2500, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userInfo.getUser_ID(), saveData, "F 회사", 0, 8200, 0, 0, 0, 0, 1);
			
			allCompanyDAO.insert("A 회사", 100, 200, userInfo.getUser_ID(), saveData, 1);
			allCompanyDAO.insert("B 회사", 150, 300, userInfo.getUser_ID(), saveData, 1);
			allCompanyDAO.insert("C 회사", 400, 1000, userInfo.getUser_ID(), saveData, 1);
			allCompanyDAO.insert("D 회사", 70, 300, userInfo.getUser_ID(), saveData, 1);
			allCompanyDAO.insert("E 회사", 2500, 5000, userInfo.getUser_ID(), saveData, 1);
			allCompanyDAO.insert("F 회사", 8200, 500, userInfo.getUser_ID(), saveData, 1);
			
			allCompanyBackdataDAO.insert("A 회사", 100, 200, userInfo.getUser_ID(), saveData, 1);
			allCompanyBackdataDAO.insert("B 회사", 150, 300, userInfo.getUser_ID(), saveData, 1);
			allCompanyBackdataDAO.insert("C 회사", 400, 1000, userInfo.getUser_ID(), saveData, 1);
			allCompanyBackdataDAO.insert("D 회사", 70, 300, userInfo.getUser_ID(), saveData, 1);
			allCompanyBackdataDAO.insert("E 회사", 2500, 5000, userInfo.getUser_ID(), saveData, 1);
			allCompanyBackdataDAO.insert("F 회사", 8200, 500, userInfo.getUser_ID(), saveData, 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public SaveData(UserInfo userInfo) {
		// 전체 배경색 조정
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 460);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false); // 앱솔루트 만든 후, 크기수정불가
		
		userinfoList = new ArrayList<>();
		try {
			userinfoList = userInfoDAO.checkExistIDAndPW(userInfo.getUser_ID(), userInfo.getUser_PW());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 첫 번째 패널, 버튼
		JButton btnSave1 = btnSetting("저장 데이터 1", userinfoList.get(0).getUser_Money() +"원, " +
				userinfoList.get(0).getUser_Date() + "일차", 10);
		
		// 두 번째 패널, 버튼
		JButton btnSave2 = btnSetting("저장 데이터2", userinfoList.size()>=2 ? 
				userinfoList.get(1).getUser_Money() +"원, " +
				userinfoList.get(1).getUser_Date() + "일차"
				:"없음" , 118);
		
		// 세 번째 패널, 버튼
		JButton btnSave3 = btnSetting("저장 데이터3", userinfoList.size()>=3 ? 
				userinfoList.get(2).getUser_Money() +"원, " +
				userinfoList.get(2).getUser_Date() + "일차"
				:"없음" , 228);

		// 뒤로가기 버튼
		JButton btnBack = new JButton("뒤로가기");
		// (x좌표, y좌표, 가로길이, 세로길이)
		btnBack.setBounds(135, 355, 165, 45);
		add(btnBack);

		// 버튼1 누르면 실행됨
		btnSave1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BaseMainFrame baseMainFrame = new BaseMainFrame(userinfoList.get(0));
				baseMainFrame.setVisible(true);
				dispose();
			}
		});
		
		btnSave2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String chkSaveData = btnSave2.getText();
				if(chkSaveData.equals("없음")) {
					insertInfoByID(userinfoList.get(0), 2);
					try {
						userinfoList = userInfoDAO.checkExistIDAndPW(userInfo.getUser_ID(), userInfo.getUser_PW());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				BaseMainFrame baseMainFrame = new BaseMainFrame(userinfoList.get(1));
				baseMainFrame.setVisible(true);
				dispose();
			}
		});
		
		btnSave3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String chkSaveData = btnSave3.getText();
				if(chkSaveData.equals("없음")) {
					insertInfoByID(userinfoList.get(0), 3);
					try {
						userinfoList = userInfoDAO.checkExistIDAndPW(userInfo.getUser_ID(), userInfo.getUser_PW());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				BaseMainFrame baseMainFrame = new BaseMainFrame(userinfoList.get(2));
				baseMainFrame.setVisible(true);
				dispose();
			}
		});
		
		
	}

	// 버튼 셋팅 메서드
	private JButton btnSetting(String lblText, String btnText, int y) {
		JPanel pnl = new JPanel();
		pnl.setBackground(Color.WHITE);
		pnl.setBounds(12, y, 410, 110); // 개별 패널에 버튼과 라벨텍스트 포함
		pnl.setLayout(null);
		getContentPane().add(pnl);

		JLabel lbl = new JLabel(lblText);
		lbl.setBounds(12, 40, 83, 29);
		pnl.add(lbl);

		JButton btn = new JButton(btnText);
		btn.setBounds(94, 9, 304, 90);
		pnl.add(btn);

		return btn; // 버튼을 반환
	}
}
