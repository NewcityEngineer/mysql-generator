package com.newcitysoft.generator.dialect;

/**
 * MysqlDialect.
 */
public class MysqlDialect extends Dialect {
	
	@Override
	public String forTableBuilderDoBuild(String tableName) {
		return "select * from `" + tableName + "` where 1 = 2";
	}
}
