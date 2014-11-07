package com.dhgate.mobile.eyedropper.util;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

public class ShellExecutor {

	/**
	 * 执行Shell命令工具。
	 * 
	 * @param cmd
	 * @param params
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static String exec(String cmd, String... params) throws IOException,
			InterruptedException {

		StringWriter writer = new StringWriter();

		try {

			StringBuilder shell = new StringBuilder();

			shell.append(cmd);

			for (String param : params) {
				shell.append(" ").append(param);
			}

			Process p = Runtime.getRuntime().exec(shell.toString());
			p.waitFor();

			IOUtils.copy(p.getInputStream(), writer, null);

			return writer.toString();

		} finally {
			if (writer != null) {
				writer.close();
			}
		}

	}

	public static void main(String[] args) {
		// System.out.println(exec("F:/test.bat", null));
		// Runtime.getRuntime().exec("cmd /c start F:\\test");
	}
}
