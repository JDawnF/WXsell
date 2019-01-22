package com.immoc.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: sell
 * @description: 接受http请求返回参数商品详情实体类，为了安全和隐私需要重新建一个对象
 * @author: baichen
 * @create: 2018-08-18 23:16
 **/
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = -2985601425341530136L;
    //    @JsonProperty作用是把该属性的名称序列化为另外一个名称，
// 如这里的括号中相当于是把productId序列化成json格式参数中的key名(相当于起了个别名)id,别名是根据api文档中来设定的
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
