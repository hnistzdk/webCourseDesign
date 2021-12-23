package com.zdk.controllers;

import cn.hutool.core.convert.Convert;
import com.zdk.pojo.Card;
import com.zdk.pojo.User;
import com.zdk.service.card.CardService;
import com.zdk.service.card.CardServiceImpl;
import com.zdk.utils.ApiResponse;
import com.zdk.utils.IpKit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/23 10:52
 */
@WebServlet(name = "cardController",urlPatterns = {"/card/*"})
public class CardController extends BaseController{
    private final CardService cardService = CardServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        User user = getLoginUser(req);
        if (user != null){
            req.setAttribute("user", user);
        }
        //卡列表
        if (url.contains("/card/list")){
            req.setAttribute("cards",cardService.getCardList(user));
            req.getRequestDispatcher("/page/cardList.jsp").forward(req, resp);
        }
        //添加卡dialog
        else if (url.contains("/card/addCard")){
            req.getRequestDispatcher("/page/addCard.jsp").forward(req, resp);
        }
        //编辑卡(充值)dialog
        else if (url.contains("/card/editCard")){
            int i = url.lastIndexOf("/");
            String idStr = url.substring(i + 1);
            Integer id = Convert.toInt(idStr);
            Card card = cardService.getCardById(id);
            req.setAttribute("card", card);
            req.getRequestDispatcher("/page/updateCard.jsp").forward(req, resp);
        }
        //转账dialog
        else if (url.contains("/card/transferAccountsCard")){
            req.getRequestDispatcher("/page/transferAccounts.jsp").forward(req, resp);
        }
        //根据卡号获取卡
        else if (url.contains("/card/getCardByCardNum")){
            int i = url.lastIndexOf("/");
            String numberStr = url.substring(i + 1);
            Card card = cardService.getCardByNumber(numberStr);
            if (card != null){
                returnJson(resp, ApiResponse.fail("该卡号已存在"));
            }else{
                returnJson(resp, ApiResponse.success());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String url = req.getRequestURL().toString();
        //更新银行卡数据：充值
        if (url.contains("/card/updateCard")){
            Integer id = getInt(req, "id");
            String recharge = getStr(req, "recharge");
            String balance = getStr(req, "balance");
            //转换成浮点
            if (notOk(id) || notOk(recharge) || notOk(balance)){
                returnJson(resp, ApiResponse.fail("参数错误"));
                return;
            }
            int money = (int) (Float.parseFloat(recharge)) *100;
            int balanceMoney = (int) (Float.parseFloat(balance)) *100;
            returnJson(resp, cardService.recharge(id, money,balanceMoney));
        }
        //添加卡
        else if (url.contains("/card/add")){
            ApiResponse response = addCard(req);
            returnJson(resp, response);
        }
        //转账操作
        else if (url.contains("/card/transferAccounts")){
            returnJson(resp, transferAccounts(req));
        }
    }

    /**
     * 添加银行卡
     * @param req
     * @return
     */
    private ApiResponse addCard(HttpServletRequest req) {
        String number = getStr(req, "number");
        String ownerName = getStr(req, "ownerName");
        if (notOk(number) || notOk(ownerName)){
            return ApiResponse.fail("参数错误");
        }
        Card card = cardService.getCardByNumber(number);
        if (card != null){
            return ApiResponse.fail("该银行卡已存在,请重新输入");
        }
        return cardService.addCard(number, getLoginUser(req).getId(), ownerName);
    }

    /**
     * 转账
     * @param req
     * @return
     */
    private ApiResponse transferAccounts(HttpServletRequest req){
        String origin = getStr(req, "origin");
        String target = getStr(req, "target");
        String s = getStr(req, "money");
        if (notOk(origin) || notOk(target)){
            return ApiResponse.fail("参数错误");
        }
        int money = (int) (Float.parseFloat(s)) *100;
        Card originCard = cardService.getCardByNumber(origin);
        if (originCard == null){
            return ApiResponse.fail("源卡号不存在");
        }
        Card targetCard = cardService.getCardByNumber(target);
        if (targetCard == null){
            return ApiResponse.fail("目的卡号不存在");
        }
        if (originCard.getBalance()<money){
            return ApiResponse.fail("余额不足,无法完成转账");
        }
        return cardService.transferAccounts(origin, target, money);
    }
}
