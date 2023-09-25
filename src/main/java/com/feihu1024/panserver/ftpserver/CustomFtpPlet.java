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
    public FtpletResult onUploadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        return super.onUploadEnd(session, request);
    }

    /**
     * 上传完成
     * Override this method to handle uploads after completion
     */
    @Override
    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
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
