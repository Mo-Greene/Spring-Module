package com.mo.jpastudy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Member {

    @Id
    @Column(name = "member_id")
    private Long id;
    private String password;
    private String name;
    private String email;

    @ToString.Exclude
    @OneToOne(mappedBy = "member")
    private Cart cart;
}
