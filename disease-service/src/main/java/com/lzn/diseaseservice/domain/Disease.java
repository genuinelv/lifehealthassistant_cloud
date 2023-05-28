package com.lzn.diseaseservice.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Disease {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datestart;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateend;
    private String diseasename;
    private String symptom;
    private String sympic;
    private String medicine;
    private String medpic;
}
