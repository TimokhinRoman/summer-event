package ru.tmhn.summerevent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreDto {

    private TaskDto task;
    private TeamDto team;
    private Integer score;
}
