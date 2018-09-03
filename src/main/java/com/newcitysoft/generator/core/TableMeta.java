package com.newcitysoft.generator.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TableMeta
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TableMeta implements Serializable {

	private static final long serialVersionUID = -4659736956433755858L;
	/**
	 * 表名
	 */
	public String name;
	/**
	 * 表备注
	 */
	public String remarks;
	/**
	 * 主键，复合主键以逗号分隔
	 */
	public String primaryKey;
	/**
	 * 字段 meta
	 */
	public List<ColumnMeta> columnMetas = new ArrayList<ColumnMeta>();

	/**
	 * 生成的 model 名
	 */
	public String modelName;
	/**
	 * 生成的 model 内容
	 */
	public String modelContent;

	/**
	 * 字段名最大宽度，用于辅助生成字典文件样式
	 */
	public int colNameMaxLen = "Field".length();
	/**
	 * 字段类型最大宽度，用于辅助生成字典文件样式
	 */
	public int colTypeMaxLen = "Type".length();
	/**
	 * 字段默认值最大宽度，用于辅助生成字典文件样式
	 */
	public int colDefaultValueMaxLen = "Default".length();
}




