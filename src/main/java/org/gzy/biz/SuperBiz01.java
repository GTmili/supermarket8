package org.gzy.biz;

import org.gzy.entity.Userinfo;
import org.gzy.util.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SuperBiz01
 * @Description: TODO
 * @Author: Gzy
 * @date: 2020/12/2 14:39
 * @Version: V1.0
 */
public class SuperBiz01 extends BaseDao {
//登录
    public int isLogin(Userinfo userinfo) {

        //sql语句
        String sql = "select count(1) from userinfo where username=? and password=?";
        //设置值
        Object[] obj = new Object[]{userinfo.getUserName(), userinfo.getPassWord()};

        ResultSet rs = null;
        //调用方法
        try {
            rs = this.queryQuery(sql, obj);

            //获得值
            rs.next();

            return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            this.closeAll(rs, this.ps, this.con);
        }
        return 0;
    }
    //查询全部
    public List<Userinfo> all() {
        List<Userinfo> list = new ArrayList<>();
        //sql语句
        String sql = "select * from userinfo";
        //设置值
        ResultSet rs = null;
        //调用方法
        try {
            rs = this.queryQuery(sql);
            while (rs.next()){
                Userinfo userinfo = new Userinfo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8));
                list.add(userinfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭
            this.closeAll(rs, this.ps, this.con);
        }
        return list;
    }
    //更新
    public int update(Userinfo userinfo) {

        //sql语句
        String sql = "update userinfo SET password = ? where userName = ?";
        //设置值
        Object[] obj = new Object[]{userinfo.getPassWord(),userinfo.getUserName()};

        try {
            return this.queryUpdate(sql, obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.closeAll(rs, this.ps, this.con);

        return 0;
    }

//    删
public int del(int id) {

    //sql语句
    String sql = "delete  from userinfo where userId=? ";
    //设置值
    Object[] obj = new Object[]{id};

    try {
        return this.queryUpdate(sql, obj);
    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        this.closeAll(rs, this.ps, this.con);
    }
    return 0;
}

//注册
//    增
//    this.userId = userId;
//        this.userName = userName;
//        this.passWord = passWord;
//        this.sex = sex;
//        this.borndate = borndate;
//        this.userTel = userTel;
//        this.userAddress = userAddress;
//        this.typeID = typeID;
public int add(Userinfo userinfo) {
    //sql语句
    String sql = "insert into userinfo values(?,?,?,?,?,?,?,?)";
    //设置值
    Object[] obj = new Object[]{userinfo.getUserId(),userinfo.getUserName(),userinfo.getPassWord(),userinfo.getSex(),userinfo.getBornDate(),userinfo.getUserTel(),userinfo.getUserAddress(),userinfo.getTypeID()};
    //调用方法
    try {
        return this.queryUpdate(sql, obj);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}



}
