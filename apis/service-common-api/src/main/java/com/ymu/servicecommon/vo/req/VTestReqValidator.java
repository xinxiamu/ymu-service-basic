package com.ymu.servicecommon.vo.req;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VTestReqValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return VTestReq.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
//        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        VTestReq v = (VTestReq) o;
        if (v.getName().equals("张木天")) {
            System.out.println("张木天validate");
        }
    }
}
