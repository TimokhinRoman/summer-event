package ru.tmhn.summerevent.dto;

import lombok.Data;

import java.util.List;

@Data
public class LobbyDto {

    private UserDto user;
    private TeamDto selectedTeam;
    private List<TeamDto> teams;
}
