package ru.tmhn.summerevent.mapper;

import org.springframework.stereotype.Component;
import ru.tmhn.summerevent.dto.EventDto;
import ru.tmhn.summerevent.dto.TaskDto;
import ru.tmhn.summerevent.model.Event;
import ru.tmhn.summerevent.model.Task;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class EventMapper {

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
        task.setAnswer(dto.getAnswer());
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
        dto.setEventId(task.getEvent().getId());
        dto.setType(task.getType());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setAnswer(task.getAnswer());
        return dto;
    }
}
