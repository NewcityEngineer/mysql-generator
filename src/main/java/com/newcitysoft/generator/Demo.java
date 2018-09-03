package com.newcitysoft.generator;

import com.newcitysoft.generator.dialect.MysqlDialect;
import com.newcitysoft.generator.core.Generator;
import com.newcitysoft.generator.kit.Prop;
import com.newcitysoft.generator.kit.PropKit;
import com.newcitysoft.generator.plugin.DruidPlugin;

import javax.sql.DataSource;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/3 15:29
 */
public class Demo {

    public static DataSource getDataSource() {
        Prop p = PropKit.use("config.txt");
        DruidPlugin druidPlugin = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));

        druidPlugin.start();
        return druidPlugin.getDataSource();
    }

    public static void main(String[] args) {
        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "com.newcitysoft.generator.model";
        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = "D:\\data\\model";

        // 创建生成器
        Generator generator = new Generator(getDataSource(), modelPackageName, modelOutputDir);
        // 设置数据库方言
        generator.setDialect(new MysqlDialect());
        // 添加不需要生成的表名
        generator.addExcludedTable("adv");
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
//        generator.setRemovedTableNamePrefixes("ent_");
        // 生成
        generator.generate();
    }
}
