package com.mrray.ray.common.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Description: 密码验证器
 *
 * @author 然诺
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public void initialize(Password constraintAnnotation) {
        // do nothing
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(s)) {
            return false;
        } else {
            return checkPass(s);
        }
    }

    /**
     * 校验密码
     */
    public static boolean checkPass(String pass) {
        String pattern = "^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{6,16}$";
        return Pattern.matches(pattern, pass);
    }

}
