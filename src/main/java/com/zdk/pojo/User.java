package com.zdk.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author zdk
 * @Date 2021/12/16 10:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer id;
    private String username;
    private String password;
    private String trueName;
    private String cardNum;
    private Integer balance;
    private String role;
}
