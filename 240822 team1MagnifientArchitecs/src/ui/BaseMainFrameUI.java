package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BaseMainFrameUI extends JFrame {
	public BaseMainFrameUI() {
		// 가장 큰 패널
		JPanel contentPane = new JPanel();
		
		contentPane.setLayout(new BorderLayout());
		
		// 북쪽 패널
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, "North");
		pnlNorth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		JButton btnNorth = new JButton("북쪽");
		pnlNorth.add(btnNorth);
		
		
		// 가운데 패널
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, "Center");
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		
		JButton btnCenter = new JButton("센터");
		pnlCenter.add(btnCenter);
		
		
		
		
		// 남쪽 패널
		JPanel pnlSouth = new JPanel();
		pnlSouth.setPreferredSize(new Dimension(500, 100));
		contentPane.add(pnlSouth, "South");
		pnlSouth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		JButton btnSouth1 = new JButton();
		JButton btnSouth2 = new JButton();
		JButton btnSouth3 = new JButton();
		btnSouth1.setText("내 정보");
		
		btnSouth1.setFont(new Font(, Font.PLAIN, 20));
		btnSouth2.setText("오늘의 뉴스");
		btnSouth3.setText("종료");
		pnlSouth.add(btnSouth1);
		pnlSouth.add(btnSouth2);
		pnlSouth.add(btnSouth3);
		
		
		
		
		
		
		
		
		
		// 기초 프레임 
		setSize(500, 500);
		add(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new BaseMainFrameUI().setVisible(true);
	}
}
