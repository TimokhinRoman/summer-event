package ru.tmhn.summerevent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointDto {

    private int id;
    private int x;
    private int y;

    public static PointDto create(TaskDto task) {
        PointDto point = new PointDto();
        point.id = task.getId();
        point.x = task.getMapX();
        point.y = task.getMapY();
        return point;
    }
}
