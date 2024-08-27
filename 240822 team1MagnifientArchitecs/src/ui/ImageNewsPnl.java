package ui;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageNewsPnl extends JPanel {
	public ImageNewsPnl(String title) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(ImageNewsPnl.class.getResource("/resource/nonTextNewsImage.jpg")));
		lblNewLabel.setBounds(0, 0, 576, 300);
		add(lblNewLabel);
		
//		URL resource = ImageNewsPnl.class.getResource("/resource/nonTextNewsImage.jpg");
//		ImageIcon icon = new ImageIcon(resource);
	}
}
