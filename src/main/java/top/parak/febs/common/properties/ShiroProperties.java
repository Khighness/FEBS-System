package top.parak.febs.common.properties;

import lombok.Data;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Data
public class ShiroProperties {

    private long sessionTimeout;
    private int cookieTimeout;
    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private String logoutUrl;
    private String unauthorizedUrl;
}
