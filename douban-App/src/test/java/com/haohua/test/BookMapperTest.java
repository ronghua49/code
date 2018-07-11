package com.haohua.test;    /*
 * @author  Administrator
 * @date 2018/7/10
 */

import com.haohua.entity.Book;
import com.haohua.entity.Card;
import com.haohua.mapper.BookMapper;
import com.haohua.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BookMapperTest {
    Logger logger = LoggerFactory.getLogger(BookMapperTest.class);
    SqlSession sqlsession;
    BookMapper bookMapper;
    @Before
    public void before(){
        sqlsession = SqlSessionUtil.getSqlSession();
        bookMapper=sqlsession.getMapper(BookMapper.class);
    }
    @After
    public  void after(){
        sqlsession.close();
    }
    @Test
    public void findBookByCardId(){
        List<Book> bookList = bookMapper.findByCardId(5);
        for(Book book :bookList){
        logger.debug("book:{}",book);
        }
    }

    @Test
    public void findLendBook(){
        List<Book> bookList = bookMapper.findLendBook();
        for(Book book: bookList){
            logger.debug("lendBook{}",book);
        }
    }
@Test
    public void insertCard(){
    List<Card> cardList = new ArrayList<>();
    Card card = new Card("毛毛","s007");
    Card card2 = new Card("阿花","s009");
    cardList.add(card);
    cardList.add(card2);
    bookMapper.insertCard(cardList);
    sqlsession.commit();
}

@Test
    public void findBookByParams(){
        Map<String,Object>  params = new HashMap<>();
        params.put("code","s005");
       params.put("author","思");
       List<Book> bookList = bookMapper.findBykeys(params);

       for(Book book : bookList){
           logger.debug("book{}",book);
       }
}@Test
    public void updateCard(){
        Card card = bookMapper.findCardById(10);

        card.setCode("s120");
        bookMapper.updateCard(card);
        sqlsession.commit();
    }

    @Test
    public void findByCardIds(){
        List<Integer> ids = Arrays.asList(1,3,5);
        List<Book> bookList = bookMapper.findByCardIds(ids);
        for(Book book :bookList){
        logger.debug("book:{}",book);
        }
    }



}
