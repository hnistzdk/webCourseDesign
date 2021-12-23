package com.zdk.controllers;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import com.zdk.pojo.User;
import com.zdk.utils.IParaValidator;
import com.zdk.utils.IpKit;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author zdk
 * @date 2021/12/12 15:49
 */
public class BaseController extends HttpServlet implements IParaValidator {
    /**
     * 判断Integer参数有效性
     *
     * @param param
     */
    @Override
    public boolean isOk(Integer param) {
        return param != null && param > 0;
    }

    /**
     * 判断Integer参数是否无效
     */
    @Override
    public boolean notOk(Integer param) {
        return param == null || param <= 0;
    }

    /**
     * 判断List参数有效性
     *
     * @param param
     */
    @Override
    public boolean isOk(List<?> param) {
        return param != null && param.size() > 0;
    }

    /**
     * 判断List参数是否无效
     *
     * @param param
     */
    @Override
    public boolean notOk(List<?> param) {
        return param == null || param.size() == 0;
    }

    /**
     * 判断String参数是否有效
     *
     * @param param
     */
    @Override
    public boolean isOk(String param) {
        return StringUtils.isNotBlank(param);
    }

    /**
     * 判断String参数无效
     *
     * @param param
     */
    @Override
    public boolean notOk(String param) {
        if (param != null) {
            int i = 0;

            for (int len = param.length(); i < len; ++i) {
                if (param.charAt(i) > ' ') {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * 判断Double参数是否有效
     *
     * @param param
     */
    @Override
    public boolean isOk(Double param) {
        return param != null && param > 0;
    }

    /**
     * 判断Double参数无效
     *
     * @param param
     */
    @Override
    public boolean notOk(Double param) {
        return param == null || param <= 0;
    }

    /**
     * 判断Float参数是否有效
     *
     * @param param
     */
    @Override
    public boolean isOk(Float param) {
        return param != null && param > 0;
    }

    /**
     * 判断Float参数无效
     *
     * @param param
     */
    @Override
    public boolean notOk(Float param) {
        return param == null || param <= 0;
    }

    /**
     * 判断Long参数是否有效
     *
     * @param param
     */
    @Override
    public boolean isOk(Long param) {
        return param != null && param > 0;
    }

    /**
     * 判断Long参数无效
     *
     * @param param
     */
    @Override
    public boolean notOk(Long param) {
        return param == null || param <= 0;
    }


    /**
     * 判断BigDecimal参数是否有效
     *
     * @param param
     */
    @Override
    public boolean isOk(BigDecimal param) {
        return param != null && param.doubleValue() > 0;
    }

    /**
     * 判断BigDecimal参数无效
     *
     * @param param
     */
    @Override
    public boolean notOk(BigDecimal param) {
        return param == null || param.doubleValue() <= 0;
    }

    /**
     * 判断Object[]数组类型数据是否正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean isOk(Object[] param) {
        return ArrayUtil.isNotEmpty(param);
    }

    /**
     * 判断Serializable[]数组类型数据是否正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean isOk(Serializable[] param) {
        return ArrayUtil.isNotEmpty(param);
    }

    /**
     * 判断Object[]数组类型数据不正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean notOk(Object[] param) {
        return ArrayUtil.isEmpty(param);
    }

    /**
     * 判断Serializable[]数组类型数据不正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean notOk(Serializable[] param) {
        return ArrayUtil.isEmpty(param);
    }

    /**
     * 判断Integer[]数组类型数据不正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean notOk(Integer[] param) {
        return ArrayUtil.isEmpty(param);
    }

    /**
     * 判断Date类型数据是否正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean isOk(Date param) {
        return param != null;
    }

    /**
     * 判断DateTime类型数据是否正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean isOk(DateTime param) {
        return param != null;
    }


    /**
     * 判断Date类型数据不正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean notOk(Date param) {
        return param == null;
    }

    /**
     * 判断DateTime类型数据不正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean notOk(DateTime param) {
        return param == null;
    }

    /**
     * 判断Boolean类型数据不正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean notOk(Boolean param) {
        return param == null;
    }

    /**
     * 判断Boolean类型数据是否正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean isOk(Boolean param) {
        return param != null;
    }

    /**
     * 判断Map类型数据不正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean notOk(Map<?, ?> param) {
        return param == null || param.isEmpty();
    }

    /**
     * 判断Map类型数据是否正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean isOk(Map<?, ?> param) {
        return param != null && !param.isEmpty();
    }

    /**
     * 判断Set类型数据不正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean notOk(Set<?> param) {
        return param == null || param.isEmpty();
    }

    /**
     * 判断Set类型数据是否正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean isOk(Set<?> param) {
        return param != null && !param.isEmpty();
    }

    /**
     * 判断byte[]类型数据不正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean notOk(byte[] param) {
        return param == null || param.length == 0;
    }

    /**
     * 判断byte[]类型数据是否正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean isOk(byte[] param) {
        return param != null && param.length > 0;
    }

    /**
     * 判断参数数据不正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean notOk(Object param) {
        if (param == null) {
            return true;
        }
        String name = param.getClass().getSimpleName();
        boolean success = false;
        switch (name) {
            case "Integer":
                success = notOk((Integer) param);
                break;
            case "String":
                success = notOk(param.toString());
                break;
            case "Long":
                success = notOk((Long) param);
                break;
            case "Double":
                success = notOk((Double) param);
                break;
            case "Float":
                success = notOk((Float) param);
                break;
            case "BigDecimal":
                success = notOk((BigDecimal) param);
                break;
            case "List":
                success = notOk((List<?>) param);
                break;
            case "ArrayList":
                success = notOk((ArrayList<?>) param);
                break;
            case "Boolean":
                success = notOk((Boolean) param);
                break;
            case "Date":
                success = notOk((Date) param);
                break;
            case "Map":
                success = notOk((Map<?, ?>) param);
                break;
            case "HashMap":
                success = notOk((HashMap<?, ?>) param);
                break;
            case "Set":
                success = notOk((Set<?>) param);
                break;
            case "HashSet":
                success = notOk((HashSet<?>) param);
                break;
            case "byte[]":
                success = notOk((byte[]) param);
                break;
            default:
        }
        return success;
    }

    /**
     * 判断参数数据是否正确
     *
     * @param param
     * @return
     */
    @Override
    public boolean isOk(Object param) {
        if (param == null) {
            return false;
        }
        String name = param.getClass().getSimpleName();
        boolean success = false;
        switch (name) {
            case "Integer":
                success = isOk((Integer) param);
                break;
            case "String":
                success = isOk(param.toString());
                break;
            case "Long":
                success = isOk((Long) param);
                break;
            case "Double":
                success = isOk((Double) param);
                break;
            case "Float":
                success = isOk((Float) param);
                break;
            case "BigDecimal":
                success = isOk((BigDecimal) param);
                break;
            case "List":
                success = isOk((List<?>) param);
                break;
            case "ArrayList":
                success = isOk((ArrayList<?>) param);
                break;
            case "Boolean":
                success = isOk((Boolean) param);
                break;
            case "Date":
                success = isOk((Date) param);
                break;
            case "DateTime":
                success = isOk((DateTime) param);
                break;
            case "Map":
                success = isOk((Map<?, ?>) param);
                break;
            case "HashMap":
                success = isOk((HashMap<?, ?>) param);
                break;
            case "Set":
                success = isOk((Set<?>) param);
                break;
            case "HashSet":
                success = isOk((HashSet<?>) param);
                break;
            default:
        }
        return success;
    }

    public String getStr(HttpServletRequest request,String paramName){
        return Convert.toStr(request.getParameter(paramName));
    }
    public Integer getInt(HttpServletRequest request,String paramName){
        return Convert.toInt(request.getParameter(paramName));
    }

    /**
     * 返回JSON数据
     * @param response
     * @param object
     * @throws IOException
     */
    public void returnJson(HttpServletResponse response, Object object) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONUtil.parseObj(object).toString());
    }

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    public User getLoginUser(HttpServletRequest request){
        String ip = IpKit.getIpAddressByRequest(request);
        String userSessionKey = ip+":userInfo";
        Object attribute = request.getSession().getAttribute(userSessionKey);
        return Convert.convert(User.class, attribute);
    }
}
