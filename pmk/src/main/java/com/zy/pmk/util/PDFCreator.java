package com.zy.pmk.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.ResourceLoader;
import org.beetl.core.Template;
import org.beetl.core.exception.BeetlException;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Configurable;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;

import lombok.Data;

/**
 * 
 * @author EX-ZHANGYANG009
 * @createTime 2018年8月14日 下午5:31:31
 * @desc : pdf生成工具
 *
 */
@Data
@org.springframework.context.annotation.Configuration
public class PDFCreator {

	/**
	 * 引入字体uri
	 */
	String fontDirs = "C:/Windows/fonts/simsun.ttc";

	/**
	 * 模板路径
	 */
	String templatePath = "/index.html";

	private GroupTemplate gt;

	/**
	 * 初始化模板
	 * 
	 * @throws IOException
	 */
	@PostConstruct
	public void init() throws IOException {
		ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
		Configuration cfg = Configuration.defaultConfiguration();
		gt = new GroupTemplate(resourceLoader, cfg);
	}

	@PreDestroy
	public void destory() {
		if (gt != null) {
			gt.close();
		}
	}

	/**
	 * 通过模板生成pdf并下载
	 * 
	 * @param FileName
	 *            文件名
	 * @param response
	 * @param map
	 *            要绑定的值,与模板一一对应
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public void createPDF(String FileName, HttpServletResponse response, Map map)
			throws DocumentException, IOException {
		// 生成模板
		Template t = gt.getTemplate(templatePath);
		// 绑定数据
		t.binding(map);

		ITextRenderer renderer = getITextRenderer();
		renderer.setDocumentFromString(t.render());
		renderer.layout();

		this.configRespose(FileName, response);

		renderer.createPDF(response.getOutputStream());

	}

	/**
	 * 生成pdf并使用zip打包
	 * 
	 * @param FileName
	 *            文件名
	 * @param response
	 * @param map
	 *            key为pdf文件名,value为模板对应的map
	 * @throws DocumentException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public void createPDFZIP(String FileName, HttpServletResponse response, Map<String, Map> map) throws IOException {

		this.configRespose(FileName, response);
		ITextRenderer renderer = getITextRenderer();
		BufferedInputStream bis = null;
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(response.getOutputStream());
			byte[] bufs = new byte[1024 * 10];

			Template t = gt.getTemplate(templatePath);

			for (Map.Entry<String, Map> x : map.entrySet()) {
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				String pdfName = x.getKey();
				Map pdfparams = x.getValue();
				ZipEntry zipEntry = new ZipEntry(pdfName);
				zos.putNextEntry(zipEntry);
				try {

					t.binding(pdfparams);

					renderer.setDocumentFromString(t.render());
					renderer.layout();
					renderer.createPDF(byteArrayOutputStream);

					bis = new BufferedInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
					int read = 0;
					while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
						zos.write(bufs, 0, read);
					}
					zos.flush();
					zos.closeEntry();
				} catch (IOException | DocumentException e) {
					e.printStackTrace();
				} finally {
					byteArrayOutputStream.close();
					if (bis != null) {
						bis.close();
					}
				}
			}
			response.flushBuffer();
		} catch (BeetlException | IOException e) {
			e.printStackTrace();
		} finally {
			if (zos != null)
				zos.close();
		}
	}

	/**
	 * 得到pdf生成工具
	 * 
	 * @return
	 */
	public ITextRenderer getITextRenderer() {
		ITextRenderer renderer = new ITextRenderer();
		ITextFontResolver fontResolver = renderer.getFontResolver();
		// 解决中文乱码
		String[] dirs = fontDirs.split(";");
		Arrays.stream(dirs).forEach(x -> {
			try {
				fontResolver.addFont(x, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			} catch (DocumentException | IOException e) {
				e.printStackTrace();
			}
		});
		return renderer;
	}

	/**
	 * 配置接口为下载
	 * 
	 * @param FileName
	 * @param response
	 */
	public void configRespose(String FileName, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-download");
		try {
			response.setHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(FileName, "UTF-8").replaceAll("%28", "\\(").replaceAll("%29", "\\)"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws IOException, DocumentException {
		ResourceLoader resourceLoader = new ClasspathResourceLoader();
		Configuration config = Configuration.defaultConfiguration();
		GroupTemplate groupTemplate = new GroupTemplate(resourceLoader, config);
		Template t = groupTemplate.getTemplate("/index.html");
		t.binding("name", "zy");
		t.binding("time", new Date());
		String render = t.render();

		String outputFile = "D:/htmlToPdf2.pdf";
		OutputStream os = new FileOutputStream(outputFile);
		ITextRenderer renderer = new ITextRenderer();
		ITextFontResolver fontResolver = renderer.getFontResolver();
		fontResolver.addFont("C:/Windows/fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		renderer.setDocumentFromString(render);
		renderer.layout();
		renderer.createPDF(os);
		os.close();
		groupTemplate.close();
	}
}
