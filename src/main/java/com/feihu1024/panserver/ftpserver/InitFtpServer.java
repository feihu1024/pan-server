package com.feihu1024.panserver.ftpserver;

import com.feihu1024.panserver.common.FtpProperties;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.ftplet.FtpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * springboot启动时初始化ftpserver
 */
@Component
public class InitFtpServer implements CommandLineRunner {

    public static final Logger log = LoggerFactory.getLogger(FtpServer.class);

    @Autowired
    private FtpServer server;

    @Autowired
    private FtpProperties ftpProperties;

    @Override
    public void run(String... args) {
        try {
            server.start();
            log.info("ftp-server start with port(s) "+ftpProperties.getPort()+" success ");
        } catch (FtpException e) {
            e.printStackTrace();
            log.error("ftp-server start error {}", e);
        }
    }
}
