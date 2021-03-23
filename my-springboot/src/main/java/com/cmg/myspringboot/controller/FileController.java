package com.cmg.myspringboot.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController("/")
public class FileController {

    @ResponseBody
    @RequestMapping("fileupload")
    public JSONObject upload(@RequestParam("file") MultipartFile srcFile, @RequestParam("fileType") String fileType) throws IOException {
        if(!srcFile.isEmpty() && srcFile.getSize() > 0){
            InputStream in = srcFile.getInputStream();
            File f = new File(srcFile.getOriginalFilename());
            inputStreamToFile(in, f);
            in.close();
        }
        JSONObject object = new JSONObject(true);
        JSONObject temp = new JSONObject(true);
        temp.put("code", "200");
        temp.put("success", "true");
        temp.put("msg", "成功");
        temp.put("data", null);
        object.put("resData",temp);
        return object;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
