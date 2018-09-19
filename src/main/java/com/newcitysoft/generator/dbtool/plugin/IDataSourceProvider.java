package com.newcitysoft.generator.dbtool.plugin;

import javax.sql.DataSource;

/**
 * 数据源提供者
 *
 * @author lixin.tian@renren-inc.com
 * @date 2018-08-19
 */
public interface IDataSourceProvider {
	/**
	 * 获取数据源
	 *
	 * @return
	 */
	DataSource getDataSource();
}


