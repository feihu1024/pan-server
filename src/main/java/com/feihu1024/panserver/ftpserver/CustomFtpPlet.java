package com.feihu1024.panserver.ftpserver;

import org.apache.ftpserver.ftplet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class CustomFtpPlet extends DefaultFtplet {

    public static final Logger log = LoggerFactory.getLogger(CustomFtpPlet.class);

    /**
     * 开始上传
     * Override this method to intercept uploads
     */
    @Override
    public FtpletResult onUploadStart(FtpSession session, FtpRequest request)
            throws FtpException, IOException {
        //获取上传文件的上传路径
        String path = session.getUser().getHomeDirectory();
        //自动创建上传路径
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取上传用户
        String name = session.getUser().getName();
        //获取上传文件名
        String filename = request.getArgument();

        log.info("用户:'{}'，上传文件到目录：'{}'，文件名称为：'{}'，状态：开始上传~", name, path, filename);
        return super.onUploadEnd(session, request);
    }

    /**
     * 上传完成
     * Override this method to handle uploads after completion
     */
    @Override
    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request)
            throws FtpException, IOException {
        //获取上传文件的上传路径
        String path = session.getUser().getHomeDirectory();
        //获取上传用户
        String name = session.getUser().getName();
        //获取上传文件名
        String filename = request.getArgument();

        File file = new File(path + "/" + filename);
        if (file.exists()) {
            System.out.println(file);
        }

        log.info("用户:'{}'，上传文件到目录：'{}'，文件名称为：'{}，状态：成功！'", name, path, filename);
        return super.onUploadStart(session, request);
    }

    @Override
    public FtpletResult onDownloadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        return super.onDownloadStart(session, request);
    }

    @Override
    public FtpletResult onDownloadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        return super.onDownloadEnd(session, request);
    }
}
