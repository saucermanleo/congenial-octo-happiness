package com.example.PDFutil;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.ResourceLoader;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
@org.springframework.context.annotation.Configuration
public class PDFCreator {
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

    private String fontDir;

    private String templatepath = "/index.html";

    private GroupTemplate groupTemplate;


    public void generatePDFDownload(String fileName, HttpServletResponse response, Map map) throws IOException, DocumentException {
        Template t = groupTemplate.getTemplate(templatepath);
        t.binding(map);

        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("C:/Windows/fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(t.render());
        renderer.layout();

        response.setContentType("application/x-download");
        fileName = URLEncoder.encode(fileName,"utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+fileName);

        renderer.createPDF(response.getOutputStream());
    }


    public void init() throws IOException {
        ResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration config = Configuration.defaultConfiguration();
        groupTemplate = new GroupTemplate(resourceLoader, config);


    }

    public void destry(){
        groupTemplate.close();
    }


}
