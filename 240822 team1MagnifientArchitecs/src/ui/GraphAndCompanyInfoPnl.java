package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import mapper.CompanyInfoMapper;
import tables.CompanyInfo;
import tables.UserInfo;

public class GraphAndCompanyInfoPnl extends JPanel implements ActionListener {
	private CardLayout GandIcardLayout;
	private JPanel GandIpnlCenter;

	private CardLayout cardLayout;
	private JPanel pnlCenter;
	private JPanel comInfoSummary;
	private JLabel companyNamelbl;
	private JLabel stockPriceNowlbl;
	private JLabel comparePrevDaylbl;
	private CompanyInfoPnlforgraph companyInfopnl;
	private UserInfo userInfo;

	public GraphAndCompanyInfoPnl(UserInfo userInfo, CardLayout cardLayout, JPanel pnlCenter) {
		this.userInfo = userInfo;
		this.cardLayout = cardLayout;
		this.pnlCenter = pnlCenter;
		
		setLayout(new BorderLayout());

		// 회사 이름, 회사 현재 주가, 전일대비 가격, 전일대비 상승률
		setComInfoSummary();
		updateComInfoSummary();

		// 차트보기, 회사 정보 버튼
		// update 필요 없음
		setGandIpnlCenter();

		// TODO 그래프 패널
		setgraphPnl();

		// 회사정보 패널
		setCompanyInfoPnl("A 회사");
		updateCompanyInfoPnl("A 회사");
		
	}
	
	public void updateCompanyInfoPnl(String companyName) {
		CompanyInfo OutInfo = selectCompany(companyName);
		companyInfopnl.update(OutInfo);
		
	}

	private void setCompanyInfoPnl(String companyName) {
		CompanyInfo companyInfo = selectCompany(companyName);
		companyInfopnl = new CompanyInfoPnlforgraph(companyInfo);
		companyInfopnl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		companyInfopnl.setBackground(Color.WHITE);
		GandIpnlCenter.add(companyInfopnl, "companyInfopnl");
		
		// south 패널 매수 버튼, 매도 버튼
		JPanel CompanyInfoPnl = new JPanel();
		JButton buybtn = new JButton("매수");
		buybtn.addActionListener(this);
		JButton sellbtn = new JButton("매도");
		sellbtn.addActionListener(this);
		
		CompanyInfoPnl.add(buybtn);
		CompanyInfoPnl.add(sellbtn);
		
		add(CompanyInfoPnl, "South");
	}

	private void setgraphPnl() {
		JPanel graphpnl = new JPanel();
		JLabel templbl = new JLabel("주식 그래프 차트 들어올 예정");
		graphpnl.add(templbl);
		GandIpnlCenter.add(graphpnl, "graphpnl");
	}

	private void setGandIpnlCenter() {
		JPanel chartAndbtnpnl = new JPanel();

		JButton graphbtn = new JButton("차트 보기");
		graphbtn.addActionListener(this);
		JButton companyInfobtn = new JButton("회사 정보");
		companyInfobtn.addActionListener(this);

		chartAndbtnpnl.add(graphbtn);
		chartAndbtnpnl.add(companyInfobtn);

		comInfoSummary.add(chartAndbtnpnl);

		GandIpnlCenter = new JPanel();
		GandIcardLayout = new CardLayout();
		GandIpnlCenter.setLayout(GandIcardLayout);
		add(GandIpnlCenter, "Center");
	}
	
	// TODO db와 연결 안됨
	private void updateComInfoSummary(UserInfo userInfo) {
		companyNamelbl.setText("A 회사");
		stockPriceNowlbl.setText("100원");
		comparePrevDaylbl.setText("전일대비 0원 (0%)");
	}

	private void setComInfoSummary() {
		comInfoSummary = new JPanel();
		comInfoSummary.setLayout(new GridLayout(2, 1));
		add(comInfoSummary, "North");
		
		JPanel companyStockpnl = new JPanel();

		companyNamelbl = new JLabel();
		stockPriceNowlbl = new JLabel();
		comparePrevDaylbl = new JLabel();
		
		companyStockpnl.add(companyNamelbl);
		companyStockpnl.add(stockPriceNowlbl);
		companyStockpnl.add(comparePrevDaylbl);

		comInfoSummary.add(companyStockpnl);
	}

	private static CompanyInfo selectCompany(String companyName) {
		IResultMapper<CompanyInfo> mapper = new CompanyInfoMapper();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM CompanyInfo WHERE companyName = ?; ";
			conn = DBUtil.getConnection("go_db");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, companyName);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return mapper.resultMapping(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeAll(rs, stmt, conn);
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if (command.equals("차트 보기")) {
			GandIcardLayout.show(GandIpnlCenter, "graphpnl");
		} 
		else if (command.equals("회사 정보")) {
			GandIcardLayout.show(GandIpnlCenter, "companyInfopnl");
		}
		else if (command.equals("매수")) {
			cardLayout.show(pnlCenter, "buyPriceGUI");
		}
		else if (command.equals("매도")) {
			cardLayout.show(pnlCenter, "sellPriceGUI");
		}
	}
}
