package ru.tmhn.summerevent.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventDto extends EntityDto {

    private String name;
    private String description;
    private List<TaskDto> tasks;
}
