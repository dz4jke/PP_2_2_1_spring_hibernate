package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   private static final String HQL_FIND_USER_BY_CAR =
           "SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series";

   @Override
   public void saveUser(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> findAllUsers() {
      return sessionFactory.getCurrentSession()
              .createQuery("FROM User", User.class)
              .list();
   }

   @Override
   public User findUserByCarModelAndSeries(String model, int series) {
      Query<User> query = sessionFactory.getCurrentSession()
              .createQuery(HQL_FIND_USER_BY_CAR, User.class)
              .setParameter("model", model)
              .setParameter("series", series);
      return query.uniqueResult();
   }
}
