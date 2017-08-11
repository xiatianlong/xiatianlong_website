package com.xiatianlong.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 生成验证码
 * @author xiatianlong .
 *
 * @date 2016年2月4日 下午4:33:06
 */
@SuppressWarnings("serial")
public class ImgServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 生成一张图片
		BufferedImage img = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
		
		Graphics gs = img.getGraphics();
		Color color = new Color(255, 255, 255);// 图片背景颜色
		gs.setColor(color);
		gs.fillRect(0, 0, 100, 40);// 生成图片的大小
		// 随机数组
		char[] ch = "ABCDEFGHJKLMNOPQRSTUVWXYZ123456789abcdefghjklmnopqrstuvwxyz".toCharArray();
		Random random = new Random();// 随机函数
		int len = ch.length, index;
		// 画干扰线
		drawRandomLine(gs, random);
		StringBuilder str = new StringBuilder(); // 保存生成的字符
		for (int i = 0; i < 4; i++) {
			// 生成四个随机字母或数字
			index = random.nextInt(len);
			// 给每个字符生成随机颜色
			gs.setColor(new Color(random.nextInt(88), random.nextInt(188), random.nextInt(255)));
			// 设置字体
			gs.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			// 画完字符，定义字符生成的位置
			gs.drawString(ch[index] + "", (i * 15) + 8, 18);
			str.append(ch[index]); // 将生成的四个字符放在StringBuffer中
		}
		// 将生成的四个字符保存到session里面，方便后面的验证
		req.getSession().setAttribute("sessionImgCode", str.toString().toUpperCase());
		// 输出生成的包含验证码的图片
		ImageIO.write(img, "JPG", resp.getOutputStream());

	}

	/**
	 * 画随机线条
	 *
	 * @param g
	 */
	private void drawRandomLine(Graphics g, Random random) {
		// 设置颜色
		g.setColor(new Color(random.nextInt(188), random.nextInt(255), random.nextInt(88)));
		// 设置线条个数并画线
		for (int i = 0; i < 3; i++) {
			int x1 = random.nextInt(100);
			int y1 = random.nextInt(40);
			int x2 = random.nextInt(100);
			int y2 = random.nextInt(40);
			g.drawLine(x1, y1, x2, y2);
		}
	}
}
