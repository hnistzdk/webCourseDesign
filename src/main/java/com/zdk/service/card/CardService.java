package com.zdk.service.card;

import com.zdk.pojo.Card;
import com.zdk.pojo.User;
import com.zdk.utils.ApiResponse;

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
    List<Card> getCardList(User user);

    /**
     * 根据id获取卡信息
     * @param id
     * @return
     */
    Card getCardById(Integer id);

    /**
     * 充值
     * @param id 卡id
     * @param money 充值金额
     * @param balance 余额
     * @return
     */
    ApiResponse recharge(Integer id, Integer money,Integer balance);

    /**
     * 根据number获取卡信息
     * @param number
     * @return
     */
    Card getCardByNumber(String number);

    /**
     * 根据持卡人姓名查询
     * @param ownerName
     * @return
     */
    List<Card> getCardByOwnerName(String ownerName);

    /**
     * 添加卡
     * @param number
     * @param ownerId
     * @param ownerName
     * @return
     */
    ApiResponse addCard(String number,Integer ownerId, String ownerName);

    /**
     * 转账特例 需要事务
     * @param origin
     * @param target
     * @param money
     * @return
     */
    ApiResponse transferAccounts(String origin,String target,Integer money);
}
