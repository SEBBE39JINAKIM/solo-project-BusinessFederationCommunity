package com.project.BusinessFederationCommunity.member;

import com.project.BusinessFederationCommunity.dto.MultiResponseDto;
import com.project.BusinessFederationCommunity.dto.SingleResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;


/**
 * - DI 적용
 * - Mapstruct Mapper 적용
 * - @ExceptionHandler 적용
 */
@RestController
@RequestMapping("/v11/members")
@Validated
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }

//    @PostMapping  // 회원 가입
//    public ResponseEntity postMember(@Valid @RequestBody MemberDto.Post requestBody) {
//        Member member = mapper.memberPostToMember(requestBody);
//
//        Member createdMember = memberService.createMember(member);
//        MemberDto.response response = mapper.memberToMemberResponse(createdMember);
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(response),
//                HttpStatus.CREATED);
//    }
//
//    @PatchMapping("/{member-id}") // 회원 정보 수정
//    public ResponseEntity patchMember(
//            @PathVariable("member-id") @Positive long memberId,
//            @Valid @RequestBody MemberDto.Patch requestBody) {
//        requestBody.setMemberId(memberId);
//
//        Member member =
//                memberService.updateMember(mapper.memberPatchToMember(requestBody));
//
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(mapper.memberToMemberResponse(member)),
//                HttpStatus.OK);
//    }

    @GetMapping("/{member-id}") // 개인 회원 조회
    public ResponseEntity getMember(
            @PathVariable("member-id") @Positive long memberId) {
        Member member = memberService.findMember(memberId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.memberToMemberResponse(member))
                , HttpStatus.OK);
    }

    @GetMapping // 전체 회원 조회
    public ResponseEntity getMembers(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Member> pageMembers = memberService.findMembers(page - 1, size);
        List<Member> members = pageMembers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.membersToMemberResponses(members),
                        pageMembers),
                HttpStatus.OK);
    }

    @GetMapping("/{company_type}") // 특정 회원 조회
    public ResponseEntity getTypeMembers( @PathVariable("company_type") @Positive int company_type,
                                          @Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Member> pageTypeMembers = memberService.findTypeMembers(company_type,page - 1, size);
        List<Member> typeMembers = pageTypeMembers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.typeMembersToMemberResponses(typeMembers),
                        pageTypeMembers),
                HttpStatus.OK);
    }

    @GetMapping("/{company_location}") // 특정 회원 조회
    public ResponseEntity getLocationMembers( @PathVariable("company_location") @Positive int company_location,
                                              @Positive @RequestParam int page,
                                             @Positive @RequestParam int size) {
        Page<Member> pageLocationMembers = memberService.findLocationMembers(company_location,page - 1, size);
        List<Member> locationMembers = pageLocationMembers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.locationMembersToMemberResponses(locationMembers),
                        pageLocationMembers),
                HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(
            @PathVariable("member-id") @Positive long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
