package com.haohua.controller;    /*
 * @author  Administrator
 * @date 2018/7/23
 */

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件上传练习
 */
@Controller
@RequestMapping("/file")
public class File {
    @GetMapping("/upload")
    public String upload(){
        return "file/upload";
    }
   @PostMapping("/upload")
    public String ResponseUpload(String remark, MultipartFile file, RedirectAttributes attributes) {
       System.out.println(remark);
        System.out.println(file.getContentType());
       System.out.println(FileUtils.byteCountToDisplaySize(file.getSize()));
       System.out.println(file.getOriginalFilename());
        if (file.getSize()!=0){
           try {
               System.out.println(file.getInputStream());
               java.io.File directory = new java.io.File("D:upload/img");
               if (!directory.exists()){
                    directory.mkdirs();
               }
               InputStream in = file.getInputStream();
               OutputStream out = new FileOutputStream(new java.io.File(directory,file.getOriginalFilename()));
               IOUtils.copy(in,out);
           } catch (IOException e) {
               e.printStackTrace();
           }
        }else{
            attributes.addFlashAttribute("message","请选择文件");
        }
        return "redirect:/file/upload";
    }
}


