package ru.tmhn.summerevent.repository;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;
import ru.tmhn.summerevent.jooq.enums.TaskType;
import ru.tmhn.summerevent.model.*;

import java.util.List;

import static ru.tmhn.summerevent.jooq.Tables.*;

@SuppressWarnings("ConstantConditions")
@Repository
public class EventRepository {

    private final DSLContext context;

    public EventRepository(DSLContext context) {
        this.context = context;
    }

    public int addEvent(Event event) {
        return context.insertInto(EVENT, EVENT.NAME, EVENT.DESCRIPTION)
                .values(event.getName(), event.getDescription())
                .returning(EVENT.ID)
                .fetchOne(EVENT.ID);
    }

    public int modifyEvent(Event event) {
        return context.update(EVENT)
                .set(EVENT.NAME, event.getName())
                .set(EVENT.DESCRIPTION, event.getDescription())
                .where(EVENT.ID.eq(event.getId()))
                .execute();
    }

    public Event findEvent(int id) {
        return context.select(EVENT.ID, EVENT.NAME, EVENT.DESCRIPTION)
                .from(EVENT)
                .where(EVENT.ID.eq(id))
                .fetchSingle(this::mapEvent);
    }

    public List<Event> listEvents() {
        return context.select(EVENT.ID, EVENT.NAME, EVENT.DESCRIPTION)
                .from(EVENT)
                .fetch(this::mapEvent);
    }

    public Event findActiveEvent() {
        return context.select(ACTIVEEVENT.EVENTID)
                .from(ACTIVEEVENT)
                .fetchOne(record -> new Event(record.get(ACTIVEEVENT.EVENTID)));
    }

    public List<EventTeamUser> listTeamsAndUsers(int eventId) {
        return context.select(EVENTTEAMUSER, EVENTTEAMUSER.ID, EVENTTEAMUSER.EVENT, EVENTTEAMUSER.TEAM, EVENTTEAMUSER.USER)
                .from(EVENTTEAMUSER)
                .where(EVENTTEAMUSER.EVENT.eq(eventId))
                .fetch(this::mapEventTeamUser);
    }

    public int addTask(Task task) {
        return context.insertInto(TASK, TASK.EVENTID, TASK.TYPE, TASK.NAME, TASK.DESCRIPTION, TASK.ANSWER)
                .values(task.getEvent().getId(), TaskType.valueOf(task.getType().name()), task.getName(), task.getDescription(), task.getAnswer())
                .returning(TASK.ID)
                .fetchOne(TASK.ID);
    }

    public int modifyTask(Task task) {
        return context.update(TASK)
                .set(TASK.TYPE, TaskType.valueOf(task.getType().name()))
                .set(TASK.NAME, task.getName())
                .set(TASK.DESCRIPTION, task.getDescription())
                .set(TASK.ANSWER, task.getAnswer())
                .where(TASK.ID.eq(task.getId()))
                .execute();
    }

    public int deleteTask(int eventId, int taskId) {
        return context.deleteFrom(TASK)
                .where(TASK.EVENTID.eq(eventId).and(TASK.ID.eq(taskId)))
                .execute();
    }

    public Task findTask(int eventId, int taskId) {
        return context.select(TASK.ID, TASK.EVENTID, TASK.TYPE, TASK.NAME, TASK.DESCRIPTION, TASK.ANSWER)
                .from(TASK)
                .where(TASK.EVENTID.eq(eventId).and(TASK.ID.eq(taskId)))
                .fetchSingle(this::mapTask);
    }

    public List<Task> listTasks(int eventId) {
        return context.select(TASK.ID, TASK.EVENTID, TASK.TYPE, TASK.NAME, TASK.DESCRIPTION, TASK.ANSWER)
                .from(TASK)
                .where(TASK.EVENTID.eq(eventId))
                .fetch(this::mapTask);
    }

    private Event mapEvent(Record record) {
        Event event = new Event();
        event.setId(record.get(EVENT.ID));
        event.setName(record.get(EVENT.NAME));
        event.setDescription(record.get(EVENT.DESCRIPTION));
        return event;
    }

    private Task mapTask(Record record) {
        Task task = new Task();
        task.setId(record.get(TASK.ID));
        task.setEvent(new Event(record.get(TASK.EVENTID)));
        task.setType(ru.tmhn.summerevent.model.TaskType.valueOf(record.get(TASK.TYPE).name()));
        task.setName(record.get(TASK.NAME));
        task.setDescription(record.get(TASK.DESCRIPTION));
        task.setAnswer(record.get(TASK.ANSWER));
        return task;
    }

    private EventTeamUser mapEventTeamUser(Record record) {
        EventTeamUser eventTeamUser = new EventTeamUser();
        eventTeamUser.setId(record.get(EVENTTEAMUSER.ID));
        eventTeamUser.setEvent(new Event());
        eventTeamUser.getEvent().setId(record.get(EVENTTEAMUSER.EVENT));
        eventTeamUser.setTeam(new Team());
        eventTeamUser.getTeam().setId(record.get(EVENTTEAMUSER.TEAM));
        eventTeamUser.setUser(new User());
        eventTeamUser.getUser().setId(record.get(EVENTTEAMUSER.USER));
        return eventTeamUser;
    }
}
