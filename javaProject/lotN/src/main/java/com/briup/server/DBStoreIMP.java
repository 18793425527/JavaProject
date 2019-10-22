package com.briup.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.log.LogIMP;

public class DBStoreIMP implements DBStore {

	public static String driver = "oracle.jdbc.driver.OracleDriver";
	public static String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	public static String user = "briup";
	public static String password = "briup";

	public static Connection conn = null;
	public static Statement stat = null;
	public static ResultSet rs = null;

	// 声明日志对象，用于写日志文件
	LogIMP log = new LogIMP();

	// 获取当前时间点
	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(Properties properties) throws Exception {

	}

	public void saveDb(Collection<Environment> coll) throws Exception {

		// 记录总共读取多少条数据
		int count = 0;
		// 记录本次读取多少条数据
		int cou = 0;
		// 记录上一条数据的日期 天
		int pareDay = 0;
		// 记录本条数据的日期 天
		int day = 0;
		// 声明数据库连接对象
		PreparedStatement ps = null;

		// 连接数据库
		conn = DriverManager.getConnection(url, user, password);
		log.info("连接数据库成功！" + dateFormat.format(date));

		// 遍历集合
		for (Environment en : coll) {
			String[] split = en.getTime().split("[ ]");
			String[] split2 = split[0].split("[-]");

			// 拿到本条数据的日期 天
			day = Integer.parseInt(split2[2]);

			// 判断是和上一次的日期是否相同，不相同的时候就说明一天的数据已经完成，提交
			// 两次日期日期不相同的时候就会进入
			if (day != pareDay) {

				// 第一次进来的时候ps是空的，所以不需要提交模板缓存的数据
				if (ps != null) {
					ps.executeBatch();
				}

				// 上一天的数据已近提交完成，那么就需要重新发送sql模板，因为表的名字发生了变化
				String sql = "insert into e_detail_" + day
						+ "(name,srcId,dstId,sersorAddress,count,cmd,status,data,gather_date)"
						+ "values(?,?,?,?,?,?,?,?,?)";

				// 发送sql模板
				ps = conn.prepareStatement(sql);
				cou = 0;
				// 时间已近发生变化，记录上天时间的数据就需要发生变化
				pareDay = day;
			}

			// 开始向模板数据中缓存数据
			ps.setString(1, en.getName());
			ps.setString(2, en.getSrcID());
			ps.setString(3, en.getDstID());
			ps.setString(4, en.getSersorAddress());
			ps.setInt(5, en.getCount());
			ps.setString(6, en.getCmd());
			ps.setInt(7, en.getStatus());
			ps.setFloat(8, en.getData());

			// 时间转换：util时间转成sql的时间
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(en.getTime());
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			ps.setDate(9, sqlDate);

			// 添加到缓存模板
			ps.addBatch();

			// 判断是否到达2000调数据，如果到达就提交缓存
			if (cou == 2000) {
				ps.executeBatch();
			}
			
			// 统计存储多少条数据
			cou++;
			count++;
		}

		// 最后再提交一次
		if (ps != null)
			ps.executeBatch();

		// 提交事务
		conn.prepareStatement("commit");
		log.info("入库完成！" + dateFormat.format(date));
		log.info("本次入库" + count + "条数据" + dateFormat.format(date));
		ps.close();
		conn.close();
	}

}
