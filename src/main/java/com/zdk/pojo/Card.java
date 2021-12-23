package com.zdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/23 10:37
 * 银行卡
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    private Integer id;
    private String number;
    private Integer balance;
    private Integer ownerId;
    private String ownerName;
}
