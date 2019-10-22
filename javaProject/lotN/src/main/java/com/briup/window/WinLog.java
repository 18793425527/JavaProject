package com.briup.window;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
@SuppressWarnings("all")
public class WinLog extends JFrame  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 声明容器和要用的组件
	private JButton button,button2;
	private JPanel panel;
	private JTextField f1;
	private JPasswordField f2;
	private JLabel label1,label2;
	private ImageIcon background;
	public boolean setv;
	Serve serve = new Serve();
	IndextPanel i = new IndextPanel();

	public WinLog() {
		initComponent();
		this.setVisible(true);
	}
	// 初始化组件
	public void initComponent() {
		JFrame windoFrame = new JFrame();
		// 初始化面板
		panel = new JPanel();
		//不使用布局
		panel.setLayout(null);

		// 初始化登录按钮
		button = new JButton("登  录");
		button2 = new JButton("注 册");
		f1 = new JTextField();
		f2 = new JPasswordField();
		label1 = new JLabel();

		// 设置大小
		button.setSize(136, 23);
		button.setLocation(339, 304);
		button.setBackground(new Color(26, 188, 229));
		// 去掉边框
		button.setBorderPainted(false);
		button.setFont(new java.awt.Font("楷体", 1, 15));
		button.setForeground(Color.white);
		
		button2.setSize(65, 23);
		button2.setLocation(480, 304);
		button2.setBackground(new Color(26, 188, 229));
		button2.setBorderPainted(false);
		button2.setForeground(Color.white);
		f1.setSize(164, 23);
		f1.setLocation(340, 225);
		f2.setSize(164, 23);
		f2.setLocation(340, 262);
		panel.setSize(800, 449);
		this.setTitle("NB物联网信息采集系统");
		this.setBounds(283, 159, 800, 449);
		this.setBackground(Color.gray);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 设置背景图片
		String path = "src/main/resources/img/111.png";
		// 背景图片
		ImageIcon background = new ImageIcon(path);
		// 把背景图片显示在一个标签里面
		JLabel label1 = new JLabel(background);
		// 把标签的大小位置设置为图片刚好填充整个面板
		label1.setBounds(0, 0, this.getWidth(), this.getHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		// 把背景图片添加到分层窗格的最底层作为背景
		this.getLayeredPane().add(label1, new Integer(Integer.MIN_VALUE));

		//	添加
		panel.add(button);
		panel.add(button2);
		panel.add(f1);
		panel.add(f2);
		panel.add(label1);
		this.add(panel);
		
		button.addActionListener(new ActionListener() {
			// 当鼠标点击这个指定按钮的时候程序就会调用这个方法
			@Override
			public void actionPerformed(ActionEvent e) {
				// 拿到第一个输入框中的数组(String转换为Double)
				String name = f1.getText();
				// 拿到第二个输入框中的数组(String转换为Double)
				String password = f2.getText();
				try {
					// 登录
					serve.login(name, password);
					i.setVisible(true);
					WinLog.this.dispose();
					new SuccessPanel("登录成功！");
				} catch (Exception e2) {
					new ErrorPanel(e2.getMessage());
				}
			}
		});
		
		//注册按钮
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 如果按下注册按钮，信息清空，获得框中信息
				// 拿到第一个输入框中的数组(String转换为Double)
				String name = f1.getText();
				// 拿到第二个输入框中的数组(String转换为Double)
				String password = f2.getText();
				try {
					serve.register(name, password);
				} catch (Exception e2) {
					new ErrorPanel(e2.getMessage());
				}
				f1.setText("");
				f2.setText("");
			}
		});
	}

//	public static void main(String[] args) {
//		new WinLog();
//	}
}







