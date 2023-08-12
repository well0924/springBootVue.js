package com.example.springbootvueproject.controller;

import com.example.springbootvueproject.domain.Member;
import com.example.springbootvueproject.domain.dto.request.MemberRequest;
import com.example.springbootvueproject.domain.dto.response.MemberResponse;
import com.example.springbootvueproject.domain.dto.response.PostResponse;
import com.example.springbootvueproject.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "member api",description = "회원 api")
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @Operation(summary = "회원 목록",description = "회원 전체를 목록으로 반환",method = "Get",tags = {"member api"})
    @ApiResponses({
            @ApiResponse(description = "정상응답",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MemberResponse.class))),
            @ApiResponse(description = "회원이 없는 경우",responseCode = "500",content = @Content())
    })
    @GetMapping("/list")
    public ResponseEntity<List<MemberResponse>>memberList(){
        List<MemberResponse>memberResponseList = memberService.memberResponseList();
        return new ResponseEntity<>(memberResponseList, HttpStatus.OK);
    }

    @Operation(summary = "회원 조회",description = "회원 개별 조회",method = "Get",tags = {"member api"})
    @ApiResponses({
            @ApiResponse(description = "정상응답",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = MemberResponse.class))),
            @ApiResponse(description = "회원이 없는 경우",responseCode = "500",content = @Content())
    })
    @GetMapping("/detail/{id}")
    public ResponseEntity<MemberResponse>memberDetail(@Parameter(name = "id",description = "회원 번호",required = true,in = ParameterIn.PATH) @PathVariable("id") Long id){
        MemberResponse memberResponse = memberService.memberDetail(id);
        return new ResponseEntity<>(memberResponse,HttpStatus.OK);
    }

    @Operation(summary = "회원 가입",description = "회원을 가입하는 기능",method = "Post",tags = {"member api"})
    @ApiResponses({
            @ApiResponse(description = "정상응답",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Member.class))),
            @ApiResponse(description = "회원이 없는 경우",responseCode = "500",content = @Content())
    })
    @PostMapping("/created")
    public ResponseEntity<Member>memberCreate(@RequestBody MemberRequest memberRequest){
        Member member = memberService.memberCreate(memberRequest);
        return new ResponseEntity<>(member,HttpStatus.CREATED);
    }

    @Operation(summary = "회원 수정",description = "회원을 수정하는 기능",method = "Put",tags = {"member api"})
    @ApiResponses({
            @ApiResponse(description = "정상응답",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Member.class))),
            @ApiResponse(description = "회원이 없는 경우",responseCode = "500",content = @Content())
    })
    @PutMapping("/{id}")
    public ResponseEntity<Member>memberUpdate(@Parameter(name = "id",description = "회원 번호",required = true,in = ParameterIn.PATH) @PathVariable("id") Long id, @RequestBody MemberRequest memberRequest){
        Member member = memberService.memberUpdate(id,memberRequest);
        return new ResponseEntity<>(member,HttpStatus.OK);
    }

    @Operation(summary = "회원 삭제",description = "회원을 삭제하는 기능",method = "Delete",tags = {"member api"})
    @ApiResponses({
            @ApiResponse(description = "정상응답",responseCode = "200"),
            @ApiResponse(description = "회원이 없는 경우",responseCode = "500",content = @Content())
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?>memberDelete(@Parameter(name = "id",description = "회원 번호",required = true,in = ParameterIn.PATH) @PathVariable("id")Long id){
        memberService.memberDelete(id);
        return new ResponseEntity<>("Delete O.k",HttpStatus.OK);
    }

}
