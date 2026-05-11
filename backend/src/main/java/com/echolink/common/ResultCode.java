package com.echolink.common;

public enum ResultCode {

    SUCCESS(200, "操作成功"),
    CREATED(201, "创建成功"),
    ACCEPTED(202, "请求已接受"),
    NO_CONTENT(204, "无内容"),

    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权"),
    PAYMENT_REQUIRED(402, "需要支付"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "方法不允许"),
    NOT_ACCEPTABLE(406, "不接受的请求"),
    PROXY_AUTHENTICATION_REQUIRED(407, "需要代理认证"),
    REQUEST_TIMEOUT(408, "请求超时"),
    CONFLICT(409, "资源冲突"),
    GONE(410, "资源已删除"),
    LENGTH_REQUIRED(411, "需要Content-Length"),
    PRECONDITION_FAILED(412, "预处理失败"),
    PAYLOAD_TOO_LARGE(413, "请求体过大"),
    URI_TOO_LONG(414, "URI过长"),
    UNSUPPORTED_MEDIA_TYPE(415, "不支持的媒体类型"),
    RANGE_NOT_SATISFIABLE(416, "范围不满足"),
    EXPECTATION_FAILED(417, "期望失败"),
    UNPROCESSABLE_ENTITY(422, "无法处理的实体"),
    LOCKED(423, "资源已锁定"),
    FAILED_DEPENDENCY(424, "依赖失败"),
    TOO_EARLY(425, "请求过早"),
    UPGRADE_REQUIRED(426, "需要升级"),
    PRECONDITION_REQUIRED(428, "需要预处理条件"),
    TOO_MANY_REQUESTS(429, "请求过多"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "请求头字段过大"),
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "因法律原因不可用"),

    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    NOT_IMPLEMENTED(501, "未实现"),
    BAD_GATEWAY(502, "网关错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP版本不支持"),
    VARIANT_ALSO_NEGOTIATES(506, "变体也协商"),
    INSUFFICIENT_STORAGE(507, "存储空间不足"),
    LOOP_DETECTED(508, "检测到循环"),
    NOT_EXTENDED(510, "未扩展"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "需要网络认证"),

    PARAM_VALID_ERROR(10001, "参数校验失败"),
    USER_NOT_EXIST(10002, "用户不存在"),
    USER_ALREADY_EXIST(10003, "用户已存在"),
    USERNAME_OR_PASSWORD_ERROR(10004, "用户名或密码错误"),
    ACCOUNT_DISABLED(10005, "账号已禁用"),
    ACCOUNT_LOCKED(10006, "账号已锁定"),
    TOKEN_EXPIRED(10007, "Token已过期"),
    TOKEN_INVALID(10008, "Token无效"),
    PERMISSION_DENIED(10009, "权限不足"),
    OPERATION_FAILED(10010, "操作失败"),
    DATA_NOT_FOUND(10011, "数据不存在"),
    DATA_ALREADY_EXIST(10012, "数据已存在"),
    DATA_INTEGRITY_VIOLATION(10013, "数据完整性冲突"),
    FILE_UPLOAD_ERROR(10014, "文件上传失败"),
    FILE_DOWNLOAD_ERROR(10015, "文件下载失败"),
    FILE_SIZE_EXCEEDED(10016, "文件大小超出限制"),
    FILE_TYPE_NOT_SUPPORTED(10017, "不支持的文件类型"),
    SMS_SEND_FAILED(10018, "短信发送失败"),
    EMAIL_SEND_FAILED(10019, "邮件发送失败"),
    THIRD_PARTY_SERVICE_ERROR(10020, "第三方服务错误");

    private final int code;
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
