package com.lzn.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class Diet {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetime;

    private String food;
    private String dietname;
    private String picture1;
    private String picture2;
    private String picture3;

    public Diet() {
    }

    public Diet(Date datetime, String food, String dietname, String picture1, String picture2, String picture3) {
        this.datetime = datetime;
        this.food = food;
        this.dietname = dietname;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
    }
}
