package springMybatisMapperTest;    /*
 * @author  Administrator
 * @date 2018/7/19
 */

import com.haohua.entity.Book;
import com.haohua.mapper.BookMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MapperTestCase extends BaseTestCase{
    @Autowired
    private BookMapper bookMapper;
    @Test
    public void findBookById(){
        Book book = bookMapper.selectByPrimaryKey(4);
        System.out.println(book);
    }
    @Test
    public void insertBook(){
        Book book = new Book();
        book.setAuthor("释迦摩尼");
        book.setName("楞严咒");
        book.setIsbn("92384");
        book.setTotal(100);
        book.setNowNum(100);
        bookMapper.insert(book);
        int id =book.getId();
        System.out.println(id);
    }
    @Test
    public void deleteBook(){
        int res =bookMapper.deleteByPrimaryKey(47);
        Assert.assertEquals(1,res);
    }


}
