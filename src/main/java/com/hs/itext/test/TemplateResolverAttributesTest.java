package com.hs.itext.test;

import com.hs.itext.util.HTMLTemplateUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by husong on 2018/9/9.
 */
public class TemplateResolverAttributesTest {

    @Test
    public void testTemplateResolutionAttributes01() throws Exception {
        String template = "<p th:text='${title}'></p>";
        Map<String, Object> params = new HashMap<>();
        params.put("title", "Thymeleaf 渲染 HTML ---- Anoy");
        String output = HTMLTemplateUtils.render(template, params);
        System.out.println(output);
    }
}
