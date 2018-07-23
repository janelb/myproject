package com.libang.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

/**
 * @author libang
 * @date 2018/7/23 9:36
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    //文件的上传
    @GetMapping("/upload")
    public String fileUplod(){

        return "file/upload";
    }

    @PostMapping("/upload")
    public String fileUpload(MultipartFile fileName, RedirectAttributes redirectAttributes) throws IOException {

        if(!fileName.isEmpty()){

            //上传控件的属性值
            System.out.println(fileName.getName());
            //判断上传文件是否为空
            System.out.println(fileName.isEmpty());
            //获取文件的原始名称
            System.out.println(fileName.getOriginalFilename());

            //获取文件的大小，将文件转换为人们可阅读大小
            System.out.println(FileUtils.byteCountToDisplaySize(fileName.getSize()));

            File file = new File("E:/upload2");
            //判断文件夹是否存在
            if(!file.exists()){
                //如果不存在创建文件夹
                file.mkdirs();
            }

            //获取文件的输入流
            InputStream inputStream = fileName.getInputStream();
            //获取文件的输出流
            FileOutputStream outputStream = new FileOutputStream(new File(file,fileName.getOriginalFilename()));
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }else{
            //RedirectAttributes的addFlashAttribute方法项前端进行传值
            redirectAttributes.addFlashAttribute("message","文件不能为空" );
        }
        //重定向
        return "redirect:/file/upload";
    }





}
