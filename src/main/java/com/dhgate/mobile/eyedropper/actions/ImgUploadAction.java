package com.dhgate.mobile.eyedropper.actions;

import java.io.File;
import java.io.IOException;

import com.dhgate.mobile.eyedropper.service.JSONFileReader;
import com.dhgate.mobile.eyedropper.service.ShellExecutor;
import com.opensymphony.xwork2.ActionSupport;

public class ImgUploadAction extends ActionSupport {

	private File file;
	private String contentType;
	private String filename;

	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}

	public String execute() {
		// ...

		try {
			// 1.saveFileAsTempDir
			ShellExecutor.exec(" ", file.getAbsolutePath());

			JSONFileReader.readerFromFile(file.getPath() + "");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}

	public static String trimExtension(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length()))) {
				return filename.substring(0, i);
			}
		}
		return filename;
	}

	public static void main(String[] args) {
		try {
			// 1.saveFileAsTempDir
			final File temp = File.createTempFile("temp",
					Long.toString(System.nanoTime()));
			temp.mkdir();

			System.out.println(temp.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
