package com.wyh.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取短资源请求路径-资源标识符-> 如: /JavaWeb_FinalCase/brand/selectAll
        String uri = req.getRequestURI();
        //获取最后一段的字符串-> :selectAll subString方法获取的子字符串左闭右开 获取的是/selectAll +1去掉/
        String methodName = uri.substring(uri.lastIndexOf('/')+1);
        //利用 *反射机制* 执行对应的方法
        //首先要获取 对应的要执行方法的类(BrandServlet/UserServlet) 的字节码对象
        //利用 this的机制-> "谁(哪个类的对象)调用我(this)所在的方法 我(this)就是谁(哪个类的对象)"
        //用this指代的类对象 获取它的类的字节码对象
        Class<? extends BaseServlet> thisServletClass = this.getClass();
        //获取方法
        try {
            Method method = thisServletClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行方法 .invoke() 参数为: 1.谁(哪个对象)来执行 2.方法本身所需的参数
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
