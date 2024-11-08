package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.NewsDAO;
import tables.News;

public class NewsPnl extends JPanel {
	NewsDAO newsDAO = new NewsDAO();
	ImageNewsPnl imageNewsPnl;
	JLabel newsContentslbl;
	int newsNum=0;
	public NewsPnl(int newsNum) {
		this.newsNum = newsNum;
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		
		// Today's News 패널
		JPanel todayNewspnl = new JPanel();
		todayNewspnl.setBackground(Color.DARK_GRAY);
		JLabel todayNewslbl = new JLabel("Todays's News");
		todayNewslbl.setFont(new Font("Dialog.bold", Font.BOLD, 20));
		todayNewslbl.setForeground(Color.WHITE);
		todayNewspnl.add(todayNewslbl);
		
		add(todayNewspnl);
		
		imageNewsPnl = new ImageNewsPnl("연준 드라마 OST 발매, 많이 들어주세요!");
		imageNewsPnl.setPreferredSize(new Dimension(500, 300));
		
//		JLabel newsTitlelbl = makeContentLbl("미국, 중시 안정화 제도 개선방안 검토중");
//		newsTitlelbl.setPreferredSize(new Dimension(400, 20));
		News news = newsDAO.findCompByID(newsNum);
		newsContentslbl = makeContentLbl("");
		newsContentslbl.setText(news.getInfo_News());
//		newsContentslbl.setPreferredSize(new Dimension(400, 20));
		
		
		
		add(imageNewsPnl);
		add(newsContentslbl);
		
	}
	
	public void updateNews(int newsInfoNum) {
		News news = newsDAO.findCompByID(newsInfoNum);
//		imageNewsPnl = new ImageNewsPnl(news.getInfo_News());
		newsContentslbl.setText(news.getInfo_News());
		this.revalidate();
		this.repaint();
	}

	private JLabel makeContentLbl(String contentText) {
		JLabel lbl = new JLabel();
		lbl.setText(contentText);
		lbl.setFont(new Font("Dialog.bold", Font.PLAIN, 16));
		
		return lbl;
	}
}
