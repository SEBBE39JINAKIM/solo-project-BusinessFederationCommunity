package com.project.BusinessFederationCommunity.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class MemberDto {
//    @Getter
//    @AllArgsConstructor
//    public static class Post {
//        @NotBlank
//        @Email
//        private String email;
//
//        @NotBlank(message = "이름은 공백이 아니어야 합니다.")
//        private String name;
//
//        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
//                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
//        private String phone;
//    }
//
//    @Getter
//    @AllArgsConstructor
//    public static class Patch {
//        private long memberId;
//
//        private String name;
//
//        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
//                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다")
//        private String phone;
//
//        private Member.Sex sex;
//
//
//        public void setMemberId(long memberId) {
//            this.memberId = memberId;
//        }
//    }

    @AllArgsConstructor
    @Getter
    public static class response {
        private long memberId;
//        private String password;
        private String name;
        private Member.Sex sex;
        private String company_name;
        private int company_type;
        private int company_location;

        public String getSex() {
            return sex.getSex();
        }
    }
}