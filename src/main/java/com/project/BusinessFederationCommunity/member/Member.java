package com.project.BusinessFederationCommunity.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(nullable = false, updatable = false, unique = true)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Sex sex = Sex.FEMALE;

    @Column(length = 100, nullable = false)
    private String company_name;

    @Column(length = 100, nullable = false)
    private int company_type;

    @Column(length = 100, nullable = false)
    private int company_location;

    public Member(String email) {
        this.email = email;
    }

    public Member(String email, String name, String company_name) {
        this.email = email;
        this.name = name;
        this.company_name = company_name;
    }

    public enum Sex {
        MALE("남자"),
        FEMALE("여자");
        @Getter
        private String sex;

        Sex(String sex) {
            this.sex = sex;
        }
    }
}