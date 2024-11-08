package MainSystem;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MakeFrame2 extends JFrame {

	public MakeFrame2() {

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 1, 0, 60));

		JLabel lblNewLabel = new JLabel("새창!");
		panel.add(lblNewLabel);

		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new MakeFrame2().setVisible(true);
	}
}
