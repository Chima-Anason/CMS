package com.chima.CMS.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String cell;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = false)
    private Date created_At;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated_At;



    @PrePersist
    protected void onCreated(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdated(){
        this.updated_At = new Date();
    }
}
