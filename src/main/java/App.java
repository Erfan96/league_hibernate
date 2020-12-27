import entities.*;
import util.JpaUtil;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class App {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        City city = new City();
        city.setName("Tehran");
        entityManager.persist(city);

        City city2 = new City();
        city2.setName("Shiraz");
        entityManager.persist(city2);

        Stadium stadium = new Stadium();
        stadium.setCity(city);
        stadium.setCapacity(72000);
        stadium.setName("Azadi");
        entityManager.persist(stadium);

        Stadium stadium2 = new Stadium();
        stadium2.setName("Fajr");
        stadium2.setCapacity(10000);
        stadium2.setCity(city2);
        entityManager.persist(stadium2);

        Team team = new Team();
        team.setName("Persepolis");
        team.setCity(city);
        team.setStadium(stadium);
        entityManager.persist(team);

        Team team2 = new Team();
        team2.setName("Bargh");
        team2.setCity(city2);
        team2.setStadium(stadium2);
        entityManager.persist(team2);

        User user = new User();
        user.setFName("Ali");
        user.setLName("Karimi");
        user.setType("Player");
        user.setPosition("Forward");
        user.setTeam(team);
        user.setEnabled(true);
        entityManager.persist(user);

        User user2 = new User();
        user2.setFName("Fazel");
        user2.setLName("Larijani");
        user2.setType("Player");
        user2.setPosition("Forward");
        user2.setTeam(team2);
        user2.setEnabled(true);
        entityManager.persist(user2);

        Contract contract = new Contract();
        contract.setUser(user);
        contract.setSeason(2015);
        contract.setSalary(250.5);
        contract.setTeam(team);
        contract.setYears(5);
        contract.setDate(parseDate("2015-11-15"));
        contract.setEnabled(true);
        entityManager.persist(contract);

        Contract contract2 = new Contract();
        contract2.setUser(user2);
        contract2.setSeason(2018);
        contract2.setSalary(350.5);
        contract2.setTeam(team2);
        contract2.setYears(3);
        contract2.setDate(parseDate("2018-07-04"));
        contract2.setEnabled(true);
        entityManager.persist(contract2);

        Match match = new Match();
        match.setSeason(2019);
        match.setStadium(stadium);
        match.setHomeTeam(team);
        match.setAwayTeam(team2);
        entityManager.persist(match);

        Result result = new Result();
        result.setTeamId(team);
        result.setMatch(match);
        result.setGoal(2);
        result.setScore(3);
        result.setPlace("Home");
        entityManager.persist(result);

        Result result2 = new Result();
        result2.setTeamId(team2);
        result2.setMatch(match);
        result2.setGoal(1);
        result2.setScore(0);
        result2.setPlace("away");
        entityManager.persist(result2);

        MatchEvent matchEvent = new MatchEvent();
        matchEvent.setMatch(match);
        matchEvent.setType("2 Goals");
        matchEvent.setUser(user);
        entityManager.persist(matchEvent);

        MatchEvent matchEvent2 = new MatchEvent();
        matchEvent2.setMatch(match);
        matchEvent2.setType("1 Goal");
        matchEvent2.setUser(user2);
        entityManager.persist(matchEvent2);

        entityManager.getTransaction().commit();
        entityManager.close();
        JpaUtil.shutdown();
    }

    private static Date parseDate(String date){
        try {
            return new Date(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }
}
