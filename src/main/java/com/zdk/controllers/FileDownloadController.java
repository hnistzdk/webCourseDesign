package com.zdk.controllers;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/24 13:34
 */
@WebServlet(urlPatterns = "/download/*")
public class FileDownloadController extends BaseController{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = getStr(req, "filename");
        File f = new File(System.getProperty("user.dir")+"\\upload\\"+fileName);
        if(f.exists()){
            FileInputStream fis = new FileInputStream(f);
            //解决中文文件名下载后乱码的问题
            String filename= URLEncoder.encode(f.getName(),"utf-8");
            resp.setCharacterEncoding("utf-8");
            resp.setHeader("Content-Disposition","attachment; filename="+filename);
            resp.setHeader("Content-type", "application/x-msdownload");
            //获取响应报文输出流对象
            ServletOutputStream out =resp.getOutputStream();
            out.flush();
            int temp;
            byte[] b = new byte[1024];
            while ((temp = fis.read(b)) != -1){
                out.write(b,0,temp);
            }
            out.flush();
            out.close();
            fis.close();
        }
    }
}
