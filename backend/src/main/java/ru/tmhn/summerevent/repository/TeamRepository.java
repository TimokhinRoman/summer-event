package ru.tmhn.summerevent.repository;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;
import ru.tmhn.summerevent.model.Team;
import ru.tmhn.summerevent.model.User;

import java.util.List;

import static ru.tmhn.summerevent.jooq.Tables.TEAM;

@SuppressWarnings("ConstantConditions")
@Repository
public class TeamRepository {

    private final DSLContext context;

    public TeamRepository(DSLContext context) {
        this.context = context;
    }

    public int addTeam(Team team) {
        return context.insertInto(TEAM, TEAM.NAME, TEAM.USERID)
                .values(team.getName(), team.getCaptain().getId())
                .returning(TEAM.ID)
                .fetchOne(TEAM.ID);
    }

    public Team findTeam(int id) {
        return context.select(TEAM.ID, TEAM.NAME, TEAM.USERID)
                .from(TEAM)
                .where(TEAM.ID.eq(id))
                .fetchSingle(this::mapTeam);
    }

    public Team findTeamByName(String name) {
        return context.select(TEAM.ID, TEAM.NAME, TEAM.USERID)
                .from(TEAM)
                .where(TEAM.NAME.eq(name))
                .fetchOne(this::mapTeam);
    }

    public List<Team> listTeams() {
        return context.select(TEAM.ID, TEAM.NAME, TEAM.USERID)
                .from(TEAM)
                .fetch(this::mapTeam);
    }

    private Team mapTeam(Record record) {
        Team team = new Team();
        team.setId(record.get(TEAM.ID));
        team.setName(record.get(TEAM.NAME));
        team.setCaptain(new User());
        team.getCaptain().setId(record.get(TEAM.USERID));
        return team;
    }
}
