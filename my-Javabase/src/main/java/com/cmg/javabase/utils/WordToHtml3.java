package com.cmg.javabase.utils;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.List;

/**
 * @Name MyWiki com.cmg.javabase.utils
 * @Author cmg
 * @Date 2020/7/16 13:25
 * @Desciption TODO
 **/
public class WordToHtml3 {
    /**
     * word07版本(.docx)转html
     * poi:word07在线预览
     * */
    public static void PoiWord07ToHtml () throws IOException {

        String path= "c:\\file\\开发文档\\上海农商行\\打印\\";
        String file = "c:\\file\\开发文档\\上海农商行\\打印\\拆借机打单据.docx";
        String file2 ="c:\\file\\开发文档\\上海农商行\\打印\\拆借机打单据.html";
        File f = new File(file);
        if (!f.exists()) {
            System.out.println("Sorry File does not Exists!");
        } else {
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {
                //读取文档内容
                InputStream in = new FileInputStream(f);
                XWPFDocument document = new XWPFDocument(in);

                File imageFolderFile = new File(path);
                //加载html页面时图片路径
                XHTMLOptions options = XHTMLOptions.create().URIResolver( new BasicURIResolver("./"));
                //图片保存文件夹路径
                options.setExtractor(new FileImageExtractor(imageFolderFile));
                OutputStream out = new FileOutputStream(new File(file2));
                XHTMLConverter.getInstance().convert(document, out, options);
                out.close();
            } else {
                System.out.println("Enter only MS Office 2007+ files");
            }
        }

    }

    public static void main(String[] args) throws IOException {
        WordToHtml3.PoiWord07ToHtml ();
    }
}
