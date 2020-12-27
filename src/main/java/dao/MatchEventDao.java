package dao;

import entities.MatchEvent;
import javax.persistence.EntityManager;


public class MatchEventDao extends EntityDao<MatchEvent, Integer> {

    public MatchEventDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<MatchEvent> getEntityClass() {
        return MatchEvent.class;
    }
}
