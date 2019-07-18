package com.fanling.dreamland;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = -2392317394954973503L;

    private static final String CODE_TAG = "code";

    private static final String MSG_TAG = "msg";

    private static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(0),
        /**
         * 错误
         */
        ERROR(500);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 状态类型
     */
    private Type type;

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回内容
     */
    private String msg;

    /**
     * 数据对象
     */
    private Object data;


    public R() {
    }

    /**
     * @param type
     * @param msg
     */
    public R(Type type, String msg) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 自定义code
     *
     * @param code
     * @param msg
     */
    public R(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    public R(Type type, String msg, Object data) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }

    public static R success() {
        return R.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static R success(Object data) {
        return R.success("操作成功", data);
    }

    public static R success(List<?> list) {
        return R.success("操作成功", list);
    }

    public static R success(String msg, Object data) {
        return new R(Type.SUCCESS, msg, data);
    }

    public static R success(String msg) {
        return new R(Type.SUCCESS, msg);
    }

    /**
     * 返回错误消息
     *
     * @return 警告消息
     */
    public static R error() {
        return R.error("操作失败");
    }


    public static R error(String msg) {
        return new R(Type.ERROR, msg);
    }


    public static R error(String msg, Object data) {
        return new R(Type.ERROR, msg, data);
    }
}
