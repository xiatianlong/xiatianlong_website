package com.xiatianlong.utils;

import java.security.MessageDigest;

/**
 * md5加密工具类
 * @author xiatianlong
 *
 */
public class MD5Util {
	
	public static String MD5Psw(String psw) {
		String ret = "";
		try {
			// 密码加盐
			psw += "xtl";
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(psw.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuilder buf = new StringBuilder();
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			ret = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return ret;
		}
		return ret;
	}
}
