package ru.tmhn.summerevent.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends Entity {

    private String name;
    private String email;
    private String password;
    private boolean admin;
}
