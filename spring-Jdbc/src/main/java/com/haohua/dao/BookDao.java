package com.haohua.dao;    /*
 * @author  Administrator
 * @date 2018/7/17
 */

import com.haohua.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insert(Book book){
        String sql = "insert into t_book (name,author,total,nowNum,isbn) values (?,?,?,?,?)";
        return jdbcTemplate.update(sql,book.getName(),book.getAuthor(),book.getTotal(),book.getNowNum(),book.getIsbn());
    }
    public Book findById(Integer id) {
        String sql = "select * from t_book where id=?";
        return  jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Book.class),id);
    }

    public List<Book> findAll(){
        String sql ="select * from t_book ";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Book.class));
    }
    public int   updateByBook(Book book){
        String sql ="update t_book set name =?,author=?,total=?,nowNum=?,isbn=? where id=?";
        return jdbcTemplate.update(sql,book.getName(),book.getAuthor(),book.getTotal(),book.getNowNum(),book.getIsbn(),book.getId());
    }
    //sum函数返回值类型为BigDecimal
    public int sumBook(String colunm){
          String sql ="select sum("+colunm+") from t_book";
         int res =jdbcTemplate.queryForObject(sql,new SingleColumnRowMapper<BigDecimal>()).intValue();
        System.out.println(sql);
         return res;
    }

    public int deleteByName(String name){
        String sql ="delete from t_book where name like ?";
        name="%"+name+"%";
        return jdbcTemplate.update(sql,name);
    }

    public Map<String ,Object> findBookAsMapById (Integer id){
        String sql = "select * from t_book where id=?";
        //返回值为Map<String,object>类型
       return jdbcTemplate.queryForObject(sql,new ColumnMapRowMapper(),id);
    }


}
