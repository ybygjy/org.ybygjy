package org.ybygjy.spring.orderservice.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ybygjy.spring.orderservice.entity.UploadItem;

/**
 * 文件上传
 * @author WangYanCheng
 * @version 2016年10月6日
 */
@Controller
@RequestMapping("/upload")
public class FileUploadController {
    @RequestMapping(method=RequestMethod.GET)
    public String getUploadForm(Model model) {
        model.addAttribute(new UploadItem());
        return "/orderservice/fileupload";
    }
    @RequestMapping(method=RequestMethod.POST)
    public String processFileUpload(UploadItem uploadItem, BindingResult result) {
        int index = 0;
        for (String fileName : uploadItem.getFileName()) {
            try {
                File file = new File("/Users/MLS/Downloads/" + fileName);
System.out.println(file);
                if (file.exists()) {
                    file.delete();
                    System.out.println("删除文件=>" + file.getAbsolutePath());
                }
                file.createNewFile();
                FileCopyUtils.copy(uploadItem.getFileData().get(index++).getBytes(), file);
System.out.println("文件上传成功=>" + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "/orderservice/fileupload";
    }
}
