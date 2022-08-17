package com.project.BusinessFederationCommunity.member;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponseDto {
    private long memberId;
//    private String password;
    private String email;
    private String name;
    private String company_name;
    private int company_type;
    private int company_location;
    private Member.Sex sex;

    public String getMemberStatus() {
        return sex.getSex();
    }

}

