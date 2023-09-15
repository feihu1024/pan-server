package com.feihu1024.panserver.ftpserver;


import com.feihu1024.panserver.entity.FtpUser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ftp客户端管理器
 * 添加一个定时机制，超过一定时间未使用客户端则自动移除客
 */
public class PanClientFactory {
    private static Map<Long, PanClient> clientMap = new HashMap();

    /** 获取一个新的网盘客户端实例 */
    public static PanClient getPanClient(FtpUser user, int port) throws IOException {
        if (clientMap.containsKey(user.getId())) {
            return clientMap.get(user.getId());
        } else {
            PanClient panClient = new PanClient(user, port);
            clientMap.put(user.getId(), panClient);
            return panClient;
        }
    }

    /** 移除一个已有的网盘客户端实例 */
    public static boolean removePanClient(Long userId) {
        if(clientMap.containsKey(userId)) {
            clientMap.remove(userId);
            return true;
        }else {
            return false;
        }
    }
}
