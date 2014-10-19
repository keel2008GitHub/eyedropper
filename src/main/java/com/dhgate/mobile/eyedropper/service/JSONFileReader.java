package com.dhgate.mobile.eyedropper.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONFileReader {

	public static String readerFromFile(String filePath)
			throws FileNotFoundException, IOException {

		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder sb = new StringBuilder();
		String s;
		while ((s = br.readLine()) != null) {
			sb.append(s.trim());
		}

		fr.close();
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
