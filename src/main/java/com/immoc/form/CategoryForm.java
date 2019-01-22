package com.immoc.form;

import lombok.Data;

/**
 * @program: sell
 * @description: 接受前端传过来的表单的值，类目表
 * @author: baichen
 * @create: 2018-08-25 17:23
 **/
@Data
public class CategoryForm {
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
}
