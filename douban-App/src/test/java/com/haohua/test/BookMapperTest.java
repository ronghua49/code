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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookMapperTest {
    Logger logger = LoggerFactory.getLogger(Mybatis.class);
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
    public void insertCard(){
    List<Card> cardList = new ArrayList<>();
    Card card = new Card("毛毛","s008");
    Card card2 = new Card("阿花","s009");
    cardList.add(card);
    cardList.add(card2);
    bookMapper.insertCard(cardList);
}

@Test
    public void findBookByParams(){
        Map<String,Object>  params = new HashMap<>();
        params.put("cardId","s005");
        params.put("author","思");
        bookMapper.findBykeys(params);
}


}
