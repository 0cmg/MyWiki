package com.cmg.pdf;

import com.spire.pdf.*;

public class CompressionPDF {
    public static void main(String[] args) {
        //加载PDF文档
        PdfDocument document = new PdfDocument("c:\\file\\知识库\\资料\\[代码大全2中文版(完整清晰版)].pdf");

        //禁用incremental update
        document.getFileInfo().setIncrementalUpdate(false);

        //设置PDF文档的压缩级别
        document.setCompressionLevel(PdfCompressionLevel.Normal);

        //保存并关闭文档
        document.saveToFile("c:\\file\\知识库\\资料\\压缩\\[代码大全2中文版(完整清晰版)]1.pdf");
        document.close();
    }
}
