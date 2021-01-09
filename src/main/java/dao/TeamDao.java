package dao;

import entities.Team;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TeamDao extends EntityDao<Team, Integer> {

    public TeamDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Team> getEntityClass() {
        return Team.class;
    }


    public void countTeamForEachCity() {
        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<Tuple> criteria = cb.createTupleQuery();
        Root<Team> fromTeam = criteria.from(Team.class);

        criteria.multiselect(fromTeam.get("city").get("name"), cb.count(fromTeam.get("id")));
        criteria.groupBy(fromTeam.get("city").get("id"));

        TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteria);
        List<Tuple> list = typedQuery.getResultList();

        list.forEach( r ->
                System.out.println(r.get(0) + " --> " + r.get(1)));
    }
}
