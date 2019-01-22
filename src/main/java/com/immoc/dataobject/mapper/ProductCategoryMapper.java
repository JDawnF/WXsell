package com.immoc.dataobject.mapper;

import com.immoc.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ProductCategoryMapper {
    //    通过注解的方式，因为这里两个字段类型不同，所以用Object类型,用#获取参数
    @Insert("insert into product_category(category_name,category_type) values (#{category_name,jdbcType=VARCHAR}, #{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    //    通过对象插入数据，这里values后面的参数名字要跟插入时map中的键名一致
    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR}, #{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    // 通过商品类型查询商品类目,注意查询之后需要有@Results，相当于一种映射
    @Select("select * from product_category where category_type=#{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name=#{categoryName}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
//    这里可能返回多条数据，所以要用List类型
    List<ProductCategory> findByCategoryName(String categoryName);
//  更新数据，当多个参数时，要加上@Param
    @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,@Param("categoryType") Integer categoryType);
    @Update("update product_category set category_name=#{categoryName} where category_type=#{categoryType}")
    int updateByObject(ProductCategory productCategory);
    @Delete("delete from product_category where category_type=#{categoryType}")
    int deleteByCategoryType(Integer categoryType);
//    使用xml文件进行查询
    ProductCategory selectByCategoryType(Integer categoryType);
}
