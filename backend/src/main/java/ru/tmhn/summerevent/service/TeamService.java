package ru.tmhn.summerevent.service;

import org.springframework.stereotype.Service;
import ru.tmhn.summerevent.dto.TeamDto;
import ru.tmhn.summerevent.mapper.TeamMapper;
import ru.tmhn.summerevent.model.Team;
import ru.tmhn.summerevent.repository.TeamRepository;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public TeamDto addTeam(TeamDto dto) {
        Team team = teamMapper.map(dto);
        int id = teamRepository.addTeam(team);
        team.setId(id);
        return teamMapper.map(team);
    }
}
