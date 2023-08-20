package com.mo.jpastudy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Board {

    @Id
    @GeneratedValue
    private Long bno;

    private String title;
    private String content;
    private String writer;
    private Long viewCount;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date inDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date upDate;
}
