package com.dhgate.mobile.eyedropper.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.dhgate.mobile.eyedropper.util.ShellExecutor;

public class DeepViewService {

	public static File copyDeepViewPicutre(File tmpFile, String filePath,
			String fileName) throws Exception {

		String tmpDirName = Long.toString(System.nanoTime(), 36);

		String tempFileDir = filePath + "/" + tmpDirName;

		File tempFileDirFile = new File(tempFileDir);

		if (!tempFileDirFile.exists()) {
			tempFileDirFile.mkdirs();
		}

		if (!tempFileDirFile.isDirectory()) {
			throw new Exception("eyedropper配置异常:" + filePath + "不是目录");
		}

		File pictureFile = new File(tempFileDir + "/" + fileName);

		FileUtils.copyFile(tmpFile, pictureFile);

		return pictureFile;
	}

	/**
	 * 执行DeepView脚本，创建结果文件。
	 * 
	 * @param pictureFile
	 * @param tempDir
	 * @throws Exception
	 */
	public static File deepViewPicute(String shellPath, File pictureFile)
			throws Exception {

		String resultFileName = pictureFile.getParent() + "/" + "result.json";
		String sysout = ShellExecutor.exec(shellPath,
				pictureFile.getAbsolutePath(), resultFileName);

		System.out.println(sysout);

		File resultFile = new File(resultFileName);

		if (!resultFile.exists()) {
			throw new Exception("读取分析结果文件失败:Couldn't find "
					+ resultFile.getAbsolutePath());
		}

		return resultFile;
	}

	/**
	 * 读取文件内容工具。
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String readerFromFile(File filePath)
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

}
