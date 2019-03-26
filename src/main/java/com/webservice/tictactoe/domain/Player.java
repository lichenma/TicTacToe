package com.webservice.tictactoe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Player {

    public Player() {}  //Empty constructor

    public Player(String userName, String email, String password) {
        this.userName=userName;
        this.email=email;
        this.password=password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "user_name",unique = true,nullable = false)
    private String userName;

    @Column(name="email",unique = true,nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
}
