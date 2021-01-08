package dao;

import entities.Contract;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class UserDao extends EntityDao<User, Integer> {

    public UserDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<User> getEntityClass() {
        return User.class;
    }

    public void getCoachHasMaxSalary() {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
        Root<User> fromUser = criteria.from(User.class);

        Subquery<Double> sq = criteria.subquery(Double.class);
        Root<Contract> subRoot = sq.from(Contract.class);
        Join<Contract, User> useConJoin = fromUser.join("contract");
        Join<User, Contract> sqJoin = subRoot.join("user");
        sq.select(cb.max(subRoot.get("salary")))
                .where(cb.equal(sqJoin.get("type"), "Coach"));


        criteria.multiselect(fromUser.get("fName"), fromUser.get("lName"), useConJoin.get("salary"))
                .where(useConJoin.get("salary").in(sq));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteria);
        List<Tuple> list = typedQuery.getResultList();

        list.forEach( r ->
                System.out.println(r.get(0)+ " --> " + r.get(1)+ "--> " + r.get(2)));
    }


}
