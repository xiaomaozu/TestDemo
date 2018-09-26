package com.chang.web.controller.base;

import com.chang.facade.dto.ResponseDTOWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * base控制器
 */
public abstract class BaseController {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    protected ResponseDTOWrapper createResponse(Object data){
        ResponseDTOWrapper responseDTOWrapper = new ResponseDTOWrapper();
        responseDTOWrapper.setSuccess(true);
        responseDTOWrapper.setData(data);
        return responseDTOWrapper;
    }

    protected ResponseDTOWrapper createResponse(){
        ResponseDTOWrapper responseDTOWrapper = new ResponseDTOWrapper();
        responseDTOWrapper.setSuccess(true);
        return responseDTOWrapper;
    }

    protected ResponseDTOWrapper createResponse(Object data, Long totalCount){
        ResponseDTOWrapper responseDTOWrapper = new ResponseDTOWrapper();
        responseDTOWrapper.setSuccess(true);
        responseDTOWrapper.setData(data);
        return responseDTOWrapper;
    }

}
