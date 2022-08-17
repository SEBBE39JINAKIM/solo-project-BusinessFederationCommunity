package com.project.BusinessFederationCommunity.restdocs;

import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.project.BusinessFederationCommunity.MemberControllerTestHelper;
import com.project.BusinessFederationCommunity.StubData;
import com.project.BusinessFederationCommunity.member.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.util.List;

import static com.project.BusinessFederationCommunity.ApiDocumentUtils.getRequestPreProcessor;
import static com.project.BusinessFederationCommunity.ApiDocumentUtils.getResponsePreProcessor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerRestDocsTest implements MemberControllerTestHelper {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @Autowired
    private Gson gson;

    @Test
    public void getMemberTest() throws Exception {
        // given
        long memberId = 1L;
        String content = gson.toJson(memberId);
        MemberDto.response response = StubData.MockMember.getSingleResponseBody();

        given(memberService.findMember(Mockito.anyInt())).willReturn(new Member());
        given(mapper.memberToMemberResponse(Mockito.any(Member.class))).willReturn(response);

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(getURI(), memberId));


        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value(memberId))
                .andDo(
                        document("get-member",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestParameters(parameterWithName("memberId").description("회원 식별자")
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                                fieldWithPath("sex").type(JsonFieldType.STRING).description("성별: MALE(남자) / FEMALE(여자)"),
                                                fieldWithPath("company_name").type(JsonFieldType.STRING).description("회사명"),
                                                fieldWithPath("company_type").type(JsonFieldType.NUMBER).description("업종"),
                                                fieldWithPath("company_location").type(JsonFieldType.NUMBER).description("지역")
                                        )
                                )
                        ));
    }

    @Test
    public void getMembersTest() throws Exception {
        // given
        String page = "1";
        String size = "10";

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);

        Page<Member> members = StubData.MockMember.getMultiResultMember();
        List<MemberDto.response> responses = StubData.MockMember.getMultiResponseBody();

        // Stubbing
        given(memberService.findMembers(Mockito.anyInt(), Mockito.anyInt())).willReturn(members);
        given(mapper.membersToMemberResponses(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(getUrl(), queryParams));

        // then
        MvcResult result =
                actions
                        .andExpect(status().isOk())
                        .andDo(
                                document(
                                        "get-members",
                                        getRequestPreProcessor(),
                                        getResponsePreProcessor(),
                                        requestParameters(
                                                getDefaultRequestParameterDescriptors()
                                        ),
                                        responseFields(
                                                getFullPageResponseDescriptors(
                                                        getDefaultMemberResponseDescriptors(DataResponseType.LIST))

                                        )
                                )
                        )
                        .andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
        assertThat(list.size(), is(3));
    }

    @Test
    public void getTypeMembersTest() throws Exception {
        // given
        String page = "1";
        String size = "10";
        int company_type = 1;

        MultiValueMap<Object, Object> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);
        queryParams.add("company_type", company_type);

        Page<Member> typeMembers = StubData.MockMember.getMultiTypeResultMember();
        List<MemberDto.response> responses = StubData.MockMember.getMultiResponseBody();

        // Stubbing
        given(memberService.findTypeMembers(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt())).willReturn(typeMembers);
        given(mapper.typeMembersToMemberResponses(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(getTypeURI(), queryParams));

        // then
        MvcResult result =
                actions
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.company_type").value(company_type))
                        .andDo(
                                document(
                                        "get-types",
                                        getRequestPreProcessor(),
                                        getResponsePreProcessor(),
                                        requestParameters(
                                                getDefaultRequestParameterDescriptors()
                                        ),
                                        responseFields(
                                                getFullPageResponseDescriptors(
                                                        getDefaultMemberResponseDescriptors(DataResponseType.LIST))

                                        )
                                )
                        )
                        .andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
        assertThat(list.size(), is(2));
    }

    @Test
    public void getLocationMembersTest() throws Exception {
        // given
        String page = "1";
        String size = "10";
        int company_location = 2;

        MultiValueMap<Object, Object> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("page", page);
        queryParams.add("size", size);
        queryParams.add("company_location", company_location);

        Page<Member> locationMembers = StubData.MockMember.getMultiLocationResultMember();
        List<MemberDto.response> responses = StubData.MockMember.getMultiResponseBody();

        // Stubbing
        given(memberService.findMembers(Mockito.anyInt(), Mockito.anyInt())).willReturn(locationMembers);
        given(mapper.locationMembersToMemberResponses(Mockito.anyList())).willReturn(responses);

        // when
        ResultActions actions = mockMvc.perform(getRequestBuilder(getLocationURI(), queryParams));

        // then
        MvcResult result =
                actions
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.company_location").value(company_location))
                        .andDo(
                                document(
                                        "get-locations",
                                        getRequestPreProcessor(),
                                        getResponsePreProcessor(),
                                        requestParameters(
                                                getDefaultRequestParameterDescriptors()
                                        ),
                                        responseFields(
                                                getFullPageResponseDescriptors(
                                                        getDefaultMemberResponseDescriptors(DataResponseType.LIST))

                                        )
                                )
                        )
                        .andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
        assertThat(list.size(), is(2));
    }

    @Test
    public void deleteMemberTest() throws Exception {
        // given
        long memberId = 1L;
        doNothing().when(memberService).deleteMember(Mockito.anyLong());

        // when
        mockMvc.perform(deleteRequestBuilder(getURI(), memberId))
                .andExpect(status().isNoContent())
                .andDo(
                        document(
                                "delete-member",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                pathParameters(
                                        getMemberRequestPathParameterDescriptor()
                                )
                        )
                );
        // then
    }
}
