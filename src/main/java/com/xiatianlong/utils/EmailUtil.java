package com.xiatianlong.utils;

import com.xiatianlong.common.Common;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件工具类
 * @author xiatianlong
 *
 * @date 2016年4月18日 下午5:12:47
 */
public class  EmailUtil {

	private static String id = "xiatianlong_xtl"; //发送账户
	private static String pwd = "xiatianlong163"; //账户密码
	private static String form = "xiatianlong_xtl@163.com"; //账户的地址
	private static String smtp = "smtp.163.com"; //smtp地址

//	private static String id = "xiatl"; //发送账户
//	private static String pwd = "admin@xiatianlong.com"; //账户密码
//	private static String form = "admin@xiatianlong.com"; //账户的地址
//	private static String smtp = "smtpdm.aliyun.com"; //smtp地址

	private static Properties props = null;
	private static Session session = null;
	private static Boolean outDebug = true;//输出调试信息
	static
	{
		props=new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		session=Session.getInstance(props);
		session.setDebug(outDebug);
	}
	public static void main(String[] args) {
		//675500969@qq.com
		boolean isok = EmailUtil.send("testtitle", "testcontent", "test", "675500969@qq.com");
		System.out.println(isok);
	}
	/**
	 * @param title 主题
	 * @param content 内容
	 * @param fromName 发件人姓名
	 * @param toAddress 收件人地址
	 * @return 是否成功
	 */
	public static Boolean send(String title,String content,String fromName,String[]toAddress) {
		if(toAddress==null || toAddress.length==0){return false;}
		Message msg=new MimeMessage(session);
		try {
			//发送的邮箱地址
			msg.setFrom(new InternetAddress(form,fromName));
			msg.setSubject(title);
			msg.setContent(content,"text/html;charset=utf8;");
			Transport transport=session.getTransport();
			//设置服务器以及账号和密码
			transport.connect(smtp,25,id,pwd);
			Address[] add = new Address[toAddress.length];
			//发送到的邮箱地址
			for (int i = 0; i < toAddress.length; i++) {
				add[i] = new InternetAddress(toAddress[i]);
			}
			transport.sendMessage(msg,add);
			transport.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static Boolean send(String title,String content,String fromName,String toAddress){
		String[] add = new String[]{toAddress};
		return send( title,content,fromName,add);
	}



	/**
	 * 异常通知邮件
	 * @param url	请求url
	 * @param ip	请求ip
	 * @param errorMessage	错误信息
     * @return	错误flag
     */
	public static boolean sendErrorEmail(String url, String ip, String errorMessage){
		StringBuilder content = new StringBuilder();
		content.append("网站发生意外错误");
		content.append("<p>请求接口：").append(url).append("</p>");
		content.append("<p>请求IP：").append(ip).append("</p>");
		content.append("<p>请求时间：").append(DateUtil.getFormatString(new Date(), DateUtil.defaultDatePattern)).append("</p>");
		content.append("<p>异常信息：").append(errorMessage).append("</p>");

		content.append("<p>").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("</p>");
		return send("网站发生意外错误",content.toString(),"夏天龙", Common.ADMIN_EMAIL);

	}
}
