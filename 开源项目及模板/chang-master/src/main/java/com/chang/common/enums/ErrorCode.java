package com.chang.common.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

/**
 * Created by neeke on 17-9-7.
 */
@JsonSerialize(using = ErrorCode.ErrorCodeJsonSerializer.class)
public enum ErrorCode {
    NO_HANDLER_FOUND("106", "该页面不存在"),
    HANDLE_HTTP_MEDIA_TYPE_NOT_ACCEPTABLE("109", "不接受的媒体类型"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("110", "不支持的请求方法"),
    ILLEGAL_ARGUMENT_EXCEPTION("119", "非法参数"),
    EXCEPTION("104", "异常"),
    METHOD_ARGUMENT_NOT_VALID_EXCEPTION("119", "参数校验不通过");

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }


    public static class ErrorCodeJsonSerializer extends JsonSerializer<ErrorCode> {

        @Override
        public void serialize(ErrorCode errorCode, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (null != errorCode) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName("code");
                jsonGenerator.writeObject(errorCode.code);
                jsonGenerator.writeFieldName("message");
                jsonGenerator.writeObject(errorCode.message);
                jsonGenerator.writeEndObject();
            } else {
                jsonGenerator.writeNull();
            }
        }
    }
}
