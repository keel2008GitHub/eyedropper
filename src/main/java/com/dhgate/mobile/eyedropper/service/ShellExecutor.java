package com.dhgate.mobile.eyedropper.service;

import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

public class ShellExecutor {

	public static String exec(String cmd, String params) {
		try {
			// Runtime.getRuntime().exec("cmd /c start F:\\test");

			StringBuilder shell = new StringBuilder();

			shell.append(cmd);
			if (params != null && !"".equals(params.trim())) {
				shell.append(" ").append(params);
			}

			Process p = Runtime.getRuntime().exec(shell.toString());
			p.waitFor();

			StringWriter writer = new StringWriter();
			IOUtils.copy(p.getInputStream(), writer, null);
			writer.close();
			return writer.toString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public static void main(String[] args) {
		System.out.println(exec("F:/test.bat", null));
	}
}
