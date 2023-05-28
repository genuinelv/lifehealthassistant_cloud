package com.lzn.userservice.domain;


import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private String id;
    private String password;
    private String name;
    private String sex;
    private String photo;
    private Date birthday;



}
