package ru.tmhn.summerevent.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamDto extends EntityDto {

    private String name;
    private UserDto owner;
    private List<UserDto> users;
    private Boolean chooser;
}
