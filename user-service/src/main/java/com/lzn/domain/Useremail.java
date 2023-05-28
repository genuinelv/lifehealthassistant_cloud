package com.lzn.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class Useremail {
    private String id;
    private String email;
    private int emailstate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date existdate;
    private String checkcode;

}
