package maktabsharif.data.repository;

import maktabsharif.data.domain.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
@Repository
public class CarRepositoryImp implements CarRepository {
    private SessionFactory sessionFactory ;

    public CarRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void saveCar(Car car){
        // open session
        Session session = sessionFactory.openSession();
        // begin a criteria
        session.beginTransaction();
        //use the session to save the car
        session.save(car);
        // create list of car
        session.getTransaction().commit();
        // close the session
        session.close();
        System.out.println("Car registration successfully finish");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

    }
    @Override
    public int  updateCarLocation(Car car){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(
                "update maktabsharif.model.Car as c " +
                        "set c.location_lat = :c_location_lat, c.location_lon = :c_location_lon" )
                .setParameter("c_location_lat", car.getLocation_lat())
                .setParameter("c_location_lon", car.getLocation_lon());
        int countUpdate = query.executeUpdate();
        transaction.commit();
        session.close();
        return countUpdate;
    }
    @Override
    public  Car findCarByDriverUsername(String id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query  = session.createQuery("from maktabsharif.model.Car as c  where c.driver_userName= :c_driver_username")
                .setParameter("c_driver_username",id);
        Car car = (Car) query.getSingleResult();
        transaction.commit();
        session.close();
        return car;
    }
}
