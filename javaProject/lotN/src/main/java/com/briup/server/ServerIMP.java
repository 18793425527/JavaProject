package com.briup.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.log.LogIMP;

/**
 * 实现服务器功能 1、接受发送过来的数据 2、传给入库模块
 * 
 */
public class ServerIMP implements Server {
	
	private static ServerSocket server = null;
	private static Socket client = null;
	private static ObjectInputStream ois = null;
	private static int port = 0;

	// 声明日志对象，用于写日志文件
	private static LogIMP log = new LogIMP();

	// 获取当前时间点
	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Override
	public void init(Properties properties) throws Exception {
		port = Integer.parseInt(properties.getProperty("port"));
	}

	// 接受客户端传过来的数据
	public Collection<Environment> reciver() throws Exception {
		// 传端口号，连接客户端
		server = new ServerSocket(port);
		log.info("服务器已启动" + dateFormat.format(date));
		
		// 连接客户端
		client = server.accept();
		log.info("客户端连接成功！" + dateFormat.format(date));

		// 接受客户端发送的数据
		ois = new ObjectInputStream(client.getInputStream());
		
		// 读取客户端发送过来的对象，并做强制转换
		Collection<Environment> read = (Collection<Environment>) ois.readObject();
		log.info("服务器接收数据完成！" + dateFormat.format(date));
		return read;
	}

	// 关闭服务器
	public void shutdown() {
		try {
			if (ois != null)
				ois.close();
			if (server != null)
				server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
