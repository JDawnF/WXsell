package com.immoc.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: sell
 * @description: http请求返回的最外层对象，根据api文档中的最外层对象
 * @author: baichen
 * @create: 2018-08-18 22:59
 **/
@Data
public class ResultVO<T> implements Serializable {
//    保证id唯一
    private static final long serialVersionUID = -2086091908368365556L;
    /*错误码*/
    private Integer code;
    /*提示信息*/
    private String msg;
    /*返回的具体内容，利用泛型，注意在类名那里要有个泛型<T>*/
    private T data;
}
