package com.project.BusinessFederationCommunity;

import com.project.BusinessFederationCommunity.member.Member;
import com.project.BusinessFederationCommunity.member.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StubData {
    private static Map<HttpMethod, Object> stubRequestBody;
    static {
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, new MemberDto.response(1L,"홍길동", Member.Sex.MALE,"길동공장", 1,2));
    }

    public static class MockMember {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static MemberDto.response getSingleResponseBody() {
            return new MemberDto.response(1L,
                    "홍길동",
                    Member.Sex.MALE,
                    "길동공장",
                    1,
                    2);
        }

        public static List<MemberDto.response> getMultiResponseBody() {
            return List.of(
                    new MemberDto.response(1L,
                            "홍길동",
                            Member.Sex.MALE,
                            "길동공장",
                            1,
                            2),
                    new MemberDto.response(2L,
                            "홍동길",
                            Member.Sex.MALE,
                            "동길식당",
                            2,
                            2),
                    new MemberDto.response(3L,
                            "홍민지",
                            Member.Sex.FEMALE,
                            "민지공장",
                            1,
                            1)
            );
        }

        public static List<MemberDto.response> getMultiTypeResponseBody() {
            return List.of(
                    new MemberDto.response(1L,
                            "홍길동",
                            Member.Sex.MALE,
                            "길동공장",
                            1,
                            2),
                    new MemberDto.response(3L,
                            "홍민지",
                            Member.Sex.FEMALE,
                            "민지공장",
                            1,
                            1)
            );
        }

        public static List<MemberDto.response> getMultiLocationResponseBody() {
            return List.of(
                    new MemberDto.response(1L,
                            "홍길동",
                            Member.Sex.MALE,
                            "길동공장",
                            1,
                            2),
                    new MemberDto.response(2L,
                            "홍동길",
                            Member.Sex.MALE,
                            "동길식당",
                            2,
                            2)
            );
        }

        public static Member getSingleResultMember() {
            Member member = new Member("hgd@gmail.com", "홍길동", "길동공장");
            member.setSex(Member.Sex.MALE);

            return member;
        }

        public static Page<Member> getMultiResultMember() {
            Member member1 = new Member("hgd@gmail.com", "홍길동", "길동공장");
            member1.setSex(Member.Sex.MALE);

            Member member2 = new Member("hdg@gmail.com", "홍동길", "동길식당");
            member2.setSex(Member.Sex.MALE);

            Member member3 = new Member("hmj@gmail.com", "홍민지", "민지공장");
            member3.setSex(Member.Sex.FEMALE);

            return new PageImpl<>(List.of(member1, member2, member3),
                    PageRequest.of(0, 10, Sort.by("memberId").descending()),
                    3);
        }

        public static Page<Member> getMultiTypeResultMember() {
            Member member1 = new Member("hgd@gmail.com", "홍길동", "길동공장");
            member1.setSex(Member.Sex.MALE);

            Member member2 = new Member("hmj@gmail.com", "홍민지", "민지공장");
            member2.setSex(Member.Sex.FEMALE);

            return new PageImpl<>(List.of(member1, member2),
                    PageRequest.of(0, 10, Sort.by("company_type").descending()),
                    2);
        }

        public static Page<Member> getMultiLocationResultMember() {
            Member member1 = new Member("hgd@gmail.com", "홍길동", "길동공장");
            member1.setSex(Member.Sex.MALE);

            Member member2 = new Member("hdg@gmail.com", "홍동길", "동길식당");
            member2.setSex(Member.Sex.FEMALE);

            return new PageImpl<>(List.of(member1, member2),
                    PageRequest.of(0, 10, Sort.by("company_location").descending()),
                    2);
        }

        public static Member getSingleResultMember(long memberId) {
            Member member = new Member("hgd@gmail.com", "홍길동", "길동공장");
            member.setMemberId(memberId);
            member.setSex(Member.Sex.MALE);
            return member;
        }
//        public static Member getSingleResultMember(long memberId, Map<String, String> updatedInfo) {
//            String name = Optional.ofNullable(updatedInfo.get("name")).orElse("홍길동");
//            String phone = Optional.ofNullable(updatedInfo.get("phone")).orElse("010-1111-1111");
//            Member member = new Member("hgd@gmail.com", name, phone);
//            member.setMemberId(memberId);
//            member.setSex(Member.Sex.MALE);
//            return member;
//        }
    }
}
