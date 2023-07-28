package ru.tmhn.summerevent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Task extends Entity {

    private Event event;
    private TaskType type;
    private String name;
    private String description;
    private String answer;
    private int mapX;
    private int mapY;
    private boolean completed;
    private Task parentTask;
    private String image;

    public Task(int id) {
        super(id);
    }
}
