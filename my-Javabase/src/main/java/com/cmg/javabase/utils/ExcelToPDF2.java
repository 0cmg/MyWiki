package com.cmg.javabase.utils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.File;

/**
 * @Name MyWiki com.cmg.javabase.utils
 * @Author cmg
 * @Date 2020/7/20 15:27
 * @Desciption TODO
 **/
public class ExcelToPDF2 {
    private static final Integer EXCEL_TO_PDF_OPERAND = 0;

    public static void main(String[] args) {
        String excelPath = "c:\\file\\开发文档\\上海农商行\\打印\\out2.xls";
        String pdfPath = "c:\\file\\开发文档\\上海农商行\\打印\\out2.pdf";
        String ttfPath = "c:\\file\\开发文档\\上海农商行\\打印\\arialuni.ttf";
        ExeclToPdf(excelPath, pdfPath);
    }

    public static void ExeclToPdf(String inFilePath,String outFilePath) {

        //pdf存在情况会出现转换错误
        File file = new File(outFilePath);
        if(file.exists()){
            file.delete();
        }

        ActiveXComponent ax = null;
        Dispatch excel = null;
        try {
            ComThread.InitSTA();
            ax = new ActiveXComponent("Excel.Application");
            ax.setProperty("Visible", new Variant(false));
            ax.setProperty("AutomationSecurity", new Variant(3));
            Dispatch excels = ax.getProperty("Workbooks").toDispatch();

            Object[] obj = new Object[]{
                    inFilePath,
                    new Variant(false),
                    new Variant(false)
            };
            excel = Dispatch.invoke(excels, "Open", Dispatch.Method, obj, new int[9]).toDispatch();

            // 转换格式
            Object[] obj2 = new Object[]{
                    new Variant(EXCEL_TO_PDF_OPERAND),
                    outFilePath,
                    new Variant(0)
            };
            Dispatch.invoke(excel, "ExportAsFixedFormat", Dispatch.Method, obj2, new int[1]);

        } catch (Exception es) {
            es.printStackTrace();
            throw es;
        } finally {
            if (excel != null) {
                Dispatch.call(excel, "Close", new Variant(false));
            }
            if (ax != null) {
                ax.invoke("Quit", new Variant[]{});
                ax = null;
            }
            ComThread.Release();
        }
    }
}
