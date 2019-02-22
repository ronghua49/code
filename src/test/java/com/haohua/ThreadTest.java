package com.haohua;    /*
 * @author  Administrator
 * @date 2018/9/4
 */

import com.haohua.entity.Student;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ThreadTest {

    @Test
    public void getClassTest() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<Student> clazz1 = Student.class;
        Class<Student> clazz2 = (Class<Student>) Class.forName("com.haohua.entity.Student");

        System.out.println(clazz1.getName());
        Method setName = clazz1.getMethod("setName", String.class);

        for (Method method : clazz1.getMethods()) {
            System.out.println(method.getName());
        }
        System.out.println("----------------------------");

        for (Method method : clazz1.getDeclaredMethods()) {
            System.out.println(method.getName());
        }
    }

    @Test
    public void testJDBC() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql:///demo", "root", "root");
            String sql = "select * from product where id =?";
            PreparedStatement pstat = con.prepareStatement(sql);
            pstat.setInt(1, 1);
            ResultSet resultSet = pstat.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String price = resultSet.getString("pro_price");
                String name = resultSet.getString("pro_name");
                System.out.println("id-->" + id + "\t\t" + "name->" + name + "\t\t" + "price->" + price);
            }
            pstat.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void thread() throws InterruptedException {
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is t2");
            }
        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                    System.out.println("this is t1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
    public class T2 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "running....." + i);
            }
        }
    }


    @Test
    public void testInteger() {
        Student student1 = new Student();
        Student student2 = new Student();
        System.out.println(student1.hashCode());
        System.out.println(student1);
        System.out.println(student2.hashCode());
        System.out.println(student2);
        System.out.println(student1.equals(student2));
    }

    @Test
    public void testList() throws UnknownHostException {
        String hostAddress = InetAddress.getLocalHost().getHostAddress();

        System.out.println(hostAddress);
    }

    @Test
    public void testAddSql() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql:///test", "root", "root");
        for (int i = 0; i < 20000; i++) {
            String sql = "INSERT INTO `order`  (order_money) VALUE (?);";
            PreparedStatement pstat = con.prepareStatement(sql);
            BigDecimal bigDecimal = new BigDecimal(i);
            pstat.setBigDecimal(1, bigDecimal);
            pstat.execute();

        }
    }


    @Test
    public void testMap() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i + "", "");
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }
        Class<?> aClass = Class.forName("com.haohua.entity.Student");
        Class<Student> studentClass = Student.class;
        Student student = studentClass.newInstance();

        String name = Student.class.getName();

        System.out.println(name);
    }


    @Test
    public void testString() {
        String name = "rose";
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        System.out.println(name);

        String fileName = "D:\\he\\ha\\nihao.img";
        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
        System.out.println(fileName);
    }


    @Test
    public void testClass() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Student student = new Student();
        Class<Student> stuClazz = (Class<Student>) student.getClass();

        Method[] declaredMethods = stuClazz.getDeclaredMethods();
        Method[] methods = stuClazz.getMethods();
        Field[] fields = stuClazz.getFields();

        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("-------------");

        /*声明的方法*/
        for (Method method : declaredMethods) {
            System.out.println(method.getName());
        }
        System.out.println("----------------------");
        /*包括继承自父类的所有方法*/
        for (Method method2 : methods) {
            System.out.println(method2.getName());
        }
        /*传入方法名和方法的参数类型的class对象*/
        Method method = stuClazz.getMethod("setName", String.class);
        /*传入调用此方法的实例，和方法的参数*/
        method.invoke(student, "sdjfvhsiugvbfs");

        System.out.println(student.getName());

    }







}

