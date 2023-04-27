package ru.tmhn.summerevent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Event extends Entity {

    private String name;
    private String description;
    private List<Task> tasks;

    public Event(int id) {
        super(id);
    }
}
