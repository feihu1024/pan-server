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
    FtpProperties.TableFields tableFields;

    @Autowired
    private DataSource dataSource;

    @Bean
    public FtpServer createFtpServer() {
        FtpServerFactory serverFactory = new FtpServerFactory();
        // 被动模式
        ListenerFactory factory = new ListenerFactory();
        factory.setPort(ftpProperties.getPort());

        DataConnectionConfigurationFactory dataConnectionConfigurationFactory = new DataConnectionConfigurationFactory();
        // dataConnectionConfigurationFactory.setIdleTime(60);
        // dataConnectionConfigurationFactory.setActiveLocalPort(ftpProperties.getPort());
        dataConnectionConfigurationFactory.setPassiveIpCheck(true);
        dataConnectionConfigurationFactory.setPassivePorts(ftpProperties.getPassivePorts());
        dataConnectionConfigurationFactory.setPassiveExternalAddress(ftpProperties.getPassiveExternalAddress());
        factory.setDataConnectionConfiguration(dataConnectionConfigurationFactory.createDataConnectionConfiguration());
        serverFactory.addListener("default", factory.createListener());


        DbUserManagerFactory userManagerFactory = new DbUserManagerFactory();
        userManagerFactory.setDataSource(dataSource);
        userManagerFactory.setAdminName("admin");
        userManagerFactory.setSqlUserAdmin(getSqlString("admin"));
        userManagerFactory.setSqlUserInsert(getSqlString("insert"));
        userManagerFactory.setSqlUserDelete(getSqlString("delete"));
        userManagerFactory.setSqlUserUpdate(getSqlString("update"));
        userManagerFactory.setSqlUserSelect(getSqlString("select"));
        userManagerFactory.setSqlUserSelectAll(getSqlString("selectAll"));
        userManagerFactory.setSqlUserAuthenticate(getSqlString("authenticate"));


        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        serverFactory.setUserManager(userManagerFactory.createUserManager());

        Map<String, Ftplet> ftpLets = new HashMap<String, Ftplet>();
        ftpLets.put("customFtpPlet", new CustomFtpPlet());
        serverFactory.setFtplets(ftpLets);

        FtpServer server = serverFactory.createServer();
        log.info("ftp-server initialized with port(s): {}", ftpProperties.getPort());
        return server;
    }

    String getSqlString(String type) {
        String tableName = ftpProperties.getTableName();
        System.out.println(tableFields);
        switch (type) {
            case "admin":
                return "SELECT " + tableFields.getUserid() + " FROM " + tableName + " WHERE " + tableFields.getUserid() + "='{userid}' AND " + tableFields.getUserid() + "='admin'";
            case "insert":
                return "INSERT INTO " + tableName + " (" +
                        tableFields.getUserid() + ", " +
                        tableFields.getUserpassword() + ", " +
                        tableFields.getHomedirectory() + ", " +
                        tableFields.getEnableflag() + ", " +
                        tableFields.getWritepermission() + ", " +
                        tableFields.getIdletime() + ", " +
                        tableFields.getUploadrate() + ", " +
                        tableFields.getDownloadrate() + ", " +
                        tableFields.getMaxloginnumber() + ", " +
                        tableFields.getMaxloginperip() + " ) " +
                        "VALUES ('{userid}', '{userpassword}', '{homedirectory}', {enableflag}, {writepermission}, {idletime}, {uploadrate}, {downloadrate}, {maxloginnumber}, {maxloginperip})";
            case "delete":
                return "DELETE FROM " + tableName + " WHERE " + tableFields.getUserid() + " = '{userid}'";
            case "update":
                return "UPDATE " + tableName + " SET " +
                        tableFields.getUserpassword() + "='{userpassword}', " +
                        tableFields.getHomedirectory() + "='{homedirectory}', " +
                        tableFields.getEnableflag() + "={enableflag}, " +
                        tableFields.getWritepermission() + "={writepermission}, " +
                        tableFields.getIdletime() + "={idletime}, " +
                        tableFields.getUploadrate() + "={uploadrate}, " +
                        tableFields.getDownloadrate() + "={downloadrate} ," +
                        tableFields.getMaxloginnumber() + "={maxloginnumber}, " +
                        tableFields.getMaxloginperip() + "={maxloginperip} WHERE " +
                        tableFields.getUserid() + "='{userid}'";
            case "select":
                return "SELECT " +
                        tableFields.getUserid() + " as userid, " +
                        tableFields.getUserpassword() + " as userpassword, " +
                        tableFields.getHomedirectory() + " as homedirectory, " +
                        tableFields.getEnableflag() + " as enableflag, " +
                        tableFields.getWritepermission() + " as writepermission, " +
                        tableFields.getIdletime() + " as idletime, " +
                        tableFields.getUploadrate() + " as uploadrate, " +
                        tableFields.getDownloadrate() + " as downloadrate, " +
                        tableFields.getMaxloginnumber() + " as maxloginnumber, " +
                        tableFields.getMaxloginperip() + " as maxloginperip FROM " +
                        tableName + " WHERE " +
                        tableFields.getUserid() + " = '{userid}'";
            case "selectAll":
                return "SELECT " + tableFields.getUserid() + " as userid FROM " + tableName + " ORDER BY " + tableFields.getUserid() + "";
            case "authenticate":
                return "SELECT " + tableFields.getUserid() + " as userid, " + tableFields.getUserpassword() + " as userpassword FROM " + tableName + " WHERE " + tableFields.getUserid() + "='{userid}'";
        }
        return "";
    }
}
