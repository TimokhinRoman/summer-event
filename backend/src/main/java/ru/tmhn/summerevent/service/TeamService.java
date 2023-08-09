package ru.tmhn.summerevent.service;

import org.springframework.stereotype.Service;
import ru.tmhn.summerevent.dto.TeamDto;
import ru.tmhn.summerevent.mapper.EventMapper;
import ru.tmhn.summerevent.model.Team;
import ru.tmhn.summerevent.repository.TeamRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final EventMapper eventMapper;

    public TeamService(TeamRepository teamRepository, EventMapper eventMapper) {
        this.teamRepository = teamRepository;
        this.eventMapper = eventMapper;
    }

    public TeamDto addTeam(TeamDto dto) {
        Team team = eventMapper.toTeam(dto);
        int id = teamRepository.addTeam(team);
        team.setId(id);
        return eventMapper.toTeamDto(team);
    }

    public TeamDto findTeam(int id) {
        Team team = teamRepository.findTeam(id);
        return eventMapper.toTeamDto(team);
    }

    public TeamDto findTeamByName(String name) {
        Team team = teamRepository.findTeamByName(name);
        return eventMapper.toTeamDto(team);
    }

    public List<TeamDto> listTeams() {
        return teamRepository.listTeams().stream()
                .map(eventMapper::toTeamDto)
                .collect(Collectors.toList());
    }
}
