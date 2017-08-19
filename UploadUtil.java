package com.yztc.download.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;

public class UploadUtil {
    public static void fileUpload(HttpServletRequest request,String filePath ) throws Exception {


        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );
        //检查form中是否有enctype ="multipart/form-date"
        if (multipartResolver.isMultipart(request)) {
            //将request编程多部分request
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            //获取multRequest 中所有文件名
            Iterator iterator = multipartHttpServletRequest.getFileNames();

            while (iterator.hasNext()) {
                //一次遍历所有文件
                MultipartFile file = multipartHttpServletRequest.getFile(iterator.next().toString());
                if (file != null) {
                    String path = filePath + file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
    }
}

