package loginUI;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MainGUI extends JFrame {
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
	}

	public JButton btnSetting(String butText, int y) {
		JButton btn = new JButton(butText);
		btn.setBounds(135, y, 200, 50);
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
