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

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(0, 1, 0, 0));

		getContentPane().add(pnl);

		JPanel panel_1 = new JPanel();
		pnl.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblID = new JLabel("아이디");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setBounds(124, 8, 88, 15);
		panel_1.add(lblID);

		textField = new JTextField(10);
		textField.setBounds(224, 5, 116, 21);
		panel_1.add(textField);

		JPanel panel_2 = new JPanel();
		pnl.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblPW = new JLabel("비밀번호");
		lblPW.setHorizontalAlignment(SwingConstants.CENTER);
		lblPW.setBounds(128, 8, 84, 15);
		panel_2.add(lblPW);

		textField_1 = new JTextField(10);
		textField_1.setBounds(224, 5, 116, 21);
		panel_2.add(textField_1);

		JPanel panel_3 = new JPanel();
		pnl.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblPWChk = new JLabel("비밀번호 확인");
		lblPWChk.setBounds(130, 8, 86, 15);
		panel_3.add(lblPWChk);

		textField_2 = new JTextField(10);
		textField_2.setBounds(224, 5, 116, 21);
		panel_3.add(textField_2);

		JPanel panel_4 = new JPanel();
		pnl.add(panel_4);

		JButton btnSUP = new JButton("회원가입");
		panel_4.add(btnSUP);

		JButton btnBack = new JButton("뒤로가기");
		panel_4.add(btnBack);

	}

	public static void main(String[] args) {
		new LoginUP().setVisible(true);
	}
}
