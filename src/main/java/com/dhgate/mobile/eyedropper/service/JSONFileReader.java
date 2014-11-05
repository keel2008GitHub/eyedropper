package com.dhgate.mobile.eyedropper.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONFileReader {

	/**
	 * 读取文件内容。
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readerFromFile(String filePath)
			throws FileNotFoundException, IOException {

		StringBuilder sb;
		FileReader fr = null;
		BufferedReader br = null;
		try {

			fr = new FileReader(filePath);
			br = new BufferedReader(fr);

			sb = new StringBuilder();
			String s;
			while ((s = br.readLine()) != null) {
				sb.append(s.trim());
			}

			fr.close();

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (fr != null) {
				fr.close();
			}
			if (br != null) {
				br.close();
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		try {
			System.out.println(readerFromFile("f:\\test.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
