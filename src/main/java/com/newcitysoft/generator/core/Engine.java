package com.newcitysoft.generator.core;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.kit.PathKit;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/3 14:46
 */
public final class Engine {

    private Engine(){}
    private static final Engine instance = new Engine();
    public static Engine getInstance() {return instance;}

    private static final String template = PathKit.getRootClassPath() + "/template";
    private static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);

    static {
        try {
            System.out.println(template);
            cfg.setDirectoryForTemplateLoading(new File(template));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String renderToString(Map<String, Object> map) {
        StringWriter out = new StringWriter();

        try {
            System.out.println(JSONObject.toJSONString(map));
            // Get the template
            Template temp = cfg.getTemplate("model.flt");
            // Merging the template with the data-model
            temp.process(map, out);
            out.flush();

            return out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
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

        return null;
    }
}
