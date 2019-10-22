package com.briup.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
@SuppressWarnings("all")
public class ErrorPanel extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jpanel;
	private JLabel label;
	private JButton jButton;
	public ErrorPanel(String msg) {
		setTitle("错误信息");
		setBounds(600,334,180,150);
		JPanel jPanel = new JPanel();
		jPanel.setSize(200,150);
		JLabel label = new JLabel(msg,JLabel.CENTER);
		label.setFont(new java.awt.Font("楷体", 1, 15));
		jButton = new JButton("确定");
		jButton.setBounds(20,235,60,15);
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ErrorPanel.this.setVisible(false);
			}
		});
		jPanel.add(label);
		jPanel.add(jButton);
		add(jPanel);
		setVisible(true);
	}
}
