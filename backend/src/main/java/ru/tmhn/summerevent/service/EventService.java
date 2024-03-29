package ru.tmhn.summerevent.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tmhn.summerevent.dto.*;
import ru.tmhn.summerevent.mapper.EventMapper;
import ru.tmhn.summerevent.mapper.UserMapper;
import ru.tmhn.summerevent.model.*;
import ru.tmhn.summerevent.repository.EventRepository;
import ru.tmhn.summerevent.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final EventMapper eventMapper;
    private final UserMapper userMapper;

    public EventService(EventRepository eventRepository,
                        TeamService teamService,
                        UserService userService,
                        EventMapper eventMapper,
                        UserMapper userMapper) {
        this.eventRepository = eventRepository;
        this.teamService = teamService;
        this.userService = userService;
        this.eventMapper = eventMapper;
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
        return eventMapper.toEventDto(event);
    }

    public List<EventDto> listEvents() {
        return eventRepository.listEvents().stream()
                .map(eventMapper::toEventDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void activateEvent(int eventId) {
        eventRepository.deleteActiveEvent();
        eventRepository.addActiveEvent(eventId);
    }

    public void deactivateEvent(int eventId) {
        eventRepository.deleteActiveEvent(eventId);
    }

    public EventDto findActiveEvent() {
        Event event = eventRepository.findActiveEvent();
        if (event == null) return null;

        return findEvent(event.getId());
    }

    public int updateEventStatus(int eventId, EventStatus status) {
        Objects.requireNonNull(status);
        return eventRepository.updateEventStatus(eventId, status);
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

    @Transactional
    public void setTaskCompleted(int eventId, int taskId, boolean completed) {
        eventRepository.updateTaskCompleted(eventId, taskId, completed);
        if (completed) {
            eventRepository.deleteTaskSelected(eventId);
            eventRepository.deleteTeamChooser(eventId);
            eventRepository.updateEventStatus(eventId, EventStatus.DRAW);
        }
    }

    public List<TaskDto> listTasks(int eventId) {
        return eventRepository.listTasks(eventId).stream()
                .map(eventMapper::toTaskDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> filterAvailableTasks(List<TaskDto> tasks) {
        Map<Integer, TaskDto> taskMap = tasks.stream()
                .collect(Collectors.toMap(TaskDto::getId, Function.identity()));

        return tasks.stream()
                .filter(task -> {
                    if (task.isCompleted()) return false;
                    if (task.getParentTaskId() != null) {
                        TaskDto parentTask = taskMap.get(task.getParentTaskId());
                        return parentTask == null || parentTask.isCompleted();
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void selectTask(int eventId, int taskId) {
        //eventRepository.deleteTaskSelected(eventId);
        eventRepository.addTaskSelected(eventId, taskId);
        eventRepository.updateEventStatus(eventId, EventStatus.TASK_IN_PROGRESS);
    }

    public void unselectTask(int eventId) {
        eventRepository.deleteTaskSelected(eventId);
        eventRepository.updateEventStatus(eventId, EventStatus.TASK_SELECTION);
    }

    public TaskDto findSelectedTask(int eventId) {
        return eventMapper.toTaskDto(eventRepository.findTaskSelected(eventId));
    }

    public TeamDto findUserTeam(int eventId, int userId) {
        Team team = eventRepository.findUserTeam(eventId, userId);
        return eventMapper.toTeamDto(team);
    }

    public List<TeamDto> listTeams(int eventId) {
        return eventRepository.listTeams(eventId).stream()
                .map(eventMapper::toTeamDto)
                .collect(Collectors.toList());
    }

    public List<UserDto> listUsers(int eventId) {
        return eventRepository.listUsers(eventId).stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    public List<UserDto> listTeamUsers(int eventId, int teamId) {
        return eventRepository.listTeamUsers(eventId, teamId).stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    public int deleteTeam(int eventId, int teamId) {
        EventDto event = findEvent(eventId);
        if (event != null && event.getStatus() == EventStatus.PENDING) {
            return teamService.deleteTeam(teamId);
            //return eventRepository.deleteEventTeam(eventId, teamId);
        }
        return 0;
    }

    @Transactional
    public void setTeamChooser(int eventId, int teamId) {
        eventRepository.deleteTeamChooser(eventId);
        eventRepository.addTeamChooser(eventId, teamId);
        eventRepository.updateEventStatus(eventId, EventStatus.TASK_SELECTION);
    }

    public void deleteTeamChooser(int eventId, int teamId) {
        eventRepository.deleteTeamChooser(eventId, teamId);
    }

    public TeamDto findTeamChooser(int eventId) {
        return eventMapper.toTeamDto(eventRepository.findTeamChooser(eventId));
    }

    public boolean isUserChooser(int eventId, int userId) {
        TeamDto team = findUserTeam(eventId, userId);
        TeamDto teamChooser = findTeamChooser(eventId);
        if (team != null && teamChooser != null && Objects.equals(team.getId(), teamChooser.getId())
                && teamChooser.getCaptain() != null && Objects.equals(teamChooser.getCaptain().getId(), userId)) {
            return true;
        }
        return false;
    }

    public boolean isTeamChooser(int eventId, int teamId) {
        Team team = eventRepository.findTeamChooser(eventId);
        return team != null && team.getId() == teamId;
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
                team.setCaptain(user);
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

    public void addTeamScore(int taskId, int teamId, int score) {
        eventRepository.addTeamScore(taskId, teamId, score);
    }

    public void setTeamScore(int taskId, int teamId, int score) {
        eventRepository.setTeamScore(taskId, teamId, score);
    }

    public List<ScoreDto> listTeamScore(int eventId, int teamId) {
        return eventRepository.listTeamScore(eventId, teamId).stream()
                .map(eventMapper::toScoreDto)
                .collect(Collectors.toList());
    }

    public List<ScoreDto> listEventScore(int eventId) {
        return eventRepository.listEventScore(eventId)
                .stream().map(eventMapper::toScoreDto)
                .collect(Collectors.toList());
    }
}
