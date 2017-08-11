package com.xiatianlong.model.response;

import com.xiatianlong.model.FileUploadModel;

import java.util.List;

/**
 * 文件上传返回结果
 * Created by xiatianlong on 2017/4/20.
 */
public class FileUploadResult extends AsynchronousResult {

    /**
     * 文件上传结果
     */
    private FileUploadModel fileModel;

    /**
     * 多文件上传返回结果
     */
    private List<FileUploadModel> fileModelList;


    /**
     * 获取 文件上传结果
     */
    public FileUploadModel getFileModel() {
        return this.fileModel;
    }

    /**
     * 设置 文件上传结果
     */
    public void setFileModel(FileUploadModel fileModel) {
        this.fileModel = fileModel;
    }

    /**
     * 获取 多文件上传返回结果
     */
    public List<FileUploadModel> getFileModelList() {
        return this.fileModelList;
    }

    /**
     * 设置 多文件上传返回结果
     */
    public void setFileModelList(List<FileUploadModel> fileModelList) {
        this.fileModelList = fileModelList;
    }
}
