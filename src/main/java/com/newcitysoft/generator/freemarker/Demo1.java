package com.newcitysoft.generator.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/3 14:12
 */
public class Demo1 {

    public static void main(String[] args) {
        // Create a configuration instance
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        Writer out = new OutputStreamWriter(System.out);
        try {
            cfg.setDirectoryForTemplateLoading(new File("D:\\tianlixin\\idea-workspace\\newcitysoft\\project\\mysql-generator\\src\\main\\resources\\template"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            // Create a data-model
            Map<String, Object> map = new HashMap<>();

            map.put("code", "12312");
            // Get the template
            Template temp = cfg.getTemplate("sms.flt");
            // Merging the template with the data-model
            temp.process(map, out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
