package cn.edu.uestc.platform.testzk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JSchUtil {
	private String charset = "UTF-8"; // 设置编码格式
	private String user; // 用户名
	private String passwd; // 登录密码
	private String host; // 主机IP
	private int port; // 端口
	private JSch jsch;
	private Session session;

	/**
	 * 
	 * @param user用户名
	 * @param passwd密码
	 * @param host主机IP
	 */
	public JSchUtil(String user, String passwd, String host, int port) {
		this.user = user;
		this.passwd = passwd;
		this.host = host;
		this.port = port;
	}

	/**
	 * 连接到指定的IP
	 * 
	 * @throws JSchException
	 */
	public void connect() throws JSchException {
		jsch = new JSch();
		session = jsch.getSession(user, host, port);
		session.setPassword(passwd);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
	}

	/**
	 * 关闭连接
	 */
	public void disconnect() throws Exception {
		if (session != null && session.isConnected()) {
			// session.disconnect();
		}
	}

	/**
	 * 执行一条命令
	 */
	public String execCmd(String command) throws Exception {
		BufferedReader reader = null;
		Channel channel = null;
		String CMDMsg = "";
		int count = 0;
		channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);
		channel.setInputStream(null);
		((ChannelExec) channel).setErrStream(System.err);
		channel.connect();
		InputStream in = channel.getInputStream();
		reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset)));
		String buf = null;
		while ((buf = reader.readLine()) != null) {
			count++;
			if (count % 8 == 0) {
				CMDMsg += "\n";
			}
			CMDMsg += buf + "   ";
			// System.out.println(buf);
		}
		channel.disconnect();
		System.out.println(CMDMsg);
		return CMDMsg;
	}

	/**
	 * 执行相关的命令
	 */
	public void execCmd() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String command = "";
		BufferedReader reader = null;
		Channel channel = null;

		try {
			while ((command = br.readLine()) != null) {
				channel = session.openChannel("exec");
				((ChannelExec) channel).setCommand(command);
				channel.setInputStream(null);
				((ChannelExec) channel).setErrStream(System.err);

				channel.connect();
				InputStream in = channel.getInputStream();
				reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset)));
				String buf = null;
				while ((buf = reader.readLine()) != null) {
					System.out.println(buf);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public String execShell(String cmd) throws JSchException, InterruptedException, IOException {
		
		Channel channel = session.openChannel("shell");
		channel.connect();
		channel.setOutputStream(System.out, true);
		OutputStream inputstream_for_the_channel = channel.getOutputStream();
		PrintStream commander = new PrintStream(inputstream_for_the_channel, true);
		commander.println(cmd);
		commander.close();

		do {
			Thread.sleep(1000);
		} while (!channel.isEOF());
		session.disconnect();
		return null;
	}
}
