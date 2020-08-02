package com.cmg.javabase.utils;

import net.sf.jxls.transformer.XLSTransformer;

import java.io.*;
import java.util.*;

/**
 * @Name MyWiki com.cmg.javabase.utils
 * @Author cmg
 * @Date 2020/7/17 15:56
 * @Desciption TODO
 **/
public class ExportExcel {
    public static void main(String[] args) throws Exception {
        String templateFileName = "c:\\file\\开发文档\\上海农商行\\打印\\temp.xlsx";
        String destFileName = "c:\\file\\开发文档\\上海农商行\\打印\\out.xlsx";

        File file = new File(destFileName);
        if(file.exists()){
            file.delete();
        }
        List<Object> prints = new ArrayList<Object>();
        prints.add(new Print("1", "2", 11));

        Map<String, List<Object>> beans = new HashMap<String,List<Object>>();

        beans.put("prints", prints);

        XLSTransformer transformer = new XLSTransformer();

        transformer.transformXLS(templateFileName, beans, destFileName);
    }
}

