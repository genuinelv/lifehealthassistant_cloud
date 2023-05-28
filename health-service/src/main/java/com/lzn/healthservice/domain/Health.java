package com.lzn.healthservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Health {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datetimehealth;

    private int age;
    private double height;
    private double weight;
    private int bloodpressurehigh;
    private int bloodpressurelow;
    private double bloodsugar;
}
