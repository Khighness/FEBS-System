package top.parak.febs.monitor.service;

import top.parak.febs.monitor.entity.ActiveUser;

import java.util.List;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

public interface ISessionService {

    /**
     * 获取在线用户列表
     *
     * @param username 用户名
     * @return List<ActiveUser>
     */
    List<ActiveUser> list(String username);

    /**
     * 踢出用户
     *
     * @param sessionId sessionId
     */
    void forceLogout(String sessionId);
}
