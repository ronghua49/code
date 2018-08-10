import com.haohua.dao.BookDao;
import com.haohua.pojo.Book;
import com.haohua.service.BookService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpringJdbcTest extends BaseTestCase {
    @Autowired
    private BookService bookService;
    @Test
    public void batchInsertBook(){
        Book book1 = new Book();
        book1.setName("截拳道");
        book1.setAuthor("李小龙");
        book1.setTotal(100);
        book1.setNowNum(50);
        book1.setIsbn("1234");

        Book book2 = new Book();
        book2.setName("spring");
        book2.setAuthor("joinHanson");
        book2.setTotal(30);
        book2.setNowNum(10);
        book2.setIsbn("4321");
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);
        for (Book book: bookList){
          bookService.insertBook(book);
        }
    }

    @Test
    public void findBookById(){
        Book book = bookService.findBookById(33);
        System.out.println(book);
    }
    @Test
    public void updateBook(){
        Book book = new Book();
        book.setAuthor("罗贯中");
        book.setNowNum(10);
        book.setTotal(200);
        book.setIsbn("789");
        book.setName("三国演义");
        int res =bookService.updateBookById(43,book);
        Assert.assertEquals(1,res);
    }
    @Test
    public void sum(){
        int sum = bookService.sum("total");
        System.out.println(sum);
    }
    @Test
    public void findBookAsMap(){
        Map<String,Object> maps = bookService.findBookAsMap(33);
        for(Map.Entry<String,Object> entry:maps.entrySet()){
            System.out.println(entry.getKey()+"-->"+entry.getValue());
        }
    }
    @Test
    public void transactionTest(){
        bookService.lendBook(43);
    }

}
