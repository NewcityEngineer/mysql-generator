package com.newcitysoft.generator;

import com.newcitysoft.generator.kit.Prop;
import com.newcitysoft.generator.kit.PropKit;
import com.newcitysoft.generator.plugin.DruidPlugin;

import javax.sql.DataSource;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/3 16:32
 */
public final class DataSourceUtil {

    public static DataSource getDataSource() {
        Prop p = PropKit.use("config.txt");
        DruidPlugin druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));

        druidPlugin.start();
        return druidPlugin.getDataSource();
    }
}
