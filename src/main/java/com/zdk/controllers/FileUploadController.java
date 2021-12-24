package com.zdk.controllers;

import com.zdk.utils.ApiResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/24 10:47
 */
@MultipartConfig(maxFileSize = 1000*1000*3)
@WebServlet(urlPatterns={"/fileUpload","/fileList"})
public class FileUploadController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        if (url.contains("/fileList")){
            //获取上传文件的目录
            String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
            //存储要下载的文件名
            Map<String,String> fileNameMap = new HashMap<>(16);
            //递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
            //File既可以代表一个文件也可以代表一个目录
            listFile(new File(uploadFilePath),fileNameMap);
            //将Map集合发送到files.jsp页面进行显示
            req.setAttribute("fileNameMap", fileNameMap);
            req.getRequestDispatcher("/files.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //获取上传的文件
            Part part = req.getPart("file");
            String realPath = req.getSession().getServletContext().getRealPath("/web");
            //获取请求的信息
            String name = part.getHeader("content-disposition");

            //获取文件的后缀
            String str = name.substring(name.lastIndexOf("."), name.length() - 1);
            //生成一个新的文件名，不重复，数据库存储的就是这个文件名，不重复的
            String fname = UUID.randomUUID() + str;
            String filename = System.getProperty("user.dir")+"\\web\\WEB-INF\\upload\\"+fname;
            System.out.println("测试产生新的文件名：" + filename);
            //上传文件到指定目录，不想上传文件就不调用这个
            part.write(filename);
            returnJson(resp, ApiResponse.success("文件上传成功"));
        } catch (Exception e) {
            e.printStackTrace();
            returnJson(resp, ApiResponse.success("文件上传失败"));
        }
    }

    /**
     * @Method: listFile
     * @Description: 递归遍历指定目录下的所有文件
     * @Anthor:hdb
     * @param file 即代表一个文件，也代表一个文件目录
     * @param map 存储文件名的Map集合
     */
    public void listFile(File file,Map<String,String> map){
        //如果file代表的不是一个文件，而是一个目录
        if(!file.isFile()){
            //列出该目录下的所有文件和目录
            File[] files = file.listFiles();
            //遍历files[]数组
            for(File f : files){
                //递归
                listFile(f,map);
            }
        }else{
            /**
             * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
             file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
             那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
             */
            String realName = file.getName().substring(file.getName().indexOf("_")+1);
            //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
            map.put(file.getName(), realName);
        }
    }
}
