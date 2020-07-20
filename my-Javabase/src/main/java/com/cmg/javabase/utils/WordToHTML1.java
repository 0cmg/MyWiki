//package com.cmg.javabase.utils;
//
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.converter.WordToHtmlConverter;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.w3c.dom.Document;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.*;
//
///**
// * @Name MyWiki com.cmg.javabase.utils
// * @Author cmg
// * @Date 2020/7/15 14:13
// * @Desciption TODO
// **/
//public class WordToHTML1 {
//    public static void main(String[] args) throws Exception {
//        String filePath = "c:\\file\\开发文档\\上海农商行\\打印\\";
//        File file = new File(filePath);
//        File[] files = file.listFiles();
//        String name = null;
//        for (File file2 : files) {
//            Thread.sleep(500);
//            name = file2.getName().substring(0, file2.getName().lastIndexOf("."));
//            System.out.println(file2.getName());
//            if (file2.getName().endsWith(".docx") || file2.getName().endsWith(".DOCX")) {
//                WordToHTML1.docx(filePath ,file2.getName(),name +".html");
//            }else{
//                WordToHTML1.dox(filePath ,file2.getName(),name +".html");
//            }
//
//        }
//    }
//    /**
//     * 转换docx
//     * @param filePath
//     * @param fileName
//     * @param htmlName
//     * @throws Exception
//     */
//    public static void docx(String filePath ,String fileName,String htmlName) throws Exception{
//        final String file = filePath + fileName;
//        File f = new File(file);
//        // ) 加载word文档生成 XWPFDocument对象
//        InputStream in = new FileInputStream(f);
//        XWPFDocument document = new XWPFDocument(in);
//        // ) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
//        File imageFolderFile = new File(filePath);
//        XHTMLOptions options = XHTMLOptions.create().URIResolver( new FileURIResolver(imageFolderFile));
//        options.setExtractor(new FileImageExtractor(imageFolderFile));
//        options.setIgnoreStylesIfUnused(false);
//        options.setFragment(true);
//        // ) 将 XWPFDocument转换成XHTML
//        OutputStream out = new FileOutputStream(new File(filePath + htmlName));
//        XHTMLConverter.getInstance().convert(document, out, options);
//    }
//    /**
//     * 转换doc
//     * @param filePath
//     * @param fileName
//     * @param htmlName
//     * @throws Exception
//     */
//    public static void dox(String filePath ,String fileName,String htmlName) throws Exception{
//        final String file = filePath + fileName;
//        InputStream input = new FileInputStream(new File(file));
//        HWPFDocument wordDocument = new HWPFDocument(input);
//        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
//        //解析word文档
//        wordToHtmlConverter.processDocument(wordDocument);
//        Document htmlDocument = wordToHtmlConverter.getDocument();
//        File htmlFile = new File(filePath + htmlName);
//        OutputStream outStream = new FileOutputStream(htmlFile);
//        DOMSource domSource = new DOMSource(htmlDocument);
//        StreamResult streamResult = new StreamResult(outStream);
//        TransformerFactory factory = TransformerFactory.newInstance();
//        Transformer serializer = factory.newTransformer();
//        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
//        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//        serializer.setOutputProperty(OutputKeys.METHOD, "html");
//        serializer.transform(domSource, streamResult);
//        outStream.close();
//    }
//}
