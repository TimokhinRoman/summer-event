package ru.tmhn.summerevent.repository;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;
import ru.tmhn.summerevent.model.EventTeamUser;
import ru.tmhn.summerevent.model.User;

import static ru.tmhn.summerevent.jooq.Tables.EVENTTEAMUSER;
import static ru.tmhn.summerevent.jooq.Tables.USER;

@SuppressWarnings("ConstantConditions")
@Repository
public class UserRepository {

    private final DSLContext context;

    public UserRepository(DSLContext context) {
        this.context = context;
    }

    public int addUser(User user) {
        return context.insertInto(USER, USER.NAME, USER.EMAIL, USER.PASSWORD)
                .values(user.getName(), user.getEmail(), user.getPassword())
                .returning(USER.ID)
                .fetchOne(USER.ID);
    }

    public User findUser(int id) {
        return context.select(USER.ID, USER.NAME, USER.EMAIL, USER.PASSWORD)
                .from(USER)
                .where(USER.ID.eq(id))
                .fetchOne(this::mapUser);
    }

    public User findUserByEmail(String email) {
        return context.select(USER.ID, USER.NAME, USER.EMAIL, USER.PASSWORD)
                .from(USER)
                .where(USER.EMAIL.eq(email))
                .fetchOne(this::mapUser);
    }

    public int addEventTeamUser(EventTeamUser eventTeamUser) {
        return context.insertInto(EVENTTEAMUSER, EVENTTEAMUSER.EVENT, EVENTTEAMUSER.TEAM, EVENTTEAMUSER.USER)
                .values(eventTeamUser.getEvent().getId(), eventTeamUser.getTeam().getId(), eventTeamUser.getUser().getId())
                .returning(EVENTTEAMUSER.ID)
                .fetchOne(EVENTTEAMUSER.ID);
    }

    private User mapUser(Record record) {
        User user = new User();
        user.setId(record.get(USER.ID));
        user.setName(record.get(USER.NAME));
        user.setEmail(record.get(USER.EMAIL));
        user.setPassword(record.get(USER.PASSWORD));
        return user;
    }
}
