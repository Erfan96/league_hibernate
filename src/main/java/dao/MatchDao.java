package dao;

import entities.Match;
import javax.persistence.EntityManager;

public class MatchDao extends EntityDao<Match, Integer>{
    public MatchDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Match> getEntityClass() {
        return Match.class;
    }
}
