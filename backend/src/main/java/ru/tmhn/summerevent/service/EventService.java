package ru.tmhn.summerevent.service;

import org.springframework.stereotype.Service;
import ru.tmhn.summerevent.dto.EventDto;
import ru.tmhn.summerevent.dto.TaskDto;
import ru.tmhn.summerevent.mapper.EventMapper;
import ru.tmhn.summerevent.model.Event;
import ru.tmhn.summerevent.model.Task;
import ru.tmhn.summerevent.repository.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
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
        event.setTasks(eventRepository.listTasks(id));
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

        event.setTasks(eventRepository.listTasks(event.getId()));
        return eventMapper.toEventDto(event);
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
}
