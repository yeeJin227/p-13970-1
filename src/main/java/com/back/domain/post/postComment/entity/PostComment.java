package com.back.domain.post.postComment.entity;


import com.back.domain.post.post.entity.Post;
import com.back.global.jpa.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PostComment extends BaseEntity {

    private String content;

    public void modify(String content){
        this.content = content;
    }

    @ManyToOne
    @JsonIgnore
    private Post post;

    public PostComment(Post post, String content){
        this.post = post;
        this.content = content;
    }

}
