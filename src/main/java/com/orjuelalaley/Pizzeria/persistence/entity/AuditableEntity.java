package com.orjuelalaley.Pizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public class AuditableEntity {
    @CreatedDate
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd hh:mma")
    @Column(name = "created_date", updatable = false, columnDefinition="DATETIME")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @JsonIgnore
    @Column(name = "modified_date", columnDefinition="DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mma")
    private LocalDateTime modifiedDate;
}
