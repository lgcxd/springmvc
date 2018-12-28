package com.cbb.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ClassName UserNameNotMatchPasseordException
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/7/6 10:01
 * @Version:
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户名和密码不匹配")
public class UserNameNotMatchPasseordException extends RuntimeException {
    /*
    * FORBIDDEN 不允许
    * @ResponseStatus 定制的*/
    private static final long serialVersionUID = 1L;

}
