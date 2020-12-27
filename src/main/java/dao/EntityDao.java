package dao;

import javax.persistence.EntityManager;

public abstract class EntityDao<T, U> {
    private EntityManager entityManager;

    public EntityDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }
}
