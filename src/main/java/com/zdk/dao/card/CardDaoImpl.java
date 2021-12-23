package com.zdk.dao.card;

import com.zdk.dao.BaseDao;
import com.zdk.dao.BaseDaoImpl;
import com.zdk.dao.user.UserDao;
import com.zdk.dao.user.UserDaoImpl;
import com.zdk.pojo.Card;
import com.zdk.utils.MyPage;

import java.util.List;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/23 10:40
 */
public class CardDaoImpl implements CardDao {
    private CardDaoImpl() {
    }

    private static final CardDao cardDao = new CardDaoImpl();
    public static CardDao getInstance(){
        return cardDao;
    }


    private final BaseDao baseDao = BaseDaoImpl.getInstance();

    @Override
    public List<Card> getCardList(Integer userId) {
        String selectSql = "select * from card where owner_id = ?";
        baseDao.queryRows(Card.class, selectSql, userId);
        return baseDao.queryRows(Card.class, selectSql, userId);
    }
}
