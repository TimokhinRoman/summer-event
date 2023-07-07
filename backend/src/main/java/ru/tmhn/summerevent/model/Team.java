package ru.tmhn.summerevent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Team extends Entity {

    private String name;
    private User captain;

    public Team(int id) {
        super(id);
    }
}
