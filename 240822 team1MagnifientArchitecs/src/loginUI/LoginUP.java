package loginUI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class LoginUP extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public LoginUP() {
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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

		textField = new JTextField(10);
		textField.setBounds(224, 5, 116, 21);
		pnlPw.add(textField);

		JPanel panel_2 = new JPanel();
		pnlBtn.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblPW = new JLabel("비밀번호");
		lblPW.setHorizontalAlignment(SwingConstants.CENTER);
		lblPW.setBounds(128, 8, 84, 15);
		panel_2.add(lblPW);

		textField_1 = new JTextField(10);
		textField_1.setBounds(224, 5, 116, 21);
		panel_2.add(textField_1);

		JPanel pnlPwCheck = new JPanel();
		pnlBtn.add(pnlPwCheck);
		pnlPwCheck.setLayout(null);

		JLabel lblPWChk = new JLabel("비밀번호 확인");
		lblPWChk.setBounds(130, 8, 86, 15);
		pnlPwCheck.add(lblPWChk);

		textField_2 = new JTextField(10);
		textField_2.setBounds(224, 5, 116, 21);
		pnlPwCheck.add(textField_2);

		JPanel pnlBtnSet = new JPanel();
		pnlBtn.add(pnlBtnSet);

		JButton btnSUP = new JButton("회원가입");
		pnlBtnSet.add(btnSUP);

		JButton btnBack = new JButton("뒤로가기");
		pnlBtnSet.add(btnBack);

	}

	public static void main(String[] args) {
		new LoginUP().setVisible(true);
	}
}
