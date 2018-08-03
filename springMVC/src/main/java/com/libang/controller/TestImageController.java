package com.libang.controller;


import com.libang.entity.ImageTestVo;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.ModelAndView;

import java.io.File;

import java.util.UUID;

/**
 * @author libang
 * @date 2018/8/2 22:20
 */
@Controller
public class TestImageController {

        @GetMapping("/upload")
        public ModelAndView toImgUpload(ImageTestVo imageTestVo){

            ModelAndView model = new ModelAndView();

            // 将传入绑定成功的参数在页面显示。

            model.setViewName("file/upload");

            return model;

        }

        @PostMapping("/upload")

        public ModelAndView testImgUpload(ImageTestVo imageTestVo, MultipartFile pic_src){

            String originalFilename = pic_src.getOriginalFilename();

            if(pic_src != null && originalFilename !=null && originalFilename.length()>0){

                String filePath = "E:\\upload5";

                String newFileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));

                File newFile = new File(filePath+newFileName);

                try{

                    pic_src.transferTo(newFile);

                }catch (Exception e){

                }

                imageTestVo.setImg_src(newFileName);

            }

            ModelAndView model = new ModelAndView();

            model.addObject("imageTestVo",imageTestVo);

            // 将传入绑定成功的参数在页面显示。

            model.setViewName("file/upload");

            return model;

        }



}
