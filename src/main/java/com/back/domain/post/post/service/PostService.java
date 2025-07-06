package com.back.domain.post.post.service;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.repository.PostRepository;
import com.back.domain.post.postComment.entity.PostComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // post 관련
    public long count(){
        return postRepository.count();
    }

    public Post write(String title, String content){
        Post post = new Post(title, content);
        return postRepository.save(post);
    }

    public Optional<Post> findById(int id){
        return postRepository.findById(id);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public void modify(Post post, String title, String content){
        post.modify(title, content);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }



    // postComment 관련
    public PostComment writeComment(Post post, String content){
        return post.addComment(content);
    }

    public void modifyComment(PostComment postComment, String content){
        postComment.modify(content);
    }

    public boolean deleteComment(Post post, PostComment postComment) {
        return post.deleteComment(postComment);
    }


    public void flush(){
        postRepository.flush();
    }
}
