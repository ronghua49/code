package com.haohua.util;    /*
 * @author  Administrator
 * @date 2018/7/9
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**获得sqlsession工厂的单例模式,和会话sqlsession
 * @author Administrator
 */
public class SqlSessionUtil {

    private static SqlSessionFactory SqlSessionFactory;
    static {
        Reader reader=null;
        try {
            //从主配置文件获得流,从流中获取会话工厂
           reader= Resources.getResourceAsReader("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
           SqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }
    public  static SqlSessionFactory getSqlSessionFactory() throws IOException {
        return SqlSessionFactory;
    }
    public static SqlSession getSqlSession(boolean commit){
        return SqlSessionFactory.openSession(commit);
    }
    public static SqlSession getSqlSession(){
        return SqlSessionFactory.openSession();
    }
}
