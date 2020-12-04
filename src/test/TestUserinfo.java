import org.gzy.biz.SuperBiz;
import org.gzy.biz.impl.SuperBizImpl;
import org.gzy.entity.Userinfo;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName: TestUserinfo
 * @Description: TODO
 * @Author: Gzy
 * @date: 2020/12/3 16:00
 * @Version: V1.0
 */
public class TestUserinfo {
    @Test
    public void log() {
        SuperBiz superBiz = new SuperBizImpl();
        Userinfo userinfo = new Userinfo("孙主管","pass123222");
        try {
            Userinfo userinfo1 = superBiz.isLogin(userinfo);
            System.out.println(userinfo1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void del(){
        SuperBiz superBiz = new SuperBizImpl();
//        Userinfo userinfo = new Userinfo("孙主管","pass123222");
        try {
            int userinfo11 = superBiz.del("A3333");
            System.out.println(userinfo11);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void find(){
        SuperBiz superBiz = new SuperBizImpl();
        Object [] objects = {"主"};
        try {
            List<Userinfo> list = superBiz.all(objects);
            for (Userinfo us:list){
                System.out.println(us);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
