package com.immoc.exception;

import com.immoc.enums.ResultEnum;
import lombok.Data;

/**
 * @program: sell
 * @description:
 * @author: baichen
 * @create: 2018-08-19 21:33
 **/
@Data
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
//        把message内容传到父类中去
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code=code;
    }
}
