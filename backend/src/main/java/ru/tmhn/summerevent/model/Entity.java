package ru.tmhn.summerevent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Entity {

    private int id;

    public Entity(int id) {
        this.id = id;
    }
}
