package com.newcitysoft.generator.core;

import com.alibaba.fastjson.JSONObject;
import com.newcitysoft.generator.kit.StrKit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model 生成器
 */
public class ModelGenerator {
	private Engine engine = Engine.getInstance();

	protected String modelPackageName;
	protected String modelOutputDir;

	public ModelGenerator(String modelPackageName, String modelOutputDir) {
		if (StrKit.isBlank(modelPackageName)) {
			throw new IllegalArgumentException("modelPackageName can not be blank.");
		}
		if (modelPackageName.contains("/") || modelPackageName.contains("\\")) {
			throw new IllegalArgumentException("modelPackageName error : " + modelPackageName);
		}
		if (StrKit.isBlank(modelOutputDir)) {
			throw new IllegalArgumentException("modelOutputDir can not be blank.");
		}

		this.modelPackageName = modelPackageName;
		this.modelOutputDir = modelOutputDir;
	}

	public void generate(List<TableMeta> tableMetas) {
		System.out.println("Generate model ...");
		System.out.println("Model Output Dir: " + modelOutputDir);

		for (TableMeta tableMeta : tableMetas) {
			genModelContent(tableMeta);
		}
		writeToFile(tableMetas);
	}

	protected void genModelContent(TableMeta tableMeta) {
		Map<String, Object> map = new HashMap<>();

		map.put("modelPackageName", modelPackageName);
		map.put("tableMeta", tableMeta);
		System.out.println(JSONObject.toJSONString(map));

		String ret = engine.renderToString(map);
		tableMeta.modelContent = ret;
	}

	protected void writeToFile(List<TableMeta> tableMetas) {
		try {
			for (TableMeta tableMeta : tableMetas) {
				writeToFile(tableMeta);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 若 model 文件存在，则不生成，以免覆盖用户手写的代码
	 */
	protected void writeToFile(TableMeta tableMeta) throws IOException {
		File dir = new File(modelOutputDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String target = modelOutputDir + File.separator + tableMeta.modelName + ".java";

		File file = new File(target);
		if (file.exists()) {
			return ;	// 若 Model 存在，不覆盖
		}

		FileWriter fw = new FileWriter(file);
		try {
			fw.write(tableMeta.modelContent);
		}
		finally {
			fw.close();
		}
	}
}


