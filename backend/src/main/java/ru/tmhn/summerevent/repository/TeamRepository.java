package ru.tmhn.summerevent.repository;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;
import ru.tmhn.summerevent.model.Team;
import ru.tmhn.summerevent.model.User;

import static ru.tmhn.summerevent.jooq.Tables.TEAM;

@SuppressWarnings("ConstantConditions")
@Repository
public class TeamRepository {

    private final DSLContext context;

    public TeamRepository(DSLContext context) {
        this.context = context;
    }

    public int addTeam(Team team) {
        return context.insertInto(TEAM, TEAM.NAME, TEAM.OWNER)
                .values(team.getName(), team.getOwner().getId())
                .returning(TEAM.ID)
                .fetchOne(TEAM.ID);
    }

    public Team findTeam(int id) {
        return context.select(TEAM.ID, TEAM.NAME, TEAM.OWNER)
                .from(TEAM)
                .where(TEAM.ID.eq(id))
                .fetchSingle(this::mapTeam);
    }

    private Team mapTeam(Record record) {
        Team team = new Team();
        team.setId(record.get(TEAM.ID));
        team.setName(record.get(TEAM.NAME));
        team.setOwner(new User());
        team.getOwner().setId(record.get(TEAM.OWNER));
        return team;
    }
}
