package com.back.global.jpa.entity;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    @Override
    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || getClass() != o.getClass() ) return false;
        BaseEntity that = (BaseEntity) o;
        return id==that.id;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}
