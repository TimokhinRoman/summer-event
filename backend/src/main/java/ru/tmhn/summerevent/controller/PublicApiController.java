package ru.tmhn.summerevent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tmhn.summerevent.dto.EventDto;
import ru.tmhn.summerevent.service.EventService;

@RestController
@RequestMapping("/api")
public class PublicApiController {

    private final EventService eventService;

    public PublicApiController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/event")
    public EventDto getEvent() {
        return eventService.findActiveEvent();
    }
}
