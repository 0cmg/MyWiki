package com.cmg.javabase.ftp;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.Date;

@Component
@Slf4j
public class FTPTools {
    /**
     * 文件上传
     * @param hostname 域名地址
     * @param port 端口号
     * @param username 用户名
     * @param password 密码
     * @param fileInfo 待上传的文件
     * @param uploadPath 待上传到的路径
     * @param saveName 预保存的文件名
     * @return
     */
    public static boolean upload(String hostname,int port,String username,String password,String fileInfo,String uploadPath,String saveName){
        boolean flag = false;

        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            ftpClient.connect(hostname,port);
            //每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据
            ftpClient.enterLocalPassiveMode();
            //设置二进制传输
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding("UTF-8");
            if(ftpClient.login(username,password)){//若连接成功
                log.info("成功登录到ftp服务端，开始文件上传");
                flag = sendFile(ftpClient,fileInfo,uploadPath,saveName);
            }else{
                log.error("登录到ftp服务端失败，请检查用户名和密码");
            }
            disconnect(ftpClient);
        }catch (SocketException e) {
            log.error("文件{}上传失败：{}",saveName,e.getMessage());
        } catch (IOException e) {
            log.error("文件{}上传失败：{}",saveName,e.getMessage());
        } finally {

        }
        return flag;
    }

    /**
     * 文件传输
     * @param fileInfo
     * @param uploadPath
     * @param saveName
     * @return
     */
    private static boolean sendFile(FTPClient ftpClient, String fileInfo, String uploadPath, String saveName) throws FileNotFoundException,IOException{
        boolean flag = false;
        try(FileInputStream fileInputStream = new FileInputStream(fileInfo)){
            //检查工作目录是否存在
            if(ftpClient.changeWorkingDirectory(uploadPath)){
                flag = ftpClient.storeFile(saveName,fileInputStream);
                log.info(flag?"文件上传成功！":"文件上传失败！");
            }else{
                log.error("找不到要上传的目录");
            }
        }catch (FileNotFoundException e){
            log.error("找不到要上传的文件{}：{}",fileInfo,e.getMessage());
            throw e;
        }catch (IOException e){
            log.error("文件传输异常：{}",e.getMessage());
            throw e;
        }
        return flag;
    }

    /**
     * 断开ftp连接
     * @param ftpClient
     */
    private static void disconnect(FTPClient ftpClient) {
        if (ftpClient.isConnected()){
            try{
                ftpClient.disconnect();
                log.info("ftp连接已关闭，文件上传结束");
            }catch (IOException e){
                log.error("ftp连接关闭失败:{}",e);
            }
        }
    }

    public static void main(String[] args) {
        //ftp配置信息
        String hostname = "10.243.140.215"; //域名地址
        int port = 22; //端口号
        String username = "gpweb"; //用户名
        String password = "gpweb"; //密码
        String fileInfo = "c:\\file\\temp\\LK2070161B1.json"; //待上传的文件目录
        String uploadPath = "/"; //待上传到的路径
        String saveName = "20210209150229001"; //预保存的文件名
        FTPTools.upload(hostname, port, username, password, fileInfo, uploadPath, saveName);
    }
}
