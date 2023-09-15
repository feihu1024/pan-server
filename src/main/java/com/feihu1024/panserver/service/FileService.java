package com.feihu1024.panserver.service;

import com.feihu1024.panserver.common.FtpProperties;
import com.feihu1024.panserver.entity.FtpUser;
import com.feihu1024.panserver.entity.PanFile;
import com.feihu1024.panserver.ftpserver.PanClient;
import com.feihu1024.panserver.ftpserver.PanClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileService {

    @Autowired
    FtpProperties ftpProperties;

    @Autowired
    AuthService authService;

    public PanFile[] getFileListByPath(Long userId, String path) throws IOException {
        FtpUser user = authService.getById(userId);
        PanClient panClient = PanClientFactory.getPanClient(user, ftpProperties.getPort());
        return panClient.listFiles(path);
    }
}
