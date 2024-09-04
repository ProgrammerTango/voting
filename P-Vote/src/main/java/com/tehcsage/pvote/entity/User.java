package com.tehcsage.pvote.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "test_user") // Adjust the table name if necessary
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private int id;
    
    private String username;

    private String password;
    
    private Timestamp last_login;
    
    private char is_active;
    
    private String name;
}

