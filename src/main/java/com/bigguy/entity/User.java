package com.bigguy.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
}
