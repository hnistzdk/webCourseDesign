package com.zdk.dao.card;

import com.zdk.pojo.Card;
import com.zdk.utils.MyPage;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/23 10:39
 */
public interface CardDao {
    /**
     * 银行卡分页
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
