package com.itheima.validation;

import com.itheima.anno.State;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class StateValidation implements ConstraintValidator<State, String> {

    /* 
     * 验证状态是否为已发布或草稿
     * @param value 状态
     * @param context 约束验证上下文
     * @return 是否有效
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return false;
        }
        else if(value.equals("已发布") || value.equals("草稿")){
            return true;
        }
        else{
            return false;
        }
    }

}
