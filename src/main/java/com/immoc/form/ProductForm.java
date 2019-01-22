package com.immoc.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: sell
 * @description: 存储前端表单中提交过来的字段
 * @author: baichen
 * @create: 2018-08-25 16:28
 **/
@Data
public class ProductForm {
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

    /** 类目编号. */
    private Integer categoryType;
}
