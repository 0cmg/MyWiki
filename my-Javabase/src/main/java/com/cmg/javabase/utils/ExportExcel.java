package com.cmg.javabase.utils;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

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
        String templateFileName = "c:\\file\\开发文档\\徽商银行\\导出\\temp3.xlsx";
        String destFileName = "c:\\file\\开发文档\\徽商银行\\导出\\out3.xlsx";
        File file = new File(destFileName);
        if(file.exists()){
            file.delete();
        }
        List<Map<String,Object>> list = prepareData();
        Map<String, Object> beans = new HashMap<String,Object>();
        beans.put("list", list);
        exportExcelTemplate(templateFileName,beans,destFileName);
    }

    public static void exportExcelTemplate(String templateFile, Map<String, Object> beans, String destFile ) throws IOException, InvalidFormatException {
        XLSTransformer transformer = new XLSTransformer();
        transformer.transformXLS(templateFile, beans, destFile);
    }

    public static List<Map<String,Object>> prepareData(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("name", "11");
        params.put("sex", "11");
        params.put("age", "11");
        list.add(params);
        params = new HashMap<String,Object>();
        params.put("name", "22");
        params.put("sex", "22");
        params.put("age", "22");
        list.add(params);
        params = new HashMap<String,Object>();
        params.put("name", "33");
        params.put("sex", "33");
        params.put("age", "33");
        list.add(params);
        return list;
    }
}

