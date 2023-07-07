package ru.tmhn.summerevent.mapper;

import org.springframework.stereotype.Component;
import ru.tmhn.summerevent.dto.TeamDto;
import ru.tmhn.summerevent.model.Team;

@Component
public class TeamMapper {

    private final UserMapper userMapper;

    public TeamMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Team map(TeamDto dto) {
        if (dto == null) return null;

        Team team = new Team();
        if (dto.getId() != null) {
            team.setId(dto.getId());
        }
        team.setCaptain(userMapper.map(dto.getCaptain()));
        team.setName(dto.getName());
        return team;
    }

    public TeamDto map(Team team) {
        if (team == null) return null;

        TeamDto dto = new TeamDto();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setCaptain(userMapper.map(team.getCaptain()));
        return dto;
    }
}
