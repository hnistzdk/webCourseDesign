package com.zdk.service.card;

import com.zdk.dao.card.CardDao;
import com.zdk.dao.card.CardDaoImpl;
import com.zdk.dao.user.UserDao;
import com.zdk.dao.user.UserDaoImpl;
import com.zdk.pojo.Card;
import com.zdk.pojo.User;
import com.zdk.service.BaseService;
import com.zdk.service.user.UserService;
import com.zdk.service.user.UserServiceImpl;
import com.zdk.utils.ApiResponse;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/23 10:50
 */
public class CardServiceImpl extends BaseService implements CardService {
    public CardServiceImpl() {
    }

    private static final CardService instance = new CardServiceImpl();
    public static CardService getInstance() {
        return instance;
    }

    private final CardDao cardDao = CardDaoImpl.getInstance();
    @Override
    public List<Card> getCardList(User user) {
        if (notOk(user)){
            return null;
        }
        return cardDao.getCardList(user);
    }

    @Override
    public Card getCardById(Integer id) {
        if (notOk(id)){
            return null;
        }
        return cardDao.getCardById(id);
    }

    @Override
    public ApiResponse recharge(Integer id, Integer money,Integer balance) {
        Integer recharge = cardDao.recharge(id, money,balance);
        return ApiResponse.result(recharge>0, "充值失败");
    }

    @Override
    public Card getCardByNumber(String number) {
        if (notOk(number)){
            return null;
        }
        return cardDao.getCardByNumber(number);
    }

    @Override
    public ApiResponse addCard(String number, Integer ownerId, String ownerName) {
        return ApiResponse.result(cardDao.addCard(number, ownerId, ownerName)>0,"添加银行卡失败");
    }

    @Override
    public ApiResponse transferAccounts(String origin, String target, Integer money) {
        return ApiResponse.result(cardDao.transferAccounts(origin, target, money), "转账失败");
    }

    @Override
    public List<Card> getCardByOwnerName(String ownerName) {
        if (notOk(ownerName)){
            return null;
        }
        return cardDao.getCardByOwnerName(ownerName);
    }
}
