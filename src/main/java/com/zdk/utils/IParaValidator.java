package com.zdk.utils;

import cn.hutool.core.date.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/14 9:39
 */
public interface IParaValidator {
    /**
     * 判断Integer参数有效性
     * @param param
     * @return
     */
    boolean isOk(Integer param);

    /**
     * 判断Integer参数是否无效
     * @param param
     * @return
     */
    boolean notOk(Integer param);
    /**
     * 判断List参数有效性
     * @param param
     * @return
     */
    boolean isOk(List<?> param);
    /**
     * 判断List参数是否无效
     * @param param
     * @return
     */
    boolean notOk(List<?> param);
    /**
     * 判断String参数是否有效
     * @param param
     * @return
     */
    boolean isOk(String param);
    /**
     * 判断String参数无效
     * @param param
     * @return
     */
    boolean notOk(String param);
    /**
     * 判断Double参数是否有效
     * @param param
     * @return
     */
    boolean isOk(Double param);
    /**
     * 判断Double参数无效
     * @param param
     * @return
     */
    boolean notOk(Double param);
    /**
     * 判断Float参数是否有效
     * @param param
     * @return
     */
    boolean isOk(Float param);
    /**
     * 判断Float参数无效
     * @param param
     * @return
     */
    boolean notOk(Float param);

    /**
     * 判断Long参数是否有效
     * @param param
     * @return
     */
    boolean isOk(Long param);
    /**
     * 判断Long参数无效
     * @param param
     * @return
     */
    boolean notOk(Long param);


    /**
     * 判断BigDecimal参数是否有效
     * @param param
     * @return
     */
    boolean isOk(BigDecimal param);
    /**
     * 判断BigDecimal参数无效
     * @param param
     * @return
     */
    boolean notOk(BigDecimal param);

    /**
     * 判断Object[]数组类型数据是否正确
     * @param param
     * @return
     */
    boolean isOk(Object[] param);
    /**
     * 判断Serializable[]数组类型数据是否正确
     * @param param
     * @return
     */
    boolean isOk(Serializable[] param);
    /**
     * 判断Object[]数组类型数据不正确
     * @param param
     * @return
     */
    boolean notOk(Object[] param);
    /**
     * 判断Serializable[]数组类型数据不正确
     * @param param
     * @return
     */
    boolean notOk(Serializable[] param);
    /**
     * 判断Integer[]数组类型数据不正确
     * @param param
     * @return
     */
    boolean notOk(Integer[] param);
    /**
     * 判断Date类型数据是否正确
     * @param param
     * @return
     */
    boolean isOk(Date param);
    /**
     * 判断DateTime类型数据是否正确
     * @param param
     * @return
     */
    boolean isOk(DateTime param);

    /**
     * 判断Date类型数据不正确
     * @param param
     * @return
     */
    boolean notOk(Date param);

    /**
     * 判断DateTime类型数据不正确
     * @param param
     * @return
     */
    boolean notOk(DateTime param);
    /**
     * 判断Boolean类型数据不正确
     * @param param
     * @return
     */
    boolean notOk(Boolean param);
    /**
     * 判断Boolean类型数据是否正确
     * @param param
     * @return
     */
    boolean isOk(Boolean param);
    /**
     * 判断Map类型数据不正确
     * @param param
     * @return
     */
    boolean notOk(Map<?,?> param);
    /**
     * 判断Map类型数据是否正确
     * @param param
     * @return
     */
    boolean isOk(Map<?,?> param);
    /**
     * 判断Set类型数据不正确
     * @param param
     * @return
     */
    boolean notOk(Set<?> param);
    /**
     * 判断Set类型数据是否正确
     * @param param
     * @return
     */
    boolean isOk(Set<?> param);
    /**
     * 判断byte[]类型数据不正确
     * @param param
     * @return
     */
    boolean notOk(byte[] param);
    /**
     * 判断byte[]类型数据是否正确
     * @param param
     * @return
     */
    boolean isOk(byte[] param);
    /**
     * 判断参数数据不正确
     * @param param
     * @return
     */
    boolean notOk(Object param);
    /**
     * 判断参数数据是否正确
     * @param param
     * @return
     */
    boolean isOk(Object param);
}
