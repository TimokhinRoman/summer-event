package ru.tmhn.summerevent.mapper;

import org.springframework.stereotype.Component;
import ru.tmhn.summerevent.dto.EventDto;
import ru.tmhn.summerevent.dto.ScoreDto;
import ru.tmhn.summerevent.dto.TaskDto;
import ru.tmhn.summerevent.dto.TeamDto;
import ru.tmhn.summerevent.model.Event;
import ru.tmhn.summerevent.model.Score;
import ru.tmhn.summerevent.model.Task;
import ru.tmhn.summerevent.model.Team;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    private final UserMapper userMapper;

    public EventMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Event toEvent(EventDto dto) {
        if (dto == null) return null;
        Event event = new Event();
        if (dto.getId() != null) {
            event.setId(dto.getId());
        }
        event.setName(dto.getName());
        event.setDescription(dto.getDescription());
        event.setStatus(dto.getStatus());
        event.setTasks(toTaskList(dto.getTasks()));
        return event;
    }

    public EventDto toEventDto(Event event) {
        if (event == null) return null;
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setDescription(event.getDescription());
        dto.setStatus(event.getStatus());
        dto.setActive(event.isActive());
        dto.setTasks(toTaskDtoList(event.getTasks()));
        return dto;
    }

    public List<Task> toTaskList(List<TaskDto> dtoList) {
        if (dtoList == null) return null;
        return dtoList.stream()
                .map(this::toTask)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Task toTask(TaskDto dto) {
        if (dto == null) return null;
        Task task = new Task();
        if (dto.getId() != null) {
            task.setId(dto.getId());
        }
        task.setEvent(new Event(dto.getEventId()));
        task.setType(dto.getType());
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setPreview(dto.getPreview());
        task.setAnswer(dto.getAnswer());
        task.setMapX(dto.getMapX());
        task.setMapY(dto.getMapY());
        return task;
    }

    public List<TaskDto> toTaskDtoList(List<Task> tasks) {
        if (tasks == null) return null;
        return tasks.stream()
                .map(this::toTaskDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public TaskDto toTaskDto(Task task) {
        if (task == null) return null;
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        if (task.getEvent() != null) {
            dto.setEventId(task.getEvent().getId());
        }
        dto.setType(task.getType());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setPreview(task.getPreview());
        dto.setAnswer(task.getAnswer());
        dto.setMapX(task.getMapX());
        dto.setMapY(task.getMapY());
        dto.setCompleted(task.isCompleted());
        if (task.getParentTask() != null) {
            dto.setParentTaskId(task.getParentTask().getId());
        }
        dto.setImage(task.getImage());
        return dto;
    }

    public Team toTeam(TeamDto dto) {
        if (dto == null) return null;

        Team team = new Team();
        if (dto.getId() != null) {
            team.setId(dto.getId());
        }
        team.setCaptain(userMapper.map(dto.getCaptain()));
        team.setName(dto.getName());
        return team;
    }

    public TeamDto toTeamDto(Team team) {
        if (team == null) return null;

        TeamDto dto = new TeamDto();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setCaptain(userMapper.map(team.getCaptain()));
        return dto;
    }

    public ScoreDto toScoreDto(Score score) {
        if (score == null) return null;
        ScoreDto dto = new ScoreDto();
        dto.setTask(toTaskDto(score.getTask()));
        dto.setTeam(toTeamDto(score.getTeam()));
        dto.setScore(score.getScore());
        return dto;
    }

    public Score toScore(ScoreDto dto) {
        if (dto == null) return null;
        Score score = new Score();
        score.setTask(toTask(dto.getTask()));
        score.setTeam(toTeam(dto.getTeam()));
        score.setScore(dto.getScore());
        return score;
    }
}
