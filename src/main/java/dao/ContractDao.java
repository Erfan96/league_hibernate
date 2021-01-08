package dao;

import entities.Contract;
import javax.persistence.EntityManager;


public class ContractDao extends EntityDao<Contract, Integer> {

    public ContractDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Contract> getEntityClass() {
        return Contract.class;
    }

}