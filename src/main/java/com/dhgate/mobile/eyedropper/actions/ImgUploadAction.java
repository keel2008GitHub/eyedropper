package com.dhgate.mobile.eyedropper.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.dhgate.mobile.eyedropper.service.DeepViewService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;

/**
 * 
 * 处理文件上传的action。
 * 
 * @author wangmeng
 * 
 */
public class ImgUploadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1300065585047744919L;

	private File file;

	private String filename;

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}

	@Inject("eyedropper.deepViewer.shell.dir")
	private String shellPath;

	@Inject("eyedropper.deepViewer.fileprocess.temp")
	private String filePath;

	public void setUpload(File file) {
		this.file = file;
	}

	public String execute() throws Exception {

		PrintWriter out = null;

		try {

			// 1.copy上传文件到待处理目录。
			File pictureFile = DeepViewService.copyDeepViewPicutre(file,
					filePath, filename);

			// 2.执行deepview，生成结果文件。
			File resultFile = DeepViewService.deepViewPicute(shellPath,
					pictureFile);

			// 3.读取执行结果，输出到response。
			this.ResponseJsonInfo(resultFile);

			// 4.删除临时文件夹。
			FileUtils.deleteDirectory(pictureFile.getParentFile());

		} catch (Exception e) {

			throw e;

		} finally {
			if (out != null) {
				out.close();
			}
		}

		return Action.SUCCESS;
	}

	/**
	 * 读取分析结果文件，输出到Res。
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void ResponseJsonInfo(File resultFile)
			throws FileNotFoundException, IOException {
		PrintWriter out;
		String resultJSON = DeepViewService.readerFromFile(resultFile);

		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType("text/html");
		out = response.getWriter();
		out.println(resultJSON.trim());
		out.flush();
		out.close();
	}

}
