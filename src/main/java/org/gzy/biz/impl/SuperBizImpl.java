package org.gzy.biz.impl;

import org.gzy.biz.SuperBiz;
import org.gzy.dao.SuperDao;
import org.gzy.dao.impl.SuperDaoImpl;
import org.gzy.entity.Userinfo;

import java.util.List;

/**
 * @ClassName: SuperBizImpl
 * @Description: TODO
 * @Author: Gzy
 * @date: 2020/12/3 15:50
 * @Version: V1.0
 */
public class SuperBizImpl implements SuperBiz {
    private SuperDao superDao = new SuperDaoImpl();
    @Override
    public Userinfo isLogin(Userinfo userInfo) throws Exception {
        return  superDao.isLogin(userInfo);

    }

    @Override
    public int add(Userinfo userInfo) throws Exception {
        return superDao.add(userInfo);
    }

    @Override
    public int del(String id) throws Exception {
        return superDao.del(id);
    }

    @Override
    public int update(Userinfo userInfo) throws Exception {
        return superDao.update(userInfo);
    }

    @Override
    public Userinfo findById(String id) throws Exception {
        return null;
    }

    @Override
    public List<Userinfo> all(Object... param) throws Exception {
        return superDao.all(param);
    }
}
