package com.zy.pmk.util;

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

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Set;

public class PdfTest {
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

    @Test
    public void testvalid() {
        TestBean testBean = new TestBean();
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<TestBean>> set = validator.validate(testBean);
        set.forEach(x -> {
            System.out.println(x.getMessage());
        });

    }
}
