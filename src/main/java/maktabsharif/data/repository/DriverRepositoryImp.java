package maktabsharif.data.repository;

import maktabsharif.data.domain.Car;
import maktabsharif.data.domain.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository
public class DriverRepositoryImp implements DriverRepository {
    private SessionFactory sessionFactory ;

    public DriverRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public  void saveDriver(Driver driver) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(driver);
        session.getTransaction().commit();
        session.close();
        System.out.println("Driver registration successfully finish");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

    }
    @Override
    public  Driver findDriverById(String id){
        Session session = sessionFactory.openSession();
        Driver driver = session.get(Driver.class,id);
        session.close();
        return driver;
    }
    @Override
    public  int  updateDriver(Driver driver){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(
                "update maktabsharif.model.Driver as c " +
                        "set c.name = :c_name, c.lastName = :c_lastName," +
                        "c.email=:c_email, c.nationalId = :c_nationalId , c.driverTripState = :c_driverTripState where c.userName = :c_userName")
                .setParameter("c_name", driver.getName())
                .setParameter("c_lastName", driver.getLastName())
                .setParameter("c_email", driver.getEmail())
                .setParameter("c_nationalId", driver.getNationalId())
                .setParameter("c_userName", driver.getUserName())
                .setParameter("c_driverTripState", driver.getDriverTripState());
        int countUpdate = query.executeUpdate();
        transaction.commit();
        session.close();
        return countUpdate;
    }
    @Override
    public String findDriverFromCarLocation(double lat,double lon){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<String> cr = cb.createQuery(String.class);
        Root<Car> root = cr.from(Car.class);
        cr.multiselect(root.get("driver_userName")).where(cb.and(cb.equal(root.get("location_lat"),lat),cb.equal(root.get("location_lon"),lon)));
        //send query to database
        Query query = session.createQuery(cr);
        String username = (String) query.getSingleResult();
        session.close();
        //TODO :: remove Statement -> add to service layer
        if (!username.isEmpty())
            return username;
        else
            return null;
    }
    @Override
    public Driver findDriverByIdAndTripState(String id,String tripState){
        Session session = sessionFactory.openSession();
        List<Driver> drivers = session.createQuery("from maktabsharif.model.Driver as c  where c.userName = :c_userName AND c.driverTripState = :c_driverTripState ")
                .setParameter("c_userName",id).setParameter("c_driverTripState",tripState).list();
        session.close();
        session.close();
        //TODO :: remove Statement -> add to service layer
        if (drivers.size()>0)
            return drivers.get(0);
        else
            return null;
    }

}
