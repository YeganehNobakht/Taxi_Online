package maktabsharif.configuration;

import maktabsharif.data.domain.Car;
import maktabsharif.data.domain.Driver;
import maktabsharif.data.domain.Passenger;
import maktabsharif.data.domain.Travel;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;
import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = {"maktabsharif"})
public class Config {
    @Bean("sessionFactory")
    public SessionFactory getSessionFactory(){
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.addAnnotatedClass(Travel.class);
            configuration.addAnnotatedClass(Car.class);
            configuration.addAnnotatedClass(Passenger.class);
            configuration.addAnnotatedClass(Driver.class);
            configuration.setProperties(new Properties(){
                {
                    put("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
                    put("hibernate.connection.url","jdbc:mysql://localhost:3306/taxi_request_hibernate");
                    put("hibernate.connection.username","root");
                    put("hibernate.connection.password","root");
                    put("hibernate.hbm2ddl.auto","update");
                    put("hibernate.show_sql","true");
                }

            });

            return configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Bean("scanner")
    public Scanner scanner(){
        return new Scanner(System.in);
    }

}

