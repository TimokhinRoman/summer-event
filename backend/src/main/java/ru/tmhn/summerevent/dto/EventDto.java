package ru.tmhn.summerevent.dto;

import lombok.Getter;
import lombok.Setter;
import ru.tmhn.summerevent.model.EventStatus;

import java.util.List;

@Getter
@Setter
public class EventDto extends EntityDto {

    private String name;
    private String description;
    private EventStatus status;
    private boolean active;
    private List<TaskDto> tasks;
}
