package com.zdk.dao.card;

import com.zdk.pojo.Card;
import com.zdk.pojo.User;
import com.zdk.utils.MyPage;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/23 10:39
 */
public interface CardDao {
    /**
     * 银行卡列表
     * @param user
     * @param keywords
     * @return
     */
    List<Card> getCardList(User user,String keywords);

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
    Integer recharge(Integer id,Integer money,Integer balance);

    /**
     * 根据number获取卡信息
     * @param number
     * @return
     */
    Card getCardByNumber(String number);

    /**
     * 添加卡
     * @param number
     * @param ownerId
     * @param ownerName
     * @return
     */
    Integer addCard(String number,Integer ownerId, String ownerName);

    /**
     * 根据持卡人姓名查询
     * @param ownerName
     * @return
     */
    List<Card> getCardByOwnerName(String ownerName);

    /**
     * 转账
     * @param origin 源卡号
     * @param target 目的卡号
     * @param money 转账金额
     * @return
     */
    Boolean transferAccounts(String origin,String target,Integer money);
}
