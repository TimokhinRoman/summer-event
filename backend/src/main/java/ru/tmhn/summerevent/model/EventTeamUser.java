package ru.tmhn.summerevent.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventTeamUser extends Entity {

    private Event event;
    private Team team;
    private User user;
}
