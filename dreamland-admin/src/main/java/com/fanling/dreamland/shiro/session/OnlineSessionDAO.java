package com.fanling.dreamland.shiro.session;

import com.fanling.dreamland.common.util.StringUtils;
import com.fanling.dreamland.domain.system.SysUserOnline;
import com.fanling.dreamland.service.system.ISysUserOnlineService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * 针对自定义的ShiroSession的db操作
 */
public class OnlineSessionDAO extends EnterpriseCacheSessionDAO {
    /**
     * 同步session到数据库的周期 单位为毫秒（默认1分钟）
     */
    @Value("${shiro.session.dbSyncPeriod}")
    private int dbSyncPeriod;

    /**
     * 上次同步数据库的时间戳
     */
    private static final String LAST_SYNC_DB_TIMESTAMP = OnlineSessionDAO.class.getName() + "LAST_SYNC_DB_TIMESTAMP";

    @Autowired
    private ISysUserOnlineService onlineService;

    @Autowired
    private OnlineSessionFactory onlineSessionFactory;

    public OnlineSessionDAO() {
        super();
    }

    public OnlineSessionDAO(long expireTime) {
        super();
    }

    /**
     * 根据会话ID获取会话
     *
     * @param sessionId 会话ID
     * @return ShiroSession
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        SysUserOnline sysUserOnline = onlineService.selectOnlineById(String.valueOf(sessionId));
        if (sysUserOnline == null) {
            return null;
        }
        return onlineSessionFactory.createSession(sysUserOnline);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        super.update(session);
    }

    /**
     * 更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
     */
    public void syncToDb(OnlineSession onlineSession) {
        Date lastSyncTimestamp = (Date) onlineSession.getAttribute(LAST_SYNC_DB_TIMESTAMP);
        if (lastSyncTimestamp != null) {
            boolean needSync = true;
            long deltaTime = onlineSession.getLastAccessTime().getTime() - lastSyncTimestamp.getTime();
            if (deltaTime < dbSyncPeriod * 60 * 1000) {
                // 时间差不足 无需同步
                needSync = false;
            }
            // isGuest = true 访客
            boolean isGuest = onlineSession.getUserId() == null || StringUtils.isNull(onlineSession);
            // session 数据变更了 同步
            if (isGuest == false && onlineSession.isAttributeChanged()) {
                needSync = true;
            }
            if (needSync == false) {
                return;
            }
        }
        // 更新上次同步数据库时间
        onlineSession.setAttribute(LAST_SYNC_DB_TIMESTAMP, onlineSession.getLastAccessTime());
        // 更新完后 重置标识
        if (onlineSession.isAttributeChanged()) {
            onlineSession.resetAttributeChanged();
        }
    }

    /**
     * 当会话过期/停止（如用户退出时）属性等会调用
     */
    @Override
    protected void doDelete(Session session) {
        OnlineSession onlineSession = (OnlineSession) session;
        if (null == onlineSession) {
            return;
        }
        onlineSession.setStatus(OnlineSession.OnlineStatus.off_line);
        onlineService.deleteOnlineById(String.valueOf(onlineSession.getId()));
    }
}
