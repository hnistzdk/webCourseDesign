package com.zdk.controllers;

import com.zdk.pojo.User;
import com.zdk.service.card.CardService;
import com.zdk.service.card.CardServiceImpl;
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
        if (url.contains("/card/list")){
            String ip = IpKit.getIpAddressByRequest(req);
            String userSessionKey = ip+":userInfo";
            User user = (User) req.getServletContext().getAttribute(userSessionKey);
            req.setAttribute("cards",cardService.getCardList(user.getId()));
            req.getRequestDispatcher("/page/cardList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
