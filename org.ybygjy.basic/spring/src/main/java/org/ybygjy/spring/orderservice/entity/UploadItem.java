package org.ybygjy.spring.orderservice.entity;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 文件上传DTO
 * @author WangYanCheng
 * @version 2016年10月6日
 */
public class UploadItem {
    private String[] fileName;
    private List<CommonsMultipartFile> fileData;
    public String[] getFileName() {
        return fileName;
    }
    public void setFileName(String[] fileName) {
        this.fileName = fileName;
    }
    public List<CommonsMultipartFile> getFileData() {
        return fileData;
    }
    public void setFileData(List<CommonsMultipartFile> fileData) {
        this.fileData = fileData;
    }
}
