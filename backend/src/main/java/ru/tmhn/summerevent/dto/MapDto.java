package ru.tmhn.summerevent.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MapDto {

    private List<PointDto> points;
    private boolean canSelect;
    private boolean taskInProgress;
}
