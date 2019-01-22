package com.immoc.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @program: sell
 * @description: 类目表,类名ProductCategory对应数据库表名product_category，springboot-jpa定义的规则
 * 如果不是加了下划线，则需要加上@Table(name="tablename")
 * @author: baichen
 * @create: 2018-08-18 11:38
 **/
@Entity
@DynamicUpdate          //动态更新，即会自动更新时间
//包含生成getter，setter和toString方法，如果只是想getter则可以用@Getter,可以直接调用这些方法
@Data
public class ProductCategory {
    /** 类目id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //支持主键自动增长
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号. */
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
    // 构造方法，不用每次都new，比较简洁

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
