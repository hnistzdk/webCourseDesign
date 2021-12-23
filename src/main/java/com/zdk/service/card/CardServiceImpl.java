package com.zdk.service.card;

import com.zdk.dao.card.CardDao;
import com.zdk.dao.card.CardDaoImpl;
import com.zdk.dao.user.UserDao;
import com.zdk.dao.user.UserDaoImpl;
import com.zdk.pojo.Card;
import com.zdk.service.BaseService;
import com.zdk.service.user.UserService;
import com.zdk.service.user.UserServiceImpl;

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
    public List<Card> getCardList(Integer userId) {
        if (notOk(userId)){
            return null;
        }
        return cardDao.getCardList(userId);
    }
}
