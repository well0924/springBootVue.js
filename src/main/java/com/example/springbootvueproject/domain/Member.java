package com.example.springbootvueproject.domain;

import com.example.springbootvueproject.config.Base.BaseTime;
import com.example.springbootvueproject.domain.dto.request.MemberRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Member extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    private Integer userAge;
    private String userEmail;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(Long id, String userName,String password,Integer userAge, String userEmail,Role role,LocalDateTime createdTime,LocalDateTime updatedTime){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.role = role;
    }

    public void memberUpdate(MemberRequest member){
        this.userAge = member.getUserAge();
        this.password = member.getPassword();
        this.userName = member.getUserName();
        this.userEmail = member.getUserEmail();
    }

}
