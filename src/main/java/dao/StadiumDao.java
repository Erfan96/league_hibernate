package dao;

import entities.Stadium;

import javax.persistence.EntityManager;

public class StadiumDao extends EntityDao<Stadium, Integer>{

    public StadiumDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Stadium> getEntityClass() {
        return null;
    }
}
