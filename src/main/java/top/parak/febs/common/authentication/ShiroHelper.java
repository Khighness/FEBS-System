package top.parak.febs.common.authentication;

import top.parak.febs.common.annotation.Helper;
import org.apache.shiro.authz.AuthorizationInfo;

/**
 * @Author: KHighness
 * @Date: 2020-7
 * @Description:
 */

@Helper
public class ShiroHelper extends ShiroRealm {

    /**
     * 获取当前用户的角色和权限集合
     *
     * @return AuthorizationInfo
     */
    public AuthorizationInfo getCurrentUserAuthorizationInfo() {
        return super.doGetAuthorizationInfo(null);
    }
}
