package com.wyh.service.impl;

import com.wyh.mapper.BrandMapper;
import com.wyh.pojo.Brand;
import com.wyh.pojo.PageBean;
import com.wyh.service.BrandService;
import com.wyh.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
    @Override
    public List<Brand> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();
        sqlSession.close();
        return brands;
    }
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.add(brand);
        sqlSession.close();
    }
    public void update(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.update(brand);
        sqlSession.close();
    }
    public Brand selectById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = brandMapper.selectById(id);
        sqlSession.close();
        return brand;
    }
    public void delete(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.delete(id);
        sqlSession.close();
    }
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        brandMapper.deleteByIds(ids);
        sqlSession.close();
    }
    public PageBean<Brand> selectByPage(int currentPage, int PageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage - 1) * PageSize;
        List<Brand> brands = brandMapper.selectByPage(begin, PageSize);
        int totalCount = brandMapper.selectTotalCount();

        PageBean<Brand> brandPageBean = new PageBean<>();
        brandPageBean.setRows(brands);
        brandPageBean.setTotalCount(totalCount);

        sqlSession.close();
        return brandPageBean;
    }
    public int selectTotalCount() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        int totalCount = brandMapper.selectTotalCount();
        sqlSession.close();
        return totalCount;
    }
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int PageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        int begin = (currentPage - 1) * PageSize;
        List<Brand> brands = brandMapper.selectByPageAndCondition(begin, PageSize, brand);
        int totalCount = brandMapper.selectTotalCountByCondition(brand);

        PageBean<Brand> brandPageBean = new PageBean<>();
        brandPageBean.setRows(brands);
        brandPageBean.setTotalCount(totalCount);

        sqlSession.close();
        return brandPageBean;
    }
}
