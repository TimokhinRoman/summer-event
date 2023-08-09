package ru.tmhn.summerevent.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class EventScoreDto {

    private List<TaskDto> tasks;
    private List<TeamDto> teams;
    private Map<Integer, Map<Integer, Integer>> scores;
}
