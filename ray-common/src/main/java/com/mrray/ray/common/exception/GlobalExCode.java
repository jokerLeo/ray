package com.mrray.ray.common.exception;

/**
 * 描述：全局编码枚举类
 * ---------------------------------------------------------
 * 编码定义格式说明：
 * 1.基础网络异常保持通用,编号1000以下；
 * 2.通用异常（4位）：前二位（模块）+ 后两位（序号）
 * 3.业务异常（5位）：前三位（模块）+ 后两位（序号）
 * ----------------------------------------------------------
 *
 * @author lyc
 */
public enum GlobalExCode implements IExceptionCode {
    // 系统相关
    SYSTEM_BUSY(-1, "系统繁忙，请稍候再试"),
    SYSTEM_TIMEOUT(-2, "系统超时，请稍候再试"),
    REQUEST_REPEAT(-3, "请勿重复提交"),
    TOKEN_EXPIRED(-4, "token已过期"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // 网络交互相关
    BAD_REQUEST(400, "请求的参数个数或格式不符合要求"),
    INVALID_ARGUMENT(400, "请求的参数不正确"),
    UNAUTHORIZED(401, "没有权限"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "请求的地址错误"),
    METHOD_NOT_ALLOWED(405, "不允许的请求方法"),
    NOT_ACCEPTABLE(406, "不接受的请求"),
    CONFLICT(409, "资源冲突"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持的Media Type"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    REQUEST_TIMEOUT(504, "请求服务超时"),
    ENCODING_ERROR(600, "编码错误"),
    METHOD_ARGUMENT_TYPE_MISMATCH(601, "请求参数类型错误"),
    METHOD_ARGUMENT_COUNT_MISMATCH(602, "请求参数缺失错误"),
    METHOD_ARGUMENT_BIND_EX(603, "请求参数有误，绑定异常"),
    METHOD_REQUEST_PART_MISSING(604, "所需的请求参数缺失数据"),
    METHOD_HTTP_MESSAGE_NOT_READABLE_EX(604, "请求参数有误，Http消息不可读"),

    // 数据库相关
    DB_ADD_FAIL(1000, "保存数据失败"),
    DB_REMOVE_FAIL(1001, "软删除失败"),
    DB_DELETE_FAIL(1002, "删除失败"),
    DB_UPDATE_FAIL(1003, "更新失败"),
    DB_QUERY_FAIL(1004, "查询失败"),
    DB_DUPLICATE_KEY_EXCEPTION(1005, "DB重复键（索引）异常"),
    DB_DATA_INTEGRITY_VIOLATION_EX(1006, "数据完整性违规异常（可能是字段缺少或错误）"),

    // 操作相关
    ACTION_FAILURE(1100, "操作失败"),
    CALLING_REMOTE_SERVICE_FAIL(1101, "远程服务调用失败"),
    ACCESS_SOURCE_NOT_EXIST(1102, "请求访问资源不存在"),
    ILLEGAL_OPERATE(1103, "非法操作"),
    FILE_MAX_UPLOAD_SIZE_EXCEEDED(1104, "上传文件超过最大上传大小限制"),
    FILE_WRITE_FAIL(1105, "写入文件失败"),
    FILE_MOVE_FAIL(1106, "移动临时文件失败"),
    FILE_HASH_WRONG(1107, "文件HASH计算有误"),
    FILE_DELETE_FAIL(1108, "文件删除失败"),
    FILE_TYPE_WRONG(1109, "文件类型错误"),
    FILE_IS_EMPTY(1110, "文件为空"),
    FILE_META_SAVE_FAIL(1111, "保存文件元数据失败"),

    // 用户、角色、权限相关
    USER_IS_EXIST(10000, "用户已存在"),
    USER_IS_NOT_EXIST(10001, "用户不存在"),
    USER_NAME_OR_PWD_ERROR(10002, "用户名或密码错误"),
    USER_REGISTER_FAIL(10003, "用户注册失败"),
    USER_NOT_REGISTERED(10004, "用户未注册"),
    USER_IS_FROZEN(10005, "用户已冻结"),
    USER_IS_UNAVAILABLE(10006, "用户不可用"),
    USER_ILLEGAL_LOGIN_OPERATION(10007, "非法的登录认证操作"),
    TOKEN_AUTHENTICATION_FAIL(10008, "token认证失败！"),
    TOKEN_LOGIN_EXPIRED(10009, "登录已过期，请重新登录"),
    USER_SUPER_ROLE_CHANGE_NOT_ALLOWED(10010, "超级账户的角色不允许被修改"),

    ROLE_IS_EXIST(10100, "角色已存在"),
    ROLE_IS_NOT_EXIST(10101, "角色不存在"),
    ROLE_IS_UNAVAILABLE(10102, "角色不可用"),
    ROLE_ASSOCIATED_USERS_CANNOT_DELETE(10103, "角色有关联用户，不能删除"),
    ROLE_ASSOCIATED_PERMISSIONS_CANNOT_DELETE(10104, "角色有关联权限，不能删除"),
    ROLE_CAN_NOT_ALLOCATE_ROOT(10105, "超级管理员角色不能被分配"),

    ORG_IS_EXIST(10200, "组织已存在"),
    ORG_IS_NOT_EXIST(10201, "组织不存在"),
    ORG_PRINCIPAL_IS_NOT_EXIST(10202, "组织负责人不存在"),
    ORG_NOT_ENABLED(10203, "您所在的组织未授权启用，请联系上级组织负责人"),
    ORG_NOT_ASSOCIATED(10204, "您的账户未关联任何组织"),
    ORG_PRINCIPAL_PWD_CAN_NOT_BLANK(10205, "负责人账户密码不能为空"),

    // 党建基础数据相关
    PARTY_ORG_NOT_EXIST(10400, "党组织不存在"),

    // 积分相关
    TOKEN_USER_IS_EXIST(10500, "积分用户不存在"),

    // 数据管理相关
    DATA_SERVER_IS_ENABLE(10600, "数据服务已经启用"),
    DATA_SERVER_IS_DISABLE(10601, "数据服务已经禁用"),
    PLEASE_ENABLE_SERVER(10602, "请先启用数据服务"),
    API_NOT_EXIST(10603, "API不存在"),
    API_TYPE_ERROR(10603, "API类型必须为对外开发接口"),
    API_METHOD_PARAMETER_NOT_EMPTY(10604, "请至少输入组织编号或者名称"),
    APP_DATA_NOT_PERMISSION(10605, "当前用户没有权限查看此组织数据"),
    ARCHIVES_NO_NOT_EXIST(10606, "党员编号或者身份证号不存在"),
    ORG_TIME_ERROR(10607, "请传入正确的加入组织时间"),
    TIME_PATTERN_WRONG(10607, "开始时间或者结束时间格式有误"),
    // 区块链浏览器相关
    TX_HASH_NOT_EXIST(10700, "交易hash不存在"),
    HEIGHT_NOT_EXIST(10701, "区块高度不存在"),

    //公共配置相关
    CONFIG_NOT_EXIST(10801, "公共配置不存在");

    private int status;
    private String message;

    GlobalExCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static GlobalExCode fromStatus(int httpStatus) {
        for (GlobalExCode errorCode : values()) {
            if (errorCode.getStatus() == httpStatus) {
                return errorCode;
            }
        }
        return INTERNAL_SERVER_ERROR;
    }

    public static GlobalExCode fromMessage(String message) {
        if (message != null) {
            for (GlobalExCode errorCode : values()) {
                if (message.equals(errorCode.getMessage())) {
                    return errorCode;
                }
            }
        }
        return INTERNAL_SERVER_ERROR;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return name();
    }

}
