package com.skolli.cms.utils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private String message;
    private Boolean success;
    private String errorCode;
    private T data;
    private LocalDateTime timeStamp;

    public static <T>ApiResponse<T> success(String message, T data) {
        return new ApiResponseBuilder<T>()
                .success(true)
                .message(message)
                .data(data)
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static <T>ApiResponse<T> error(String message, String errorCode) {
        return new ApiResponseBuilder<T>()
                .success(false)
                .message(message)
                .errorCode(errorCode)
                .timeStamp(LocalDateTime.now())
                .build();
    }

    public static class ApiResponseBuilder<T> {
        private String message;
        private Boolean success;
        private String errorCode;
        private T data;
        private LocalDateTime timeStamp;

        public ApiResponseBuilder<T> success(Boolean success) {
            this.success = success;
            return this;
        }

        public ApiResponseBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder<T> errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ApiResponseBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder<T> timeStamp(LocalDateTime timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public ApiResponse<T> build() {
            ApiResponse<T> response =  new ApiResponse<>();
            response.data = this.data;
            response.success = this.success;
            response.message = this.message;
            response.errorCode = this.errorCode;
            response.timeStamp = this.timeStamp;
            return response;
        }
    }
}
