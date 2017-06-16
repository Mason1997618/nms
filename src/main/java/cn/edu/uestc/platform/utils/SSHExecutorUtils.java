package cn.edu.uestc.platform.utils;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.jcraft.jsch.JSchException;

/**
 * SSH执行工具类
 */
public class SSHExecutorUtils {
	private static Logger logger = Logger.getLogger(SSHExecutorUtils.class);
	private SSHExecutor ssh = null;

	public SSHExecutorUtils(String username, String password, String host) {
		boolean login = false;
		while (login == false) {
			try {
				ssh = new SSHExecutor(username, password, host);
				login = true;
				logger.info("成功进入虚拟机   "+username +" 其浮动ip地址为：-> " + host);
			} catch (JSchException e) {
				// 出了异常，说明没有成功进入虚拟机，从新进行while循环
				logger.warn("正在尝试进入虚拟机，其浮动ip地址为：-> " + host + " 原因为：浮动ip的添加不能立即生效");
				try {
					TimeUnit.SECONDS.sleep(3);// 每隔3s进行检测
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * 主要是在这里执行命令
	 */
	public String exec(String cmd) throws Exception {
		return ssh.exec(cmd);
	}

	public void close() {
		ssh.close();
	}

}
