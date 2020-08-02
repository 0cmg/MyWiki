package com.cmg.javabase.utils;

import net.sf.jxls.transformer.Workbook;
import net.sf.jxls.transformer.XLSTransformer;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Name MyWiki com.cmg.javabase.utils
 * @Author cmg
 * @Date 2020/7/17 15:56
 * @Desciption TODO
 **/
public class ExportExcel2 {
    public static void main(String[] args) throws Exception {
        String templateFileName = "c:\\file\\开发文档\\上海农商行\\打印\\temp2.xls";
        String destFileName = "c:\\file\\开发文档\\上海农商行\\打印\\out2.xls";

        File file = new File(destFileName);
        if(file.exists()){
            file.delete();
        }

        Map<String, String> beans = new HashMap<String,String>();
        beans.put("p_bill_no","1231");
        beans.put("p_borrow_name","1232");
        beans.put("p_lend_name","1233");
        beans.put("p_borrow_client_id","1234");
        beans.put("p_lend_client_id","1235");
        beans.put("p_isdelay","是");
        beans.put("p_lend_variety","202");
        beans.put("p_trade_qty",new BigDecimal(124.123).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        beans.put("p_reg_date","20200712");
        beans.put("p_due_date","20200712");
        beans.put("p_pay_date","20200712");
        beans.put("p_year_rate",new BigDecimal(123.123).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        beans.put("p_interest_baseprice",new BigDecimal(1254879.1235).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        beans.put("p_interest_base","365");
        beans.put("p_interest",new BigDecimal(123654.123).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        beans.put("tempV","AA");

        XLSTransformer transformer = new XLSTransformer();

        transformer.transformXLS(templateFileName, beans, destFileName);
    }
}

