package com.example.subscriptions.repository.subscriber;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subscriptions.entities.subscriber.Subscriber;




public interface SubscriberRepository extends JpaRepository<Subscriber,Integer>{
    List<Subscriber> findAllByMobileNumber(String mobile,Pageable pageable);
    List<Subscriber> findAllByEmail(String email,Pageable pageable);
    List<Subscriber> findByNameLike(String name,Pageable pageable);

    /* ------------------------------ address quota ----------------------------- */
    List<Subscriber> findByAddressHouseNo(String housno,Pageable pageable);
    List<Subscriber> findByAddressStreetname(String streetname,Pageable pageable);
    
    List<Subscriber> findByAddressCity(String city,Pageable pageable);
    List<Subscriber> findByAddressZipcode(String zipcode,Pageable pageable);
    List<Subscriber> findByAddressCountry(String country,Pageable pageable);

    /* ----------------------------- combined search ---------------------------- */
    List<Subscriber> findByAddressCountryAndPrimarySpokenLanguage(String countryname,String languageSpoken,Pageable pageable);
    List<Subscriber> findByAddressCountryAndNameContaining(String countryname,String nameOfSubscriber,Pageable Pageable);
}
