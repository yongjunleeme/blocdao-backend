package com.blocdao.project.entity.base;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime create_time;

    @LastModifiedDate
    private LocalDateTime update_time;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        create_time = now;
        update_time = now;
    }

    @PreUpdate
    public void preUpdate() {
        update_time = LocalDateTime.now();
    }
}
