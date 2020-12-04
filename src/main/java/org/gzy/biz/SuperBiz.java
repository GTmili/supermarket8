package org.gzy.biz;

import org.gzy.entity.Userinfo;

import java.util.List;

public interface SuperBiz {
    //登录
    Userinfo isLogin(Userinfo userInfo) throws Exception;

    //    //添加
    int add(Userinfo userInfo)throws Exception;
    //
//    //删除
    int del(String id) throws Exception;
    //
//    //修改
    int update(Userinfo userInfo) throws Exception;
    //
//    //根据ID查询
    Userinfo findById(String id)throws Exception;
    //    //查询所有(2)
    List<Userinfo> all(Object... param) throws Exception;
}
