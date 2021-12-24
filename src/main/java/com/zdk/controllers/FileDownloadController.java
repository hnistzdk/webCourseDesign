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
        String url = req.getRequestURL().toString();
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        File f = new File(System.getProperty("user.dir")+"\\upload\\"+fileName);
        if(f.exists()){
            FileInputStream fis = new FileInputStream(f);
            //解决中文文件名下载后乱码的问题
            String filename= URLEncoder.encode(f.getName(),"utf-8");
            byte[] b = new byte[fis.available()];
            fis.read(b);
            resp.setCharacterEncoding("utf-8");
            resp.setHeader("Content-Disposition","attachment; filename="+filename+"");
            //获取响应报文输出流对象
            ServletOutputStream out =resp.getOutputStream();
            //输出
            out.write(b);
            out.flush();
            out.close();
        }
    }
}
