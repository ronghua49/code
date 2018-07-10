package com.haohua.mapper;    /*
 * @author  Administrator
 * @date 2018/7/10
 */

import com.haohua.entity.Book;
import com.haohua.entity.Card;

import java.util.List;
import java.util.Map;

public interface BookMapper {

    List<Book> findByCardId(Integer cardid);
    void insertCard(List<Card> cardList);
    List<Book> findBykeys(Map<String,Object> map);

}
