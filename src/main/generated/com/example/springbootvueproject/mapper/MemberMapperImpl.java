package com.example.springbootvueproject.mapper;

import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.domain.Member.MemberBuilder;
import com.example.springbootvueproject.domain.dto.request.MemberRequest;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import com.example.springbootvueproject.domain.dto.response.MemberResponse.MemberResponseBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-13T23:19:03+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.5 (Amazon.com Inc.)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberResponse entityToDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponseBuilder memberResponse = MemberResponse.builder();

        memberResponse.id( member.getId() );
        memberResponse.userName( member.getUserName() );
        memberResponse.password( member.getPassword() );
        memberResponse.userAge( member.getUserAge() );
        memberResponse.userEmail( member.getUserEmail() );
        memberResponse.createdTime( member.getCreatedTime() );
        memberResponse.updatedTime( member.getUpdatedTime() );

        return memberResponse.build();
    }

    @Override
    public Member dtoToEntity(MemberRequest memberRequest) {
        if ( memberRequest == null ) {
            return null;
        }

        MemberBuilder member = Member.builder();

        member.userName( memberRequest.getUserName() );
        member.password( memberRequest.getPassword() );
        member.userAge( memberRequest.getUserAge() );
        member.userEmail( memberRequest.getUserEmail() );

        return member.build();
    }
}
