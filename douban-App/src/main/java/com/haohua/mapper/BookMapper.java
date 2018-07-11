package com.haohua.mapper;    /*
 * @author  Administrator
 * @date 2018/7/10
 */

import com.haohua.entity.Book;
import com.haohua.entity.Card;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    void save(Book book);
     List<Book> findByCardId(Integer cardid);
     List<Book> findLendBook();
    void insertCard(@Param("cardList") List<Card> cardList);
    List<Book> findBykeys(Map<String,Object> map);
    void updateCard(Card card);
    Card findCardById(Integer cardid);
    List<Book> findByCardIds(@Param("cardIdList") List<Integer> cardIdList);

}
