package com.netsmartz.mis.base;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(columnDefinition = "boolean default true")
    private boolean isDeleted;
}
