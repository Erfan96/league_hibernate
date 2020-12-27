package dao;

import entities.Result;
import javax.persistence.EntityManager;

public class ResultDao extends EntityDao<Result, Integer> {
    public ResultDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Result> getEntityClass() {
        return Result.class;
    }
}
