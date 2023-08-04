package com.feihu1024.panserver.config;


import com.feihu1024.panserver.common.FtpProperties;
import com.feihu1024.panserver.ftpserver.CustomFtpPlet;
import org.apache.ftpserver.DataConnectionConfigurationFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.DbUserManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * ftpserver配置文件
 */

@Configuration
public class FtpConfig extends CachingConfigurerSupport {

    public static final Logger log = LoggerFactory.getLogger(FtpConfig.class);

    @Autowired
    private FtpProperties ftpProperties;

    @Autowired
    private DataSource dataSource;

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


        DbUserManagerFactory userManagerFactory = new DbUserManagerFactory();
        userManagerFactory.setDataSource(dataSource);
        userManagerFactory.setAdminName("admin");
        userManagerFactory.setSqlUserAdmin("SELECT user_name FROM pan.ftp_user WHERE user_name='{userid}' AND user_name='admin'");
        userManagerFactory.setSqlUserInsert("INSERT INTO pan.ftp_user (user_name, password, home_directory, enable_flag, write_permission, idle_time, upload_rate, download_rate, max_login_number, max_login_perip) " +
            "VALUES ('{userid}', '{userpassword}', '{homedirectory}', {enableflag}, {writepermission}, {idletime}, {uploadrate}, {downloadrate}, {maxloginnumber}, {maxloginperip})");
        userManagerFactory.setSqlUserDelete("DELETE FROM pan.ftp_user WHERE user_name = '{userid}'");
        userManagerFactory.setSqlUserUpdate("UPDATE pan.ftp_user SET password='{userpassword}', home_directory='{homedirectory}', enable_flag={enableflag}, write_permission={writepermission}, idle_time={idletime}, upload_rate={uploadrate}, download_rate={downloadrate} ,max_login_number={maxloginnumber}, max_login_perip={maxloginperip} WHERE user_name='{userid}'");
        userManagerFactory.setSqlUserSelect("SELECT user_name as userid, password as userpassword, home_directory as homedirectory, enable_flag as enableflag, write_permission as writepermission, idle_time as idletime, upload_rate as uploadrate, download_rate as downloadrate, max_login_number as maxloginnumber, max_login_perip as maxloginperip FROM pan.ftp_user WHERE user_name = '{userid}'");
        userManagerFactory.setSqlUserSelectAll("SELECT user_name as  userid FROM pan.ftp_user ORDER BY user_name");
        userManagerFactory.setSqlUserAuthenticate("SELECT user_name as userid, password as userpassword FROM pan.ftp_user WHERE user_name='{userid}'");


        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        serverFactory.setUserManager(userManagerFactory.createUserManager());

        Map<String, Ftplet> ftpLets = new HashMap<String, Ftplet>();
        ftpLets.put("customFtpPlet", new CustomFtpPlet());
        serverFactory.setFtplets(ftpLets);

        FtpServer server = serverFactory.createServer();
        log.info("ftp-server initialized with port(s): {}", ftpProperties.getPort());
        return server;
    }
}
