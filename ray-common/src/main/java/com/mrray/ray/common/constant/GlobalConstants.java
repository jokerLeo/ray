package com.mrray.ray.common.constant;

/**
 * 全局常量类
 *
 * @author lyc
 */
public class GlobalConstants {
    /**
     * 失败
     */
    public final static int FAIL = 0;
    /**
     * 成功
     */
    public final static int SUCCESS = 1;
    /**
     * 成功描述
     */
    public final static String SUCCESS_DESC = "操作成功";
    /**
     * 失败描述
     */
    public final static String FAIL_DESC = "操作失败";

    /**
     * 日志分隔符
     */
    public final static String LOG_SEP = "\t";

    /**
     * 逗号分隔符
     */
    public final static String COMMA_SEP = ",";
    /**
     * 句号分隔符
     */
    public final static String PERIOD_SEP = ".";
    /**
     * 路径分隔符
     */
    public final static String PATH_SEP = "/";

    /**
     * 树结构根
     */
    public final static int ROOT = 0;

    /**
     * API KEY 有效期
     */
    public final static long API_KEY_EXPIRE = 2 * 60 * 60 * 100;

    /**
     * 临时文件上传路径
     */
    public final static String TEMP_FILE_PATH = "data/temp/";

    /**
     * 超级账户角色编码
     */
    public final static String ROLE_ROOT = "root";

    /**
     * 组织负责人角色编码
     */
    public final static String ROLE_ORG_HEAD = "org_head";

}
