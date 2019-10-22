package com.briup.util;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.briup.client.Client;
import com.briup.client.Gather;
import com.briup.server.DBStore;
import com.briup.server.Server;

/**
 * 读取配置文件信息 1、解析出数据 2、将对应信息进行传递过去
 * 
 */
public class ConFIgurationIMP implements Configuration {

	// 用来存放xml内容，key 是名字，value 是文本内容
	HashMap<String, WossModuleInit> map = null;

	// 传递解读的配置信息
	private static Properties properties = new Properties();

	// 存放反射的对象
	private static Object newInstance = null;

	@Override
	public Log getLogger() throws Exception {
		return (Log) map.get("Log");
	}

	@Override
	public Server getServer() throws Exception {
		return (Server) map.get("Server");
	}

	@Override
	public Client getClient() throws Exception {
		return (Client) map.get("Client");
	}

	@Override
	public DBStore getDbStore() throws Exception {
		return (DBStore) map.get("DBStore");
	}

	@Override
	public Gather getGather() throws Exception {
		return (Gather) map.get("Gather");
	}

	// 解析XML文件
	public void readXML() throws Exception {
		// WossModuleInit是子类对象
		map = new HashMap<String, WossModuleInit>();

		String text = "src/main/resources/config.xml";
		// 获得dom4j的解析对象
		SAXReader r = new SAXReader();
		try {
			Document doc = r.read(text);
			// 拿到根元素
			Element root = doc.getRootElement();

			// 拿到根节点的集合
			List<Element> elements = root.elements();

			// 遍历root的子元素
			elements.forEach(s -> {
				// 拿到子元素的属性值
				String cl = s.attribute("class").getText();
				try {
					// 使用反射，拿到class属性值的类对象
					newInstance = Class.forName(cl).newInstance();
					if (newInstance instanceof WossModuleInit) {
						map.put(s.getName(), (WossModuleInit) newInstance);
					}

					// 拿到子元素的对象
					List<Element> elements2 = s.elements();

					// 遍历二级子元素，将元素的Name和文本内容存放在properties中
					elements2.forEach(n -> {
						properties.setProperty(n.getName(), n.getText());
					});
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					System.out.println("反射：" + newInstance);
					((WossModuleInit) newInstance).init(properties);
				} catch (Exception e) {
					e.printStackTrace();
				}

			});

		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}
}
