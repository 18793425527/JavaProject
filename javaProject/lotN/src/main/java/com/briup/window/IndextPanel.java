package com.briup.window;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class IndextPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	public static String user = "briup";
	public static String pa = "briup";

	public static Connection conn = null;

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public IndextPanel() {
//		JFrame windoFrame = new JFrame();
		this.setTitle("数据展示");
		JPanel panel = new JPanel();
		panel.setLayout(null);

		// 下拉列表1
		JComboBox<String> jComboBox = new JComboBox<String>();
		jComboBox.addItem("2018-01-1");
		jComboBox.addItem("2018-01-2");
		jComboBox.addItem("2018-01-3");
		jComboBox.addItem("2018-01-4");
		jComboBox.addItem("2018-01-5");
		jComboBox.addItem("2018-01-6");
		jComboBox.addItem("2018-01-7");
		jComboBox.addItem("2018-01-8");
		jComboBox.addItem("2018-01-9");
		jComboBox.addItem("2018-01-10");
		jComboBox.addItem("2018-01-11");
		jComboBox.addItem("2018-01-12");
		jComboBox.addItem("2018-01-13");
		jComboBox.addItem("2018-01-14");
		jComboBox.addItem("2018-01-15");
		jComboBox.addItem("2018-01-16");
		jComboBox.addItem("2018-01-17");
		jComboBox.addItem("2018-01-18");
		jComboBox.addItem("2018-01-19");
		jComboBox.addItem("2018-01-20");
		jComboBox.addItem("2018-01-21");
		jComboBox.addItem("2018-01-22");
		jComboBox.addItem("2018-01-23");
		jComboBox.addItem("2018-01-24");
		jComboBox.addItem("2018-01-25");
		jComboBox.addItem("2018-01-26");
		jComboBox.addItem("2018-01-27");
		jComboBox.addItem("2018-01-28");
		jComboBox.addItem("2018-01-29");
		jComboBox.addItem("2018-01-30");
		jComboBox.addItem("2018-01-31");
		jComboBox.setBounds(70, 30, 150, 35);

		// 下拉列表2
		JComboBox<String> jComboBox1 = new JComboBox<String>();
		jComboBox1.addItem("温度");
		jComboBox1.addItem("湿度");
		jComboBox1.addItem("二氧化碳");
		jComboBox1.addItem("光照强度数据");
		jComboBox1.setBounds(300, 30, 150, 35);

		// 查询按钮
		JButton jButton = new JButton("查询");
		jButton.setBounds(510, 32, 80, 30);
		jButton.setBackground(new Color(26, 188, 229));
		// 去掉边框
		jButton.setBorderPainted(false);
		jButton.setFont(new java.awt.Font("楷体", 1, 15));
		jButton.setForeground(Color.white);

		// 标题

		Label label = new Label();
		label.setBounds(72, 75, 50, 20);
		label.setFont(new java.awt.Font("楷体", 1, 15));
		label.setText("Type");
		Label label1 = new Label();
		label1.setBounds(142, 75, 50, 20);
		label1.setFont(new java.awt.Font("楷体", 1, 15));
		label1.setText("Data");
		Label label2 = new Label();
		label2.setBounds(300, 75, 50, 20);
		label2.setFont(new java.awt.Font("楷体", 1, 15));
		label2.setText("Date");
		Label label3 = new Label();
		label3.setBounds(400, 75, 50, 20);
		label3.setFont(new java.awt.Font("楷体", 1, 15));
		label3.setText("Time");

		// 多行文本
		JTextArea jTextArea = new JTextArea();
		// 设置禁止编辑
		jTextArea.setEditable(false);
		// 设置字体
		jTextArea.setFont(new java.awt.Font("楷体", 1, 15));
		// 创建滚动窗格
		JScrollPane pane = new JScrollPane(jTextArea);
		pane.setBounds(50, 95, 695, 300);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		panel.add(label);
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(jComboBox);
		panel.add(jComboBox1);
		panel.add(jButton);
		panel.add(pane);
		panel.setVisible(true);
		this.add(panel);
		this.setBounds(283, 159, 800, 449);

		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 拿到下拉列表中值
				String jcb = (String) jComboBox.getSelectedItem();
				String jcb1 = (String) jComboBox1.getSelectedItem();
				String[] split = jcb.split("[-]");
				HashMap<String, String> cdata = JDBCdata(split[2], jcb1);
				Iterator<Entry<String, String>> it = cdata.entrySet().iterator();
				if (it == null)
					jTextArea.append("暂无数据");
				while (it.hasNext()) {
					Entry<String, String> next = it.next();
					String key = next.getKey();
					String value = next.getValue();
					jTextArea.append("   " + jcb1 + "   " + key + "     	" + value +"\n");
				}
			}
		});
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
//		this.add(panel);
//		this.setVisible(true);
	}

	// 连接数据库
	public HashMap<String, String> JDBCdata(String time, String name) {
		HashMap<String, String> map = new HashMap<String, String>();

		try {
			conn = DriverManager.getConnection(url, user, pa);
			Statement met = conn.createStatement();
			ResultSet exeSet = met
					.executeQuery("select data,gather_date from  e_detail_" + time + " where name = '" + name + "'");
			while (exeSet.next()) {
				String n = exeSet.getString("data");
				String p = exeSet.getString("gather_date");
				map.put(n, p);
			}
			// select data,gather_date from e_detail_1 where name = '温度'
			if (met != null)
				met.close();
			if (exeSet != null)
				exeSet.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

//
//	public static void main(String[] args) throws Exception {
//		new IndextPanel();
//	}

}
