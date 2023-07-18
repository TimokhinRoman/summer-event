package ru.tmhn.summerevent.controller.admin;

import org.springframework.web.bind.annotation.*;
import ru.tmhn.summerevent.dto.*;
import ru.tmhn.summerevent.model.EventStatus;
import ru.tmhn.summerevent.service.EventService;
import ru.tmhn.summerevent.service.TeamService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin/events")
public class AdminEventController {

    private final EventService eventService;
    private final TeamService teamService;

    public AdminEventController(EventService eventService, TeamService teamService) {
        this.eventService = eventService;
        this.teamService = teamService;
    }

    @GetMapping
    public List<EventDto> listEvents() {
        return eventService.listEvents();
    }

    @PostMapping
    public EventDto addEvent(@RequestBody EventDto dto) {
        return eventService.addEvent(dto);
    }

    @PostMapping("/{id}")
    public EventDto modifyEvent(@PathVariable int id, @RequestBody EventDto dto) {
        dto.setId(id);
        return eventService.modifyEvent(dto);
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable int id) {
        return eventService.findEvent(id);
    }

    @PostMapping("/{eventId}/tasks")
    public TaskDto addTask(@PathVariable int eventId, @RequestBody TaskDto dto) {
        dto.setEventId(eventId);
        return eventService.addTask(dto);
    }

    @PostMapping("/{eventId}/tasks/{taskId}")
    public TaskDto modifyTask(@PathVariable int eventId, @PathVariable int taskId, @RequestBody TaskDto dto) {
        dto.setEventId(eventId);
        dto.setId(taskId);
        return eventService.modifyTask(dto);
    }

    @GetMapping("/{eventId}/tasks/{taskId}")
    public TaskDto getTask(@PathVariable int eventId, @PathVariable int taskId) {
        TaskDto task = eventService.findTask(eventId, taskId);
        TaskDto selectedTask = eventService.findSelectedTask(eventId);
        if (selectedTask != null && selectedTask.getId().equals(task.getId())) {
            task.setSelected(true);
        }
        return task;
    }

    @DeleteMapping("/{eventId}/tasks/{taskId}")
    public EventDto deleteTask(@PathVariable int eventId, @PathVariable int taskId) {
        eventService.deleteTask(eventId, taskId);
        return eventService.findEvent(eventId);
    }

    @PostMapping("/{eventId}/tasks/{taskId}/complete")
    public void completeTask(@PathVariable int eventId, @PathVariable int taskId, @RequestParam boolean completed) {
        eventService.setTaskCompleted(eventId, taskId, completed);
    }

    @GetMapping("/{eventId}/tasks")
    public List<TaskDto> listTasks(@PathVariable int eventId) {
        List<TaskDto> tasks = eventService.listTasks(eventId);
        TaskDto selectedTask = eventService.findSelectedTask(eventId);
        if (selectedTask != null) {
            for (TaskDto task : tasks) {
                if (selectedTask.getId().equals(task.getId())) {
                    task.setSelected(true);
                    break;
                }
            }
        }
        return tasks;
    }

    @PostMapping("/{eventId}/activate")
    public EventDto activateEvent(@PathVariable int eventId) {
        eventService.activateEvent(eventId);
        return eventService.findEvent(eventId);
    }

    @PostMapping("/{eventId}/deactivate")
    public EventDto deactivateEvent(@PathVariable int eventId) {
        eventService.deactivateEvent(eventId);
        return eventService.findEvent(eventId);
    }

    @PostMapping("/{eventId}/pending")
    public EventDto setEventPending(@PathVariable int eventId) {
        eventService.updateEventStatus(eventId, EventStatus.PENDING);
        return eventService.findEvent(eventId);
    }

    @PostMapping("/{eventId}/started")
    public EventDto setEventStarted(@PathVariable int eventId) {
        eventService.updateEventStatus(eventId, EventStatus.STARTED);
        return eventService.findEvent(eventId);
    }

    @PostMapping("/{eventId}/ended")
    public EventDto setEventEnded(@PathVariable int eventId) {
        eventService.updateEventStatus(eventId, EventStatus.ENDED);
        return eventService.findEvent(eventId);
    }

    @GetMapping("/{eventId}/teams")
    public List<TeamDto> listEventTeams(@PathVariable int eventId) {
        List<TeamDto> teams = eventService.listTeams(eventId);
        TeamDto teamChooser = eventService.findTeamChooser(eventId);
        if (teamChooser != null) {
            for (TeamDto team : teams) {
                if (Objects.equals(team.getId(), teamChooser.getId())) {
                    team.setChooser(true);
                }
            }
        }
        return teams;
    }

    @GetMapping("/{eventId}/teams/{teamId}")
    public ResponseDto getEventTeam(@PathVariable int eventId, @PathVariable int teamId) {
        EventDto event = eventService.findEvent(eventId);
        TeamDto team = teamService.findTeam(teamId);
        if (team != null) {
            team.setUsers(eventService.listTeamUsers(eventId, teamId));
            team.setChooser(eventService.isTeamChooser(eventId, teamId));
        }
        ResponseDto response = new ResponseDto();
        response.setEvent(event);
        response.setTeam(team);
        return response;
    }

    @DeleteMapping("/{eventId}/teams/{teamId}")
    public int deleteEventTeam(@PathVariable int eventId, @PathVariable int teamId) {
        return eventService.deleteTeam(eventId, teamId);
    }

    @GetMapping("/{eventId}/users")
    public List<UserDto> listEventUsers(@PathVariable int eventId) {
        return eventService.listUsers(eventId);
    }

    @PostMapping("/{eventId}/teams/{teamId}/choose")
    public void setTeamChooser(@PathVariable int eventId, @PathVariable int teamId) {
        eventService.setTeamChooser(eventId, teamId);
    }

    @DeleteMapping("/{eventId}/teams/{teamId}/choose")
    public void deleteTeamChooser(@PathVariable int eventId, @PathVariable int teamId) {
        eventService.deleteTeamChooser(eventId, teamId);
    }
}
