package com.briup.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.log.LogIMP;
import com.briup.util.ConFIgurationIMP;

/********************************
 * 1、读取文件，一次一行 分割数据，9个 2、封装数据 3、备份数据
 * 
 *****************************/
public class GatherImp implements Gather {

	static BufferedReader in = null;
	static FileInputStream r = null;
	static BufferedWriter w = null;
	static File file1 = null;
	static File file2 = null;

	// 存储所有的Environment对象，最后Environment对象的集合返回
	ArrayList<Environment> list = new ArrayList<Environment>();

	// 声明日志对象，用于写日志文件
	LogIMP log = new LogIMP();

	// 获取当前时间点
	Date date = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Override
	public void init(Properties properties) throws Exception {
		file1 = new File(properties.getProperty("path"));
		file2 = new File(properties.getProperty("backup"));
	}

	@Override
	public Collection<Environment> gather() throws Exception {
		// 读取数据
		read();

		// 备份数据
		backupData();

		return list;
	}

	/*
	 * 1、读取 2、解析数据
	 */
	public void read() {
		log.info("客户端已启动" + dateFormat.format(date));
		// 定义计数器
		int TC = 0, S = 0, CO2 = 0;

		try {
			in = new BufferedReader(new FileReader(file1));
			String lin;
			while ((lin = in.readLine()) != null) {
				// 使用“|”分割，得到九个数据
				String[] split = lin.split("[|]");

				/*
				 * 封装温度、湿度 1、判断这条数据是温度、湿度数据、光照强度数据、二氧化碳数据四者中那个
				 * 2、拿到的数据String类型的，所以在存储的时候要进行强转，Integer.parseInt(String) split[]
				 * 数组中第7条数据为data数据，当为温湿度数据时，前4个位温度data，中间四个数据是湿度data 使用“|”,substring(start，end)
				 * 截取
				 **/
				if ("16".equals(split[3])) {
					Integer data = Integer.parseInt(split[6].substring(0, 4), 16);
					float f = (float) (((float) data * 0.00268127) - 46.85);

					// 将数据传到passData() 中进行添加到集合中存储
					passData(split, "温度", f);

					Integer data1 = Integer.parseInt(split[6].substring(4, 9), 16);
					float f1 = (float) (((float) data1 * 0.00190735) - 6);

					passData(split, "湿度", f1);
					TC++;
				} else if ("256".equals(split[3])) {
					// 类型强转 16 ---> 10
					Integer data = Integer.parseInt(split[6], 16);
					passData(split, "光照强度数据", (float) data);
					S++;
				} else if ("1280".equals(split[3])) {
					Integer data = Integer.parseInt(split[6], 16);
					passData(split, "二氧化碳数据", (float) data);
					CO2++;
				}
			}
			log.info("温湿度：" + TC);
			log.info("光照强度数据：" + S);
			log.info("二氧化碳数据：" + CO2);
			log.info("数据采集完成！" + dateFormat.format(date));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 将数据封装成Environment对象
	public ArrayList<Environment> passData(String[] split, String name, Float f) {
		Environment environment = new Environment();
		environment.setName(name);
		environment.setSrcID(split[0]);
		environment.setDstID(split[1]);
		environment.setDevID(split[2]);
		environment.setSersorAddress(split[3]);
		environment.setCount(Integer.valueOf(split[4]));
		environment.setCmd(split[5]);
		environment.setData(f);
		environment.setStatus(Integer.valueOf(split[7]));

		// 将时间戳转成字符串
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(new Date(Long.parseLong(split[8])));
		// 将时间存储
		environment.setTime(format);

		// 将存储本行数据的对象存储在集合中
		list.add(environment);
		return list;
	}

	/*
	 * 备份数据 每次调用之前都检查下备份文件大小 跳过那些已经备份的数据 1、跳过备份文件大小开始读取 fileinputStrem 读取文件
	 * 2、BufferedWriter去写文件
	 */
	public void backupData() {
		try {
			in = new BufferedReader(new FileReader(file1));
			w = new BufferedWriter(new FileWriter(file2, true));
			r = new FileInputStream(file2);

			// 判断文件是否存在，不存在新建一个
			if (!file2.exists()) {
				file2.createNewFile();
			}

			// 得到file2的字节数,跳过已经备份的数据
			in.skip(r.available());

			// 读取的数据进行备份
			String str = null;
			while ((str = in.readLine()) != null) {
				w.write(str);
				w.newLine();
				w.flush();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (r != null)
					r.close();
				in.close();
				if (w != null)
					w.close();
				log.info("备份数据完成！" + dateFormat.format(date));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
