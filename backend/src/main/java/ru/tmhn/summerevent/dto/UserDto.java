package ru.tmhn.summerevent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends EntityDto {

    private String name;
    private String email;
    private String password;
    private String passwordConfirmation;
}
