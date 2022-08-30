package com.wyh.mapper;

import com.wyh.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    @Select("select * from tb_brand")
    @ResultMap("BrandResultMap")
    List<Brand> selectAll();
    @Select("select * from tb_brand where id=#{id}")
    @ResultMap("BrandResultMap")
    Brand selectById(int id);

    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    @ResultMap("BrandResultMap")
    void add(Brand brand);

    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    void update(Brand brand);

    @Delete("delete from tb_brand where id = #{id}")
    void delete(int id);

    void deleteByIds(@Param("ids")int[] ids);

    @Select("select * from tb_brand limit #{begin}, #{size}")
    @ResultMap("BrandResultMap")
    List<Brand> selectByPage(@Param("begin")int begin, @Param("size")int size);

    @Select("select count(id) from tb_brand")
    int selectTotalCount();

    List<Brand> selectByPageAndCondition(@Param("begin")int begin, @Param("size")int size,@Param("brand")Brand brand);

    int selectTotalCountByCondition(Brand brand);
}
