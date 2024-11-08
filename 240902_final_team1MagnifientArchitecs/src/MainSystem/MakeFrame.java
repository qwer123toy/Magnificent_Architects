package MainSystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MakeFrame extends JFrame {

	public MakeFrame() {

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 60));

		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);

		JButton btnNewButton = new JButton("새로 시작");
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MakeFrame2 mk2 = new MakeFrame2();
				mk2.setVisible(true);
				dispose();
			}
		});

		JButton btnNewButton_1 = new JButton("로그인하기");
		panel.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("랭킹보기");
		panel.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("종료");
		panel.add(btnNewButton_3);

		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new MakeFrame().setVisible(true);
	}
}
