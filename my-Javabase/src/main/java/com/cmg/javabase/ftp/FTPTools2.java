package com.cmg.javabase.ftp;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.springframework.stereotype.Component;
import sun.net.ftp.FtpClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.Properties;

@Component
@Slf4j
public class FTPTools2 {
    public static void upload(String hostname,int port,String username,String password,String prvkey,String fileInfo,String uploadPath,String saveName) throws Exception {
        FTPClient ftp = new FTPClient();
        ftp.connect(hostname, port);
        //下面三行代码必须要，而且不能改变编码格式
        ftp.setControlEncoding("UTF-8");
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
        conf.setServerLanguageCode("zh");
        //如果采用默认端口，可以使用ftp.connect(url) 的方式直接连接FTP服务器
        ftp.login(username, password);//登录
        JSch jsch = new JSch();
        if (prvkey != null) {
            jsch.addIdentity(prvkey);// 设置私钥
        }
        Session session = jsch.getSession(username, hostname, port);
        if (password != null) {
            session.setPassword(password);
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftp = (ChannelSftp) channel;
        sendFile(sftp,fileInfo,uploadPath,saveName);
        disconnect(sftp, session);
    }

    /**
     * 文件传输
     * @param fileInfo
     * @param uploadPath
     * @param saveName
     * @return
     */
    private static void sendFile(ChannelSftp ftpClient, String fileInfo, String uploadPath, String saveName) throws FileNotFoundException, IOException, SftpException {
        try(FileInputStream fileInputStream = new FileInputStream(fileInfo)){
            //检查工作目录是否存在
            ftpClient.cd(uploadPath);
            ftpClient.put(fileInputStream,saveName);
            log.info("文件上传成功！");
        }catch (SftpException e) {
            log.error("文件传输异常：{}",e.getMessage());
            throw e;
        }
    }

    /**
     * 断开ftp连接
     * @param ftpClient
     */
    private static void disconnect(ChannelSftp ftpClient, Session session) {
        if (ftpClient.isConnected()){
            ftpClient.disconnect();
            log.info("sftp连接已关闭，文件上传结束");
        }
        if(session.isConnected()){
            session.disconnect();
            log.info("Session连接已关闭，文件上传结束");
        }
    }

    public static void main(String[] args) throws Exception {
        //ftp配置信息
        String hostname = "10.243.141.30"; //域名地址
        int port = 22; //端口号
        String username = "gpweb"; //用户名
        String password = "gpweb"; //密码
        String fileInfo = "c:\\file\\temp\\LK2070161B1.json"; //待上传的文件目录
        String uploadPath = "/gphome/gpweb"; //待上传到的路径
        String saveName = "20210209150229001"; //预保存的文件名
        FTPTools2.upload(hostname, port, username, password, "", fileInfo, uploadPath, saveName);
    }
}
