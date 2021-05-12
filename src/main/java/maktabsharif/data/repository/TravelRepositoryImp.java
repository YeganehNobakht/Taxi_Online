package maktabsharif.data.repository;

import maktabsharif.data.domain.Travel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
@Repository
public class TravelRepositoryImp implements TravelRepository {
    private SessionFactory sessionFactory;

    public TravelRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void saveTravel(Travel travel){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(travel);
        session.getTransaction().commit();
        session.close();

    }
    @Override
    public Travel findTravelByDriverUsername(String id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query  = session.createQuery("from maktabsharif.model.Travel as c  where c.Driver_userName= :c_driver_username")
                .setParameter("c_driver_username",id);
        List<Travel> travels =  query.getResultList();
        transaction.commit();
        session.close();
        if (travels.size()>0)
            return travels.get(0);
        else
            return null;
    }
    @Override
    public  int  updateTravel(Travel travel){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(
                "update maktabsharif.model.Travel as c " +
                        "set c.Driver_userName = :c_Driver_userName, c.Passenger_userName = :c_Passenger_userName" )
                .setParameter("c_Driver_userName", travel.getDriver_userName())
                .setParameter("c_Passenger_userName", travel.getPassenger_userName());
        int countUpdate = query.executeUpdate();
        transaction.commit();
        session.close();
        return countUpdate;
    }
}
