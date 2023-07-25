package com.feihu1024.panserver.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("ftp")
@Data
public class FtpProperties {
    /**
     * ftp连接端口
     */
    private Integer port;

    /**
     * 被动连接数据传输端口
     * 例: 3000-4000
     */
    private String passivePorts;

    /**
     * 部署的服务器ip地址
     */
    private String passiveExternalAddress;
}
