package com.briup.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 */
@SuppressWarnings("all")
public class Environment implements Serializable {
	private String name;	
	// 发送端id
	private String SrcID;
	// 树莓派系统id
	private String DstID;
	// 实验箱区域模块id(1-8)
	private String DevID;
	// 模块上传感器地址
	private String SersorAddress;
	// 传感器个数
	private int Count;
	// 指令标号
	private String Cmd;
	// 数据
	private float Data;
	// 状态标示
	private int Status;
	// 采集时间
	private String time;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrcID() {
		return SrcID;
	}
	public void setSrcID(String srcID) {
		SrcID = srcID;
	}
	public String getDstID() {
		return DstID;
	}
	public void setDstID(String dstID) {
		DstID = dstID;
	}
	public String getDevID() {
		return DevID;
	}
	public void setDevID(String devID) {
		DevID = devID;
	}
	public String getSersorAddress() {
		return SersorAddress;
	}
	public void setSersorAddress(String sensorAddress) {
		SersorAddress = sensorAddress;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}
	public String getCmd() {
		return Cmd;
	}
	public void setCmd(String cmd) {
		Cmd = cmd;
	}
	public float getData() {
		return Data;
	}
	public void setData(float data) {
		Data = data;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Environment() {
	}
	
	@Override
	public String toString() {
		return "Environment [name=" + name + ", SrcID=" + SrcID + ", DstID=" + DstID + ", DevID=" + DevID
				+ ", SensorAddress=" + SersorAddress + ", Count=" + Count + ", Cmd=" + Cmd + ", Data=" + Data
				+ ", Status=" + Status + ", time=" + time + "]";
	}
	
}
