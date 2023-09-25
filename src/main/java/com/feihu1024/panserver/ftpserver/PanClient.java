package com.feihu1024.panserver.ftpserver;

import com.feihu1024.panserver.entity.FtpUser;
import com.feihu1024.panserver.entity.file.PanFile;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.ftpserver.FtpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PanClient {

    public static final Logger log = LoggerFactory.getLogger(FtpServer.class);

    private FTPClient ftpClient = null;

    public PanClient(FtpUser user,int port) throws IOException {
        ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(1000*30);
        ftpClient.connect("localhost", port);
        ftpClient.login(user.getUserName(), "server:"+user.getPassword());
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.enterLocalPassiveMode();

        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            log.info("连接FTP失败，用户名或密码错误!，用户名：'{}'",user.getUserName());
            ftpClient.disconnect();
            this.ftpClient = ftpClient;
        }else {
            log.info("连接FTP成功，用户名：'{}'",user.getUserName());
        }
    }

    public PanFile[] listFiles(String path) throws IOException {
        return  PanFile.convertToPanFileList(ftpClient.listFiles(path));
    }

    public PanFile makeDirectory(String path) throws IOException {
        if(ftpClient.makeDirectory(path)){
            return new PanFile(ftpClient.mdtmFile(path));
        }else{
            return null;
        }
    }
}
