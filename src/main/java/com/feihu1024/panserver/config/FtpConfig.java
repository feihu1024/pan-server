package com.feihu1024.panserver.config;


import com.feihu1024.panserver.common.FtpProperties;
import com.feihu1024.panserver.ftpserver.CustomFtpPlet;
import org.apache.ftpserver.DataConnectionConfigurationFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * ftpserver配置文件
 */

@Configuration
public class FtpConfig extends CachingConfigurerSupport {
    @Autowired
    private FtpProperties ftpProperties;

    @Bean
    public FtpServer createFtpServer() {
        FtpServerFactory serverFactory = new FtpServerFactory();
        // 被动模式
        ListenerFactory factory = new ListenerFactory();
        factory.setPort(ftpProperties.getPort());

        DataConnectionConfigurationFactory dataConnectionConfigurationFactory = new DataConnectionConfigurationFactory();
        dataConnectionConfigurationFactory.setIdleTime(60);
        // dataConnectionConfigurationFactory.setActiveLocalPort(ftpProperties.getPort());
        dataConnectionConfigurationFactory.setPassiveIpCheck(true);
        dataConnectionConfigurationFactory.setPassivePorts(ftpProperties.getPassivePorts());
        dataConnectionConfigurationFactory.setPassiveExternalAddress(ftpProperties.getPassiveExternalAddress());
        factory.setDataConnectionConfiguration(dataConnectionConfigurationFactory.createDataConnectionConfiguration());
        serverFactory.addListener("default", factory.createListener());

        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        try {
            ClassPathResource classPathResource = new ClassPathResource("users.properties");
            URL url = classPathResource.getURL();
            userManagerFactory.setUrl(url);
        } catch (Exception e) {
            throw new RuntimeException("配置文件users.properties不存在");
        }

        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        serverFactory.setUserManager(userManagerFactory.createUserManager());

        Map<String, Ftplet> ftpLets = new HashMap<String, Ftplet>();
        ftpLets.put("customFtpPlet", new CustomFtpPlet());
        serverFactory.setFtplets(ftpLets);

        FtpServer server = serverFactory.createServer();
        return server;
    }
}
