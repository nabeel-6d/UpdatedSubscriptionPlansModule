package com.example.subscriptions.controllers.subscriber;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.subscriptions.LoggerImpl.MyLogger;
import com.example.subscriptions.entities.subscriber.Subscriber;
import com.example.subscriptions.service.subscriber.SubscriberService;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {

    @Autowired
    private SubscriberService subService;

    @PostMapping("/add")
    public void add(@RequestBody Subscriber s){
        subService.addSubscriber(s);
        MyLogger.trace("in subscr contrller add");
    }

    @DeleteMapping("/delete/{subscr_id}")
    public void delete(@PathVariable int subscr_id){
        subService.removeSubscriber(subscr_id);
        MyLogger.trace("in subscr contrller delete");
    }

    @PutMapping("/update/{id}")
    public void update(@RequestBody Subscriber s,@PathVariable int id){
        subService.updateSubscriber(s,id);
        MyLogger.trace("in subscr contrller update");
    }

    @GetMapping("/getall/no-of-entries/{noOfEntries}")
    public Collection<Subscriber> getALLSubscribers(@PathVariable int noOfEntries){
        return subService.retriveAllSubscribers(noOfEntries); 
    }

    @GetMapping("/getall/name/{name}/no-of-entries/{noOfEntries}")
    public Collection<Subscriber> getSubscriberByName(@PathVariable String name,@PathVariable int noOfEntries){
        return subService.allSubscribersByName(name,noOfEntries);
    }
    /* -------------------------- code involving plans -------------------------- */

    @PostMapping("/{subscr_id}/addplan/{plan_id}")
    public void addPlanForASubscriber(@PathVariable int subscr_id,@PathVariable int plan_id){
        subService.addExistingPlanForASubscriber(subscr_id, plan_id);
    }

    @DeleteMapping("/{subscr_id}/deleteplan/{plan_id}")
    public void removePlanForSubscriber(@PathVariable int subscr_id,@PathVariable int plan_id){
        subService.removeExistingPlanForASubscriber(subscr_id, plan_id);
    }
    
  /* -------------------------- code involving search criteria by address type -------------------------- */

    @GetMapping("/getall/bymob/{mob}/no-of-entries/{noOfEntries}")
    public Collection<Subscriber> getSubscribersByMobileNumber(@PathVariable String mob,@PathVariable int noOfEntries){
        return subService.allSubscribersByMobileNumber(mob,noOfEntries);
    }

    @GetMapping("/getall/byemail/{email}/no-of-entries/{noOfEntries}")
    public Collection<Subscriber> getSubscribersByEmail(@PathVariable String email,@PathVariable int noOfEntries){
        return subService.allSubscribersByEmail(email,noOfEntries);
    }

    @GetMapping("/getall/address/houseno/{housno}/no-of-entries/{noOfEntries}")
    public Collection<Subscriber> getSubscribersByAddressHouseNo(@PathVariable String housno,@PathVariable int noOfEntries){
        return subService.allSubscribersByAddressHouseNo(housno,noOfEntries);
    }

    
    @GetMapping("/getall/address/streetname/{streetname}/no-of-entries/{noOfEntries}")
    public Collection<Subscriber> getSubscribersByAddressSteetName(@PathVariable String streetname,@PathVariable int noOfEntries){
        return subService.allSubscribersByAddressStreetName(streetname,noOfEntries);
    }

    
    @GetMapping("/getall/address/city/{city}/no-of-entries/{noOfEntries}")
    public Collection<Subscriber> getSubscribersByAddressCity(@PathVariable String city,@PathVariable int noOfEntries){
        return subService.allSubscribersByAddressCity(city,noOfEntries);
    }

    
    @GetMapping("/getall/address/zipcode/{zipcode}/no-of-entries/{noOfEntries}")
    public Collection<Subscriber> getSubscribersByAddressZipcode(@PathVariable String zipcode,@PathVariable int noOfEntries){
        return subService.allSubscribersByAddressZipCode(zipcode,noOfEntries);
    }

    @GetMapping("/getall/address/country/{countryname}/no-of-entries/{noOfEntries}")
    public Collection<Subscriber> getSubscribersByAddressCountry(@PathVariable String countryname,@PathVariable int noOfEntries){
        return subService.allSubscribersByAddressCountry(countryname,noOfEntries);
    }

    @GetMapping("/getall/country/{countryname}/language/{language}/no-of-entries/{noOfEntries}")
    public Collection<Subscriber> getSubscriberByCountryAndLanguage(@PathVariable String countryname,@PathVariable String language,@PathVariable int noOfEntries){
        return subService.allSubscribersByAddressCountryAndLanguage(countryname, language,noOfEntries);
    }

    @GetMapping("/getall/country/{countryname}/name/{subscribername}/no-of-entries/{noOfEntries}")
    public Collection<Subscriber> getSubscriberByCountryAndName(@PathVariable String countryname,@PathVariable String subscribername,@PathVariable int noOfEntries){
        return subService.allSubscribersByAddressCountryAndSubscriberName(countryname, subscribername,noOfEntries);
    }
}
