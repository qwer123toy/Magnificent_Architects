package priceGUI;

import javax.swing.JFrame;

import tables.UserInfo;
import ui.ImageNewsPnl;

public class TestStockGUI extends JFrame {
	JFrame frame = new JFrame("테스트 창");
	UserInfo userInfo = new UserInfo("asdf", 1, "12", 92490, 0, 9, null);
	public TestStockGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 510);
		add(new BuyPriceGUI(userInfo,"A 회사", 0));
		
	}

	public static void main(String[] args) {
		new TestStockGUI().setVisible(true);
	}
}
