package com.xiatianlong.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Propertites文件读取工具类
 * @author xtl
 * @date 2016年9月22日上午11:46:09
 */
public class ReadPropertiesUtil {

	public static String read(String str){
		InputStream in = ReadPropertiesUtil.class.getClassLoader().getResourceAsStream("xiatianlong.properties");
		Properties p = new Properties();   
		try {
			p.load(in);
			return p.getProperty(str);
		} catch (IOException e) {
			e.printStackTrace();
			return "read properties fail";
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				//  do nothing
				e.printStackTrace();
			}
		}
	}
}
