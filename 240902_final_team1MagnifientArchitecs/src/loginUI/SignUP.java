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

public class SignUP extends JDialog {
	private JTextField txtID;
	private JPasswordField txtIDPW;
	private JPasswordField txtIDPWChk;

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
			userMoneyHistoryDAO.insert(userName, saveData, "A 회사", 0, 100, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userName, saveData, "B 회사", 0, 150, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userName, saveData, "C 회사", 0, 400, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userName, saveData, "D 회사", 0, 70, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userName, saveData, "E 회사", 0, 2500, 0, 0, 0, 0, 1);
			userMoneyHistoryDAO.insert(userName, saveData, "F 회사", 0, 8200, 0, 0, 0, 0, 1);
			
			allCompanyDAO.insert("A 회사", 100, 200, userName, saveData, 1);
			allCompanyDAO.insert("B 회사", 150, 300, userName, saveData, 1);
			allCompanyDAO.insert("C 회사", 400, 1000, userName, saveData, 1);
			allCompanyDAO.insert("D 회사", 70, 300, userName, saveData, 1);
			allCompanyDAO.insert("E 회사", 2500, 5000, userName, saveData, 1);
			allCompanyDAO.insert("F 회사", 8200, 500, userName, saveData, 1);
			
			allCompanyBackdataDAO.insert("A 회사", 100, 200, userName, saveData, 1);
			allCompanyBackdataDAO.insert("B 회사", 150, 300, userName, saveData, 1);
			allCompanyBackdataDAO.insert("C 회사", 400, 1000, userName, saveData, 1);
			allCompanyBackdataDAO.insert("D 회사", 70, 300, userName, saveData, 1);
			allCompanyBackdataDAO.insert("E 회사", 2500, 5000, userName, saveData, 1);
			allCompanyBackdataDAO.insert("F 회사", 8200, 500, userName, saveData, 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public SignUP() {
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pnl1 = new JPanel();
		getContentPane().add(pnl1);

		JPanel pnlBtn = new JPanel();
		pnlBtn.setLayout(new GridLayout(0, 1, 0, 0));

		getContentPane().add(pnlBtn);

		JPanel pnlPw = new JPanel();
		pnlBtn.add(pnlPw);
		pnlPw.setLayout(null);

		JLabel lblID = new JLabel("아이디");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(124, 8, 88, 15);
		pnlPw.add(lblID);

		txtID = new JTextField(10);

		txtID.setBounds(224, 5, 116, 21);
		pnlPw.add(txtID);

		JPanel panel_2 = new JPanel();
		pnlBtn.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblPW = new JLabel("비밀번호");
		lblPW.setHorizontalAlignment(SwingConstants.CENTER);
		lblPW.setBounds(128, 8, 84, 15);
		panel_2.add(lblPW);

		txtIDPW = new JPasswordField(10);

		txtIDPW.setBounds(224, 5, 116, 21);
		panel_2.add(txtIDPW);

		JPanel pnlPwCheck = new JPanel();
		pnlBtn.add(pnlPwCheck);
		pnlPwCheck.setLayout(null);

		JLabel lblPWChk = new JLabel("비밀번호 확인");
		lblPWChk.setBounds(130, 8, 86, 15);
		pnlPwCheck.add(lblPWChk);

		txtIDPWChk = new JPasswordField(10);
		txtIDPWChk.setBounds(224, 5, 116, 21);

		pnlPwCheck.add(txtIDPWChk);

		JPanel pnlBtnSet = new JPanel();
		pnlBtn.add(pnlBtnSet);

		JButton btnSUP = new JButton("회원가입");
		btnSUP.setFocusable(false);
		pnlBtnSet.add(btnSUP);
		
		btnSUP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean chkDupID = true;
				String userName = null;

				userName = txtID.getText();
				if (userName.length() == 0) {
					JOptionPane.showMessageDialog(SignUP.this, "아이디를 입력하세요.");
				} else {
					chkDupID = false;
					UserInfo userInfo = UserInfoDAO.findByID(userName);
					if (userInfo != null) {
						JOptionPane.showMessageDialog(SignUP.this, "중복된 아이디가 있어요.");
						chkDupID = true;
					}

				}

				if (!chkDupID) {
					String password = txtIDPW.getText();
					String passwordChk = txtIDPWChk.getText();

					if (password.equals(passwordChk)) {
						// 회원 생성 시 무조건 saveData 1에 저장
						try {

							UserInfoDAO.insert(userName, password, 1);
							insertInfoByID(userName, 1);
							JOptionPane.showMessageDialog(SignUP.this, "회원가입이 완료되었습니다.");
							dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(SignUP.this, "비밀번호가 일치하지 않습니다.");
					}

				}

			}
		});

		JButton btnBack = new JButton("뒤로가기");
		btnBack.setFocusable(false);
		pnlBtnSet.add(btnBack);

		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	}
	
	
	//텍스트필드를 초기화 하는 부분
	private void initializeFields() {
        txtID.setText("");
        txtIDPW.setText("");
        txtIDPWChk.setText("");
    }
	
	//setVisible을 했을 때 텍스트 필드를 모두 초기화 해준 후 setVisible 실행
	 @Override
	    public void setVisible(boolean b) {
	        if (b) {
	            initializeFields();  // 다이얼로그가 열릴 때마다 초기화
	        }
	        super.setVisible(b);
	    }

}
