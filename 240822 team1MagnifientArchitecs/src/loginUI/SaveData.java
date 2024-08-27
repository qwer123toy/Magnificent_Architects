package loginUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class SaveDataGUI extends JFrame {
	public SaveDataGUI() {
		// 전체 배경색 조정
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 460);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false); // 앱솔루트 만든 후, 크기수정불가

		String example = "3일차, 현재 보유금액 23400원";

		// 첫 번째 패널, 버튼
		JButton btnSave = btnSetting("저장 데이터1", "없음", 10);
		// 두 번째 패널, 버튼
		JButton btnSave2 = btnSetting("저장 데이터2", "", 118);
		// 세 번째 패널, 버튼
		JButton btnSave3 = btnSetting("저장 데이터3", example, 228);
		// 뒤로가기 버튼
		JButton btnBack = new JButton("뒤로가기");
		// (x좌표, y좌표, 가로길이, 세로길이)
		btnBack.setBounds(135, 355, 165, 45);
		add(btnBack);

		// 버튼1을 누르면 버튼2에 텍스트가 삽입
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSave2.setText(example);
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

public class SaveData {
	public static void main(String[] args) {
		SaveDataGUI init = new SaveDataGUI();
		init.setVisible(true);
	}
}
