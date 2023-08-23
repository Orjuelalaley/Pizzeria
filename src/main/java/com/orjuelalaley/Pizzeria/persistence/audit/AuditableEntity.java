package com.orjuelalaley.Pizzeria.persistence.audit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 *A base class for auditable entities, providing fields for creation and modification timestamps.
 */
@MappedSuperclass
public class AuditableEntity {
    /**
     * The timestamp when the entity was created.
     */
    @CreatedDate
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd hh:mma")
    @Column(name = "created_date", updatable = false, columnDefinition="DATETIME")
    private LocalDateTime createdDate;

    /**
     * The timestamp when the entity was last modified.
     */
    @LastModifiedDate
    @JsonIgnore
    @Column(name = "modified_date", columnDefinition="DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mma")
    private LocalDateTime modifiedDate;
}
