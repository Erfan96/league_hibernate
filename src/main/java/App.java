import entities.User;
import util.JpaUtil;


import javax.persistence.EntityManager;

public class App {
    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        User user = new User();
        user.setFName("Ali");
        user.setLName("Karimi");
        user.setType("Player");
        user.setTeamId(111);
        user.setEnabled(true);

        entityManager.persist(user);

        entityManager.getTransaction().commit();
        entityManager.close();
        JpaUtil.shutdown();
    }
}
