package loginUI;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import com.sun.corba.se.impl.protocol.AddressingDispositionException;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import DAO.UserInfoDAO;
import DAO.UserMoneyHistoryDAO;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.CompanyInfo;
import tables.UserInfo;
import tables.UserMoneyHistory;

public class Login extends JDialog {
	private JTextField tfId;
	private JPasswordField tfPw;

	List<UserInfo> userList = new ArrayList<>();
	List<List<UserMoneyHistory>> userMoneyHistoryListList = new ArrayList<>();
	List<CompanyInfo> companyInfoList = new ArrayList<>();
	List<AllCompanyBackdata> allCompanyBackdataList = new ArrayList<>();
	List<AllCompany> allCompanyList = new ArrayList<>();
	UserInfoDAO UserInfoDAO = new UserInfoDAO();
	UserMoneyHistoryDAO userMoneyHistoryDAO = new UserMoneyHistoryDAO();
	AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();

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

	public Login() {
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pnlNorth = new JPanel();
		getContentPane().add(pnlNorth);

		JPanel pnlSouth = new JPanel();
		pnlSouth.setLayout(new GridLayout(0, 1, 0, 0));

		getContentPane().add(pnlSouth);

		JPanel pnlId = new JPanel();
		pnlSouth.add(pnlId);
		pnlId.setLayout(null);

		JLabel lblId = new JLabel("아이디");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(128, 8, 84, 15);
		pnlId.add(lblId);

		tfId = new JTextField(10);
		tfId.setBounds(224, 5, 116, 21);
		pnlId.add(tfId);

		JPanel pnlPw = new JPanel();
		pnlSouth.add(pnlPw);
		pnlPw.setLayout(null);

		JLabel lblPw = new JLabel("비밀번호");
		lblPw.setBounds(130, 8, 86, 15);
		pnlPw.add(lblPw);

		tfPw = new JPasswordField(10);
		tfPw.setBounds(224, 5, 116, 21);
		pnlPw.add(tfPw);

		JPanel pnlBtn = new JPanel();
		pnlSouth.add(pnlBtn);

		JButton btnLogin = new JButton("로그인");
		btnLogin.setFocusable(false);
		pnlBtn.add(btnLogin);

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String user_ID = tfId.getText();
				String user_PW = tfPw.getText();
				UserInfo userInfoIDChk = UserInfoDAO.findByID(user_ID);

				if (userInfoIDChk == null) {
					JOptionPane.showMessageDialog(Login.this, "가입된 회원 정보가 없습니다.");
				} else {
					UserInfo userInfo = UserInfoDAO.findByIDAndPW(user_ID, user_PW);
					if (userInfo != null) {
						SaveData saveData = new SaveData(userInfo);
						saveData.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(Login.this, " 비밀번호를 확인해주세요.");
					}
				}

			}
		});

		JButton btnBack = new JButton("뒤로가기");
		btnBack.setFocusable(false);
		pnlBtn.add(btnBack);

		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}

	// 텍스트필드를 초기화 하는 부분
	private void initializeFields() {
		tfId.setText("");
		tfPw.setText("");
	}

	// setVisible을 했을 때 텍스트 필드를 모두 초기화 해준 후 setVisible 실행
	@Override
	public void setVisible(boolean b) {
		if (b) {
			initializeFields(); // 다이얼로그가 열릴 때마다 초기화
		}
		super.setVisible(b);
	}

}
