package com.xiatianlong.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.xiatianlong.common.Common;
import com.xiatianlong.model.FileUploadModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 阿里云文件上传
 * Created by xiatianlong on 2017/4/20.
 */
public class AliyunFileUploadUtil {

    /**
     * 文件上传
     * @param file  file
     * @return  Object(文件原名,文件新名,文件路径,文件大小)
     */
    public static FileUploadModel upload(MultipartFile file){

        // 创建ossClient实例
        OSSClient ossClient = new OSSClient(Common.ALIYUN_UPLOAD_URL, Common.ACCESS_KEY_ID, Common.ACCESS_KEY_SECRET);
        try{
            FileUploadModel fileUploadModel = new FileUploadModel();
            // 创建Bucket
            if (!ossClient.doesBucketExist(Common.BACKET)){
                CreateBucketRequest createBucketRequest= new CreateBucketRequest(Common.BACKET);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            String uuid = UUID.randomUUID().toString();
            fileUploadModel.setFileName(file.getOriginalFilename());
            fileUploadModel.setFileSize(file.getSize());
            fileUploadModel.setFileBucketName(uuid+"-"+file.getOriginalFilename());
            // 文件上传
            ossClient.putObject(Common.BACKET, uuid+"-"+file.getOriginalFilename(), file.getInputStream());
            // 拼装文件物理url
            StringBuilder fileUrl = new StringBuilder();
            fileUrl.append("https://").append(Common.ALIYUN_UPLOAD_URL)
                    .append("/").append(uuid).append("-").append(file.getOriginalFilename());
            System.out.println("upload file success! url : " + fileUrl.toString());
            fileUploadModel.setUrl(fileUrl.toString());
            return fileUploadModel;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            ossClient.shutdown();
        }

        return null;
    }


}
