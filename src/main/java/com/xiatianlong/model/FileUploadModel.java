package com.xiatianlong.model;

/**
 * 文件上传模型
 * Created by xiatianlong on 2017/4/20.
 */
public class FileUploadModel {

    /**
     * 文件物理路径
     */
    private String url;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件在Bucket中的名称
     */
    private String fileBucketName;

    /**
     * 文件大小
     */
    private long fileSize;


    /**
     * 获取 文件物理路径
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置 文件物理路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取 文件名称
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * 设置 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取 文件在Bucket中的名称
     */
    public String getFileBucketName() {
        return this.fileBucketName;
    }

    /**
     * 设置 文件在Bucket中的名称
     */
    public void setFileBucketName(String fileBucketName) {
        this.fileBucketName = fileBucketName;
    }

    /**
     * 获取 文件大小
     */
    public long getFileSize() {
        return this.fileSize;
    }

    /**
     * 设置 文件大小
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
