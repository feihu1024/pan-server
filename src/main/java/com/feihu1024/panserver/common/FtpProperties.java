package com.feihu1024.panserver.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("ftp")
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

    /**
     * ftp用户数据表名
     */
    private String tableName;

    /**
     * ftp用户数据表字段
     */
    private TableFields tableFields;

    @Data
    @Component
    @ConfigurationProperties("ftp.table-fields")
    public class TableFields{
        /**
         * 用户名称
         */
        public String userid ="userid";

        /**
         * 用户密码
         */
        private String userpassword = "userpassword";

        /**
         * 主目录
         */
        private String homedirectory = "homedirectory";

        /**
         * 账户是否启用
         */
        private String enableflag = "enableflag";

        /**
         * 是否具有写入权限
         */
        private String writepermission = "writepermission";

        /**
         * 空闲时间间隔(秒)
         */
        private String idletime = "idletime";

        /**
         * 上传速率限制(字节每秒)
         */
        private String uploadrate = "uploadrate";

        /**
         * 下载速率限制(字节每秒)
         */
        private String downloadrate = "downloadrate";

        /**
         * 最大登陆用户数
         */
        private String maxloginnumber = "maxloginnumber";

        /**
         * 最大同IP登陆用户数
         */
        private String maxloginperip = "maxloginperip";

    }
}


