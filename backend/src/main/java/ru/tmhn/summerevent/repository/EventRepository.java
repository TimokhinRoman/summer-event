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
        return context.select(EVENT.ID, EVENT.NAME, EVENT.DESCRIPTION, EVENT.STATUS, ACTIVEEVENT.ACTIVE)
                .from(EVENT)
                .leftJoin(ACTIVEEVENT).on(ACTIVEEVENT.EVENTID.eq(EVENT.ID))
                .where(EVENT.ID.eq(id))
                .fetchSingle(this::mapEvent);
    }

    public List<Event> listEvents() {
        return context.select(EVENT.ID, EVENT.NAME, EVENT.DESCRIPTION, EVENT.STATUS, ACTIVEEVENT.ACTIVE)
                .from(EVENT)
                .leftJoin(ACTIVEEVENT).on(ACTIVEEVENT.EVENTID.eq(EVENT.ID))
                .fetch(this::mapEvent);
    }

    public int addActiveEvent(int eventId) {
        return context.insertInto(ACTIVEEVENT, ACTIVEEVENT.EVENTID)
                .values(eventId)
                .execute();
    }

    public int deleteActiveEvent() {
        return context.deleteFrom(ACTIVEEVENT)
                .execute();
    }

    public int deleteActiveEvent(int eventId) {
        return context.deleteFrom(ACTIVEEVENT)
                .where(ACTIVEEVENT.EVENTID.eq(eventId))
                .execute();
    }

    public Event findActiveEvent() {
        return context.select(ACTIVEEVENT.EVENTID)
                .from(ACTIVEEVENT)
                .fetchOne(record -> new Event(record.get(ACTIVEEVENT.EVENTID)));
    }

    public int updateEventStatus(int id, EventStatus status) {
        return context.update(EVENT)
                .set(EVENT.STATUS, ru.tmhn.summerevent.jooq.enums.EventStatus.valueOf(status.name()))
                .where(EVENT.ID.eq(id))
                .execute();
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
        return context.select()
                .from(TASK)
                .where(TASK.EVENTID.eq(eventId).and(TASK.ID.eq(taskId)))
                .fetchSingle(this::mapTask);
    }

    public int updateTaskCompleted(int eventId, int taskId, boolean completed) {
        return context.update(TASK)
                .set(TASK.COMPLETED, (byte) (completed ? 1 : 0))
                .where(TASK.EVENTID.eq(eventId).and(TASK.ID.eq(taskId)))
                .execute();
    }

    public List<Task> listTasks(int eventId) {
        return context.select()
                .from(TASK)
                .where(TASK.EVENTID.eq(eventId))
                .fetch(this::mapTask);
    }

    public void addTaskSelected(int eventId, int taskId) {
        context.insertInto(TASKSELECTED, TASKSELECTED.EVENTID, TASKSELECTED.TASKID)
                .values(eventId, taskId)
                .execute();
    }

    public void deleteTaskSelected(int eventId) {
        context.deleteFrom(TASKSELECTED)
                .where(TASKSELECTED.EVENTID.eq(eventId))
                .execute();
    }

    public Task findTaskSelected(int eventId) {
        return context.select(TASKSELECTED.TASKID)
                .from(TASKSELECTED)
                .where(TASKSELECTED.EVENTID.eq(eventId))
                .fetchOne(record -> new Task(record.get(TASKSELECTED.TASKID)));
    }

    public int addEventTeamUser(int eventId, int teamId, int userId) {
        return context.insertInto(EVENTTEAMUSER, EVENTTEAMUSER.EVENTID, EVENTTEAMUSER.TEAMID, EVENTTEAMUSER.USERID)
                .values(eventId, teamId, userId)
                .execute();
    }

    public int deleteEventTeamUser(int eventId, int userId) {
        return context.deleteFrom(EVENTTEAMUSER)
                .where(EVENTTEAMUSER.EVENTID.eq(eventId).and(EVENTTEAMUSER.USERID.eq(userId)))
                .execute();
    }

    public int deleteEventTeam(int eventId, int teamId) {
        return context.deleteFrom(EVENTTEAMUSER)
                .where(EVENTTEAMUSER.EVENTID.eq(eventId).and(EVENTTEAMUSER.TEAMID.eq(teamId)))
                .execute();
    }

    public Team findUserTeam(int eventId, int userId) {
        return context.select(TEAM.ID, TEAM.NAME, TEAM.USERID)
                .from(EVENTTEAMUSER)
                .leftJoin(TEAM).on(EVENTTEAMUSER.TEAMID.eq(TEAM.ID))
                .where(EVENTTEAMUSER.EVENTID.eq(eventId).and(EVENTTEAMUSER.USERID.eq(userId)))
                .fetchOne(this::mapTeam);
    }

    public List<Team> listTeams(int eventId) {
        return context.selectDistinct(TEAM.ID, TEAM.NAME, TEAM.USERID)
                .from(EVENTTEAMUSER)
                .leftJoin(TEAM).on(EVENTTEAMUSER.TEAMID.eq(TEAM.ID))
                .where(EVENTTEAMUSER.EVENTID.eq(eventId))
                .fetch(this::mapTeam);
    }

    public List<User> listUsers(int eventId) {
        return context.select(USER.ID, USER.NAME)
                .from(EVENTTEAMUSER)
                .leftJoin(USER).on(EVENTTEAMUSER.USERID.eq(USER.ID))
                .where(EVENTTEAMUSER.EVENTID.eq(eventId))
                .fetch(this::mapUser);
    }

    public List<User> listTeamUsers(int eventId, int teamId) {
        return context.select(USER.ID, USER.NAME)
                .from(EVENTTEAMUSER)
                .leftJoin(USER).on(EVENTTEAMUSER.USERID.eq(USER.ID))
                .where(EVENTTEAMUSER.EVENTID.eq(eventId).and(EVENTTEAMUSER.TEAMID.eq(teamId)))
                .fetch(this::mapUser);
    }

    public List<EventTeamUser> listTeamsAndUsers(int eventId) {
        return context.select(EVENTTEAMUSER.ID, EVENTTEAMUSER.EVENTID, EVENTTEAMUSER.TEAMID, EVENTTEAMUSER.USERID)
                .from(EVENTTEAMUSER)
                .where(EVENTTEAMUSER.EVENTID.eq(eventId))
                .fetch(this::mapEventTeamUser);
    }

    public int addTeamChooser(int eventId, int teamId) {
        return context.insertInto(EVENTTEAMCHOOSER, EVENTTEAMCHOOSER.EVENTID, EVENTTEAMCHOOSER.TEAMID)
                .values(eventId, teamId)
                .execute();
    }

    public int deleteTeamChooser(int eventId) {
        return context.deleteFrom(EVENTTEAMCHOOSER)
                .where(EVENTTEAMCHOOSER.EVENTID.eq(eventId))
                .execute();
    }

    public int deleteTeamChooser(int eventId, int teamId) {
        return context.deleteFrom(EVENTTEAMCHOOSER)
                .where(EVENTTEAMCHOOSER.EVENTID.eq(eventId).and(EVENTTEAMCHOOSER.TEAMID.eq(teamId)))
                .execute();
    }

    public Team findTeamChooser(int eventId) {
        return context.select(EVENTTEAMCHOOSER.TEAMID, TEAM.USERID)
                .from(EVENTTEAMCHOOSER)
                .leftJoin(TEAM).on(EVENTTEAMCHOOSER.TEAMID.eq(TEAM.ID))
                .where(EVENTTEAMCHOOSER.EVENTID.eq(eventId))
                .fetchOne(record -> {
                    Team team = new Team(record.get(EVENTTEAMCHOOSER.TEAMID));
                    team.setCaptain(new User(record.get(TEAM.USERID)));
                    return team;
                });
    }

    private Event mapEvent(Record record) {
        Event event = new Event();
        event.setId(record.get(EVENT.ID));
        event.setName(record.get(EVENT.NAME));
        event.setDescription(record.get(EVENT.DESCRIPTION));
        event.setStatus(EventStatus.valueOf(record.get(EVENT.STATUS).name()));
        event.setActive(record.get(ACTIVEEVENT.ACTIVE) != null);
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
        task.setMapX(record.get(TASK.MAPX));
        task.setMapY(record.get(TASK.MAPY));
        task.setCompleted(record.get(TASK.COMPLETED) == 1);
        Integer parentTaskId = record.get(TASK.PARENTTASKID);
        if (parentTaskId != null) {
            task.setParentTask(new Task(parentTaskId));
        }
        task.setImage(record.get(TASK.IMAGE));
        return task;
    }

    private Team mapTeam(Record record) {
        Team team = new Team();
        team.setId(record.get(TEAM.ID));
        team.setName(record.get(TEAM.NAME));
        team.setCaptain(new User(record.get(TEAM.USERID)));
        return team;
    }

    private User mapUser(Record record) {
        User user = new User();
        user.setId(record.get(USER.ID));
        user.setName(record.get(USER.NAME));
        return user;
    }

    private EventTeamUser mapEventTeamUser(Record record) {
        EventTeamUser eventTeamUser = new EventTeamUser();
        eventTeamUser.setId(record.get(EVENTTEAMUSER.ID));
        eventTeamUser.setEvent(new Event());
        eventTeamUser.getEvent().setId(record.get(EVENTTEAMUSER.EVENTID));
        eventTeamUser.setTeam(new Team());
        eventTeamUser.getTeam().setId(record.get(EVENTTEAMUSER.TEAMID));
        eventTeamUser.setUser(new User());
        eventTeamUser.getUser().setId(record.get(EVENTTEAMUSER.USERID));
        return eventTeamUser;
    }
}
