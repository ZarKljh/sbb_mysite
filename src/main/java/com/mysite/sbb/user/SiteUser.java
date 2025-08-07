package com.mysite.sbb.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //unique를 걸어놨다.
    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
}
