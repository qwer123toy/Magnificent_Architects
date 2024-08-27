package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CompanyStockBoardPnl extends JPanel {
	public CompanyStockBoardPnl() {
		// 사이즈랑 레이아웃
		setSize(500, 500);
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		
		// 총 매수, 평가손익, 총 평가, 수익률 패널
		JPanel pnl1 = new JPanel();
		pnl1.setLayout(new GridLayout(2, 2));
		pnl1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		pnl1.setPreferredSize(new Dimension(480, 100));

		JLabel allBuyMoney = new JLabel(); // 총 매수
		allBuyMoney.setText("총매수: 매수한 금액");
		allBuyMoney.setHorizontalAlignment(JLabel.CENTER);

		JLabel allProfitMoney = new JLabel(); // 평가손익
		allProfitMoney.setText("평가손익: 111원");
		allProfitMoney.setHorizontalAlignment(JLabel.CENTER);

		JLabel allProperty = new JLabel(); // 주식 + 현금 = 총 재산
		allProperty.setText("총 평가: 현재 금액");
		allProperty.setHorizontalAlignment(JLabel.CENTER);

		JLabel profitRate = new JLabel(); // 수익률
		profitRate.setText("수익률: 12.34%");
		profitRate.setHorizontalAlignment(JLabel.CENTER);

		pnl1.add(allBuyMoney);
		pnl1.add(allProfitMoney);
		pnl1.add(allProperty);
		pnl1.add(profitRate);
		add(pnl1);

		// 회사명, 현재가, 전일대비, 잔여수량 패널
		JPanel pnl2 = new JPanel();
		pnl2.setLayout(new GridLayout(1, 4));
		
		pnl2.setBackground(Color.CYAN);
		pnl2.setPreferredSize(new Dimension(480, 30));
		pnl2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JLabel lbl1 = new JLabel("회사명");
		lbl1.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbl2 = new JLabel("현재가");
		lbl2.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbl3 = new JLabel("전일대비");
		lbl3.setHorizontalAlignment(JLabel.CENTER);
		JLabel lbl4 = new JLabel("잔여수량");
		lbl4.setHorizontalAlignment(JLabel.CENTER);

		pnl2.add(lbl1);
		pnl2.add(lbl2);
		pnl2.add(lbl3);
		pnl2.add(lbl4);
		add(pnl2);

		// A B C D 회사 정보 표시 패널
		JPanel companyInfoPnl1 = returnCompnayInfoPnl("A회사", 100, 0, 100);
			JPanel companyInfoPnl2 = returnCompnayInfoPnl("B회사", 200, 0, 300);
		JPanel companyInfoPnl3 = returnCompnayInfoPnl("C회사", 150, 0, 500);
		JPanel companyInfoPnl4 = returnCompnayInfoPnl("D회사", 500, 0, 800);

		add(companyInfoPnl1);
		add(companyInfoPnl2);
		add(companyInfoPnl3);
		add(companyInfoPnl4);

	}

	private JPanel returnCompnayInfoPnl(String companyName, int priceNow, int comparePrevDay, int stockCount) {
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(1, 4));
		
		pnl.setBackground(Color.WHITE);
		pnl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		pnl.setPreferredSize(new Dimension(480, 60));

		JLabel companyNameLbl = new JLabel();
		companyNameLbl.setText(companyName);
		companyNameLbl.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel priceNowLbl = new JLabel();
		priceNowLbl.setText("" + priceNow);
		priceNowLbl.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel comparePrevDayLbl = new JLabel();
		comparePrevDayLbl.setText(comparePrevDay + "%");
		comparePrevDayLbl.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel stockCountLbl = new JLabel();
		stockCountLbl.setText("" + stockCount);
		stockCountLbl.setHorizontalAlignment(JLabel.CENTER);

		pnl.add(companyNameLbl);
		pnl.add(priceNowLbl);
		pnl.add(comparePrevDayLbl);
		pnl.add(stockCountLbl);
		return pnl;
	}
}
