package ru.tmhn.summerevent.controller;

import org.springframework.web.bind.annotation.*;
import ru.tmhn.summerevent.dto.EventDto;
import ru.tmhn.summerevent.dto.TaskDto;
import ru.tmhn.summerevent.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    private final EventService eventService;

    public AdminApiController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public List<EventDto> listEvents() {
        return eventService.listEvents();
    }

    @PostMapping("/events")
    public EventDto addEvent(@RequestBody EventDto dto) {
        return eventService.addEvent(dto);
    }

    @PostMapping("/events/{id}")
    public EventDto modifyEvent(@PathVariable int id, @RequestBody EventDto dto) {
        dto.setId(id);
        return eventService.modifyEvent(dto);
    }

    @GetMapping("/events/{id}")
    public EventDto getEvent(@PathVariable int id) {
        return eventService.findEvent(id);
    }

    @PostMapping("/events/{eventId}/tasks")
    public TaskDto addTask(@PathVariable int eventId, @RequestBody TaskDto dto) {
        dto.setEventId(eventId);
        return eventService.addTask(dto);
    }

    @PostMapping("/events/{eventId}/tasks/{taskId}")
    public TaskDto modifyTask(@PathVariable int eventId, @PathVariable int taskId, @RequestBody TaskDto dto) {
        dto.setEventId(eventId);
        dto.setId(taskId);
        return eventService.modifyTask(dto);
    }

    @GetMapping("/events/{eventId}/tasks/{taskId}")
    public TaskDto getTask(@PathVariable int eventId, @PathVariable int taskId) {
        return eventService.findTask(eventId, taskId);
    }

    @DeleteMapping("/events/{eventId}/tasks/{taskId}")
    public EventDto deleteTask(@PathVariable int eventId, @PathVariable int taskId) {
        eventService.deleteTask(eventId, taskId);
        return eventService.findEvent(eventId);
    }
}
