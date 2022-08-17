package com.project.BusinessFederationCommunity.member;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-17T22:32:24+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDto.response memberToMemberResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        long memberId = 0L;
        String name = null;
        Member.Sex sex = null;
        String company_name = null;
        int company_type = 0;
        int company_location = 0;

        if ( member.getMemberId() != null ) {
            memberId = member.getMemberId();
        }
        name = member.getName();
        sex = member.getSex();
        company_name = member.getCompany_name();
        company_type = member.getCompany_type();
        company_location = member.getCompany_location();

        MemberDto.response response = new MemberDto.response( memberId, name, sex, company_name, company_type, company_location );

        return response;
    }

    @Override
    public List<MemberDto.response> membersToMemberResponses(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberDto.response> list = new ArrayList<MemberDto.response>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberResponse( member ) );
        }

        return list;
    }

    @Override
    public List<MemberDto.response> typeMembersToMemberResponses(List<Member> typeMembers) {
        if ( typeMembers == null ) {
            return null;
        }

        List<MemberDto.response> list = new ArrayList<MemberDto.response>( typeMembers.size() );
        for ( Member member : typeMembers ) {
            list.add( memberToMemberResponse( member ) );
        }

        return list;
    }

    @Override
    public List<MemberDto.response> locationMembersToMemberResponses(List<Member> locationMembers) {
        if ( locationMembers == null ) {
            return null;
        }

        List<MemberDto.response> list = new ArrayList<MemberDto.response>( locationMembers.size() );
        for ( Member member : locationMembers ) {
            list.add( memberToMemberResponse( member ) );
        }

        return list;
    }
}
