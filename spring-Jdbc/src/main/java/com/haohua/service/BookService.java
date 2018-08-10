package com.haohua.service;    /*
 * @author  Administrator
 * @date 2018/7/18
 */

import com.haohua.dao.BookDao;
import com.haohua.pojo.Book;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.annotation.ExceptionProxy;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;
   @Transactional(rollbackFor = RuntimeException.class)
    public void insertBook(Book book){
        bookDao.insert(book);
    }
    public Book findBookById(Integer id) {
      Book book = bookDao.findById(id);
      return book;
    }
    public List<Book> findAll(){
        List<Book> bookList = bookDao.findAll();
        return bookList;
    }
    @Transactional(rollbackFor = Exception.class)
    public int  updateBookById(Integer id,Book book){
        book.setId(id);
        int res =  bookDao.updateByBook(book);
        return res;
    }
    @Transactional
    public int  updateBook(Book book){
        return bookDao.updateByBook(book);
    }

    public int sum(String column){
        int sum = bookDao.sumBook(column);
        return sum;
    }

    public Map<String,Object> findBookAsMap(Integer id){
      return bookDao.findBookAsMapById(id);
    }
   @Transactional(rollbackFor = RuntimeException.class)
    public void lendBook(Integer id){
        Book book = bookDao.findById(id);
        book.setNowNum(book.getNowNum()-1);
        bookDao.updateByBook(book);

        Book newBook = new Book();
        newBook .setIsbn("110");
        bookDao.insert(newBook);
    }

}
