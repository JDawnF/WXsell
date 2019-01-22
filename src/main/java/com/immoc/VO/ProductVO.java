package com.immoc.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: sell
 * @description: 接受http请求返回参数实体类：商品(包含类目)
 * @author: baichen
 * @create: 2018-08-18 23:13
 **/
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 3588067464543858775L;
    //@JsonProperty括号中相当于是json格式参数中的key名,相当于起了个别名
    //返回json对象
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
