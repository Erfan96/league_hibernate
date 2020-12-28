package dao;

import entities.Matches;
import javax.persistence.EntityManager;

public class MatchDao extends EntityDao<Matches, Integer>{
    public MatchDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Matches> getEntityClass() {
        return Matches.class;
    }
}
