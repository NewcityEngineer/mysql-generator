package com.newcitysoft.generator.dbtool.core.manager;

import com.newcitysoft.generator.dbtool.model.DbInfo;

import javax.sql.DataSource;

/**
 * @author lixin.tian@renren-inc.com
 * @date 2018/9/19 14:51
 */
public interface DsManager {
    DataSource getDataSource(DbInfo dbInfo);
    DataSource getDataSource(String jdbcUrl, String user, String password);
}
