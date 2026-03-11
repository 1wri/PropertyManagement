package com.jzxy.controller;

import com.jzxy.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author 龙殇
 * @version 1.0
 * @description 文件的上传与下载
 * @date 2023/3/11 9:23
 */

@RestController
@RequestMapping("/file")
public class FillController {

    private static final String pathNamePre = "D:\\GraduationThesis\\img\\";

    /**
    * @description 文件的上传
    * @param file 文件的信息
    * @return com.jzxy.common.Result
    * @author 龙殇
    * @date 2023/3/11 9:25
    */
    @PostMapping("/upload")
    public Result fileUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();

        String pathName = pathNamePre + originalFilename;

        File des = new File(pathName);
        file.transferTo(des);

        return Result.success(originalFilename);
    }

    /**
    * @description 图片的下载
    * @param name  图片的名称
    * @author 龙殇
    * @date 2023/3/11 11:20
    */
    @GetMapping("/download")
    public void fileDownload(String name, HttpServletResponse response){

        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(pathNamePre + name);

            //输出流，通过输出流将文件写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
