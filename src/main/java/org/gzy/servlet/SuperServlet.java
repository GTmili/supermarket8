package org.gzy.servlet;

import org.gzy.biz.SuperBiz;
import org.gzy.biz.SuperBiz01;
import org.gzy.biz.impl.SuperBizImpl;
import org.gzy.entity.Userinfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "SuperServlet", urlPatterns = "*.super")
public class SuperServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        //获取请求路径   /add.user
        String path = request.getServletPath();
        //截取 add
        String pathName = path.substring(1, path.lastIndexOf("."));
        //利用反射机制
        try {
            //pathName方法名; 后面2个是参数
            Method method = getClass().getDeclaredMethod(pathName, HttpServletRequest.class, HttpServletResponse.class);
            //调用
            method.invoke(this, request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }


        out.flush();
        out.close();
    }

    //登录
    protected void isLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("username");
        String userPass = request.getParameter("password");
        SuperBiz superBiz = new SuperBizImpl();
        Userinfo userinfo = new Userinfo(name, userPass);
        Userinfo userinfo1 = superBiz.isLogin(userinfo);
        if (userinfo1 !=null) {
            HttpSession session = request.getSession();
            //保存
            session.setAttribute("name", new Userinfo(name,userPass));
            session.setAttribute("userId",userinfo.getUserId());

            response.sendRedirect("welcome.jsp");
        } else {
            response.sendRedirect("login.html");
        }
        out.flush();
        out.close();
    }

    //修改密码
    protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int count = 0;
        SuperBiz superBiz = new SuperBizImpl();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");
        String userPass = request.getParameter("newPassword");
        HttpSession session = request.getSession();

        System.out.println(session.getAttribute("name"));
        System.out.println(session.getAttribute("userId"));

        Userinfo userinfo = new Userinfo((String) session.getAttribute("name"));

        count = superBiz.update(userinfo);
        System.out.println("count=>"+count);
        if (count > 0) {
            response.sendRedirect("userList.html");
        } else {
            response.sendRedirect("password.jsp");
        }

        out.flush();
        out.close();

    }
//    注册
protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Date date = null;
    response.setContentType("text/html;charset=utf-8");
    PrintWriter out = response.getWriter();
    int sexs = 0;
    String userId = request.getParameter("userId");
    String userName =request.getParameter("userName");
    String userpassword =request.getParameter("userpassword");
    String userRemi =request.getParameter("userRemi");
    String sex =request.getParameter("sex");

    String houseDate =request.getParameter("houseDate");

    String userphone =request.getParameter("userphone");
    String userAddress =request.getParameter("userAddress");
    String userlei =request.getParameter("userlei");
    System.out.println("userId=>"+userId);
    System.out.println("sex=>"+sex);
    System.out.println("userlei1=>"+userlei);

    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

    try {
         date = ft.parse(houseDate);
    } catch (ParseException e) {
        e.printStackTrace();
    }
//    this.userId = userId;
//    this.userName = userName;
//    this.passWord = passWord;
//    this.sex = sex;
//    this.borndate = borndate;
//    this.userTel = userTel;
//    this.userAddress = userAddress;
//    this.typeID = typeID;
    if (sex.equals("男")){
        sexs = 1;
    }
  out.println("2=>"+userlei);
    Userinfo userinfo = new Userinfo(userId,userName,userpassword,sexs,date,userphone,userAddress,userlei);
    SuperBiz01 superBiz=new SuperBiz01();
    int add = superBiz.add(userinfo);
    if (add>0){
        response.sendRedirect("userList.html");
    }else{
        response.sendRedirect("userAdd.html");
    }

    out.flush();
    out.close();

}
}
