package com.back.domain.post.postComment.controller;


import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import com.back.domain.post.postComment.dto.PostCommentDto;
import com.back.domain.post.postComment.entity.PostComment;
import com.back.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class ApiV1PostCommentController {
    private final PostService postService;


    // PostComment 다건조회
    @GetMapping
    @Transactional(readOnly = true)
    public List<PostCommentDto> getItems(@PathVariable int postId){
        Post post = postService.findById(postId).get();
        return post
                .getComments()
                .stream()
                .map(PostCommentDto::new)
                .toList();
    }

    // PostComment 단건조회
    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public PostCommentDto getItem(@PathVariable int postId, @PathVariable int id){
        Post post = postService.findById(postId).get();
        PostComment postComment = post.findCommentById(id).get();
        return new PostCommentDto(postComment);
    }

    // PostComment 삭제
    @GetMapping("/{id}/delete")
    @Transactional
    public RsData<void> delete(@PathVariable int postId, @PathVariable int id){
        Post post = postService.findById(postId).get();
        PostComment postComment = post.findCommentById(id).get();
        postService.deleteComment(post, postComment);
        return new RsData<>("200-1", "댓글이 삭제되었습니다.");
    }
}
