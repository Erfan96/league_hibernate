package dao;

import entities.City;
import javax.persistence.EntityManager;

public class CityDao extends EntityDao<City, Integer>{

    public CityDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<City> getEntityClass() {
        return City.class;
    }
}
