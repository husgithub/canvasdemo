package com.hs.itext.util;


import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by husong on 2018/9/9.
 */
public class PDFUtil {
    /**
     * 生成 PDF 文件
     * @param out 输出流
     * @param html HTML字符串
     * @throws IOException IO异常
     * @throws DocumentException Document异常
     */
    public static void createPDF(OutputStream out, String html) throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("pdf/font/fangsong.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("pdf/font/PingFangSC.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(out);
    }

}
