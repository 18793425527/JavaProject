package com.briup.client;

import java.util.Collection;

import com.briup.bean.Environment;
import com.briup.util.WossModuleInit;

/**
 * Simple to Introduction
 * @ProjectName:  物联网环境监测系统
 * @Package: com.briup.environment.client
 * @InterfaceName:  Client
 * @Description:  Client接口是采集系统网络模块客户端的规范.<br/>
 * 				  Client的作用就是与服务器端进行通信,传递数据
 * @CreateDate:   2018-1-25 14:28:30
 * @author briup
 * @Version: 1.0
 */
public interface Client extends WossModuleInit{
	/**
	 * send方法用于与服务器端进行交互，发送Environment集合至服务器端。
	 * @param coll
	 * @throws Exception
	 */
	public void send(Collection<Environment> coll)throws Exception;
}
