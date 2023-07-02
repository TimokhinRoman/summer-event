package ru.tmhn.summerevent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

    private EventDto event;
    private TeamDto team;
}
