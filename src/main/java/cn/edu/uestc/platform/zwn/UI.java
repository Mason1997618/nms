package cn.edu.uestc.platform.zwn;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cn.edu.uestc.platform.dynamicChange.DynamicController;
import cn.edu.uestc.platform.dynamicChange.DynamicDockerController;

public class UI extends JFrame {
	private static final long serialVersionUID = 6804861217844465136L;

	UI() {
		setSize(900, 600);
		setTitle("网络仿真动态场景");
		Container conPane = getContentPane();
		conPane.setLayout(new BorderLayout(10, 10));
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		TextArea text = new TextArea("------------TimeLine------------:");
		conPane.add("Center", text);
		JButton createNodes = new JButton("生成所有节点");
		createNodes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				new DynamicController().createAllNodes();
				new DynamicDockerController().createAllNodes();
			}
		});
		p.add(createNodes);
		JButton createLinks = new JButton("生成所有网段");
		createLinks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DynamicDockerController().createAllLinks();
			}
		});
		p.add(createLinks);
		JButton t0But = new JButton("生成初始拓扑图");
		t0But.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new DynamicDockerController().t0();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		p.add(t0But);
		JButton dyBut = new JButton("开始动态变化");
		dyBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				new DynamicController().change(text);
				new DynamicThread(text).start();
			}
		});
		p.add(dyBut);
		conPane.add("North", p);
	}

}

class DynamicThread extends Thread {
	private TextArea text;

	public DynamicThread(TextArea text) {
		this.text = text;
	}

	@Override
	public void run() {
		new DynamicDockerController().change(text);
	}
}
