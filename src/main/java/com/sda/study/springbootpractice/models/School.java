package com.sda.study.springbootpractice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Tiina Raudla
 * @Date
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class School extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;
    private String address;
    private String phone;
    private boolean isActive;
}
