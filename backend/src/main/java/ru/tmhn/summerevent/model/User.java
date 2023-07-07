package ru.tmhn.summerevent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User extends Entity {

    private String name;
    private String email;
    private String password;
    private boolean admin;

    public User(int id) {
        super(id);
    }
}
