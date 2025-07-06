package com.back.domain.post.post.controller;

import com.back.domain.post.post.dto.PostDto;
import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import com.back.global.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class ApiV1PostController {
    private final PostService postService;

    // post 다건조회
    @GetMapping
    @Transactional(readOnly = true)
    public List<PostDto> getItems(){
        List<Post> items = postService.findAll();
        return items
                .stream()
                .map(PostDto::new)
                .toList();
    }

    // post 단건조회
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public PostDto getItem(@PathVariable int id){
        Post post = postService.findById(id).get();
        return new PostDto(post);
    }

    // post 삭제
    @DeleteMapping("/{id}")
    @Transactional
    public RsData<Void> delete(@PathVariable int id){
        Post post = postService.findById(id).get();
        postService.delete(post);
        return new RsData<>("200-1", "%d번 글이 삭제되었습니다.".formatted(id));
    }


    record PostWriteReqBody(
            @NotBlank
            @Size(min=2, max=100)
            String title,

            @NotBlank
            @Size(min=2, max=5000)
            String content
    ){

    }


    // post 작성
    @PostMapping
    @Transactional
    public RsData<PostDto> write(@Valid @RequestBody PostWriteReqBody form){
        Post post = postService.write(form.title, form.content);
        return new RsData<>("200-1", "%d번 글이 생성되었습니다.".formatted(post.getId()), new PostDto(post));
    }



























}
