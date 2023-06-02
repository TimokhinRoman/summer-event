package ru.tmhn.summerevent.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team extends Entity {

    private String name;
    private User owner;
}
