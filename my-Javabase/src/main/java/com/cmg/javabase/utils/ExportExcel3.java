package com.cmg.javabase.utils;

import net.sf.jxls.transformer.XLSTransformer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Name MyWiki com.cmg.javabase.utils
 * @Author cmg
 * @Date 2020/7/17 15:56
 * @Desciption TODO
 **/
public class ExportExcel3 {
    public static void main(String[] args) throws Exception {
        String templateFileName = "c:\\file\\开发文档\\上海农商行\\打印\\temp3.xlsx";
        String destFileName = "c:\\file\\开发文档\\上海农商行\\打印\\out3.xlsx";

        File file = new File(destFileName);
        if(file.exists()){
            file.delete();
        }

        Map<String, String> beans = new HashMap<String,String>();

        beans.put("name", "name");
        beans.put("sex", "sex");
        beans.put("age", "11");

        XLSTransformer transformer = new XLSTransformer();

        transformer.transformXLS(templateFileName, beans, destFileName);
    }
}

