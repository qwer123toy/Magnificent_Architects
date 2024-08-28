package loginUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class MainGUI extends JFrame {
	Login login = new Login();
	LoginUP loginUP = new LoginUP();

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
				loginUP.setVisible(true);
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
}

public class Main {
	public static void main(String[] args) {
		MainGUI init = new MainGUI();
		init.setVisible(true);
	}
}
