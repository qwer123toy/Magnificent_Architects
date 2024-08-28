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
		String example = "3일차, 현재 보유금액 23400원";

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

		// 버튼1을 누르면 버튼2에 텍스트가 삽입
		btnSave1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BaseMainFrame baseMainFrame = new BaseMainFrame(userinfoList.get(0));
				baseMainFrame.setVisible(true);
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
