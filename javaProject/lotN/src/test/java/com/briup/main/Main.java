package com.briup.main;

import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.server.Server;
import com.briup.util.ConFIgurationIMP;
import com.briup.window.WinLog;

public class Main {
	public static void main(String[] args) {
		ConFIgurationIMP config = new ConFIgurationIMP();
		try {
			config.readXML();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 客户端线程，负责采集数据和备份数据
		Thread client = new Thread() {
			@Override
			public void run() {
				// 拿到GatherImp 对象，调用gather()方法
				try {
					// 调用gather()，得到Collection的集合
					Collection<Environment> c = (Collection<Environment>) config.getGather().gather();
					// 数据发送给服务器
					config.getClient().send(c);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		};
		
		// 服务器线程，负责接收客户端发送过来的数据，将数据进行存入数据库中
		Thread server = new Thread() {
			@Override
			public void run() {
				// 拿到ServerIMP对象，调用reciver方法，拿到Collection
				Collection<Environment> r;
				try {
					Server s = config.getServer();
					r = s.reciver();
					// 数据入库
					config.getDbStore().saveDb(r);
					
					// 调用关闭服务程序
					s.shutdown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		// 启动线程
		server.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		client.start();
		
		// 启动数据查询界面
		new WinLog();
		
	}
}
