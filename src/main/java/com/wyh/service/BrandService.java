package com.wyh.service;

import com.wyh.pojo.Brand;
import com.wyh.pojo.PageBean;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();
    void add(Brand brand);
    void update(Brand brand);
    Brand selectById(int id);
    void delete(int id);
    void deleteByIds(int[] ids);
    PageBean<Brand> selectByPage(int currentPage, int PageSize);
    int selectTotalCount();
    PageBean<Brand> selectByPageAndCondition(int currentPage, int PageSize,Brand brand);
}
