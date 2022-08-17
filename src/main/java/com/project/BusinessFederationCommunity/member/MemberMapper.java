package com.project.BusinessFederationCommunity.member;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
//    Member memberPostToMember(MemberDto.Post requestBody);
//    Member memberPatchToMember(MemberDto.Patch requestBody);
    MemberDto.response memberToMemberResponse(Member member);
    List<MemberDto.response> membersToMemberResponses(List<Member> members);
    List<MemberDto.response> typeMembersToMemberResponses(List<Member> typeMembers);
    List<MemberDto.response> locationMembersToMemberResponses(List<Member> locationMembers);
}
