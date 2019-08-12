package com.fanling.dreamland.config;

import com.fanling.dreamland.entity.SysCodeMain;
import com.fanling.dreamland.service.ISysCodeItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CodeMainCache implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISysCodeItemService sysCodeItemService;

    public static CodeMainCache codeMainCache;

    @PostConstruct
    public void init() {
        codeMainCache = this;
    }

    private static List<SysCodeMain> CODE_MAIN_LIST = new ArrayList<>();


    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("====开始缓存");
        CODE_MAIN_LIST = sysCodeItemService.codeMainItem();
        logger.info("====缓存完成，数据量：{}条", CODE_MAIN_LIST.size());
    }

    /**
     * 公共方法抽取
     *
     * @return
     */
    public static List<SysCodeMain> getCodeMainList() {
        return CODE_MAIN_LIST;
    }

    /**
     * 重建缓存
     */
    public void rebuild() {
        if (CODE_MAIN_LIST != null) {
            CODE_MAIN_LIST.clear();
            CODE_MAIN_LIST = codeMainCache.sysCodeItemService.codeMainItem();
        }
    }
}
