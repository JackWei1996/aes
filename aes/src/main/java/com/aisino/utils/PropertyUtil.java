package com.aisino.utils;

import java.util.Properties;

import org.apache.log4j.Logger;
/**
 * class name:PropertyUtil <BR>
 * class description: 读取配置文件工具类 <BR>
 * Remark: <BR>
 * @version 1.00 2018-12-21
 * @author Aisino)WeiHaoHao
 */
public class PropertyUtil {
	
	/** 读取src下configure.properties配置文件内容 */
	private static final  String CONFIGURE_LOCATION = "configure.properties";
	/** 接收Properties内容 */
	private static final  Properties property = new Properties();
	/** Log4J打印日志  */
	private static Logger logger = Logger.getLogger(PropertyUtil.class);
	/** 工具类,不可实现  */
	private PropertyUtil() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Method name: getConfigureProperties <BR>
	 * Description: 读取src下第三方配置文件configure.properties配置文件内容 <BR>
	 * Remark: <BR>
	 * @param name
	 * @return String<BR>
	 */
	public static String getValue(String name) {
		try {
			property.load(PropertyUtil.class.getClassLoader().getResourceAsStream(CONFIGURE_LOCATION));
			String result = property.getProperty(name);
			if (null != result && !"".equalsIgnoreCase(result)) {
				return result.trim();
			} else {				
				return null;
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		} 
	}
}