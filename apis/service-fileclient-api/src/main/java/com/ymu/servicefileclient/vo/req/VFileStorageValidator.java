package com.ymu.servicefileclient.vo.req;

import com.ymu.framework.spring.mvc.api.APIs;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VFileStorageValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return VFileStorageReq.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        VFileStorageReq v = (VFileStorageReq) o;
        if (v.getOrgName() == null || v.getOrgName().equals("")) {
            throw APIs.error(1000,"原名称不能空",null);
        }
    }
}
