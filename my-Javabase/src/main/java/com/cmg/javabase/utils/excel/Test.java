package com.cmg.javabase.utils.excel;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

/**
 * @Name MyWiki com.cmg.javabase.utils.excel
 * @Author cmg
 * @Date 2020/7/20 16:05
 * @Desciption TODO
 **/
public class Test {
    public static void main(String[] args) throws IOException, DocumentException {
        String excelPath = "c:\\file\\开发文档\\上海农商行\\打印\\out2.xls";
        String pdfPath = "c:\\file\\开发文档\\上海农商行\\打印\\out2.pdf";
        Xls2Pdf xls2Pdf = new Xls2Pdf(excelPath, 0);
        xls2Pdf.savePdf(pdfPath);
    }
}
