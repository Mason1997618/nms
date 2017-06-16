package cn.edu.uestc.platform.zwn;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class DynamicUI {

	public static void main(String[] args) {
		try {
			// 设置本属性将改变窗口边框样式定义
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
			// UIManager.put("RootPane.setupButtonVisible", false);
			UIManager.put("RootPane.setupButtonVisible", false);
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		} catch (Exception e) {
		}

		UI gUI = new UI();// 声明并创建按钮对象
		gUI.setVisible(true);//可视
		gUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//X关闭程序
	}
}


