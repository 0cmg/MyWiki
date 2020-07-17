package com.cmg.javabase.utils;

import net.sf.jxls.transformer.XLSTransformer;
import org.springframework.ui.Model;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Name MyWiki com.cmg.javabase.utils
 * @Author cmg
 * @Date 2020/7/17 15:56
 * @Desciption TODO
 **/
public class ExportExcel {
    private void export() throws Exception {
        //获得模版
        String tempFileName = "";
        //将结果放入这个list中123
        List values = new ArrayList();

        values.add("");
        Map beans = new HashMap();
        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("yyyyMMddHHmmss");
        String currntTime = simpl.format(date);
        tempFileName = "c:\\workspace\\Coding\\MyWiki\\my-Javabase\\src\\main\\resources\\policyDemo.xls";

        //导出列表名
        String fileName = currntTime+"temp.xls";
        beans.put("values", values);

        //文件名称统一编码格式
        fileName = URLEncoder.encode(fileName, "utf-8");

        //生成的导出文件
        File destFile = File.createTempFile(fileName, ".xls");

        //transformer转到Excel
        XLSTransformer transformer = new XLSTransformer();

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //将数据添加到模版中生成新的文件
            transformer.transformXLS(tempFileName, beans, destFile.getAbsolutePath());
            //将文件输入
            InputStream inputStream = new FileInputStream(new File(""));
            //将文件输出到页面
            OutputStream out = new FileOutputStream(destFile);
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // 根据读取并写入
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {

        }finally {
            //使用完成后关闭流
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
            }

        }
    }
}
