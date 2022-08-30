package com.wyh.web.servlet;

import com.alibaba.fastjson.JSON;
import com.wyh.pojo.Brand;
import com.wyh.pojo.PageBean;
import com.wyh.service.BrandService;
import com.wyh.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private final BrandService service = new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service层去查询并获取数据
        List<Brand> brands = service.selectAll();
        //将获取的数据转为JSON格式
        String jsonString = JSON.toJSONString(brands);
        //打印一下jsonString 看是否查询成功
//        System.out.println(jsonString); ->成功了
        //响应JSON数据到相应网页 防止乱码要设置响应格式
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        Brand brand = service.selectById(Integer.parseInt(id));

        //转JSON对象并响应
        String jsonString = JSON.toJSONString(brand);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //提交过来的数据 是Vue的模型-是一个brand对象模型 是JSON数据格式
        String jsonStr = request.getReader().readLine();
        Brand brand = JSON.parseObject(jsonStr, Brand.class);

        service.add(brand);

        response.getWriter().write("success");
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonStr = request.getReader().readLine();
        Brand brand = JSON.parseObject(jsonStr, Brand.class);

        service.update(brand);

        response.getWriter().write("success");
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        service.delete(Integer.parseInt(id));

        response.getWriter().write("success");
    }
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jsonStr = request.getReader().readLine();
        int[] ids = JSON.parseObject(jsonStr, int[].class);

        service.deleteByIds(ids);

        response.getWriter().write("success");
    }
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //分页查询传过来的URL是 ...?currentPage=1&PageSize=5 所以是get请求
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("PageSize");

        PageBean<Brand> brandPageBean = service.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));

        String jsonString = JSON.toJSONString(brandPageBean);

        //转JSON有中文会乱码 要设置格式
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //分页条件查询传过来的URL是 ...?currentPage=1&PageSize=5 post请求后面带上了请求参数 同时又有请求体
        //读取请求参数
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("PageSize");
        //读取请求体
        String jsonStr = request.getReader().readLine();
        Brand brand = JSON.parseObject(jsonStr, Brand.class);

        PageBean<Brand> brandPageBean = service.selectByPageAndCondition(Integer.parseInt(currentPage), Integer.parseInt(pageSize), brand);

        String jsonString = JSON.toJSONString(brandPageBean);

        //转JSON有中文会乱码 要设置格式
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
