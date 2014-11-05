package com.dhgate.mobile.eyedropper.actions;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.dhgate.mobile.eyedropper.service.JSONFileReader;
import com.dhgate.mobile.eyedropper.service.ShellExecutor;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

public class ImgUploadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1300065585047744919L;

	private File file;
	private String contentType;
	private String filename;

	@Inject("eyedropper.deepViewer.shell.dir")
	private String shellPath;

	@Inject("eyedropper.deepViewer.fileprocess.temp")
	private String tempFilePath;

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
		PrintWriter out = null;
		try {

			String tempDirName = Long.toString(System.nanoTime(), 36);

			String tempFileName = ServletActionContext.getServletContext()
					.getRealPath("/" + tempDirName);

			System.out.println(tempFileName);

			// // 1.saveFileAsTempDir;
			// ShellExecutor.exec(shellPath, file.getAbsolutePath(), "");
			//
			// String resultJSON = JSONFileReader.readerFromFile(file.getPath()
			// + "");
			//
			// // String resultJSON = "{aa:bb}";
			//
			// HttpServletResponse response =
			// ServletActionContext.getResponse();
			// // 2.Write json to HttpResponse;
			// response.setContentType("text/html");
			// out = response.getWriter();
			// out.println(resultJSON.trim());
			// out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

		return "";
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

}
