package com.hs.itext.test;

import com.hs.itext.util.HTMLTemplateUtils;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import org.junit.Test;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by husong on 2018/9/9.
 */
public class MyTest {

    private final static String TEMP_PAHT = "./src/main/resources/templates/test.html";
    public static final String YAHEIFONT = "./src/main/resources/font/msyh.ttc";
    public static final String FONT = "./src/main/resources/font/NotoSansCJKsc-Regular.otf";
    public static final String SIMFONT = "./src/main/resources/font/simsun.ttc";
    private final static String DEST = "./target/desc/备案申请书.pdf";
    private final static String IMG = "./src/main/resources/img/dog.jpg";

    @Test
    public void test() {
        try {
            File file = new File(TEMP_PAHT);
            file.getParentFile().mkdirs();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line = reader.readLine())!=null){
                sb.append(line);
            }
            System.out.println(sb.toString());

            File target = new File(DEST);
            target.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(target);
            Map<String, Object> params = new HashMap<>();
            params.put("code", "Thymeleaf 渲染 HTML ---- Anoy");
            String output = HTMLTemplateUtils.render(sb.toString(), params);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(output);
            // 解决中文支持问题
            ITextFontResolver fontResolver = renderer.getFontResolver();
            //fontResolver.addFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            fontResolver.addFont(SIMFONT, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            renderer.layout();
            renderer.createPDF(out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test2(){
        try {
            File file = new File(TEMP_PAHT);
            file.getParentFile().mkdirs();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while((line = reader.readLine())!=null){
                sb.append(line);
            }
            System.out.println(sb.toString());

            File target = new File(DEST);
            target.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(target);
            Map<String, Object> params = new HashMap<>();
            params.put("code", "Thymeleaf 渲染 HTML ---- Anoy");
            params.put("imgs",IMG);
            String output = HTMLTemplateUtils.render(sb.toString(), params);

            ConverterProperties props = new ConverterProperties();
            DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, false, false);
            defaultFontProvider.addFont(FONT);
            props.setFontProvider(defaultFontProvider);
            PdfWriter writer = new PdfWriter(DEST);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(new PageSize(595, 14400));
            Document document = HtmlConverter.convertToDocument(new ByteArrayInputStream(output.getBytes()), pdf, props);
            document.close();
            pdf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}