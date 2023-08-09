package ru.tmhn.summerevent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Team extends Entity {

    private String name;
    private User captain;
    private List<Score> scores;

    public Team(int id) {
        super(id);
    }
}
