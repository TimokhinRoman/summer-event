package ru.tmhn.summerevent.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Score extends Entity {

    private Task task;
    private Team team;
    private Integer score;
}
