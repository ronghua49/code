package com.haohua.dao;    /*
 * @author  Administrator
 * @date 2018/7/17
 */

import com.haohua.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(Book book){
        String sql = "insert into t_book (name,author,nowNum,isbn) values (?,?,?,?)";
        jdbcTemplate.update(sql,book.getName(),book.getAuthor(),book.getNowNum(),book.getIsbn());
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
    public int sumBook(){
        String sql ="select sum(total) from t_book";
        return  jdbcTemplate.queryForObject(sql,new SingleColumnRowMapper<BigDecimal>()).intValue();
    }


}
