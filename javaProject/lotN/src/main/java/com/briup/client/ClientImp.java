package com.briup.client;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.log.LogIMP;

public class ClientImp implements Client {
	private static String ip;
	private static Integer port;
	
	private static LogIMP log = new LogIMP();

	// 获取当前时间点
	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	// 拿到ClientImp对象，调用send()和init()方法
	private static ClientImp client = new ClientImp();
	

	@Override
	public void init(Properties properties) throws Exception {
		ip = properties.getProperty("ip");
		port = Integer.parseInt(properties.getProperty("port"));
	}

	/*
	 * 连接服务器，传list集合过去
	 * 
	 */
	
	@Override
	public void send(Collection<Environment> coll) throws Exception {
		// 连接服务器 (套接字，双向链路节点)
		Socket socket = new Socket(ip, port);

		// 声明IO流，将Environment对象的集合发送给服务器
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject(coll);
		out.flush();
		log.info("发送数据完成！" + dateFormat.format(date));
		if (out != null)
			out.close();
	}
}
