package com.echolink.common;

public class ResultUtils {

    private ResultUtils() {
    }

    public static <T> ApiResponse<T> success() {
        return buildFromResultCode(ResultCode.SUCCESS, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return buildFromResultCode(ResultCode.SUCCESS, data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return buildFromRawParams(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ApiResponse<T> error(ResultCode resultCode) {
        return buildFromResultCode(resultCode, null);
    }

    public static <T> ApiResponse<T> error(Integer code, String message) {
        return buildFromRawParams(code, message, null);
    }

    public static <T> ApiResponse<T> error(ResultCode resultCode, String message) {
        return buildFromRawParams(resultCode.getCode(), message, null);
    }

    private static <T> ApiResponse<T> buildFromResultCode(ResultCode resultCode, T data) {
        return buildFromRawParams(resultCode.getCode(), resultCode.getMessage(), data);
    }

    private static <T> ApiResponse<T> buildFromRawParams(Integer code, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
