package com.wyh.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.wyh.pojo.Brand;
import com.wyh.service.BrandService;
import com.wyh.service.impl.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

//@WebServlet("/addServlet")
public class addServlet extends HttpServlet {
    private final BrandService service = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //提交过来的数据 是Vue的模型-是一个brand对象模型 是JSON数据格式
        String jsonStr = request.getReader().readLine();
        Brand brand = JSON.parseObject(jsonStr, Brand.class);

        service.add(brand);

        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
