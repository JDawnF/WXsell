package com.immoc.utils;

import com.immoc.VO.ResultVO;

/**
 * @program: sell
 * @description: 返回结果json数据通用类
 * @author: baichen
 * @create: 2018-08-19 09:05
 **/
public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
    public static ResultVO success(){
        return success(null);
    }
    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
