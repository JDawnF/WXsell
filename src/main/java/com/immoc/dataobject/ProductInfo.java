package com.immoc.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immoc.enums.ProductStatusEnum;
import com.immoc.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: sell
 * @description: 商品表
 * @author: baichen
 * @create: 2018-08-18 19:42
 **/
@Entity
@Data
@DynamicUpdate      //自动更新时间
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 6372948129681556169L;
    @Id
    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态, 0正常1下架. 默认为在架的状态，防止后面新增商品的时候状态为null*/
    private Integer productStatus=ProductStatusEnum.UP.getCode() ;

    /** 类目编号. */
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
//    注意方法要用public
    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getBycode(productStatus,ProductStatusEnum.class);
    }
}
