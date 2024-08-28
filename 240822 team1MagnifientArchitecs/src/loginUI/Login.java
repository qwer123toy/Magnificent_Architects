package loginUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

import com.sun.corba.se.impl.protocol.AddressingDispositionException;

public class Login extends JFrame {
	private JTextField textField;
	private JTextField tfId;
	private JTextField tfPw;

	public Login() {
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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

		tfPw = new JTextField(10);
		tfPw.setBounds(224, 5, 116, 21);
		pnlPw.add(tfPw);

		JPanel pnlBtn = new JPanel();
		pnlSouth.add(pnlBtn);

		JButton btnLogin = new JButton("로그인");
		btnLogin.setFocusable(false);
		pnlBtn.add(btnLogin);

		JButton btnBack = new JButton("뒤로가기");
		btnBack.setFocusable(false);
		pnlBtn.add(btnBack);

		
	}

	public static void main(String[] args) {
		new Login().setVisible(true);
	}
}
