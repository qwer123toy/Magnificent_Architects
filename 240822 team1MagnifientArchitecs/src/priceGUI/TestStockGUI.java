package priceGUI;

import javax.swing.JFrame;

import ui.ImageNewsPnl;

public class TestStockGUI extends JFrame {
	JFrame frame = new JFrame("매수 가격 입력");

	public TestStockGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 510);
		add(new SellPriceGUI());
		
	}

	public static void main(String[] args) {
		new TestStockGUI().setVisible(true);
	}
}
