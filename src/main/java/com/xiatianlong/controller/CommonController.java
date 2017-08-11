package com.xiatianlong.controller;

import com.xiatianlong.common.Common;
import com.xiatianlong.model.FileUploadModel;
import com.xiatianlong.model.response.FileUploadResult;
import com.xiatianlong.utils.AliyunFileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File Upload Controller
 * Created by xiatianlong on 2017/1/19.
 */

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController{

    /**
     * 限制文件最大size : 2M
     */
    private long fileMaxSize2=2097152;
    /**
     * 限制文件最大size : 10M
     */
    private long fileMaxSize20=20971520;

    /**
     * 单文件上传
     * @param file  文件
     * @return  文件对象
     * @throws IOException  异常
     */
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public FileUploadResult fileUpload(MultipartFile file) throws IOException {

        FileUploadResult result = new FileUploadResult();
        if (file != null){
            // 限制文件上传大小
            if (file.getSize() > fileMaxSize20){
                result.setMessage(getMessage("file.upload.max.size", new Object[]{"20M"}));
                return result;
            }
            FileUploadModel fileUploadModel = AliyunFileUploadUtil.upload(file);
            if (fileUploadModel != null && !StringUtils.isEmpty(fileUploadModel.getUrl())){
                result.setResult(Common.SUCCESS);
                result.setFileModel(fileUploadModel);
                return result;
            }else{
                result.setMessage(getMessage("do.fail"));
            }
        }
        return result;
    }

    /**
     * 多文件上传
     * @param fileArray 文件数组
     * @return  文件对象
     * @throws IOException  异常
     */
    @RequestMapping(value = "/fileUploadArray", method = RequestMethod.POST)
    @ResponseBody
    public FileUploadResult fileUploadArray(MultipartFile[] fileArray) throws IOException {

        FileUploadResult result = new FileUploadResult();
        List<FileUploadModel> fileModelList = new ArrayList<>();
        if (fileArray.length > 0){
            for (MultipartFile file : fileArray) {
                // 限制文件上传大小
                if (file.getSize() > fileMaxSize20){
                    result.setMessage(getMessage("file.upload.max.size", new Object[]{"20M"}));
                    return result;
                }
                FileUploadModel fileUploadModel = AliyunFileUploadUtil.upload(file);
                if (fileUploadModel != null && !StringUtils.isEmpty(fileUploadModel.getUrl())){
                    fileModelList.add(fileUploadModel);
                }else{
                    result.setMessage(getMessage("do.fail"));
                }
            }
        }else{
            result.setMessage(getMessage("file.upload.not.found.file"));
            return result;
        }
        //判断是否有文件上传成功
        if (fileModelList.size() > 0){
            result.setResult(Common.SUCCESS);
            result.setFileModelList(fileModelList);
            return result;
        }else{
            result.setMessage(getMessage("do.fail"));
        }
        return result;
    }


    /**
     * editor富文本图片上传
     * @param editorUploadFileName    图片
     * @return  图片url
     * @throws IOException  异常
     */
    @RequestMapping(value = "/editorFileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String editorFileUpload(MultipartFile editorUploadFileName) throws IOException {

        if (editorUploadFileName != null){
            if (editorUploadFileName.getSize()>fileMaxSize2){
                return "error|"+getMessage("file.upload.max.size", new Object[]{"2M"});
            }
            FileUploadModel fileUploadModel = AliyunFileUploadUtil.upload(editorUploadFileName);
            if (fileUploadModel != null && !StringUtils.isEmpty(fileUploadModel.getUrl())) {
               return fileUploadModel.getUrl();
            }else{
                return "error|"+getMessage("do.fail");
            }
        }
        return "error|"+getMessage("file.upload.not.found.file");
    }

}
