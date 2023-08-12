package com.example.springbootvueproject.config.Base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTime {
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdTime;
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedTime;
}
