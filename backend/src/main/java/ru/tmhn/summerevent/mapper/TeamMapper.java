package ru.tmhn.summerevent.mapper;

import org.springframework.stereotype.Component;
import ru.tmhn.summerevent.dto.TeamDto;
import ru.tmhn.summerevent.model.Team;

@Component
public class TeamMapper {

    public Team map(TeamDto dto) {
        if (dto == null) return null;

        Team team = new Team();
        team.setId(dto.getId());
        team.setName(dto.getName());
        return team;
    }

    public TeamDto map(Team team) {
        if (team == null) return null;

        TeamDto dto = new TeamDto();
        dto.setId(team.getId());
        dto.setName(team.getName());
        return dto;
    }
}
