package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewsPnl extends JPanel {
	public NewsPnl() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		
		// Today's News 패널
		JPanel todayNewspnl = new JPanel();
		todayNewspnl.setBackground(Color.DARK_GRAY);
		JLabel todayNewslbl = new JLabel("Todays's News");
		todayNewslbl.setFont(new Font("Dialog.bold", Font.BOLD, 20));
		todayNewslbl.setForeground(Color.WHITE);
		todayNewspnl.add(todayNewslbl);
		
		add(todayNewspnl);
		
		ImageNewsPnl imageNewsPnl = new ImageNewsPnl("S&P, '연준, 드라마틱한 변화 필요성 없어 내년 동결");
		imageNewsPnl.setPreferredSize(new Dimension(500, 300));
		
//		JLabel newsTitlelbl = makeContentLbl("미국, 중시 안정화 제도 개선방안 검토중");
//		newsTitlelbl.setPreferredSize(new Dimension(400, 20));
		
		JLabel newsContentslbl = makeContentLbl("운수 관련 사업의 주가가 증가될 것으로 예상됨");
//		newsContentslbl.setPreferredSize(new Dimension(400, 20));
		
		JLabel warninglbl = makeContentLbl("※ 항상 예상이 맞는 것은 아니니 유의할 것");
		
		
		add(imageNewsPnl);
		add(newsContentslbl);
		add(warninglbl);
	}

	private JLabel makeContentLbl(String contentText) {
		JLabel lbl = new JLabel();
		lbl.setText(contentText);
		lbl.setFont(new Font("Dialog.bold", Font.PLAIN, 16));
		
		return lbl;
	}
}
