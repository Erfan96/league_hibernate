package dao;

import entities.Team;
import javax.persistence.EntityManager;

public class TeamDao extends EntityDao<Team, Integer> {

    public TeamDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Team> getEntityClass() {
        return Team.class;
    }
}
