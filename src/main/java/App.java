import dao.*;
import entities.*;
import util.JpaUtil;
import javax.persistence.EntityManager;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class App {

    private static UserDao userDao;
    private static CityDao cityDao;
    private static StadiumDao stadiumDao;
    private static ContractDao contractDao;
    private static MatchDao matchDao;
    private static ResultDao resultDao;
    private static MatchEventDao matchEventDao;
    private static TeamDao teamDao;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        initializeDao(entityManager);
        entityManager.getTransaction().begin();

        City city = new City();
        city.setName("Tehran");
        cityDao.save(city);

        City city2 = new City();
        city2.setName("Shiraz");
        cityDao.save(city2);

        Stadium stadium = new Stadium();
        stadium.setCity(city);
        stadium.setCapacity(72000);
        stadium.setName("Azadi");
        stadiumDao.save(stadium);

        Stadium stadium2 = new Stadium();
        stadium2.setName("Fajr");
        stadium2.setCapacity(10000);
        stadium2.setCity(city2);
        stadiumDao.save(stadium2);

        Team team = new Team();
        team.setName("Persepolis");
        team.setCity(city);
        team.setStadium(stadium);
        teamDao.save(team);

        Team team2 = new Team();
        team2.setName("Bargh");
        team2.setCity(city2);
        team2.setStadium(stadium2);
        teamDao.save(team2);

        User user = new User();
        user.setFName("Ali");
        user.setLName("Karimi");
        user.setType("Player");
        user.setPosition("Forward");
        user.setTeam(team);
        user.setEnabled(true);
        userDao.save(user);

        User user2 = new User();
        user2.setFName("Fazel");
        user2.setLName("Larijani");
        user2.setType("Player");
        user2.setPosition("Forward");
        user2.setTeam(team2);
        user2.setEnabled(true);
        userDao.save(user2);

        Contract contract = new Contract();
        contract.setUser(user);
        contract.setSeason(2015);
        contract.setSalary(250.5);
        contract.setTeam(team);
        contract.setYears(5);
        contract.setDate(parseDate("2015-11-15"));
        contract.setEnabled(true);
        contractDao.save(contract);

        Contract contract2 = new Contract();
        contract2.setUser(user2);
        contract2.setSeason(2018);
        contract2.setSalary(350.5);
        contract2.setTeam(team2);
        contract2.setYears(3);
        contract2.setDate(parseDate("2018-07-04"));
        contract2.setEnabled(true);
        contractDao.save(contract2);

        Match match = new Match();
        match.setSeason(2019);
        match.setStadium(stadium);
        match.setHomeTeam(team);
        match.setAwayTeam(team2);
        match.setDateTime(parseTimestamp("2019-02-11 20:15:00"));
        matchDao.save(match);

        Result result = new Result();
        result.setTeamId(team);
        result.setMatch(match);
        result.setGoal(2);
        result.setScore(3);
        result.setPlace("Home");
        resultDao.save(result);

        Result result2 = new Result();
        result2.setTeamId(team2);
        result2.setMatch(match);
        result2.setGoal(1);
        result2.setScore(0);
        result2.setPlace("away");
        resultDao.save(result2);

        MatchEvent matchEvent = new MatchEvent();
        matchEvent.setMatch(match);
        matchEvent.setType("Goal");
        matchEvent.setDateTime(parseTimestamp("2019-02-11 20:30:00"));
        matchEvent.setUser(user);
        matchEventDao.save(matchEvent);

        MatchEvent matchEvent2 = new MatchEvent();
        matchEvent2.setMatch(match);
        matchEvent2.setType("Goal");
        matchEvent2.setDateTime(parseTimestamp("2019-02-11 20:52:26"));
        matchEvent2.setUser(user2);
        matchEventDao.save(matchEvent2);

        MatchEvent matchEvent3 = new MatchEvent();
        matchEvent3.setMatch(match);
        matchEvent3.setType("Goal");
        matchEvent3.setDateTime(parseTimestamp("2019-02-11 21:33:45"));
        matchEvent3.setUser(user);
        matchEventDao.save(matchEvent3);

        entityManager.getTransaction().commit();
        entityManager.close();
        JpaUtil.shutdown();
    }

    private static void initializeDao(EntityManager entityManager){
       userDao = new UserDao(entityManager);
       cityDao = new CityDao(entityManager);
       stadiumDao = new StadiumDao(entityManager);
       contractDao = new ContractDao(entityManager);
       matchDao = new MatchDao(entityManager);
       resultDao = new ResultDao(entityManager);
       matchEventDao = new MatchEventDao(entityManager);
       teamDao = new TeamDao(entityManager);
    }

    private static Date parseDate(String date){
        try {
            return new Date(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    private static Timestamp parseTimestamp(String timestamp){
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
