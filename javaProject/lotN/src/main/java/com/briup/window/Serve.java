package com.briup.window;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.sound.midi.MetaEventListener;

import com.briup.log.LogIMP;

import oracle.net.aso.s;

@SuppressWarnings("all")
public class Serve {
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

	// 声明日志对象，用于写日志文件
	LogIMP log = new LogIMP();

	// 获取当前时间点
	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	// 从数据库中拿出name和password，存在HashMap里面
	HashMap<String, String> map = new HashMap<String, String>();

	private static Serve serve = new Serve();

	public HashMap<String, String> JDBCConn() {

		// 连接数据库
		try {
			conn = DriverManager.getConnection(url, user, pa);
			log.info("登录模块连接数据库成功！" + dateFormat.format(date));
			Statement met = conn.createStatement();
			ResultSet exeSet = met.executeQuery("select name,passworld from users");
			while (exeSet.next()) {
				String n = exeSet.getString("name");
				String p = exeSet.getString("passworld");
				map.put(n, p);
			}
			if (exeSet != null)
				exeSet.close();
			if (met != null)
				met.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
					log.info("登录模块退出！" + dateFormat.format(date));
					log.info("\n");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	// 验证登录时用户名和密码是否正确
	public void login(String name, String password) throws Exception {
		// 验证是否为空
		verify(name, password);
		HashMap<String, String> jdbcmap = serve.JDBCConn();

		// 用于判断的标志位
		boolean sign = true;

		Iterator<Entry<String, String>> it = jdbcmap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> next = it.next();
			String n = next.getKey();
			String p = next.getValue();
			if (name.equals(n)) {
				try {
					if (password.equals(p)) {
						sign = false;
						return;
					} else {
						throw new Exception("用户名密码错误!");
					}
				} catch (Exception e) {
					new ErrorPanel(e.getMessage());
				}
			}
		}

		if (sign) {
			throw new Exception("用户名不存在!！");
		}
	}

	// 验证是否为空
	public static boolean verify(String name, String password) throws Exception {
		if ("".equals(name) || name == null) {
			throw new Exception("用户名不能为空!");
		} else if ("".equals(password) || password == null) {
			throw new Exception("密码不能为空!！");
		} else {
			return true;
		}
	}

	// 注册时验证
	public void register(String name, String password) throws IOException {

		// 判断的标志
		boolean sign = true;
		HashMap<String, String> jdbcmap = serve.JDBCConn();

		Iterator<Entry<String, String>> it = jdbcmap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> next = it.next();
			String n = next.getKey();
			if (name.equals(n))
				try {
					sign = false;
					throw new Exception("用户名已存在！");
				} catch (Exception e) {
					new ErrorPanel(e.getMessage());
				}
		}

		if (sign) {
			try {
				System.out.println("得到" + url + user + pa);
				conn = DriverManager.getConnection(url, user, pa);
				log.info("注册模块连接数据库成功！" + dateFormat.format(date));
				Statement met = conn.createStatement();
				ResultSet exeSet = met
						.executeQuery("insert into users(name,passworld) values('" + name + "','" + password + "')");
				if (exeSet != null)
					exeSet.close();
				if (met != null)
					met.close();
				new SuccessPanel("注册成功！");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
