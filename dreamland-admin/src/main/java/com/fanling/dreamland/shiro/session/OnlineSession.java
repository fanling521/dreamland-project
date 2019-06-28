package com.fanling.dreamland.shiro.session;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * 在线用户会话属性
 */
@Setter
@Getter
public class OnlineSession extends SimpleSession {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String loginName;

    /**
     * 登录IP地址
     */
    private String host;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 在线状态
     */
    private OnlineStatus status = OnlineStatus.on_line;

    /**
     * 属性是否改变 优化session数据同步
     */
    private transient boolean attributeChanged = false;

    public void markAttributeChanged() {
        this.attributeChanged = true;
    }

    public void resetAttributeChanged() {
        this.attributeChanged = false;
    }

    @Override
    public void setAttribute(Object key, Object value) {
        super.setAttribute(key, value);
    }

    @Override
    public Object removeAttribute(Object key) {
        return super.removeAttribute(key);
    }

    public static enum OnlineStatus {
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
