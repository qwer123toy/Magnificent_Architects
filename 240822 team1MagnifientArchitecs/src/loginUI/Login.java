package loginUI;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public Login() {
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);

		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(0, 1, 0, 0));

		getContentPane().add(pnl);

		JPanel panel_2 = new JPanel();
		pnl.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblId = new JLabel("아이디");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(128, 8, 84, 15);
		panel_2.add(lblId);

		textField_1 = new JTextField(10);
		textField_1.setBounds(224, 5, 116, 21);
		panel_2.add(textField_1);

		JPanel panel_3 = new JPanel();
		pnl.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblPw = new JLabel("비밀번호");
		lblPw.setBounds(130, 8, 86, 15);
		panel_3.add(lblPw);

		textField_2 = new JTextField(10);
		textField_2.setBounds(224, 5, 116, 21);
		panel_3.add(textField_2);

		JPanel panel_4 = new JPanel();
		pnl.add(panel_4);

		JButton btnSUP = new JButton("로그인");
		panel_4.add(btnSUP);

		JButton btnBack = new JButton("뒤로가기");
		panel_4.add(btnBack);

	}

	public static void main(String[] args) {
		new Login().setVisible(true);
	}
}
