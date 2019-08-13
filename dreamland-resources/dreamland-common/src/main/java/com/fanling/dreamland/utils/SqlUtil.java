package com.fanling.dreamland.utils;

/**
 * sql操作工具类
 * 
 */
public class SqlUtil
{

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value)
    {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value))
        {
            return StringUtils.EMPTY;
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    private static boolean isValidOrderBySql(String value)
    {
        String SQL_PATTERN = "[a-zA-Z0-9_ ,]+";
        return value.matches(SQL_PATTERN);
    }
}
