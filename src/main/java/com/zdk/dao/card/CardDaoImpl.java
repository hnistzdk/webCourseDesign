package com.zdk.dao.card;

import com.zdk.dao.BaseDao;
import com.zdk.dao.BaseDaoImpl;
import com.zdk.dao.user.UserDao;
import com.zdk.dao.user.UserDaoImpl;
import com.zdk.pojo.Card;
import com.zdk.pojo.User;
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
    public List<Card> getCardList(User user,String keywords) {
        String role = user.getRole();
        String selectSql;
        if (role.equals("管理员")){
            if (keywords != null){
                selectSql = "select * from card where number like ? or owner_name like ?";
                return baseDao.queryRows(Card.class, selectSql,"%"+keywords+"%","%"+keywords+"%");
            }
            selectSql = "select * from card";
            return baseDao.queryRows(Card.class, selectSql);
        }else{
            if (keywords != null){
                selectSql = "select * from card where owner_id = ? and number like ?";
                return baseDao.queryRows(Card.class, selectSql,user.getId(),"%"+keywords+"%");
            }
            selectSql = "select * from card where owner_id = ?";
            return baseDao.queryRows(Card.class, selectSql,user.getId());
        }
    }

    @Override
    public Card getCardById(Integer id) {
        String selectSql = "select * from card where id = ?";
        return baseDao.queryRow(Card.class, selectSql, id);
    }

    @Override
    public Integer recharge(Integer id, Integer money,Integer balance) {
        int newBalance = money+balance;
        String updateSql = "update card set balance = ? where id = ?";
        return baseDao.update(updateSql, new Object[]{newBalance,id});
    }

    @Override
    public Card getCardByNumber(String number) {
        String selectSql = "select * from card where number = ?";
        return baseDao.queryRow(Card.class, selectSql, number);
    }

    @Override
    public Integer addCard(String number,Integer ownerId, String ownerName) {
        Object[] params = new Object[]{number,ownerId,ownerName};
        String insertSql = "insert into card(number,owner_id,owner_name) values(?,?,?)";
        return baseDao.update(insertSql, params);
    }

    @Override
    public List<Card> getCardByOwnerName(String ownerName) {
        Object[] params = new Object[]{ownerName};
        String selectSql = "select * from card where owner_name = ?";
        return baseDao.queryRows(Card.class, selectSql, params);
    }

    @Override
    public Boolean transferAccounts(String origin, String target, Integer money) {
        return baseDao.transferAccounts(origin, target, money);
    }
}
