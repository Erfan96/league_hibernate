package dao;

import entities.Matches;
import entities.Result;
import entities.Team;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class ResultDao extends EntityDao<Result, Integer> {
    public ResultDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Result> getEntityClass() {
        return Result.class;
    }


    public void sumScoreForEachTeam() {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
        Root<Result> fromResult = criteria.from(Result.class);

        Join<Result, Team> join = fromResult.join("teamId");
        Join<Result, Matches> join2 = fromResult.join("matches");

        criteria.multiselect(join.get("name"), cb.sum(fromResult.get("score")))
        .where(cb.equal(join2.get("season"), "2019"));
        criteria.groupBy(fromResult.get("teamId"));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteria);
        List<Tuple> list = typedQuery.getResultList();

        list.forEach(r ->
                System.out.println(r.get(0) + " --> " + r.get(1)));
    }

    public void getChampion(){
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
        Root<Result> fromResult = criteria.from(Result.class);

        Join<Team, Result> teamResultJoin = fromResult.join("teamId");
        Join<Result, Matches> join2 = fromResult.join("matches");

        criteria.multiselect(teamResultJoin.get("name"), cb.sum(fromResult.get("score")))
                .where(cb.equal(join2.get("season"), "2019"));
        criteria.groupBy(teamResultJoin.get("id"));
        criteria.orderBy(cb.desc(cb.sum(fromResult.get("score"))));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteria).setMaxResults(1);
        Tuple list = typedQuery.getSingleResult();

        System.out.println(list);
    }
}
