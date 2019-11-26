package com.zy.pmk.util;

import com.google.common.collect.Maps;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.exception.BeetlException;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author EX-ZHANGYANG009
 * @createTime 2018年8月14日 下午5:31:31
 * @desc : pdf生成工具
 */
@Slf4j
@Data
@org.springframework.context.annotation.Configuration
public class PDFCreator {

    ITextRenderer renderer = new ITextRenderer();

    /**
     * 模板路径
     */
    private String templatePath = "index.html";

    private GroupTemplate gt;

    /**
     * 下载图片并转化为base64码
     *
     * @param url
     * @return
     */
    public static String getImgBase64(@NotNull String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        byte[] data = null;
        InputStream in = null;
        CloseableHttpResponse httpresponse = null;
        // 读取图片字节数组
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet get = new HttpGet(uriBuilder.build());
            httpresponse = httpclient.execute(get);
            in = httpresponse.getEntity().getContent();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (Exception e) {
            return "";
        } finally {
            httpclient.close();
            httpresponse.close();
        }
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * 特殊字符处理
     *
     * @param model
     */
    public static void specialFontSolution(Object model) {
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.getType().toString().equalsIgnoreCase("class java.lang.String")) {
                String firstLetter = f.getName().substring(0, 1).toUpperCase();
                String getter = "get" + firstLetter + f.getName().substring(1);
                Method method;
                try {
                    method = model.getClass().getMethod(getter, new Class[]{});
                    Object value = method.invoke(model, new Object[]{});
                    if (value != null) {
                        String values = StringEscapeUtils.escapeHtml4(value.toString()).replaceAll("\n", "<br />");
                        model.getClass().getDeclaredField(f.getName());
                        f.setAccessible(true);
                        f.set(model, values);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 初始化
     *
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        // 模板初始化
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        gt = new GroupTemplate(resourceLoader, cfg);
        // pdf工具初始化
        ITextFontResolver fontResolver = renderer.getFontResolver();
        // 解决中文乱码
        //URL url = this.getClass().getClassLoader().getResource("C:/Windows/fonts/simsun.ttc");
        try {
            fontResolver.addFont("C:/Windows/fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destory() {
        renderer = null;
        if (gt != null) {
            gt.close();
        }
    }

    /**
     * 生成pdf到文件中 模板字体必须为font-family:SimSun;
     * <html><head><style type="text/css">body{font-family:SimSun;}</style></head><body></body></html>
     *
     * @param path    文件路径
     * @param content 内容
     * @throws DocumentException
     * @throws IOException
     */
    public void createPDF(String path, String content) throws DocumentException, IOException {
        FileOutputStream out = new FileOutputStream(createFile(path));
        this.preCreatePDF(content, out);
        out.flush();
        out.close();
        renderer.finishPDF();
    }

    /**
     * @param path
     * @return
     */
    private File createFile(String path) {
        File file = new File(path);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        return file;
    }

    /**
     * 通过模板 生成pdf到文件中
     *
     * @param path
     * @param map
     * @throws DocumentException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public void createPDF(String path, Map map) throws DocumentException, IOException {
        this.createPDF(path, map, this.getTemplatePath());
    }

    /**
     * 通过模板 生成pdf到文件中
     *
     * @param path
     * @param map
     * @param templatePath
     * @throws BeetlException
     * @throws DocumentException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public void createPDF(String path, Map map, String templatePath) throws BeetlException, DocumentException, IOException {
        createPDF(path, createTemplate(templatePath, map));
    }

    /**
     * @param templatePath
     * @param map
     * @return
     */
    public String createTemplate(String templatePath, Map map) {
        Template t = gt.getTemplate(templatePath);
        // 绑定数据
        t.binding(map);
        return t.render();
    }

    /**
     * 通过content生成pdf并配置为可下载
     *
     * @param FileName
     * @param response
     * @param content
     * @throws DocumentException
     * @throws IOException
     */
    public void createPDF(String FileName, HttpServletResponse response, String content) throws DocumentException, IOException {
        this.configRespose(FileName, response);
        this.preCreatePDF(content, response.getOutputStream());
    }

    /**
     * 通过模板生成pdf并配置为可下载
     *
     * @param fileName 文件名
     * @param response
     * @param map      要绑定的值,与模板一一对应
     * @throws DocumentException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public void createPDF(String fileName, HttpServletResponse response, Map map) throws DocumentException, IOException {
        // 生成模板
        this.createPDF(fileName, response, map, this.getTemplatePath());
    }

    /**
     * 通过模板生成pdf并配置为可下载
     * * @param fileName
     *
     * @param response
     * @param map
     * @param templatePath
     * @throws BeetlException
     * @throws DocumentException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public void createPDF(String fileName, HttpServletResponse response, Map map, String templatePath) throws BeetlException, DocumentException, IOException {
        createPDF(fileName, response, createTemplate(templatePath, map));
    }

    /**
     * 通过模板生成pdf并使用zip打包,并配置为可下载
     *
     * @param FileName 文件名
     * @param response
     * @param map      key为pdf文件名,value为模板对应的map
     * @throws DocumentException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public void createPDFZIP(String FileName, HttpServletResponse response, Map<String, Map> map) throws IOException {
        Template t = gt.getTemplate(templatePath);
        Map<String, InputStream> paramMap = Maps.newHashMap();
        for (Map.Entry<String, Map>
                x :
                map.entrySet()) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String pdfName = x.getKey();
            Map pdfparams = x.getValue();
            try {
                t.binding(pdfparams);
                preCreatePDF(t.render(), byteArrayOutputStream);
                BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                paramMap.put(pdfName, bis);
            } catch (DocumentException e) {
                log.error("create pdf  error :" + e);
            } finally {
                byteArrayOutputStream.close();
            }
        }
        this.zipCompress(FileName, response, paramMap);
        response.flushBuffer();
    }

    /**
     * zip压缩文件并配置接口为下载
     *
     * @param FileName
     * @param response
     * @param map
     */
    public void zipCompress(String FileName, HttpServletResponse response, Map<String, InputStream> map) {
        this.configRespose(FileName, response);
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(response.getOutputStream());
            zipCompress(zos, map);
        } catch (IOException e) {
            log.error("zip  error :" + e);
        }
    }

    /**
     * zip压缩
     *
     * @param zos
     * @param map<String,InputStream> string为文件名
     * @throws IOException
     */
    public void zipCompress(ZipOutputStream zos, Map<String, InputStream> map) throws IOException {
        try {
            for (Map.Entry<String, InputStream>
                    x :
                    map.entrySet()) {
                String filename = x.getKey();
                InputStream is = x.getValue();
                this.zipCompress(zos, is, filename);
            }
        } catch (IOException e) {
            log.error("zip  error :" + e);
        } finally {
            if (zos != null) zos.close();
        }
    }

    /**
     * zip压缩
     *
     * @param zos
     * @param is
     * @param fileName
     * @throws IOException
     */
    public void zipCompress(ZipOutputStream zos, InputStream is, String fileName) throws IOException {
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);
        byte[] bufs = new byte[1024 * 10];
        int read = 0;
        while ((read = is.read(bufs, 0, 1024 * 10)) != -1) {
            zos.write(bufs, 0, read);
        }
        zos.flush();
        zos.closeEntry();
        if (is != null) is.close();
    }

    /**
     * @param content
     * @param out
     * @throws DocumentException
     */
    private void preCreatePDF(String content, OutputStream out) throws DocumentException {
        renderer.setDocumentFromString(content);
        renderer.layout();
        renderer.createPDF(out);
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
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(FileName, "UTF-8").replaceAll("%28", "\\(").replaceAll("%29", "\\)"));
        } catch (UnsupportedEncodingException e) {
            log.error("encode FileName error :" + e);
        }
    }
}