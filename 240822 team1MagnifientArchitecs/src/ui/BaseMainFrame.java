package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dbUtil.DBUtil;
import dbUtil.IResultMapper;
import mapper.CompanyInfoMapper;
import tables.CompanyInfo;

public class BaseMainFrame extends JFrame implements ActionListener {
	private CardLayout cardLayout;
	private JPanel pnlCenter;

	public BaseMainFrame() {
		// 가장 큰 패널
		JPanel contentPane = new JPanel();

		contentPane.setLayout(new BorderLayout());

		// 북쪽 패널
		JPanel pnlNorth = new JPanel();
		contentPane.add(pnlNorth, "North");
		pnlNorth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JButton btnNorth1 = new JButton();
		btnNorth1.setText("메인화면");
		JButton btnNorth2 = new JButton();
		btnNorth2.setText("이전");
		btnNorth2.addActionListener(this);
		JButton btnNorth3 = new JButton();
		btnNorth3.setText("다음");
		btnNorth3.addActionListener(this);
		JButton btnNorth4 = new JButton();
		btnNorth4.setText("장 마감");
		pnlNorth.add(btnNorth1);
		pnlNorth.add(btnNorth2);
		pnlNorth.add(btnNorth3);
		pnlNorth.add(btnNorth4);
		pnlNorth.setBackground(Color.BLACK);

		pnlCenter = new JPanel();
		contentPane.add(pnlCenter, "Center");
		pnlCenter.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

		// 센터에 설정할 pnl들
		setCardLayout();

		// 남쪽 패널
		JPanel pnlSouth = new JPanel();
		pnlSouth.setPreferredSize(new Dimension(500, 100));
		contentPane.add(pnlSouth, "South");
		pnlSouth.setBackground(Color.BLACK);
		pnlSouth.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		// 버튼 간격 설정
		FlowLayout pnlSouthLayout = new FlowLayout();
		pnlSouthLayout.setHgap(30);
		pnlSouth.setLayout(pnlSouthLayout);

		JButton btnSouth1 = new JButton();
		JButton btnSouth2 = new JButton();
		JButton btnSouth3 = new JButton();
		btnSouth1.setText("내 정보");
//		btnSouth1.setFont(new Font("Dialog.bold", Font.BOLD, 20));

		btnSouth2.setText("오늘의 뉴스");
		btnSouth3.setText("종료");
		pnlSouth.add(btnSouth1);
		pnlSouth.add(btnSouth2);
		pnlSouth.add(btnSouth3);

		// 기초 프레임
		setSize(500, 500);
		add(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void setCardLayout() {
		cardLayout = new CardLayout();
		pnlCenter.setLayout(cardLayout);

		String selectedCompanyName = "A 회사";
		CompanyInfo companyInfo = selectCompany(selectedCompanyName);
		CompanyInfoPnl pnlCompanyInfo = new CompanyInfoPnl(companyInfo);

		JPanel pnlSecond = new JPanel();
		pnlSecond.setBackground(Color.CYAN);
		JPanel pnlThird = new JPanel();
		pnlThird.setBackground(Color.YELLOW);

		pnlCenter.add(pnlCompanyInfo, "First");
		pnlCenter.add(pnlSecond, "Second");
		pnlCenter.add(pnlThird, "Third");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 공통으로 사용할 액션 리스너 설정 중
		String command = e.getActionCommand();
//		System.out.println(command);

		if (command.equals("이전")) {
			cardLayout.previous(pnlCenter);
		} else if (command.equals("다음")) {
			cardLayout.next(pnlCenter);
		}
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

	public static void main(String[] args) {
		new BaseMainFrame().setVisible(true);
	}

}
