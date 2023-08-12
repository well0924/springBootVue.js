package com.example.springbootvueproject.controller;

import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.domain.dto.request.MemberRequest;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import com.example.springbootvueproject.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/list")
    public ResponseEntity<List<MemberResponse>>memberList(){
        List<MemberResponse>memberResponseList = memberService.memberResponseList();
        return new ResponseEntity<>(memberResponseList, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<MemberResponse>memberDetail(@PathVariable("id") Long id){
        MemberResponse memberResponse = memberService.memberDetail(id);
        return new ResponseEntity<>(memberResponse,HttpStatus.OK);
    }

    @PostMapping("/created")
    public ResponseEntity<Member>memberCreate(@RequestBody MemberRequest memberRequest){
        Member member = memberService.memberCreate(memberRequest);
        return new ResponseEntity<>(member,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member>memberUpdate(@PathVariable("id") Long id, @RequestBody MemberRequest memberRequest){
        Member member = memberService.memberUpdate(id,memberRequest);
        return new ResponseEntity<>(member,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>memberDelete(@PathVariable("id")Long id){
        memberService.memberDelete(id);
        return new ResponseEntity<>("Delete O.k",HttpStatus.OK);
    }

}
