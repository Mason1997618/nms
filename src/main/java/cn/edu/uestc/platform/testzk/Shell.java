package cn.edu.uestc.platform.testzk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.junit.Test;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class Shell {
	private static final String USER = "controller";
	private static final String PASSWORD = "123456";
	private static final String HOST = "10.0.0.11";
	private static final int DEFAULT_SSH_PORT = 22;

	public static void main(String[] arg) {
		// ChannelSftp sdf = new ChannelSftp();

		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(USER, HOST, DEFAULT_SSH_PORT);
			session.setPassword(PASSWORD);

			UserInfo userInfo = new UserInfo() {
				@Override
				public String getPassphrase() {
					System.out.println("getPassphrase");
					return null;
				}

				@Override
				public String getPassword() {
					System.out.println("getPassword");
					return null;
				}

				@Override
				public boolean promptPassword(String s) {
					System.out.println("promptPassword:" + s);
					return false;
				}

				@Override
				public boolean promptPassphrase(String s) {
					System.out.println("promptPassphrase:" + s);
					return false;
				}

				@Override
				public boolean promptYesNo(String s) {
					System.out.println("promptYesNo:" + s);
					return true;// notice here!
				}

				@Override
				public void showMessage(String s) {
					System.out.println("showMessage:" + s);
				}
			};

			session.setUserInfo(userInfo);

			// It must not be recommended, but if you want to skip host-key
			// check,
			// invoke following,
			 session.setConfig("StrictHostKeyChecking", "no");

			// session.connect();
			session.connect(30000); // making a connection with timeout.

			Channel channel = session.openChannel("shell");

			// Enable agent-forwarding.
			// ((ChannelShell)channel).setAgentForwarding(true);
//			channel.setInputStream(new FilterInputStream(System.in){
//		          public int read(byte[] b, int off, int len)throws IOException{
//		            return in.read(b, off, (len>1024?1024:len));
//		          }
//		        });
//			 String cmd = "ls";
//			 InputStream in = new ByteArrayInputStream(cmd.getBytes());
//			 channel.setInputStream(in);
			channel.setInputStream(System.in);
//			 
//			 channel.sendSignal(cmd);
//			 OutputStream out = new ByteArrayOutputStream();
//			channel.setOutputStream(out);
//			byte []chr = new byte[1024];
//			out.write(chr);
//			System.out.println(new String(chr));
			// in = channel.getInputStream();
			// byte [] chr = new byte[50];
			// in.read(chr);
			// System.out.println(new String(chr));
			/*
			 * // a hack for MS-DOS prompt on Windows.
			 * channel.setInputStream(new FilterInputStream(System.in){ public
			 * int read(byte[] b, int off, int len)throws IOException{ return
			 * in.read(b, off, (len>1024?1024:len)); } });
			 */
			// OutputStream out = new ByteArrayOutputStream();
			// out.write(chr);
			// System.out.println(new String(chr));
			/*
			 * // Choose the pty-type "vt102".
			 * ((ChannelShell)channel).setPtyType("vt102");
			 */

			/*
			 * // Set environment variable "LANG" as "ja_JP.eucJP".
			 * ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
			 */

			// channel.connect();
			channel.connect(3 * 1000);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test
	public void demo10() throws JSchException, IOException {
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(USER, HOST, DEFAULT_SSH_PORT);
			session.setPassword(PASSWORD);

			UserInfo userInfo = new UserInfo() {
				@Override
				public String getPassphrase() {
					System.out.println("getPassphrase");
					return null;
				}

				@Override
				public String getPassword() {
					System.out.println("getPassword");
					return null;
				}

				@Override
				public boolean promptPassword(String s) {
					System.out.println("promptPassword:" + s);
					return false;
				}

				@Override
				public boolean promptPassphrase(String s) {
					System.out.println("promptPassphrase:" + s);
					return false;
				}

				@Override
				public boolean promptYesNo(String s) {
					System.out.println("promptYesNo:" + s);
					return true;// notice here!
				}

				@Override
				public void showMessage(String s) {
					System.out.println("showMessage:" + s);
				}
			};

			session.setUserInfo(userInfo);

			// It must not be recommended, but if you want to skip host-key
			// check,
			// invoke following,
			 session.setConfig("StrictHostKeyChecking", "no");

			// session.connect();
			session.connect(30000); // making a connection with timeout.

			Channel channel = session.openChannel("shell");
			String cmd = "ls";
			InputStream fin = new ByteArrayInputStream(cmd.getBytes());
			channel.setInputStream(fin);
			InputStream in = channel.getInputStream();
			// 写入该流的所有数据都将发送到远程端。
			OutputStream outputStream = channel.getOutputStream();
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					if (in.available() > 0)
						continue;
					System.out.println("exit-status: " + channel.getExitStatus());
					break;
				}
				// Enable agent-forwarding.
				// ((ChannelShell)channel).setAgentForwarding(true);

				// String cmd = "ls";
				// InputStream in = new ByteArrayInputStream(cmd.getBytes());
				// channel.setInputStream(in);
				// in = channel.getInputStream();
				// byte [] chr = new byte[50];
				// in.read(chr);
				// System.out.println(new String(chr));
				/*
				 * // a hack for MS-DOS prompt on Windows.
				 * channel.setInputStream(new FilterInputStream(System.in){
				 * public int read(byte[] b, int off, int len)throws
				 * IOException{ return in.read(b, off, (len>1024?1024:len)); }
				 * });
				 */
				// OutputStream out = new ByteArrayOutputStream();
				// out.write(chr);
				// System.out.println(new String(chr));
				/*
				 * // Choose the pty-type "vt102".
				 * ((ChannelShell)channel).setPtyType("vt102");
				 */

				/*
				 * // Set environment variable "LANG" as "ja_JP.eucJP".
				 * ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
				 */

				// channel.connect();
				channel.connect(3 * 1000);
				channel.disconnect();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
