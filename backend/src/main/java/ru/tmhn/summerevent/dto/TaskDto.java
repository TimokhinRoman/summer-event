package ru.tmhn.summerevent.dto;

import lombok.Getter;
import lombok.Setter;
import ru.tmhn.summerevent.model.TaskType;

@Getter
@Setter
public class TaskDto extends EntityDto {

    private int eventId;
    private TaskType type;
    private String name;
    private String description;
    private String answer;
}
