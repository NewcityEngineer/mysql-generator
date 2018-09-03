package com.newcitysoft.generator.core;

import com.newcitysoft.generator.dialect.Dialect;

import javax.sql.DataSource;
import java.util.List;

public class Generator {
	
	protected Dialect dialect = null;
	protected MetaBuilder metaBuilder;
	protected ModelGenerator modelGenerator;

	/**
	 * 构造 Generator，生成 BaseModel、Model、MappingKit 三类文件，其中 MappingKit 输出目录与包名与 Model相同
	 * @param dataSource 数据源
	 * @param modelPackageName model 包名
	 * @param modelOutputDir model 输出目录
	 */
	public Generator(DataSource dataSource, String modelPackageName, String modelOutputDir) {
		this(dataSource, new ModelGenerator(modelPackageName, modelOutputDir));
	}

	/**
	 * 使用指定 BaseModelGenerator、ModelGenerator 构造 Generator
	 * 生成 BaseModel、Model、MappingKit 三类文件，其中 MappingKit 输出目录与包名与 Model相同
	 */
	public Generator(DataSource dataSource, ModelGenerator modelGenerator) {
		if (dataSource == null) {
			throw new IllegalArgumentException("dataSource can not be null.");
		}
		if (modelGenerator == null) {
			throw new IllegalArgumentException("modelGenerator can not be null.");
		}

		this.metaBuilder = new MetaBuilder(dataSource);
		this.modelGenerator = modelGenerator;
	}
	
	/**
	 * 设置 MetaBuilder，便于扩展自定义 MetaBuilder
	 */
	public void setMetaBuilder(MetaBuilder metaBuilder) {
		if (metaBuilder != null) {
			this.metaBuilder = metaBuilder;
		}
	}
	
	public void setTypeMapping(TypeMapping typeMapping) {
		this.metaBuilder.setTypeMapping(typeMapping);
	}
	

	/**
	 * 设置数据库方言，默认为 MysqlDialect
	 */
	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	/**
	 * 设置需要被移除的表名前缀，仅用于生成 modelName 与  baseModelName
	 * 例如表名  "osc_account"，移除前缀 "osc_" 后变为 "account"
	 */
	public void setRemovedTableNamePrefixes(String... removedTableNamePrefixes) {
		metaBuilder.setRemovedTableNamePrefixes(removedTableNamePrefixes);
	}
	
	/**
	 * 添加不需要处理的数据表
	 */
	public void addExcludedTable(String... excludedTables) {
		metaBuilder.addExcludedTable(excludedTables);
	}
	

	public void generate() {
		if (dialect != null) {
			metaBuilder.setDialect(dialect);
		}
		
		long start = System.currentTimeMillis();
		List<TableMeta> tableMetas = metaBuilder.build();
		if (tableMetas.size() == 0) {
			System.out.println("TableMeta 数量为 0，不生成任何文件");
			return ;
		}

		if (modelGenerator != null) {
			modelGenerator.generate(tableMetas);
		}
		
		long usedTime = (System.currentTimeMillis() - start) / 1000;
		System.out.println("Generate complete in " + usedTime + " seconds.");
	}
}



