package com.zdk.service.card;

import com.zdk.pojo.Card;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/23 10:49
 */
public interface CardService {
    /**
     * 银行卡List
     * @param userId
     * @return
     */
    List<Card> getCardList(Integer userId);

    /**
     * 根据id获取卡信息
     * @param id
     * @return
     */
    Card getCardById(Integer id);
}
