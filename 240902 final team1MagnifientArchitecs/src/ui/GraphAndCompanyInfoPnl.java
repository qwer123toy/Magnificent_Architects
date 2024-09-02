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
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import DAO.AllCompanyBackdataDAO;
import DAO.AllCompanyDAO;
import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import mapper.CompanyInfoMapper;
import tables.AllCompany;
import tables.AllCompanyBackdata;
import tables.CompanyInfo;
import tables.UserInfo;

public class GraphAndCompanyInfoPnl extends JPanel implements ActionListener {
	private CardLayout GandIcardLayout = new CardLayout();
	private JPanel GandIpnlCenter = new JPanel();

	private CardLayout cardLayout;
	private JPanel pnlCenter;
	private JPanel comInfoSummary;
	private JLabel companyNamelbl;
	private JLabel stockPriceNowlbl;
	private JLabel comparePrevDaylbl;
	private CompanyInfoPnlforgraph companyInfopnl;
	private UserInfo userInfo;
	
	private String companyName;
	private GraphAndCompanyInfoPnl GraphAndCompanyInfoPnl;
	
	private AllCompanyDAO allCompanyDAO = new AllCompanyDAO();
	private AllCompanyBackdataDAO allCompanyBackdataDAO = new AllCompanyBackdataDAO();

	public GraphAndCompanyInfoPnl(UserInfo userInfo, CardLayout cardLayout, JPanel pnlCenter, int companyIndex) {
		this.userInfo = userInfo;
		this.cardLayout = cardLayout;
		this.pnlCenter = pnlCenter;

		setLayout(new BorderLayout());

		// 회사 이름, 회사 현재 주가, 전일대비 가격, 전일대비 상승률
		setComInfoSummary();
		updateComInfoSummary("A 회사", userInfo);

		// 차트보기, 회사 정보 버튼
		// update 필요 없음
		setGandIpnlCenter();

		// TODO 그래프 패널 -> 버튼 누르면 frame 뜨는 걸로 변경
//		setgraphPnl();

		// 회사정보 패널
		setCompanyInfoPnl("A 회사");
		updateCompanyInfoPnl("A 회사");

	}
	
	
//	public JPanel getPnl() {
//		return GandIpnlCenter;
//	}
//	
//	public CardLayout getCardLayout() {
//		return GandIcardLayout;
//	}
	
	public CardLayout getGandIcardLayout() {
		return GandIcardLayout;
	}


	public JPanel getGandIpnlCenter() {
		return GandIpnlCenter;
	}


	public void updateAll(String companyName, UserInfo userInfo) {
		updateCompanyInfoPnl(companyName);
		updateComInfoSummary(companyName, userInfo);
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
		add(companyInfopnl, "Center");
//		GandIpnlCenter.add(companyInfopnl, "companyInfopnl");

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

//	private void setgraphPnl() {
//		JPanel graphpnl = new JPanel();
//		JLabel templbl = new JLabel("주식 그래프 차트 들어올 예정");
//		graphpnl.add(templbl);
//		GandIpnlCenter.add(graphpnl, "graphpnl");
//	}

	private void setGandIpnlCenter() {
		JPanel chartAndbtnpnl = new JPanel();

		JButton graphbtn = new JButton("차트 보기");
		graphbtn.addActionListener(this);
//		JButton companyInfobtn = new JButton("회사 정보");
//		companyInfobtn.addActionListener(this);

		chartAndbtnpnl.add(graphbtn);
//		chartAndbtnpnl.add(companyInfobtn);

		comInfoSummary.add(chartAndbtnpnl);

//		GandIpnlCenter.setLayout(GandIcardLayout);
//		add(GandIpnlCenter, "Center");
	}

	public void updateComInfoSummary(String companyName, UserInfo userInfo) {
		List<AllCompany> allCompanyList = allCompanyDAO.findAllByID(userInfo.getUser_ID(), userInfo.getUser_SaveData());
		int companyIndex = selectCompanyIndex(companyName);
		this.companyName = companyName;
		
		String stockPriceNow = allCompanyList.get(companyIndex).getCompanyStockPrice() + "원";
		
		AllCompany allCompany = allCompanyDAO.findCompByID(companyName, userInfo.getUser_ID(), userInfo.getUser_SaveData());
		int changeStockPrice;
		if (userInfo.getUser_Date() == 1) {
			changeStockPrice = 0;
		} else {
			int today = userInfo.getUser_Date();
			int yesterday = today - 1;

			AllCompanyBackdata acbdYesterday = allCompanyBackdataDAO.findCompanyByDate(companyName, yesterday,
					userInfo.getUser_ID(), userInfo.getUser_SaveData());

			int todaysStockPrice = allCompany.getCompanyStockPrice();
			int yesterdayStockPrice = acbdYesterday.getCompanyStockPrice();
			changeStockPrice = todaysStockPrice - yesterdayStockPrice;
		}
		String comparePrevDay;
		if (changeStockPrice > 0) {
			comparePrevDay = "+" + changeStockPrice + "원";
		} else {
			comparePrevDay = changeStockPrice + "원";
		}
		
		// 회사 이름
		companyNamelbl.setText(companyName);
		// 현재 해당 회사 주식 가격
		stockPriceNowlbl.setText(stockPriceNow);
		// 현재 해당 회사 주식 가격 변동값
		comparePrevDaylbl.setText(comparePrevDay);
	}

	private int selectCompanyIndex(String companyName) {
		int companyIndex;
		if (companyName.equals("A 회사")) {
			companyIndex = 0;
		} else if (companyName.equals("B 회사")) {
			companyIndex = 1;
		} else if (companyName.equals("C 회사")) {
			companyIndex = 2;
		} else if (companyName.equals("D 회사")) {
			companyIndex = 3;
		} else if (companyName.equals("E 회사")) {
			companyIndex = 4;
		} else if (companyName.equals("F 회사")) {
			companyIndex = 5;
		} else {
			companyIndex = 0;
		}
		return companyIndex;
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
			SwingUtilities.invokeLater(() -> {
				ChartFrame chartFrame = new ChartFrame(userInfo, companyName);
				chartFrame.setTitle("Chart");
				chartFrame.setSize(500, 500);
				chartFrame.setLocationRelativeTo(this.GraphAndCompanyInfoPnl);
//				chartFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				chartFrame.setVisible(true);
			});
			
			
//		} else if (command.equals("회사 정보")) {
//			GandIcardLayout.show(GandIpnlCenter, "companyInfopnl");
		} else if (command.equals("매수")) {
			cardLayout.show(pnlCenter, "buyPriceGUI");
		} else if (command.equals("매도")) {
			cardLayout.show(pnlCenter, "sellPriceGUI");
		}
	}
}
