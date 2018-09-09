package com.hs.itext.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

/**
 * Created by husong on 2018/9/9.
 */
public class HTMLTemplateUtils {

    private final static TemplateEngine templateEngine = new TemplateEngine();

    /**
     * 使用 Thymeleaf 渲染 HTML
     * @param template  HTML模板
     * @param params 参数
     * @return  渲染后的HTML
     */
    public static String render(String template, Map<String, Object> params){
        Context context = new Context();
        context.setVariables(params);
        return templateEngine.process(template, context);
    }
}
