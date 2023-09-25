package com.feihu1024.panserver.service;

import com.feihu1024.panserver.common.FtpProperties;
import com.feihu1024.panserver.entity.FtpUser;
import com.feihu1024.panserver.entity.file.PanFile;
import com.feihu1024.panserver.entity.file.UploadFile;
import com.feihu1024.panserver.ftpserver.PanClient;
import com.feihu1024.panserver.ftpserver.PanClientFactory;
import com.feihu1024.panserver.interrupt.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileService {

    @Autowired
    FtpProperties ftpProperties;

    @Autowired
    AuthService authService;

    public PanFile[] listByPath(Long userId, String path) throws IOException {
        PanClient client = getClientByUseId(userId);

        // 设置默认path参数
        if (path == null) path = "/";

        return client.listFiles(path);
    }

    public void uploadByPath(Long userId, UploadFile file) throws IOException {
        PanClient client = getClientByUseId(userId);
        if (file.getPath() != null) {
            client.makeDirectory(file.getPath());
        }
    }

    PanClient getClientByUseId(Long userId) throws IOException {
        FtpUser user = authService.getById(userId);
        return PanClientFactory.getPanClient(user, ftpProperties.getPort());
    }
}
