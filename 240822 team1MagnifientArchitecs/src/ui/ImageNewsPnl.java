package ui;

import java.net.URL;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;


public class ImageNewsPnl extends JPanel {
	public ImageNewsPnl(String title) {
		setSize(500, 300);
		setLayout(null);
		
		JLabel NewsTitlelbl = new JLabel(title);
		NewsTitlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		NewsTitlelbl.setFont(new Font("굴림", Font.BOLD, 16));
		NewsTitlelbl.setBounds(0, 76, 500, 41);
		add(NewsTitlelbl);
		
		JLabel newsImagelbl = new JLabel();
		
		// 이미지 사이즈는 625 x 379 -> 0.6064
		newsImagelbl.setBounds(0, 0, 500, 300);
		
		URL resource = ImageNewsPnl.class.getResource("/resource/nonTextNewsImage.jpg");
		ImageIcon icon = new ImageIcon(resource);
		
		// import java.awt.Image;
		// 아이콘의 이미지를 추출
		Image image = icon.getImage();
		// 추출한 이미지의 크기를 라벨의 크기와 같게 만든다.
		Image scaledImage = image.getScaledInstance(newsImagelbl.getWidth(), newsImagelbl.getHeight()
				// 크기 조정할 때 부드럽게
				, Image.SCALE_SMOOTH);
		// 크기를 바꾼 이미지를 다시 아이콘으로 바꾼다.
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		newsImagelbl.setIcon(scaledIcon);
		
		add(newsImagelbl);
		
		
		
		
	}
}
