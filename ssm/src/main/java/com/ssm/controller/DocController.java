package com.ssm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.pojo.Doc;
import com.ssm.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/doc")
public class DocController {

    @Autowired
    @Qualifier("docServiceImpl")
    private DocService docService;

    @RequestMapping("/")
    public String queryAllDoc() throws JsonProcessingException {
        List<Doc> list = docService.queryAllDoc();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(list);
    }

    @PostMapping("/")
    public String addDoc(@RequestParam("file") CommonsMultipartFile file, Doc doc, HttpServletRequest request) throws IOException {
        //上传路径保存设置
        String path = request.getSession().getServletContext().getRealPath("/upload");
        File realPath = new File(path);
        if (!realPath.exists()){
            boolean isMkdirs = realPath.mkdirs();
            System.out.println(isMkdirs);
        }
        //上传文件地址
        System.out.println("上传文件保存地址："+realPath);
        file.transferTo(new File(realPath +"/"+ file.getOriginalFilename()));
        docService.addDoc(doc);
        return null;
    }

    @DeleteMapping("/{docID}/")
    public String deleteDocByID(@PathVariable int docID) throws IOException {
        //上传路径保存设置
        docService.deleteDocByID(docID);
        return null;
    }

    @GetMapping(value="/file/{docPath}/")
    public String downloads(@PathVariable String docPath, HttpServletResponse response , HttpServletRequest request) throws Exception{
        //要下载的图片地址
        String  path = request.getSession().getServletContext().getRealPath("/upload");
        //1、设置response 响应头
        response.reset(); //设置页面不缓存,清空buffer
        response.setCharacterEncoding("UTF-8"); //字符编码
        response.setContentType("multipart/form-data"); //二进制传输数据
        //设置响应头
        response.setHeader("Content-Disposition",
                "attachment;fileName="+ URLEncoder.encode(docPath, "UTF-8"));
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        File file = new File(path, docPath);
        //2、 读取文件--输入流
        InputStream input=new FileInputStream(file);
        //3、 写出文件--输出流
        OutputStream out = response.getOutputStream();
        byte[] buff =new byte[1024];
        int index=0;
        //4、执行 写出操作
        while((index= input.read(buff))!= -1){
            out.write(buff, 0, index);
            out.flush();
        }
        out.close();
        input.close();
        return null;
    }


}
