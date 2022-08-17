//package com.project.BusinessFederationCommunity.member;
//
//import lombok.Getter;
//
//import javax.validation.constraints.Pattern;
//
//@Getter
//public class MemberPatchDto {
//    private long memberId;
//
//    private String name;
//
//    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
//            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다")
//    private String phone;
//
//    private Member.Sex sex;
//
//
//    public void setMemberId(long memberId) {
//        this.memberId = memberId;
//    }
//}
