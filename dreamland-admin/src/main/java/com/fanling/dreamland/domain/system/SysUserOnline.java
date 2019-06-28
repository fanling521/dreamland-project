package com.fanling.dreamland.domain.system;

import com.fanling.dreamland.common.BaseEntity;
import com.fanling.dreamland.shiro.session.OnlineSession;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 当前在线会话 sys_user_online
 *
 * @author ruoyi
 */
@Getter
@Setter
public class SysUserOnline extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户会话id
     */
    private String sessionId;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地址
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * session创建时间
     */
    private Date startTimestamp;

    /**
     * session最后访问时间
     */
    private Date lastAccessTime;

    /**
     * 超时时间，单位为分钟
     */
    private Long expireTime;

    /**
     * 在线状态
     */
    private OnlineStatus status = OnlineStatus.on_line;

    /**
     * 备份的当前用户会话
     */
    private OnlineSession session;

    enum OnlineStatus {
        /**
         * 用户状态
         */
        on_line("在线"), off_line("离线");
        private final String info;

        OnlineStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

}
