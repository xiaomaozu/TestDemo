package com.chang.facade.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

/**
 * @author 常培兵
 * @Description: 全局返回json格式
 * @date 2018/3/22 10:06
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "响应", description = "接口调用响应")
public class ResponseDTOWrapper {
    @ApiModelProperty(value = "是否成功")
    private boolean success;        //是否成功

    @ApiModelProperty(value = "返回数据")
    private Object data;            //返回数据

    @ApiModelProperty(value = "返回描述")
    private String message;         //返回信息

    @ApiModelProperty(value = "错误参数")
    private Map<String, String> argErrors;

    public ResponseDTOWrapper(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
    public ResponseDTOWrapper(boolean success, String message, Map<String, String> argErrors) {
        this.success = success;
        this.message = message;
        this.argErrors=argErrors;
    }
    public ResponseDTOWrapper(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseDTOWrapper() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseDTOWrapper that = (ResponseDTOWrapper) o;

        if (success != that.success) return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = (success ? 1 : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }


    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public Map<String, String> getArgErrors() {
        return argErrors;
    }

    public void setArgErrors(Map<String, String> argErrors) {
        this.argErrors = argErrors;
    }
}
