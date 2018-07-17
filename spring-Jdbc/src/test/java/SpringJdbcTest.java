import com.haohua.dao.BookDao;
import com.haohua.pojo.Book;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SpringJdbcTest extends BaseTestCase {
        @Autowired
        private BookDao bookDao;
        @Test
        public void insertBook(){
            Book book = new Book();
            book.setAuthor("蒲松龄");
            book.setName("聊斋志异");
            book.setIsbn("2745");
            book.setNowNum(100);
          bookDao.insert(book);
        }
        @Test
        public void findBookById() {
            Book book= bookDao.findById(4);
            System.out.println(book);
        }
        @Test
        public void findAll(){
            List<Book> bookList = bookDao.findAll();
            System.out.println(bookList.size());
        }
        @Test
        public void updateBookById(){
            Book book = bookDao.findById(25);
            book.setTotal(100);
            int res =  bookDao.updateByBook(book);
            Assert.assertEquals(1,res);
        }
        @Test
        public void sumAllBooksTotal(){
            int sum = bookDao.sumBook();
            System.out.println(sum);
        }

        @Test
        @Transactional
        public void transactionTest(){
            Book book = bookDao.findById(25);
            book.setTotal(90);
            bookDao.updateByBook(book);
           book.setNowNum(book.getNowNum()/0);
           bookDao.updateByBook(book);
        }
}
