package ui;

import javax.swing.JFrame;

public class TestGUI extends JFrame {
	public TestGUI() {
		MainPanel1 pnl = new MainPanel1();
		add(pnl);
		
		
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TestGUI().setVisible(true);
	}
}
