package com.echolink.common;

public class ResponseUtils {

    private ResponseUtils() {
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ResultCode.SUCCESS, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResultCode.SUCCESS, data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(ResultCode.SUCCESS, data);
        response.setMessage(message);
        return response;
    }

    public static <T> ApiResponse<T> error(ResultCode resultCode) {
        return new ApiResponse<>(resultCode, null);
    }

    public static <T> ApiResponse<T> error(ResultCode resultCode, String message) {
        ApiResponse<T> response = new ApiResponse<>(resultCode, null);
        response.setMessage(message);
        return response;
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public static <T> ApiResponse<T> build(ResultCode resultCode, T data) {
        return new ApiResponse<>(resultCode, data);
    }

    public static <T> ApiResponse<T> build(int code, String message, T data) {
        return new ApiResponse<>(code, message, data);
    }
}
