package ru.tmhn.summerevent.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tmhn.summerevent.dto.EventDto;
import ru.tmhn.summerevent.dto.TaskDto;
import ru.tmhn.summerevent.dto.TeamDto;
import ru.tmhn.summerevent.dto.UserDto;
import ru.tmhn.summerevent.mapper.EventMapper;
import ru.tmhn.summerevent.mapper.TeamMapper;
import ru.tmhn.summerevent.mapper.UserMapper;
import ru.tmhn.summerevent.model.Event;
import ru.tmhn.summerevent.model.EventTeamUser;
import ru.tmhn.summerevent.model.Task;
import ru.tmhn.summerevent.model.Team;
import ru.tmhn.summerevent.repository.EventRepository;
import ru.tmhn.summerevent.utils.Utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final EventMapper eventMapper;
    private final TeamMapper teamMapper;
    private final UserMapper userMapper;

    public EventService(EventRepository eventRepository,
                        TeamService teamService,
                        UserService userService,
                        EventMapper eventMapper,
                        TeamMapper teamMapper,
                        UserMapper userMapper) {
        this.eventRepository = eventRepository;
        this.teamService = teamService;
        this.userService = userService;
        this.eventMapper = eventMapper;
        this.teamMapper = teamMapper;
        this.userMapper = userMapper;
    }

    public EventDto addEvent(EventDto dto) {
        Event event = eventMapper.toEvent(dto);
        int id = eventRepository.addEvent(event);
        event.setId(id);
        return eventMapper.toEventDto(event);
    }

    public EventDto modifyEvent(EventDto dto) {
        Event event = eventMapper.toEvent(dto);
        eventRepository.modifyEvent(event);
        return eventMapper.toEventDto(event);
    }

    public EventDto findEvent(int id) {
        Event event = eventRepository.findEvent(id);
        // todo: вынести в отдельный метод
        event.setTasks(eventRepository.listTasks(id));
        List<EventTeamUser> eventTeamUsers = eventRepository.listTeamsAndUsers(id);
        event.setTeams(eventTeamUsers.stream()
                .map(EventTeamUser::getTeam)
                .distinct()
                .collect(Collectors.toList()));
        event.setUsers(eventTeamUsers.stream()
                .map(EventTeamUser::getUser)
                .distinct()
                .collect(Collectors.toList()));
        return eventMapper.toEventDto(event);
    }

    public List<EventDto> listEvents() {
        return eventRepository.listEvents().stream()
                .map(eventMapper::toEventDto)
                .collect(Collectors.toList());
    }

    public EventDto findActiveEvent() {
        Event event = eventRepository.findActiveEvent();
        if (event == null) return null;

        return findEvent(event.getId());
    }

    public TaskDto addTask(TaskDto dto) {
        Task task = eventMapper.toTask(dto);
        int id = eventRepository.addTask(task);
        task.setId(id);
        return eventMapper.toTaskDto(task);
    }

    public TaskDto modifyTask(TaskDto dto) {
        Task task = eventMapper.toTask(dto);
        eventRepository.modifyTask(task);
        return eventMapper.toTaskDto(task);
    }

    public void deleteTask(int eventId, int taskId) {
        eventRepository.deleteTask(eventId, taskId);
    }

    public TaskDto findTask(int eventId, int taskId) {
        Task task = eventRepository.findTask(eventId, taskId);
        return eventMapper.toTaskDto(task);
    }

    public TeamDto findUserTeam(int eventId, int userId) {
        Team team = eventRepository.findUserTeam(eventId, userId);
        return teamMapper.map(team);
    }

    public List<TeamDto> listTeams(int eventId) {
        return eventRepository.listTeams(eventId).stream()
                .map(teamMapper::map)
                .collect(Collectors.toList());
    }

    public List<UserDto> listTeamUsers(int eventId, int teamId) {
        return eventRepository.listTeamUsers(eventId, teamId).stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    @Transactional
    public TeamDto joinTeam(EventDto event, TeamDto team) {
        Objects.requireNonNull(event);
        Objects.requireNonNull(team);

        if (team.getId() == null && Utils.isEmpty(team.getName())) {
            throw new IllegalArgumentException("Both team id and team name are empty");
        }

        UserDto user = userService.getCurrentUser();

        if (team.getId() == null) {
            TeamDto existingTeam = teamService.findTeamByName(team.getName());
            if (existingTeam == null) {
                team.setOwner(user);
                team = teamService.addTeam(team);
            } else {
                team = existingTeam;
            }
        }

        eventRepository.addEventTeamUser(event.getId(), team.getId(), user.getId());

        return team;
    }

    public void leaveTeam() {
        EventDto event = findActiveEvent();
        Objects.requireNonNull(event);

        UserDto user = userService.getCurrentUser();

        eventRepository.deleteEventTeamUser(event.getId(), user.getId());
    }
}
