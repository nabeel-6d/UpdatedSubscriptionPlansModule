package com.example.subscriptions.service.subscriber;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.subscriptions.LoggerImpl.MyLogger;
import com.example.subscriptions.entities.subscriber.Subscriber;
import com.example.subscriptions.entities.subscriber.plan.Plan;
import com.example.subscriptions.exceptionhandler.ExceptionHandler;
import com.example.subscriptions.repository.plan.PlanRepository;
import com.example.subscriptions.repository.subscriber.SubscriberRepository;


@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberrepo;
  
    @Autowired
    private PlanRepository planRepository;

    
    public SubscriberService() {
    }

    public SubscriberService(SubscriberRepository subscriberrepo, PlanRepository planRepository) {
        this.subscriberrepo = subscriberrepo;
        this.planRepository = planRepository;
    }

    public void addSubscriber(Subscriber subscr){
            subscr = ( Subscriber ) ExceptionHandler.handleEvent(subscr);
            subscriberrepo.save(subscr);
            MyLogger.trace("in subscriber service - in add subcr catch");
    }

    public void removeSubscriber(int subscr_id){
      
        subscriberrepo.deleteById(subscr_id);
        MyLogger.trace("in subscriber service - in remove subcr catch");
    }

    public void updateSubscriber(Subscriber subscriber,int id){
       
            ExceptionHandler.handleEvent(subscriber);
            Subscriber matchedSubscriber=subscriberrepo.findById(id).get();
            matchedSubscriber.setName(subscriber.getName());
            matchedSubscriber.setMobileNumber(subscriber.getMobileNumber());
            matchedSubscriber.setAddress(subscriber.getAddress());
            matchedSubscriber.setEmail(subscriber.getEmail());
            matchedSubscriber.setPrimarySpokenLanguage(subscriber.getPrimarySpokenLanguage());
            matchedSubscriber.setPlan_ids(matchedSubscriber.getPlan_ids());
            subscriberrepo.save(matchedSubscriber);
          MyLogger.trace("in subscriber service - in update subscr catch");
    }

    public List<Subscriber> retriveAllSubscribers(int noOfEntries){
        Pageable allPagedData=PageRequest.of(0, noOfEntries);
        return subscriberrepo.findAll(allPagedData).getContent();

    }

    public Subscriber getSubscriber(int id){
        return  subscriberrepo.findById(id).get();
    }

    public void addExistingPlanForASubscriber(int subscr_id,int plan_id){
           
        Subscriber matchedSubscriber = subscriberrepo.findById(subscr_id).get();
        ExceptionHandler.handleEvent(matchedSubscriber);  // -> exception handler
        Plan matchedPlan = planRepository.findById(plan_id).get();
        ExceptionHandler.handleEvent(matchedPlan);      // -> exception handler
        matchedSubscriber.setPlan(matchedPlan);
        subscriberrepo.save(matchedSubscriber);
    
       MyLogger.trace("in subscriber service - in addExistingPlan catch");       

    }

    public boolean removeExistingPlanForASubscriber(int subscr_id,int plan_id){
        Subscriber matchedSubscriber = subscriberrepo.findById(subscr_id).get();
        Plan planToBeDeleted = null;
        for(Plan plan: matchedSubscriber.getPlan_ids()){
            if(plan.getPlan_id() == plan_id){
                planToBeDeleted = plan;
            }
        }
        if(planToBeDeleted != null){
                MyLogger.trace("plan to remove is ------------------------->>>>>"+planToBeDeleted);
                matchedSubscriber.removePlan(planToBeDeleted);
                planRepository.deleteById(plan_id);
            }
           
        return false;
    }
    
    public Collection<Subscriber> allSubscribersByName(String name,int noOfEntries){
        Pageable pagedByName=PageRequest.of(0, noOfEntries);
        return subscriberrepo.findByNameLike('%'+name+'%', pagedByName);
    } 
    public Collection<Subscriber> allSubscribersByEmail(String email,int noOfEntries){
        Pageable pagedByEmail=PageRequest.of(0, noOfEntries);
        return subscriberrepo.findAllByEmail(email,pagedByEmail);
    }

    public Collection<Subscriber> allSubscribersByMobileNumber(String mob,int noOfEntries){
        Pageable pagedByMobileNumber=PageRequest.of(0, noOfEntries);
        return subscriberrepo.findAllByMobileNumber(mob,pagedByMobileNumber);
    }

    public Collection<Subscriber> allSubscribersByAddressHouseNo(String houseno,int noOfEntries){
        Pageable pagedByHouseNo=PageRequest.of(0, noOfEntries);
    return subscriberrepo.findByAddressHouseNo(houseno, pagedByHouseNo);
    }
    
    public Collection<Subscriber> allSubscribersByAddressStreetName(String streetname,int noOfEntries){
        Pageable pagedByAddress=PageRequest.of(0, noOfEntries);
        return subscriberrepo.findByAddressStreetname(streetname, pagedByAddress);
    }

    public Collection<Subscriber> allSubscribersByAddressCity(String city,int noOfEntries){
        Pageable pagedByCity=PageRequest.of(0, noOfEntries);
        return subscriberrepo.findByAddressCity(city, pagedByCity);
    }
    
    public Collection<Subscriber> allSubscribersByAddressZipCode(String zipcode,int noOfEntries){
        Pageable pagedByZipCode=PageRequest.of(0, noOfEntries);
        return  subscriberrepo.findByAddressZipcode(zipcode, pagedByZipCode);
    }

    public Collection<Subscriber> allSubscribersByAddressCountry(String country,int noOfEntries){
        Pageable pagedByCountry=PageRequest.of(0, noOfEntries);
        return subscriberrepo.findByAddressCountry(country, pagedByCountry);
    }

    public Collection<Subscriber> allSubscribersByAddressCountryAndLanguage(String country,String spokenLanguage,int noOfEntries){
        Pageable pagedByCountryAndLanguage=PageRequest.of(0, noOfEntries);
        return subscriberrepo.findByAddressCountryAndPrimarySpokenLanguage(country, spokenLanguage, pagedByCountryAndLanguage);
    }

    public Collection<Subscriber> allSubscribersByAddressCountryAndSubscriberName(String countryname,String name,int noOfEntries){
        Pageable pagedByCountryAndName=PageRequest.of(0, noOfEntries);
        return subscriberrepo.findByAddressCountryAndNameContaining(countryname,name, pagedByCountryAndName);
    }
}














/*  java implementation rather to findBy(Reference)(variable with-in referred object)

    Collection<Subscriber> matchedSubscribersLot=new ArrayList<Subscriber>();
        subscriberrepo.findAll(pagedByCountry).forEach(subscriber  -> {
            if(subscriber.getAddress().getCountry().contains(country))
                matchedSubscribersLot.add(subscriber);
        });
     */