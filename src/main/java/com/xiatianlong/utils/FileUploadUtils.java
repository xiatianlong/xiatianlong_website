package com.xiatianlong.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传工具类
 * 
 * @author xiatianlong
 *
 * @date 2016年4月18日 下午5:13:03
 */
public class FileUploadUtils {

	/**
	 * 图片上传
	 * @param request
	 * 			请求
	 * @return
	 * 			图片上传的路径
	 * @throws IllegalStateException
	 * @throws IOException
	 */
    public static List<String> upload(HttpServletRequest request)
            throws IllegalStateException, IOException {

    	List<String> imagePathList = new ArrayList<String>();
    	
        String path2 = request.getSession().getServletContext()
                .getRealPath("/");
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.getFileUpload();
        String path = path2 + "/upload/image/";
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();

                    String suffixName = myFileName
                            .substring(myFileName.lastIndexOf(".") + 1);

                    String newName = UUID.randomUUID().toString();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        System.out.println(myFileName);
                        // 重命名上传后的文件名
                        File targetFile = new File(path,
                                newName + "." + suffixName);
                        if (!targetFile.exists()) {
                            targetFile.mkdirs();
                        }
                        // 文件上传3.
                        file.transferTo(targetFile);
                        // 保存图片路径
                        imagePathList.add("upload/image/" + newName + "." + suffixName);
                    }
                }
            }

        }
		return imagePathList;
    }
}
