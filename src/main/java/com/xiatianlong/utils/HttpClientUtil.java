package com.xiatianlong.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * HTTP Client Util
 * @author xtl
 * @date 2016年11月24日下午12:02:42
 * # 返回json格式
 */
public class HttpClientUtil {
	
	
	
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return JSONObject 所代表远程资源的响应结果，如果异常，返回为null
	 */
	public static JSONObject get(String url, String param) {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				stringBuilder.append(line);
			}
			return JSONObject.parseObject(stringBuilder.toString());
		} catch (Exception e) {
			System.out.println("send get client error" + e);
			e.printStackTrace();
			return null;
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return JSONObject 所代表远程资源的响应结果，如果异常，返回为null
	 */
	public static JSONObject post(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuilder stringBuilder = new StringBuilder();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				stringBuilder.append(line);
			}
			return JSONObject.parseObject(stringBuilder.toString());
		} catch (Exception e) {
			System.out.println("send post client error" + e);
			e.printStackTrace();
			return null;
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * main方法测试类
	 * @param args	参数
	 */
	public static void main(String[] args) {

		// 发送 GET 请求
//		String s = HttpClientUtil.get("http://127.0.0.1:8080/order/operate/10636/1.html", "key=123&v=456");
//		System.out.println(s);

//		// 发送 POST 请求
		JSONObject sr = HttpClientUtil.post("http://localhost:8080/xiatianlong/index/query", "lastBlogId=30&showCount=10");
		System.out.println(sr);

	}
	
}