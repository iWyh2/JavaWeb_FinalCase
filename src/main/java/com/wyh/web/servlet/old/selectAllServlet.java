package com.wyh.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.wyh.pojo.Brand;
import com.wyh.service.BrandService;
import com.wyh.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/selectAllServlet")
public class selectAllServlet extends HttpServlet {
    private final BrandService service = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service层去查询并获取数据
        List<Brand> brands = service.selectAll();
        //将获取的数据转为JSON格式
        String jsonString = JSON.toJSONString(brands);
        //打印一下jsonString 看是否查询成功
//        System.out.println(jsonString);
        //响应JSON数据到相应网页 防止乱码要设置响应格式
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
