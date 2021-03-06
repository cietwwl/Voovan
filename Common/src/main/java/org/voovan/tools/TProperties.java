package org.voovan.tools;

import org.voovan.tools.log.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

/**
 * properties文件操作类
 *
 * @author helyho
 *
 * Voovan Framework.
 * WebSite: https://github.com/helyho/Voovan
 * Licence: Apache v2 License
 */
public class TProperties {

	private static HashMap<File, Properties> propertiesCache = new HashMap<File, Properties>();

	/**
	 * 解析 Properties 文件
	 *
	 * @param file 文件对象
	 * @return Properties 对象
	 */
	public static Properties getProperties(File file) {
		try {
			if (!propertiesCache.containsKey(file)) {
				Properties properites = new Properties();
				String content = null;
				if(!file.getPath().contains("!"+File.separator)) {
					content = new String(TFile.loadFile(file));
				}else{
					String filePath = file.getPath();
					String resourcePath = filePath.substring(filePath.indexOf("!"+File.separator)+2, filePath.length());
					content = new String(TFile.loadResource(resourcePath));
				}
				properites.load(new StringReader(content));
				propertiesCache.put(file, properites);
			}

			return propertiesCache.get(file);

		} catch (IOException e) {
			Logger.error("Get properites file failed. File:" + file.getAbsolutePath(),e);
			return null;
		}
	}

	/**
	 * 解析 Properties 文件
	 *
	 * @param fileName 文件对象
	 * @return Properties 对象
	 */
	public static Properties getProperties(String fileName) {
		if(!fileName.contains(".properties")){
			fileName = fileName + ".properties";
		}
		String filePath = TString.assembly("./classes/", fileName);

		try {
			File file = new File(filePath);
			if(file==null || !file.exists()){
				Logger.error("properites file not exists. File: " + filePath);
				return null;
			}
			if (!propertiesCache.containsKey(file)) {
				Properties properites = new Properties();
				String content = null;
				if(!file.getPath().contains("!"+File.separator)) {
					content = new String(TFile.loadFile(file));
				}else{
					filePath = file.getPath();
					String resourcePath = filePath.substring(filePath.indexOf("!"+File.separator)+2, filePath.length());
					content = new String(TFile.loadResource(resourcePath));
				}
				properites.load(new StringReader(content));
				propertiesCache.put(file, properites);
			}

			return propertiesCache.get(file);

		} catch (IOException e) {
			Logger.error("Get properites file fialed. File:" + filePath,e);
			return null;
		}
	}

	/**
	 * 从Properties文件读取字符串
	 *
	 * @param file 文件对象
	 * @param name 属性名
	 * @return 属性值
	 */
	public static String getString(File file, String name) {
		Properties properites = getProperties(file);
		return properites.getProperty(name);
	}

	/**
	 * 从Properties文件读取整形
	 *
	 * @param file 文件对象
	 * @param name 属性名
	 * @return 属性值
	 */
	public static int getInt(File file, String name) {
		String value = getString(file, name);
		return Integer.valueOf(TString.isNullOrEmpty(value)?"0":value.trim());
	}

	/**
	 * 从Properties文件读取浮点数
	 *
	 * @param file 文件对象
	 * @param name 属性名
	 * @return 属性值
	 */
	public static float getFloat(File file, String name) {
		String value = getString(file, name);
		return Float.valueOf(TString.isNullOrEmpty(value)?"0":value.trim());
	}

	/**
	 * 从Properties读取双精度浮点数
	 *
	 * @param file 文件对象
	 * @param name 属性名
	 * @return 属性值
	 */
	public static double getDouble(File file, String name) {
		String value = getString(file, name);
		return Double.valueOf(TString.isNullOrEmpty(value)?"0":value.trim());
	}

	/**
	 * 从Properties文件读取 Boolean
	 *
	 * @param file 文件对象
	 * @param name 属性名
	 * @return 属性值
	 */
	public static boolean getBoolean(File file, String name) {
		Properties properites = getProperties(file);
		return Boolean.valueOf(properites.getProperty(name));
	}

	/**
	 * 保存信息到 Properties文件
	 *
	 * @param file 文件对象
	 * @param name 属性名
	 * @param value 属性值
	 * @throws IOException IO异常
	 */
	public static void setString(File file, String name, String value) throws IOException {
		Properties properites = getProperties(file);
		properites.setProperty(name, value);
		properites.store(new FileOutputStream(file), null);
	}

	//-----------------------------------------------------------------------------


	/**
	 * 从Properties文件读取字符串
	 *
	 * @param fileName 文件对象
	 * @param name 属性名
	 * @return 属性值
	 */
	public static String getString(String fileName, String name) {
		Properties properites = getProperties(fileName);
		return properites.getProperty(name);
	}

	/**
	 * 从Properties文件读取整形
	 *
	 * @param fileName 文件对象
	 * @param name 属性名
	 * @return 属性值
	 */
	public static int getInt(String fileName, String name) {
		String value = getString(fileName, name);
		return Integer.valueOf(TString.isNullOrEmpty(value)?"0":value.trim());
	}

	/**
	 * 从Properties文件读取浮点数
	 *
	 * @param fileName 文件对象
	 * @param name 属性名
	 * @return 属性值
	 */
	public static float getFloat(String fileName, String name) {
		String value = getString(fileName, name);
		return Float.valueOf(TString.isNullOrEmpty(value)?"0":value.trim());
	}

	/**
	 * 从Properties读取双精度浮点数
	 *
	 * @param fileName 文件对象
	 * @param name 属性名
	 * @return 属性值
	 */
	public static double getDouble(String fileName, String name) {
		String value = getString(fileName, name);
		return Double.valueOf(TString.isNullOrEmpty(value)?"0":value.trim());
	}

	/**
	 * 从Properties文件读取 Boolean
	 *
	 * @param fileName 文件对象
	 * @param name 属性名
	 * @return 属性值
	 */
	public static boolean getBoolean(String fileName, String name) {
		Properties properites = getProperties(fileName);
		return Boolean.valueOf(properites.getProperty(name));
	}

	/**
	 * 保存信息到 Properties文件
	 *
	 * @param fileName 文件对象
	 * @param name 属性名
	 * @param value 属性值
	 * @throws IOException IO异常
	 */
	public static void setString(String fileName, String name, String value) throws IOException {
		Properties properites = getProperties(fileName);
		properites.setProperty(name, value);
		properites.store(new FileOutputStream(TString.assembly("./classes/", fileName, ".properties")), null);
	}

	/**
	 * 清空 指定文件的 Properites 缓存
	 * @param fileName 文件名, 可以是完整文件名,也可以是不带扩展名的文件名
	 */
	public static void clear(String fileName){
		Iterator<File> iterator = propertiesCache.keySet().iterator();
		while(iterator.hasNext()){
			File file = iterator.next();
			if (file.getName().startsWith(fileName)){
				iterator.remove();
			}
		}
	}

	/**
	 * 清空 Properites 缓存
	 */
	public void clear(){
		propertiesCache.clear();
	}
}
