package ru.tmhn.summerevent.controller;

import org.springframework.web.bind.annotation.*;
import ru.tmhn.summerevent.dto.*;
import ru.tmhn.summerevent.service.EventService;
import ru.tmhn.summerevent.service.TeamService;
import ru.tmhn.summerevent.service.UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PublicApiController {

    private final EventService eventService;
    private final TeamService teamService;
    private final UserService userService;

    public PublicApiController(EventService eventService, TeamService teamService, UserService userService) {
        this.eventService = eventService;
        this.teamService = teamService;
        this.userService = userService;
    }

    @GetMapping("/event")
    public EventDto getEvent() {
        return eventService.findActiveEvent();
    }

    @GetMapping("/briefing")
    public BriefingDto getBriefing() {
        EventDto event = eventService.findActiveEvent();
        UserDto user = userService.getCurrentUser();

        BriefingDto dto = new BriefingDto();
        dto.setEvent(event);
        dto.setUser(user);
        if (event != null) {
            dto.setTask(eventService.findSelectedTask(event.getId()));
        }

        return dto;
    }

    @GetMapping("/lobby")
    public LobbyDto getLobby() {
        EventDto event = eventService.findActiveEvent();
        if (event == null) throw new IllegalStateException("No active event");

        UserDto user = userService.getCurrentUser();
        TeamDto userTeam = eventService.findUserTeam(event.getId(), user.getId());
        if (userTeam != null) {
            userTeam.setUsers(eventService.listTeamUsers(event.getId(), userTeam.getId()));
        }

        LobbyDto dto = new LobbyDto();
        dto.setUser(user);
        dto.setSelectedTeam(userTeam);
        dto.setTeams(eventService.listTeams(event.getId()));

        return dto;
    }

    @PostMapping("/team/join")
    public TeamDto joinTeam(@RequestBody TeamDto team) {
        EventDto event = eventService.findActiveEvent();
        team = eventService.joinTeam(event, team);
        team = teamService.findTeam(team.getId());
        team.setUsers(eventService.listTeamUsers(event.getId(), team.getId()));
        return team;
    }

    @DeleteMapping("/team/leave")
    public void leaveTeam() {
        eventService.leaveTeam();
    }

    @GetMapping("/map")
    public MapDto getMapData() {
        EventDto event = eventService.findActiveEvent();
        MapDto map = new MapDto();
        List<TaskDto> tasks;

        TaskDto selectedTask = eventService.findSelectedTask(event.getId());
        if (selectedTask != null) {
            selectedTask = eventService.findTask(event.getId(), selectedTask.getId());
            selectedTask.setSelected(true);
            tasks = Collections.singletonList(selectedTask);
            map.setTaskInProgress(true);
        } else {
            tasks = eventService.listTasks(event.getId());
            tasks = eventService.filterAvailableTasks(tasks);

            UserDto user = userService.getCurrentUser();
            map.setCanSelect(eventService.isUserChooser(event.getId(), user.getId()));
        }

        map.setPoints(tasks.stream()
                .map(PointDto::create)
                .collect(Collectors.toList()));

        return map;
    }

    @PostMapping("/task/{taskId}/select")
    public TaskDto selectTask(@PathVariable int taskId) {
        EventDto event = eventService.findActiveEvent();
        UserDto user = userService.getCurrentUser();

        boolean canSelect = eventService.isUserChooser(event.getId(), user.getId());
        if (!canSelect) {
            throw new IllegalStateException("This user can't select a task");
        }

        try {
            eventService.selectTask(event.getId(), taskId);
            TaskDto task = new TaskDto();
            task.setId(taskId);
            return task;
        } catch (Exception e) {
            return eventService.findSelectedTask(event.getId());
        }
    }

    @GetMapping("/task/current")
    public TaskDto getCurrentTask() {
        EventDto event = eventService.findActiveEvent();
        TaskDto task = eventService.findSelectedTask(event.getId());
        if (task == null) return null;
        return eventService.findTask(event.getId(), task.getId());
    }

    @GetMapping("/scores")
    public EventScoreDto getEventScore() {
        EventDto event = eventService.findActiveEvent();
        List<TeamDto> teams = eventService.listTeams(event.getId());
        List<TaskDto> tasks = eventService.listTasks(event.getId());
        List<ScoreDto> scores = eventService.listEventScore(event.getId());

        EventScoreDto eventScore = new EventScoreDto();
        eventScore.setTasks(tasks);
        eventScore.setTeams(teams);

        Map<Integer, Map<Integer, Integer>> scoreMap = new HashMap<>();

        for (ScoreDto score : scores) {
            Map<Integer, Integer> teamScoreMap = scoreMap.computeIfAbsent(score.getTask().getId(), k -> new HashMap<>());
            teamScoreMap.put(score.getTeam().getId(), score.getScore());
        }

        eventScore.setScores(scoreMap);

        return eventScore;
    }
}
