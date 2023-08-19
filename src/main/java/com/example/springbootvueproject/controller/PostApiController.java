package com.example.springbootvueproject.controller;

import com.example.springbootvueproject.config.Constant.SearchType;
import com.example.springbootvueproject.domain.Post;
import com.example.springbootvueproject.domain.dto.request.PostRequest;
import com.example.springbootvueproject.domain.dto.response.PostResponse;
import com.example.springbootvueproject.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Tag(name = "post api",description = "게시판 컨트롤러")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/board")
@AllArgsConstructor
public class PostApiController {
    private final PostService postService;

    @Operation(summary = "게시글 목록",description = "게시글 전체 목록을 페이징을 해서 게시글을 반환",method = "Get",tags = {"post api"})
    @ApiResponses({
            @ApiResponse(description = "정상응답",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(description = "게시글이 없는 경우",responseCode = "500",content = @Content())
    })
    @GetMapping("/list")
    public ResponseEntity<Page<PostResponse>>listResponseEntity(@Parameter(hidden = true) @PageableDefault(size = 5,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        Page<PostResponse>list = postService.postPagingResponse(pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(summary = "게시글 검색",description = "게시글 검색",method = "Get",tags = {"post api"})
    @ApiResponses({
            @ApiResponse(description = "정상응답",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(description = "게시글이 없는 경우",responseCode = "500",content = @Content())
    })
    @Parameters({
            @Parameter(name = "searchType",description = "검색 종류",in = ParameterIn.QUERY),
            @Parameter(name = "keyword",description = "검색어",in = ParameterIn.QUERY)
    })
    @GetMapping("/search")
    public ResponseEntity<Page<PostResponse>>searchResponseEntity(@RequestParam(value = "searchType") SearchType searchType,@RequestParam(value = "keywords") String keyword, @ApiIgnore @PageableDefault(size = 5,direction = Sort.Direction.DESC) Pageable pageable){
        Page<PostResponse>list = postService.postSearch(searchType,keyword,pageable);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(summary = "게시글 조회",description = "게시글을 조회",method = "Get",tags = {"post api"})
    @ApiResponses({
            @ApiResponse(description = "정상응답",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(description = "게시글이 없는 경우",responseCode = "500",content = @Content())
    })
    @GetMapping("/detail/{id}")
    public ResponseEntity<PostResponse>postResponseEntity(@Parameter(required = true,name = "id",description = "게시글 번호",in = ParameterIn.PATH) @PathVariable("id") Long id){
        PostResponse postResponse = postService.postDetail(id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @Operation(summary = "게시글 글작성",description = "게시글을 작성",method = "Post",tags = {"post api"})
    @ApiResponses({
            @ApiResponse(description = "정상응답",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Post.class))),
            @ApiResponse(description = "게시글이 없는 경우",responseCode = "500",content = @Content())
    })
    @PostMapping("/create")
    public ResponseEntity<Post>postCreatedEntity(@RequestBody PostRequest postRequest){
        Post post = postService.postCreate(postRequest);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @Operation(summary = "게시글 수정",description = "게시글을 수정",method = "Put",tags = {"post api"})
    @ApiResponses({
            @ApiResponse(description = "정상응답",responseCode = "200",content = @Content(mediaType = "application/json",schema = @Schema(implementation = Post.class))),
            @ApiResponse(description = "게시글이 없는 경우",responseCode = "500",content = @Content())
    })
    @PutMapping("/{id}")
    public ResponseEntity<Post>postUpdateEntity(@Parameter(required = true,name = "id",description = "게시글의 번호",in = ParameterIn.PATH) @PathVariable("id") Long id, @RequestBody PostRequest postRequest){
        Post post = postService.postUpdate(id,postRequest);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @Operation(summary = "게시글 삭제",description = "게시글을 삭제",method = "Delete",tags = {"post api"})
    @ApiResponses({
         @ApiResponse(description = "게시글이 없는 경우",responseCode = "200",content = @Content()),
         @ApiResponse(description = "게시글이 없는 경우",responseCode = "500",content = @Content())
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?>postDeleteEntity(@Parameter(required = true,name = "id",description = "게시글의 번호",in = ParameterIn.PATH) @PathVariable("id")Long id){
        postService.postDelete(id);
        return new ResponseEntity<>("Post Delete!",HttpStatus.OK);
    }
}
