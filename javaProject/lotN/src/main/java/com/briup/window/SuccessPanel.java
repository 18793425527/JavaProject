package com.briup.window;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
@SuppressWarnings("all")
public class SuccessPanel extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanel;
	private JLabel label;
	private JButton jButton;
	
	public SuccessPanel(String msg) {
		setTitle("成功");
		setBounds(600,334,200,150);
		setVisible(true);
		JPanel jPanel = new JPanel();
		setLayout(new GridLayout(2,2));
		JLabel label = new JLabel(msg,JLabel.CENTER);
		label.setFont(new java.awt.Font("楷体", 1, 20));
		jButton = new JButton("确定");
		jButton.setSize(30, 20);
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IndextPanel indextPanel = new IndextPanel();
				SuccessPanel.this.dispose();
			}
		});
		jPanel.add(jButton);
		add(label);
		add(jPanel);
	}
	
}
