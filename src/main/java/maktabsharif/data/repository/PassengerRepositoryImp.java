package maktabsharif.data.repository;

import maktabsharif.data.domain.Passenger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
@Repository
public class PassengerRepositoryImp implements PassengerRepository {
    private  SessionFactory sessionFactory ;

    public PassengerRepositoryImp(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void savePassenger(Passenger passenger) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(passenger);
        session.getTransaction().commit();
        session.close();
        System.out.println("Passenger registration successfully finish");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }
    @Override
    public Passenger findPassengerById(String id){
        Session session = sessionFactory.openSession();
        Passenger passenger = session.get(Passenger.class,id);
        session.close();
        return passenger;
    }
    @Override
    public int  updatePassenger(Passenger passenger){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(
                "update maktabsharif.data.domain.Passenger as c " +
                        "set c.name = :c_name, c.lastName = :c_lastName," +
                        "c.email=:c_email, c.nationalId = :c_nationalId, c.passengerTripState = :c_passengerTripState where c.userName = :c_userName")
                .setParameter("c_name", passenger.getName())
                .setParameter("c_lastName", passenger.getLastName())
                .setParameter("c_email", passenger.getEmail())
                .setParameter("c_nationalId", passenger.getNationalId())
                .setParameter("c_userName", passenger.getUserName())
                .setParameter("c_passengerTripState", passenger.getPassengerTripState());
        int countUpdate = query.executeUpdate();
        transaction.commit();
        session.close();
        return countUpdate;
    }
}
