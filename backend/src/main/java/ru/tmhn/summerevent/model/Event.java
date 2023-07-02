package ru.tmhn.summerevent.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Event extends Entity {

    private String name;
    private String description;
    private boolean active;
    private EventStatus status;
    private List<Task> tasks;
    private List<Team> teams;
    private List<User> users;

    public Event(int id) {
        super(id);
    }

    public List<Task> getTasks() {
        if (tasks == null) tasks = new ArrayList<>();
        return tasks;
    }

    public List<Team> getTeams() {
        if (teams == null) teams = new ArrayList<>();
        return teams;
    }

    public List<User> getUsers() {
        if (users == null) users = new ArrayList<>();
        return users;
    }
}
